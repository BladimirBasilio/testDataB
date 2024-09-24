package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteId;

import javax.sql.DataSource;

public class AlmacenaDatosClienteIdJDBCProcess  extends DoFn<ClienteId, KV<String,String>> {

	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteId;
	private Integer SystemATG=58;
	private Integer SystemTE=59;
	private String tipoClienteInsertadoATG = "tipoCliente=ATG";
	private String tipoClienteInsertadoMDM = "tipoCliente=MDM";
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteId";


	public AlmacenaDatosClienteIdJDBCProcess(String jdbcUrl,
										   String jdbcUsuario,
										   String jdbcPassword,
										   String consultaInsertClienteId,
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
		this.consultaInsertClienteId = consultaInsertClienteId;
		this.connectionPoolMaxSize = connectionPoolMaxSize;
		this.connectionPoolMinIdle = connectionPoolMinIdle;
		this.connectionPoolTimeout = connectionPoolTimeout;
		this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
		this.instanceNameSM = instanceNameSM;
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
	public void processElement(DoFn<ClienteId, KV<String,String>>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Id");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ClienteId clienteId = c.element();

		try{
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertClienteId);

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime localDateTime = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime();

			if(Objects.isNull(clienteId.getFecha_de_creacion()))
				statement.setTimestamp(1, java.sql.Timestamp.valueOf(localDateTime.format(formato)));
			else
				statement.setTimestamp(1, clienteId.getFecha_de_creacion());

			if(Objects.isNull(clienteId.getId_origen()))
				statement.setNull(2, java.sql.Types.VARCHAR);
			else
				statement.setString(2, clienteId.getId_origen());

			if(Objects.isNull(clienteId.getId_cliente()))
				statement.setNull(3, java.sql.Types.INTEGER);
			else
				statement.setInt(3, clienteId.getId_cliente());

			if(Objects.isNull(clienteId.getId_sistema_origen()))
				statement.setNull(4, java.sql.Types.INTEGER);
			else
				statement.setInt(4, clienteId.getId_sistema_origen());

			if(Objects.isNull(clienteId.getFecha_de_actualizacion()))
				statement.setTimestamp(5, java.sql.Timestamp.valueOf(localDateTime.format(formato)));
			else
				statement.setTimestamp(5, clienteId.getFecha_de_actualizacion());

			boolean rowsInserted = statement.execute();
			LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

			if(rowsInserted){
				resultSet = statement.getResultSet();

				if(resultSet.next()) {

					int id_cliente_Id = resultSet.getInt(1);
					LOG.info("id_cliente_Id {}",id_cliente_Id );

				}
			}

		} catch(SQLException sqlex){
			LOG.error("Error al intentar query Cliente_IDS en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente_IDS:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Id");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Id:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Id:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Id:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Id:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Id:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Id:" + en.getMessage(), en);
			}

			LOG.info("id_cliente {}, sistema_origen {}", c.element().getId_cliente(), c.element().getId_sistema_origen());
			if(Objects.nonNull(clienteId.getId_sistema_origen())) {
				if (clienteId.getId_sistema_origen().equals(SystemATG)) {
					LOG.info("Entra ATG id_cliente {}", c.element().getId_cliente());
					c.output(KV.of(c.element().getId_cliente().toString(), tipoClienteInsertadoATG));
				} else if (!(clienteId.getId_sistema_origen().equals(SystemATG)) || !(clienteId.getId_sistema_origen().equals(SystemTE))) {
					LOG.info("Entra MDM id_cliente {}", c.element().getId_cliente());
					c.output(KV.of(c.element().getId_cliente().toString(), tipoClienteInsertadoMDM));
				}
				LOG.info("Sali al Finally de Almacena Datos Cliente Id");
			}

		}
		LOG.info("Finalizando process en Almacena Datos Cliente Id");
	}
}


