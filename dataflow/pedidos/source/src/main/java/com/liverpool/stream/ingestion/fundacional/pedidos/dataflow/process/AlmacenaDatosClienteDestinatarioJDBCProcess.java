package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteDestinatario;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteDireccion;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteEmailContacto;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteRemitente;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

import javax.sql.DataSource;

public class AlmacenaDatosClienteDestinatarioJDBCProcess extends DoFn<RegistraPedido, RegistraPedido>    {


	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteDestinatarioJDBCProcess.class);
	private Connection conn;
	private DataSource dataSource;
	private PreparedStatement statement;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertCliente;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private final String stepName = "AlmacenaDatosClienteDestinatario";



	public AlmacenaDatosClienteDestinatarioJDBCProcess(String jdbcUrl,
													   String jdbcUsuario,
													   String jdbcPassword,
													   String consultaInsertCliente,
													   String instanceNameSM,
													   String applicationName,
													   Integer connectionPoolMaxSize,
													   Integer connectionPoolMinIdle,
													   Integer connectionPoolTimeout,
													   Integer connectionPoolIdleTimeout,
													   Integer connectionPoolMaxLifetime)
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertCliente = consultaInsertCliente;
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
//		dataSource = ConnectionPoolProvider.getInstance(this.jdbcUrl,this.jdbcPassword,this.jdbcUsuario,this.instanceNameSM,"AlmacenaDatosClienteDestinatario").getDataSource();
//		if(conn == null) {
//			try {
//				Properties props = new Properties();
//				props.setProperty("user", this.jdbcUsuario);
//				props.setProperty("password", this.jdbcPassword);
//				conn = DriverManager.getConnection(this.jdbcUrl, props);
//				statement = conn.prepareStatement(this.consultaInsertCliente);
//				LOG.info("Conexion creada para Cliente Destinatario");
//			} catch(SQLException sqlex) {
//				LOG.error("Error al crear conexion de base de datos para Cliente Destinatario:" + sqlex.getMessage(), sqlex);
//			}
//		}
    }
	
	@ProcessElement
	public void processElement(DoFn<RegistraPedido, RegistraPedido>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Cliente Destinatario");
		RegistraPedido registraPedido = c.element();
		RegistraPedido registraPedidoReturn = (RegistraPedido) registraPedido.clone();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			if(registraPedido.getClienteDestinatario() != null) {

				conn = dataSource.getConnection();
				LOG.info("Creando la conexion:." + conn.toString());

				String consultaInsertClienteLocal = "SELECT * FROM clientes.clientes_crud (?, ?, ?, ?, ?, ?, ?, ?)";
				statement = conn.prepareStatement(consultaInsertClienteLocal);
				LOG.info("Creando el statement." + statement.toString());

				ClienteRemitente clienteRemitente =
						registraPedido.getClienteRemitente();

				ClienteDestinatario clienteDestinatario =
						registraPedido.getClienteDestinatario();

				Pedido pedido = registraPedido.getPedido();

				ClienteEmailContacto clienteDestinatarioEmailContacto =
						clienteDestinatario.getEmail_de_contacto();


				ClienteDireccion clienteDestinatarioDireccion =
						clienteDestinatario.getDireccion();


				if (clienteDestinatario.getOperacion_crud() == null ||
						clienteDestinatario.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if (!(clienteDestinatario.getOperacion_crud() == null)) {
					if (clienteDestinatario.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, clienteDestinatario.getId_cliente());
					}
				}

				if (!(clienteDestinatario.getOperacion_crud().equals("NONE"))) {

					if (clienteDestinatario.getPrimer_nombre() == null)
						statement.setNull(3, java.sql.Types.VARCHAR);
					else
						statement.setString(3, clienteDestinatario.getPrimer_nombre());

					if (clienteDestinatario.getSegundo_nombre() == null)
						statement.setNull(4, java.sql.Types.VARCHAR);
					else
						statement.setString(4, clienteDestinatario.getSegundo_nombre());

					if (clienteDestinatario.getApellido_paterno() == null)
						statement.setNull(5, java.sql.Types.VARCHAR);
					else
						statement.setString(5, clienteDestinatario.getApellido_paterno());

					if (clienteDestinatario.getApellido_materno() == null)
						statement.setNull(6, java.sql.Types.VARCHAR);
					else
						statement.setString(6, clienteDestinatario.getApellido_materno());

					if (clienteDestinatario.getClave_homologada() == null)
						statement.setNull(7, java.sql.Types.VARCHAR);
					else
						statement.setString(7, clienteDestinatario.getClave_homologada());

					if (clienteDestinatario.getId_sistema_origen() == null)
						statement.setNull(8, java.sql.Types.INTEGER);
					else
						statement.setInt(8, clienteDestinatario.getId_sistema_origen());


					boolean rowsInserted = statement.execute();
					LOG.info("Executando el statement, rowsInserted: " + rowsInserted);
					if (rowsInserted) {

						resultSet = statement.getResultSet();

						if (resultSet.next() &&
								clienteDestinatario.getOperacion_crud().equals("INSERT")) {
							int id_cliente = resultSet.getInt(1);
							LOG.info("Iterando el resultset: id_cliente:" + id_cliente);

							RegistraPedido copyRegistraPedido = (RegistraPedido) registraPedido.clone();
							Pedido copyPedido = (Pedido) pedido.clone();
							ClienteDestinatario copyClienteDestinatario = (ClienteDestinatario) clienteDestinatario.clone();
							ClienteEmailContacto copyClienteDestinatarioEmailContacto = null;
							ClienteDireccion copyClienteDireccion = (ClienteDireccion) clienteDestinatarioDireccion.clone();

							if (clienteDestinatarioEmailContacto != null) {
								copyClienteDestinatarioEmailContacto = (ClienteEmailContacto) clienteDestinatarioEmailContacto.clone();
								copyClienteDestinatarioEmailContacto.setId_cliente(id_cliente);
								copyClienteDestinatario.setEmail_de_contacto(copyClienteDestinatarioEmailContacto);
							}


							copyClienteDestinatario.setId_cliente(id_cliente);
							copyClienteDireccion.setId_cliente_destinatario(id_cliente);
							copyClienteDireccion.setId_cliente(id_cliente);
							copyPedido.setId_cliente_destinatario(id_cliente);

							copyClienteDestinatario.setDireccion(copyClienteDireccion);
							copyRegistraPedido.setClienteDestinatario(copyClienteDestinatario);
							copyRegistraPedido.setPedido(copyPedido);

							registraPedidoReturn = copyRegistraPedido;
						}
					}

				} else {

					//Si es el mismo cliente remitente y destinatario
					RegistraPedido copyRegistraPedido = (RegistraPedido) registraPedido.clone();

					ClienteRemitente copyClienteRemitente = (ClienteRemitente) clienteRemitente.clone();
					ClienteDestinatario copyClienteDestinatario = (ClienteDestinatario) clienteDestinatario.clone();

					ClienteEmailContacto copyClienteDestinatarioEmailContacto;
					ClienteDireccion copyClienteDestinatarioDireccion = (ClienteDireccion) clienteDestinatarioDireccion.clone();

					if (clienteDestinatarioEmailContacto != null) {
						copyClienteDestinatarioEmailContacto = (ClienteEmailContacto) clienteDestinatarioEmailContacto.clone();
						copyClienteDestinatarioEmailContacto.setId_cliente(copyClienteRemitente.getId_cliente());
						copyClienteDestinatario.setEmail_de_contacto(copyClienteDestinatarioEmailContacto);
					}

					copyClienteDestinatario.setId_cliente(copyClienteRemitente.getId_cliente());

					copyClienteDestinatarioDireccion.setId_cliente(copyClienteRemitente.getId_cliente());
					copyClienteDestinatarioDireccion.setId_cliente_destinatario(copyClienteRemitente.getId_cliente());

					copyClienteDestinatario.setDireccion(copyClienteDestinatarioDireccion);
					copyRegistraPedido.setClienteDestinatario(copyClienteDestinatario);
					copyRegistraPedido.setClienteRemitente(copyClienteRemitente);

					registraPedidoReturn = copyRegistraPedido;
				}
			}
		}catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Destinatario en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		}
		catch(Exception e)
		{
			LOG.error("Error al procesar Cliente Destinatario:" + e.getMessage(), e);
			throw new Exception(e);
		}finally {
			LOG.info("Entre al Finally de Cliente Destinatario.");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Cliente Destinatario:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Cliente Destinatario:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Cliente Destinatario:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Cliente Destinatario:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Cliente Destinatario:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Cliente Destinatario:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Cliente Destinatario.");
			if(registraPedidoReturn!=null){
				c.output(registraPedidoReturn);
			}
		}
		LOG.info("Finalizando process en Cliente Destinatario");
	}
//
//	@Teardown
//    public void tearDown() {
//		if(conn != null) {
//			try {
//				statement.close();
//				LOG.info("Statement cerrado");
//				conn.close();
//				LOG.info("Conexion cerrado");
//			} catch(SQLException sqlex) {
//				LOG.error("Exception in shutting database connection:" + sqlex.getMessage(), sqlex);
//			}
//		}
//    }
}
