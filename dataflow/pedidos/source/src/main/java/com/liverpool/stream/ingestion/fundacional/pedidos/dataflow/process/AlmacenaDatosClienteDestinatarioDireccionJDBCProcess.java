package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.apache.beam.sdk.transforms.DoFn.Setup;
import org.apache.beam.sdk.transforms.DoFn.Teardown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteDestinatario;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteDireccion;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

import javax.sql.DataSource;

public class AlmacenaDatosClienteDestinatarioDireccionJDBCProcess extends DoFn<RegistraPedido, RegistraPedido>    {


	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteDestinatarioDireccionJDBCProcess.class);
	private DataSource dataSource;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteDireccion;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private final String stepName = "AlmacenaDatosClienteDestinatarioDireccion";

	public AlmacenaDatosClienteDestinatarioDireccionJDBCProcess(String jdbcUrl,
																String jdbcUsuario,
																String jdbcPassword,
																String consultaInsertClienteDireccion,
																String instanceNameSM,
																String applicationName,
																Integer connectionPoolMaxSize,
																Integer connectionPoolMinIdle,
																Integer connectionPoolTimeout,
																Integer connectionPoolIdleTimeout,
																Integer connectionPoolMaxLifetime
										  )
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertClienteDireccion = consultaInsertClienteDireccion;
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
//				statement = conn.prepareStatement(this.consultaInsertCliente);
//				LOG.info("Conexion creada para Clientes Destinatario Direccion");
//			} catch(SQLException sqlex) {
//				LOG.error("Error al crear conexion de base de datos para Clientes Destinatario Direccion:" + sqlex.getMessage(), sqlex);
//			}
//		}
    }
	
	@ProcessElement
	public void processElement(DoFn<RegistraPedido, RegistraPedido>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Datos Cliente Destinatario Direccion");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		RegistraPedido registraPedido = c.element();
		RegistraPedido registraPedidoReturn = (RegistraPedido) registraPedido.clone();
		try{
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertClienteDireccion);
			LOG.info("Creando el statement." + statement.toString());

			ClienteDireccion clienteDestinatarioDireccion = null;
			Pedido pedido = registraPedido.getPedido();

			if( registraPedido.getClienteDestinatario() != null && registraPedido.getClienteDestinatario().getDireccion() != null ) {

				clienteDestinatarioDireccion = registraPedido
						.getClienteDestinatario()
						.getDireccion();

				if(clienteDestinatarioDireccion.getOperacion_crud() == null ||
						clienteDestinatarioDireccion.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if(!(clienteDestinatarioDireccion.getOperacion_crud() == null)) {
					if(clienteDestinatarioDireccion.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, clienteDestinatarioDireccion.getId_direccion_del_cliente());
					}
					if(clienteDestinatarioDireccion.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
						statement.setInt(2, clienteDestinatarioDireccion.getId_direccion_del_cliente());
					}
				}
				Integer clienteDestinararioTmp = registraPedido.getClienteDestinatario().getId_cliente();

				if(clienteDestinatarioDireccion.getId_cliente() == null)
					statement.setNull(3, java.sql.Types.INTEGER);
				else
					statement.setInt(3, clienteDestinararioTmp);

				if (clienteDestinatarioDireccion.getCalle() == null)
					statement.setNull(4, java.sql.Types.VARCHAR);
				else
					statement.setString(4, clienteDestinatarioDireccion.getCalle());

				if(clienteDestinatarioDireccion.getNumero_exterior() == null)
					statement.setNull(5, java.sql.Types.VARCHAR);
				else
					statement.setString(5, clienteDestinatarioDireccion.getNumero_exterior());

				if(clienteDestinatarioDireccion.getNumero_interior() == null)
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, clienteDestinatarioDireccion.getNumero_interior());

				if(clienteDestinatarioDireccion.getCodigo_postal() == null)
					statement.setNull(7, java.sql.Types.VARCHAR);
				else
					statement.setString(7, clienteDestinatarioDireccion.getCodigo_postal());

				if(clienteDestinatarioDireccion.getId_colonia() == null)
					statement.setNull(8, java.sql.Types.INTEGER);
				else
					statement.setInt(8, clienteDestinatarioDireccion.getId_colonia());

				if(clienteDestinatarioDireccion.getId_municipio() == null)
					statement.setNull(9, java.sql.Types.INTEGER);
				else
					statement.setInt(9, clienteDestinatarioDireccion.getId_municipio());

				if(clienteDestinatarioDireccion.getId_estado_del_pais() == null)
					statement.setNull(10, java.sql.Types.INTEGER);
				else
					statement.setInt(10, clienteDestinatarioDireccion.getId_estado_del_pais());

				if(clienteDestinatarioDireccion.getEntre_calle_1() == null)
					statement.setNull(11, java.sql.Types.VARCHAR);
				else
					statement.setString(11, clienteDestinatarioDireccion.getEntre_calle_1());

				if(clienteDestinatarioDireccion.getEntre_calle_2() == null)
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, clienteDestinatarioDireccion.getEntre_calle_2());

				if(clienteDestinatarioDireccion.getId_cliente_destinatario() == null)
					statement.setNull(13, java.sql.Types.INTEGER);
				else
					statement.setInt(13, clienteDestinatarioDireccion.getId_cliente_destinatario());

				if(clienteDestinatarioDireccion.getId_sistema_origen() == null)
					statement.setNull(14, java.sql.Types.INTEGER);
				else
					statement.setInt(14, clienteDestinatarioDireccion.getId_sistema_origen());

				if(clienteDestinatarioDireccion.getCiudad() == null)
					statement.setNull(15, java.sql.Types.VARCHAR);
				else
					statement.setString(15, clienteDestinatarioDireccion.getCiudad());

				if(clienteDestinatarioDireccion.getEdificio() == null)
					statement.setNull(16, java.sql.Types.VARCHAR);
				else
					statement.setString(16, clienteDestinatarioDireccion.getEdificio());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted) {

					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int id_direcciones_destinatario = resultSet.getInt(1);
						LOG.info("id_direcciones {}",id_direcciones_destinatario );
						RegistraPedido copyRegistraPedido = (RegistraPedido) registraPedido.clone();
						Pedido copyPedido = (Pedido) pedido.clone();

						copyPedido.setId_direcciones_destinatario(id_direcciones_destinatario);
						copyRegistraPedido.setPedido(copyPedido);

						registraPedidoReturn = copyRegistraPedido;
					}
				}
			}
		}catch(SQLException sqlex)
		{
			LOG.error("Error al intentar query Cliente Destinatario Direccion en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		}
		catch(Exception e)
		{
			LOG.error("Error al procesar Cliente Destinatario Direccion:" + e.getMessage(), e);
			throw new Exception(e);
		}finally {
			LOG.info("Entre al Finally de Datos Cliente Destinatario Direccion");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Datos Cliente Destinatario Direccion:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Datos Cliente Destinatario Direccion:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Datos Cliente Destinatario Direccion:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Datos Cliente Destinatario Direccion:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Cliente Destinatario Direccion:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Datos Cliente Destinatario Direccion:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Datos Cliente Destinatario Direccion");
			if(registraPedidoReturn!=null){
				c.output(registraPedidoReturn);
			}
		}
		LOG.info("Finalizando process en Datos Cliente Destinatario Direccion");
	}
	
//	@Teardown
//    public void tearDown() {
//		if(conn != null) {
//			try {
//				statement.close();
//				LOG.info("Statement cerrado");
//				conn.close();
//				LOG.info("Conexion cerrada");
//			} catch(SQLException sqlex) {
//				LOG.error("Exception in shutting database connection:" + sqlex.getMessage(), sqlex);
//			}
//		}
//    }
}

