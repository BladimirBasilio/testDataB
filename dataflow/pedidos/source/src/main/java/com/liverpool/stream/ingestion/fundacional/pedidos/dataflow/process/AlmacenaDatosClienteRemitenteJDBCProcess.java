package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import java.sql.*;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteEmailContacto;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteRemitente;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

import javax.sql.DataSource;

public class AlmacenaDatosClienteRemitenteJDBCProcess extends DoFn<RegistraPedido, RegistraPedido>   {


	private static final long serialVersionUID = 1462827259L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteRemitenteJDBCProcess.class);
	private DataSource dataSource;
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
	private final String stepName = "AlmacenaDatosClienteRemitente";


	public AlmacenaDatosClienteRemitenteJDBCProcess(String jdbcUrl,
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
    }
	
	@ProcessElement
	public void processElement(DoFn<RegistraPedido, RegistraPedido>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Cliente Remitente");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		conn = dataSource.getConnection();
		LOG.info("Creando la conexion:." +  conn.toString());

		RegistraPedido registraPedido = c.element();
		RegistraPedido registraPedidoReturn = (RegistraPedido) registraPedido.clone();

		//TODO:Agregar logica para revisar si conexion esta activa.
		try{
			String consultaInsertClienteLocal = "SELECT * FROM clientes.clientes_crud (?, ?, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(consultaInsertClienteLocal);
			LOG.info("Creando el statement." + statement.toString());

			if( registraPedido.getClienteRemitente() != null ){

				ClienteRemitente clienteRemitente = registraPedido.getClienteRemitente();
				Pedido pedido = registraPedido.getPedido();
				ClienteEmailContacto  clienteRemitenteEmailContacto = clienteRemitente.getEmail_de_contacto();


				if(clienteRemitente.getOperacion_crud() == null ||
						clienteRemitente.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if(!(clienteRemitente.getOperacion_crud() == null)) {
					if(clienteRemitente.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, clienteRemitente.getId_cliente());
					}
				}
				if(!(clienteRemitente.getOperacion_crud() == null)) {
					if(clienteRemitente.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
						statement.setInt(2, clienteRemitente.getId_cliente());
					}
				}

				if(clienteRemitente.getPrimer_nombre() == null)
					statement.setNull(3, java.sql.Types.VARCHAR);
				else
					statement.setString(3, clienteRemitente.getPrimer_nombre());

				if(clienteRemitente.getSegundo_nombre() == null)
					statement.setNull(4, java.sql.Types.VARCHAR);
				else
					statement.setString(4, clienteRemitente.getSegundo_nombre());

				if(clienteRemitente.getApellido_paterno() == null)
					statement.setNull(5, java.sql.Types.VARCHAR);
				else
					statement.setString(5, clienteRemitente.getApellido_paterno());

				if(clienteRemitente.getApellido_materno() == null)
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, clienteRemitente.getApellido_materno());

				if(clienteRemitente.getClave_homologada() == null)
					statement.setNull(7, java.sql.Types.VARCHAR);
				else
					statement.setString(7, clienteRemitente.getClave_homologada());

				if(clienteRemitente.getId_sistema_origen() == null)
					statement.setNull(8, java.sql.Types.INTEGER);
				else
					statement.setInt(8, clienteRemitente.getId_sistema_origen());


				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);
				if(rowsInserted) {
					resultSet = statement.getResultSet();

					if(resultSet.next() && clienteRemitente.getOperacion_crud().equals("INSERT")) {
						int id_cliente = resultSet.getInt(1);
						LOG.info("Iterando el resultset: id_cliente:" + id_cliente);

						RegistraPedido copyRegistraPedido = (RegistraPedido) registraPedido.clone();
						Pedido copyPedido = (Pedido) pedido.clone();
						ClienteRemitente copyClienteRemitente = (ClienteRemitente) clienteRemitente.clone();
						ClienteEmailContacto  copyClienteRemitenteEmailContacto = (ClienteEmailContacto) clienteRemitenteEmailContacto.clone();

						copyClienteRemitenteEmailContacto.setId_cliente(id_cliente);
						copyClienteRemitente.setId_cliente(id_cliente);
						copyPedido.setId_cliente_remitente(id_cliente);

						copyClienteRemitente.setEmail_de_contacto(copyClienteRemitenteEmailContacto);
						copyRegistraPedido.setClienteRemitente(copyClienteRemitente);
						copyRegistraPedido.setPedido(copyPedido);
						//c.output(copyRegistraPedido);
						registraPedidoReturn = copyRegistraPedido;
					} else {
						//c.output(registraPedido);
						registraPedidoReturn = registraPedido;
					}
				}
			}
		} catch(SQLException sqlex){
			LOG.error("Error al intentar query Cliente Remitente en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		}
		catch(Exception e){
			LOG.error("Error al procesar Cliente Remitente:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Cliente Remitente.");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Cliente Remitente:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Cliente Remitente:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Cliente Remitente:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Cliente Remitente:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Cliente Remitente:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Cliente Remitente:" + en.getMessage(), en);
			}
			if(registraPedidoReturn!=null){
				c.output(registraPedidoReturn);
			}
			LOG.info("Sali al Finally de Cliente Remitente.");
		}
		LOG.info("Finalizando process en Cliente Destinatario");
	}
	
//	@Teardown
//    public void tearDown() {
//
//			try {
//				if(statement != null ){
//					statement.close();
//				}
//				if(conn != null ){
//					conn.close();
//				}
//			}catch(SQLException sqlex) {
//				LOG.error("Exception in shutting database connection:" + sqlex.getMessage(), sqlex);
//			}
//    }
}
