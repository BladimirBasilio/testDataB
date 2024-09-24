package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;


import java.sql.*;
import java.util.Objects;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteDestinatario;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class AlmacenaDatosClienteDestinatarioJDBCProcess  extends DoFn<ClienteDestinatario, KV<String,String>> {


	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteDestinatarioJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteDestinatario;
	private String consultaDataClienteByIdPadreDestinatario;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteDestinatario";

	public AlmacenaDatosClienteDestinatarioJDBCProcess(String jdbcUrl,
													   String jdbcUsuario,
													   String jdbcPassword,
													   String consultaInsertClienteDestinatario,
													   String consultaDataClienteByIdPadreDestinatario,
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
		this.consultaInsertClienteDestinatario = consultaInsertClienteDestinatario;
		this.consultaDataClienteByIdPadreDestinatario = consultaDataClienteByIdPadreDestinatario;
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
	public void processElement(DoFn<ClienteDestinatario,  KV<String,String>>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Destinatario");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			ClienteDestinatario clienteDestinatario = c.element();

			LOG.info("Inicia llamado - getDataClientebyIdClientePadreDestinatario");
			Cliente clientePadreDestinatario = getDataClientebyIdClientePadreDestinatario(clienteDestinatario);

			if((!Objects.isNull(clienteDestinatario.getPrimer_nombre()) &&
					clienteDestinatario.getPrimer_nombre().trim().length() > 0 &&
					!clienteDestinatario.getPrimer_nombre().equals(clientePadreDestinatario.getPrimer_nombre())) &&
					(!Objects.isNull(clienteDestinatario.getApellido_paterno()) &&
							clienteDestinatario.getApellido_paterno().trim().length() > 0 &&
							!clienteDestinatario.getApellido_paterno().equals(clientePadreDestinatario.getApellido_paterno()))){

				conn = dataSource.getConnection();
				LOG.info("Creando la conexion:." +  conn.toString());

				statement = conn.prepareStatement(this.consultaInsertClienteDestinatario);
				LOG.info("Creando el statement. - " + statement.toString());

				if (Objects.isNull(clienteDestinatario.getOperacion_crud()) ||
						clienteDestinatario.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if (Objects.nonNull(clienteDestinatario.getOperacion_crud())) {
					if (clienteDestinatario.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, clienteDestinatario.getId_cliente());
					}
					if (clienteDestinatario.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
						statement.setInt(2, clienteDestinatario.getId_cliente());
					}
				}

				if (Objects.isNull(clienteDestinatario.getPrimer_nombre()))
					statement.setNull(3, java.sql.Types.VARCHAR);
				else
					statement.setString(3, clienteDestinatario.getPrimer_nombre());

				if (Objects.isNull(clienteDestinatario.getSegundo_nombre()))
					statement.setNull(4, java.sql.Types.VARCHAR);
				else
					statement.setString(4, clienteDestinatario.getSegundo_nombre());

				if (Objects.isNull(clienteDestinatario.getApellido_paterno()))
					statement.setNull(5, java.sql.Types.VARCHAR);
				else
					statement.setString(5, clienteDestinatario.getApellido_paterno());

				if (Objects.isNull(clienteDestinatario.getApellido_materno()))
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, clienteDestinatario.getApellido_materno());

				if (Objects.isNull(clienteDestinatario.getId_cliente_padre_del_destinatario()))
					statement.setNull(7, java.sql.Types.INTEGER);
				else
					statement.setInt(7, clienteDestinatario.getId_cliente_padre_del_destinatario());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int id_cliente_destinatario = resultSet.getInt(1);
						LOG.info("id_cliente_destinatario {}",id_cliente_destinatario );

						//c.output(KV.of(c.element().getId_cliente_padre_del_destinatario().toString(), "ClienteDestinatario"));

					}
				}
			}else{
				LOG.info("El cliente destinatario no tiene datos para crear el registro o tiene los mismos datos del cliente y no es necesario registrar.");
				LOG.info("Datos del Cliente Destinatario: {}", clienteDestinatario);
				LOG.info("Datos del Cliente Padre Destinatario: {}", clientePadreDestinatario);
			}

		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query ClienteDestinatario en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar ClienteDestinatario:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Destinatario");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Destinatario:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Destinatario:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Destinatario:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Destinatario:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Destinatario:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Destinatario:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente Destinatario");
			if(Objects.nonNull(c.element().getId_cliente_padre_del_destinatario())){
				c.output(KV.of(c.element().getId_cliente_padre_del_destinatario().toString(), "ClienteDestinatario"));
			}
		}
		LOG.info("Finalizando process en Almacena Datos Cliente Destinatario");
	}

	private Cliente getDataClientebyIdClientePadreDestinatario(ClienteDestinatario clienteDestinatario) throws Exception {

		Cliente datosClientePadreDestinatario = null;
		LOG.info("Iniciando process en Almacena Datos Cliente Destinatario");
		Connection conn = null;
		PreparedStatement statementDataClientePadreDestinatario = null;
		ResultSet resultSet = null;

		try {

			conn = dataSource.getConnection();
			LOG.info("Creando la conexion Data Cliente Padre Destinatario: - {}", conn.toString());

			statementDataClientePadreDestinatario = conn.prepareStatement(this.consultaDataClienteByIdPadreDestinatario);
			LOG.info("Creando el statement Data Cliente Padre Destinatario. - {}", statementDataClientePadreDestinatario.toString());

			LOG.info("--- Datos del Cliente Destinatario --- {}", clienteDestinatario.toString());

			if (!Objects.isNull(clienteDestinatario) &&
					!Objects.isNull(clienteDestinatario.getId_cliente_padre_del_destinatario()) &&
					clienteDestinatario.getId_cliente_padre_del_destinatario() > 0) {

				statementDataClientePadreDestinatario.setInt(1, clienteDestinatario.getId_cliente_padre_del_destinatario());

				resultSet = statementDataClientePadreDestinatario.executeQuery();

				if(resultSet.next()) {

					LOG.info("Datos Obtenidos de Consulta Datos Cliente Padre Destinatario - {}", resultSet.toString());

					datosClientePadreDestinatario = new Cliente();

					datosClientePadreDestinatario.setId_cliente(resultSet.getInt(1));
					datosClientePadreDestinatario.setPrimer_nombre(resultSet.getString(2));
					datosClientePadreDestinatario.setSegundo_nombre(resultSet.getString(3));
					datosClientePadreDestinatario.setApellido_paterno(resultSet.getString(4));
					datosClientePadreDestinatario.setApellido_materno(resultSet.getString(5));
				}

				LOG.info("Objeto Datos Cliente Padre Destinatario Por Id Cliente Padre Destinatario - {}", datosClientePadreDestinatario);

			}

		} catch (SQLException sqlex) {
			LOG.error("Error al intentar query ClientePadreDestinatario en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch (Exception e) {
			LOG.error("Error al procesar ClientePadreDestinatario:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos ClientePadreDestinatario");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en ClientePadreDestinatario:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en ClientePadreDestinatario:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statementDataClientePadreDestinatario)) {
					LOG.info("Cerrando el statement en ClientePadreDestinatario:");
					statementDataClientePadreDestinatario.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en ClientePadreDestinatario:" + es.getMessage(), es);
			}
			try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en ClientePadreDestinatario:");
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en ClientePadreDestinatario:" + en.getMessage(), en);
			}

		}

		return datosClientePadreDestinatario;

	}

}



