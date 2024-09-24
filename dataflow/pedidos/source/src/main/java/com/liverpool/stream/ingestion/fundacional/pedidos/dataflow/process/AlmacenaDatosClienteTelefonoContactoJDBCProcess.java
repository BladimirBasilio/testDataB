package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import java.sql.*;
import java.util.Properties;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteTelefonoContacto;

import javax.sql.DataSource;

public class AlmacenaDatosClienteTelefonoContactoJDBCProcess extends DoFn<ClienteTelefonoContacto, String> {

	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteTelefonoContactoJDBCProcess.class);
	private static final long serialVersionUID = 147123567L;
	private DataSource dataSource;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertClienteTelefonoContacto;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private final String stepName = "AlmacenaDatosClienteTelefonoContacto";


	public AlmacenaDatosClienteTelefonoContactoJDBCProcess(String jdbcUrl,
														   String jdbcUsuario,
														   String jdbcPassword,
														   String consulInsertClienteTelefonoContacto,
														   String instanceNameSM,
														   String applicationName,
														   Integer connectionPoolMaxSize,
														   Integer connectionPoolMinIdle,
														   Integer connectionPoolTimeout,
														   Integer connectionPoolIdleTimeout,
														   Integer connectionPoolMaxLifetime) {
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consulInsertClienteTelefonoContacto = consulInsertClienteTelefonoContacto;
		this.instanceNameSM = instanceNameSM;
		this.applicationName = applicationName;
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
//		if(conn == null) {
//			try {
//				Properties props = new Properties();
//				props.setProperty("user", this.jdbcUsuario);
//				props.setProperty("password", this.jdbcPassword);
//				conn = DriverManager.getConnection(this.jdbcUrl, props);
//				statement = conn.prepareStatement(this.consulInsertClienteTelefonoContacto);
//				LOG.info("Conexion creada para Cliente");
//			} catch(SQLException sqlex) {
//				LOG.error("Error al crear conexion de base de datos para ClienteTelefonoContacto:" + sqlex.getMessage(), sqlex);
//			}
//		}
	}


	@ProcessElement
	public void processElement(DoFn<ClienteTelefonoContacto, String>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Datos Cliente Telefono Contacto");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consulInsertClienteTelefonoContacto);
			LOG.info("Creando el statement." + statement.toString());

			ClienteTelefonoContacto element = c.element();
			if(element.getOperacion_crud() == null || element.getOperacion_crud().equals("INSERT")) {
				statement.setString(1, "INSERT");
				statement.setInt(2, 0);
			}

			if(!(element.getOperacion_crud() == null)) {
				if(element.getOperacion_crud().equals("UPDATE")) {
					statement.setString(1, "UPDATE");
					statement.setInt(2, element.getId_cliente_telefonos_de_contacto());
				}
			}

			if(element.getId_cliente() == null)
				statement.setNull(3, java.sql.Types.INTEGER);
			else
				statement.setInt(3, element.getId_cliente());

			if(element.getId_sistema_origen() == null)
				statement.setNull(4, java.sql.Types.INTEGER);
			else
				statement.setInt(4, element.getId_sistema_origen());

			if(element.getTelefono() == null)
				statement.setNull(5, java.sql.Types.VARCHAR);
			else
				statement.setString(5, element.getTelefono());

			if(element.getAlias_telefono() == null)
				statement.setNull(6, java.sql.Types.VARCHAR);
			else
				statement.setString(6, element.getAlias_telefono());

			statement.execute();
			LOG.info("Executando el statement");

		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Telefono Contacto en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente Telefono Contacto:" + e.getMessage(), e);
			throw new Exception(e);
		}finally {
			LOG.info("Entre al Finally de Datos Cliente Telefono Contacto.");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Datos Cliente Telefono Contacto");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Datos Cliente Telefono Contacto:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Datos Cliente Telefono Contacto");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Datos Cliente Telefono Contacto:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Cliente Telefono Contacto");
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Datos Cliente Telefono Contacto" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Datos Cliente Telefono Contacto.");
		}
		LOG.info("Finalizando process en Datos Cliente Telefono Contacto");
	}

//	@Teardown
//	public void tearDown() {
//		if(conn != null) {
//			try {
//				statement.close();
//				conn.close();
//			} catch(SQLException sqlex) {
//				LOG.error("Exception in shutting database connection:" + sqlex.getMessage(), sqlex);
//			}
//		}
//	}

}


