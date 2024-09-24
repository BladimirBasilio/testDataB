package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteEmailContacto;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class AlmacenaDatosClienteDestinatarioEmailJDBCProcess extends DoFn<RegistraPedido, RegistraPedido>  {

	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteDestinatarioEmailJDBCProcess.class);
	private static final long serialVersionUID = 147123567L;
	private DataSource dataSource;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteEmailContacto;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private final String stepName = "AlmacenaDatosClienteDestinatarioEmail";

	public AlmacenaDatosClienteDestinatarioEmailJDBCProcess(String jdbcUrl,
															String jdbcUsuario,
															String jdbcPassword,
															String consultaInsertClienteEmailContacto,
															String instanceNameSM,
															String applicationName,
															Integer connectionPoolMaxSize,
															Integer connectionPoolMinIdle,
															Integer connectionPoolTimeout,
															Integer connectionPoolIdleTimeout,
															Integer connectionPoolMaxLifetime
	) {
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertClienteEmailContacto = consultaInsertClienteEmailContacto;
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
//				statement = conn.prepareStatement(this.consulInsertClienteEmailContacto);
//				LOG.info("Conexion creada para Cliente Destinatario Email");
//			} catch(SQLException sqlex) {
//				LOG.error("Error al crear conexion de base de datos para ClienteEmailContacto Destinatario:" + sqlex.getMessage(), sqlex);
//			}
//		}
	}

	@ProcessElement
	public void processElement(DoFn<RegistraPedido, RegistraPedido>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Datos Cliente Destinatario Email");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		RegistraPedido registraPedido = c.element();
		Pedido pedido = registraPedido.getPedido();
		RegistraPedido registraPedidoReturn = (RegistraPedido) registraPedido.clone();


		try {
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertClienteEmailContacto);
			LOG.info("Creando el statement." + statement.toString());

			ClienteEmailContacto element =  registraPedido.getClienteDestinatario() != null? registraPedido.getClienteDestinatario().getEmail_de_contacto() : null;
			Integer clienteDestinararioTmp = registraPedido.getClienteDestinatario() != null? registraPedido.getClienteDestinatario().getId_cliente() : null;

			if(element != null && Objects.nonNull(element.getId_cliente_emails_de_contacto())) {
				if (element.getOperacion_crud() == null || element.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if ( element.getOperacion_crud() != null && element.getOperacion_crud().equals("UPDATE")) {
					statement.setString(1, "UPDATE");
					statement.setInt(2, element.getId_cliente_emails_de_contacto());
				}

				if ( element.getOperacion_crud() != null && element.getOperacion_crud().equals("NONE") ) {
					statement.setString(1, "NONE");
					statement.setInt(2, 0);
				}

				statement.setInt(3, clienteDestinararioTmp);

				if (element.getId_sistema_origen() == null)
					statement.setNull(4, java.sql.Types.INTEGER);
				else
					statement.setInt(4, element.getId_sistema_origen());

				if (element.getEmail() == null)
					statement.setNull(5, java.sql.Types.VARCHAR);
				else
					statement.setString(5, element.getEmail());

				//statement.execute();

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if (rowsInserted) {
					resultSet = statement.getResultSet();

					if (resultSet.next()) {

						Integer id_cliente_email = resultSet.getInt(1);
						LOG.info("id_cliente_email {}",id_cliente_email );
						RegistraPedido copyRegistraPedido = (RegistraPedido) registraPedido.clone();
						Pedido copyPedido = (Pedido) pedido.clone();

						copyPedido.setId_cliente_emails_de_contacto(id_cliente_email);
						copyRegistraPedido.setPedido(copyPedido);

						registraPedidoReturn = copyRegistraPedido;
					}
				}
			}
		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Email Contacto en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente Email Contacto:" + e.getMessage(), e);
			throw new Exception(e);
		}finally {
			LOG.info("Entre al Finally de Datos Cliente Destinatario Email.");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Datos Cliente Destinatario Email:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Datos Cliente Destinatario Email:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Datos Cliente Destinatario Email:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Datos Cliente Destinatario Email:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Cliente Destinatario Email:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Datos Cliente Destinatario Email:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Datos Cliente Destinatario Email.");
			if(registraPedidoReturn!=null){
				c.output(registraPedidoReturn);
			}
		}
		LOG.info("Finalizando process en Datos Cliente Destinatario Email");
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