package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import java.sql.*;
import java.util.Objects;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteEmailContacto;

import javax.sql.DataSource;

public class AlmacenaDatosClienteEmailContactoJDBCProcess  extends DoFn<ClienteEmailContacto, KV<String,String>> {

	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteEmailContactoJDBCProcess.class);
	private static final long serialVersionUID = 147123567L;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertClienteEmailContacto;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteEmailContacto";
	public AlmacenaDatosClienteEmailContactoJDBCProcess(String jdbcUrl,
														String jdbcUsuario,
														String jdbcPassword,
														String consulInsertClienteEmailContacto,
														String instanceNameSM,
														Integer connectionPoolMaxSize,
														Integer connectionPoolMinIdle,
														Integer connectionPoolTimeout,
														Integer connectionPoolIdleTimeout,
														Integer connectionPoolMaxLifetime)	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consulInsertClienteEmailContacto = consulInsertClienteEmailContacto;
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
	public void processElement(DoFn<ClienteEmailContacto, KV<String,String>>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Email Contacto");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try{
			ClienteEmailContacto clienteEmailContacto = c.element();

			if(!Objects.isNull(clienteEmailContacto) && !Objects.isNull(clienteEmailContacto.getEmail()) && clienteEmailContacto.getEmail().trim().length() > 0){
				conn = dataSource.getConnection();
				LOG.info("Creando la conexion:." +  conn.toString());

				statement = conn.prepareStatement(this.consulInsertClienteEmailContacto);
				LOG.info("Creando el statement." + statement.toString());

				if (Objects.isNull(clienteEmailContacto.getOperacion_crud()) ||
						clienteEmailContacto.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if (Objects.nonNull(clienteEmailContacto.getOperacion_crud())) {
					if (clienteEmailContacto.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, clienteEmailContacto.getId_cliente_emails_de_contacto());
					}

					if (clienteEmailContacto.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
						statement.setInt(2, clienteEmailContacto.getId_cliente_emails_de_contacto());
					}

				}

				if (Objects.isNull(clienteEmailContacto.getId_cliente()))
					statement.setNull(3, java.sql.Types.INTEGER);
				else
					statement.setInt(3, clienteEmailContacto.getId_cliente());

				if (Objects.isNull(clienteEmailContacto.getId_sistema_origen()))
					statement.setNull(4, java.sql.Types.INTEGER);
				else
					statement.setInt(4, clienteEmailContacto.getId_sistema_origen());

				if (Objects.isNull(clienteEmailContacto.getTipo()))
					statement.setNull(5, java.sql.Types.VARCHAR);
				else
					statement.setString(5, clienteEmailContacto.getTipo());

				if (Objects.isNull(clienteEmailContacto.getEmail()))
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, clienteEmailContacto.getEmail());

				if (Objects.isNull(clienteEmailContacto.getEsta_borrado()))
					statement.setNull(7, java.sql.Types.VARCHAR);
				else
					statement.setString(7, clienteEmailContacto.getEsta_borrado());

				if (Objects.isNull(clienteEmailContacto.getId_transaccion()))
					statement.setNull(8, java.sql.Types.VARCHAR);
				else
					statement.setString(8, clienteEmailContacto.getId_transaccion());

				if (Objects.isNull(clienteEmailContacto.getId_operacion()))
					statement.setNull(9, java.sql.Types.VARCHAR);
				else
					statement.setString(9, clienteEmailContacto.getId_operacion());

				if (Objects.isNull(clienteEmailContacto.getSalida_correo_electronico()))
					statement.setNull(10, java.sql.Types.VARCHAR);
				else
					statement.setString(10, clienteEmailContacto.getSalida_correo_electronico());

				if (Objects.isNull(clienteEmailContacto.getSalida_tipo()))
					statement.setNull(11, java.sql.Types.VARCHAR);
				else
					statement.setString(11, clienteEmailContacto.getSalida_tipo());

				if (Objects.isNull(clienteEmailContacto.getBandera_tipo()))
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, clienteEmailContacto.getBandera_tipo());

				if (Objects.isNull(clienteEmailContacto.getToken_correo_electronico()))
					statement.setNull(13, java.sql.Types.VARCHAR);
				else
					statement.setString(13, clienteEmailContacto.getToken_correo_electronico());

				if (Objects.isNull(clienteEmailContacto.getFecha_operacion()))
					statement.setNull(14, Types.TIMESTAMP);
				else
					statement.setTimestamp(14, java.sql.Timestamp.valueOf(clienteEmailContacto.getFecha_operacion()));

				if (Objects.isNull(clienteEmailContacto.getFecha_creacion()))
					statement.setNull(15, Types.TIMESTAMP);
				else
					statement.setTimestamp(15, java.sql.Timestamp.valueOf(clienteEmailContacto.getFecha_creacion()));

				if (Objects.isNull(clienteEmailContacto.getFecha_actualizacion()))
					statement.setNull(16, Types.TIMESTAMP);
				else
					statement.setTimestamp(16, java.sql.Timestamp.valueOf(clienteEmailContacto.getFecha_actualizacion()));

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int id_cliente_email_contacto = resultSet.getInt(1);
						LOG.info("id_cliente_email_contacto {}",id_cliente_email_contacto );

					}
				}

			}else{
				LOG.info("El cliente email de contacto no proporciono una direccion de email.");
				LOG.info("Datos del Cliente Email de Contacto: {}", clienteEmailContacto);
			}

		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Email Contacto en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente Email Contacto:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Email Contacto");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Email Contacto:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Email Contacto:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Email Contacto:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Email Contacto:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Email Contacto:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Email Contacto:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente Email Contacto");
			if(Objects.nonNull(c.element().getId_cliente())) {
				c.output(KV.of(c.element().getId_cliente().toString(), "AlmacenaDatosClienteEmailContacto"));
			}
		}
		LOG.info("Finalizando process en Almacena Datos Cliente Email Contacto");
	}

}


