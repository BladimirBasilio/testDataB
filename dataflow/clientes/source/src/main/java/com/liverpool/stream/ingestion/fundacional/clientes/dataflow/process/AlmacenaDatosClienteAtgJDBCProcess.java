package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;


import java.sql.*;
import java.util.Objects;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteAtg;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class AlmacenaDatosClienteAtgJDBCProcess  extends DoFn<ClienteAtg, KV<String,String>> {

	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteAtgJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteAtg;
	private String tipoClienteActualizadoATG = "tipoCliente=ATG";
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteAtg";

	public AlmacenaDatosClienteAtgJDBCProcess(String jdbcUrl,
											  String jdbcUsuario,
											  String jdbcPassword,
											  String consultaInsertClienteAtg,
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
		this.instanceNameSM = instanceNameSM;
		this.connectionPoolMaxSize = connectionPoolMaxSize;
		this.connectionPoolMinIdle = connectionPoolMinIdle;
		this.connectionPoolTimeout = connectionPoolTimeout;
		this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
		this.consultaInsertClienteAtg = consultaInsertClienteAtg;
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
	public void processElement(DoFn<ClienteAtg, KV<String,String>>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Atg");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try{

			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertClienteAtg);
			LOG.info("Creando el statement." + statement.toString());

			ClienteAtg clienteAtg = c.element();

			if(Objects.nonNull(clienteAtg)){

				if (Objects.isNull(clienteAtg.getOperacion_crud()) ||
						clienteAtg.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}

				if (Objects.nonNull(clienteAtg.getOperacion_crud())) {
					if (clienteAtg.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, clienteAtg.getId_cliente_atg());
					}

					if (clienteAtg.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
						statement.setInt(2, clienteAtg.getId_cliente_atg());
					}
				}

				if (Objects.isNull(clienteAtg.getId_atg()))
					statement.setNull(3, java.sql.Types.VARCHAR);
				else
					statement.setString(3, clienteAtg.getId_atg());

				if (Objects.isNull(clienteAtg.getSistema_fuente_atg()))
					statement.setNull(4, java.sql.Types.VARCHAR);
				else
					statement.setString(4, clienteAtg.getSistema_fuente_atg());

				if (Objects.isNull(clienteAtg.getId_cliente()))
					statement.setNull(5, java.sql.Types.INTEGER);
				else
					statement.setInt(5, clienteAtg.getId_cliente());

				if (Objects.isNull(clienteAtg.getHash_1()))
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, clienteAtg.getHash_1());

				if (Objects.isNull(clienteAtg.getId_tipo_tarjeta()))
					statement.setNull(7, java.sql.Types.VARCHAR);
				else
					statement.setString(7, clienteAtg.getId_tipo_tarjeta());

				if (Objects.isNull(clienteAtg.getRealm_id()))
					statement.setNull(8, java.sql.Types.VARCHAR);
				else
					statement.setString(8, clienteAtg.getRealm_id());

				if (Objects.isNull(clienteAtg.getBaja_de_tarjeta()))
					statement.setNull(9, java.sql.Types.VARCHAR);
				else
					statement.setString(9, clienteAtg.getBaja_de_tarjeta());

				if (Objects.isNull(clienteAtg.getId_facebook()))
					statement.setNull(10, java.sql.Types.VARCHAR);
				else
					statement.setString(10, clienteAtg.getId_facebook());

				if (Objects.isNull(clienteAtg.getId_sistema_operativo_dispositivo()))
					statement.setNull(11, java.sql.Types.INTEGER);
				else
					statement.setInt(11, clienteAtg.getId_sistema_operativo_dispositivo());

				if (Objects.isNull(clienteAtg.getId_apple()))
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, clienteAtg.getId_apple());

				if (Objects.isNull(clienteAtg.getAlta_de_tarjeta()))
					statement.setNull(13, java.sql.Types.VARCHAR);
				else
					statement.setString(13, clienteAtg.getAlta_de_tarjeta());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int id_cliente_atg = resultSet.getInt(1);
						LOG.info("id_cliente_atg {}",id_cliente_atg );

						//c.output(KV.of(c.element().getId_cliente().toString() + "," + c.element().getId_atg() + "," + c.element().getId_cliente_atg() + "," + c.element().getHash_1() + "," + c.element().getAlta_de_tarjeta(), tipoClienteActualizadoATG));

					}
				}

			}

		} catch(SQLException sqlex){
			LOG.error("Error al intentar query Cliente ATG en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente ATG:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Atg");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Atg:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Atg:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Atg:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Atg:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Atg:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Atg:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente Atg");
				if(Objects.nonNull(c.element().getId_cliente())){
					c.output(KV.of(c.element().getId_cliente().toString() + "," + c.element().getId_atg() + "," + c.element().getId_cliente_atg() + "," + c.element().getHash_1() + "," + c.element().getAlta_de_tarjeta(), tipoClienteActualizadoATG));
				}
			}
		LOG.info("Finalizando process en Almacena Datos Cliente Atg");
	}

}



