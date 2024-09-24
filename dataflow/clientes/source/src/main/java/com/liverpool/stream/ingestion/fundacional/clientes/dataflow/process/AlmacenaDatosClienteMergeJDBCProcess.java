package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;


import java.sql.*;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteMerge;

import javax.sql.DataSource;

public class AlmacenaDatosClienteMergeJDBCProcess extends DoFn<ClienteMerge, KV<String,String>> {
	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteJDBCProcess.class);
	private Connection conn;
	private PreparedStatement statement;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteMerge;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteMerge";

	public AlmacenaDatosClienteMergeJDBCProcess(String jdbcUrl,
											 String jdbcUsuario,
											 String jdbcPassword,
											 String consultaInsertClienteMerge,
												String instanceNameSM,
												Integer connectionPoolMaxSize,
												Integer connectionPoolMinIdle,
												Integer connectionPoolTimeout,
												Integer connectionPoolIdleTimeout,
												Integer connectionPoolMaxLifetime)
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertClienteMerge = consultaInsertClienteMerge;
		this.instanceNameSM = instanceNameSM;
		this.connectionPoolMaxSize = connectionPoolMaxSize;
		this.connectionPoolMinIdle = connectionPoolMinIdle;
		this.connectionPoolTimeout = connectionPoolTimeout;
		this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
	}

	@Setup
	public void init() {
		dataSource = ConnectionPoolProvider.getInstance(this.jdbcUrl,
				this.jdbcPassword,
				this.jdbcUsuario,
				this.instanceNameSM,
				this.applicationName,
				this.connectionPoolMaxSize,
				this.connectionPoolMinIdle,
				this.connectionPoolTimeout,
				this.connectionPoolIdleTimeout,
				this.connectionPoolMaxLifetime,
				this.stepName).getDataSource();
	}

	@ProcessElement
	public void processElement(DoFn<ClienteMerge, KV<String,String>>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Merge");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertClienteMerge);

				ClienteMerge element = c.element();

				if (Objects.isNull(element.getRowid()))
					statement.setNull(1, java.sql.Types.VARCHAR);
				else
					statement.setString(1, element.getRowid());

				if (Objects.isNull(element.getId_cliente()))
					statement.setNull(2, java.sql.Types.INTEGER);
				else
					statement.setInt(2, element.getId_cliente());

				if (Objects.isNull(element.getFuente()))
					statement.setNull(3, java.sql.Types.VARCHAR);
				else
					statement.setString(3, element.getFuente());

				if (Objects.isNull(element.getFecha_de_carga()))
					statement.setNull(4, java.sql.Types.DATE);
				else
					statement.setDate(4, Date.valueOf(element.getFecha_de_carga()));

			boolean rowsInserted = statement.execute();
			LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

			if(rowsInserted){
				resultSet = statement.getResultSet();

				if(resultSet.next()) {

					int id_cliente_merge = resultSet.getInt(1);
					LOG.info("id_cliente_destinatario {}", id_cliente_merge);

				}
			}
		} catch (SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Merge en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());

		} catch (Exception e) {
			LOG.error("Error al procesar Cliente Merge:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Merge");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Merge:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Merge:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Merge:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Merge:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Merge:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Merge:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente Merge");
			if(Objects.nonNull(c.element().getId_cliente())) {
				c.output(KV.of(c.element().getId_cliente().toString(), "AlmacenaDatosClienteMerge"));
			}
		}
		LOG.info("Finalizando process en Almacena Datos Cliente Merge");


	}

}


