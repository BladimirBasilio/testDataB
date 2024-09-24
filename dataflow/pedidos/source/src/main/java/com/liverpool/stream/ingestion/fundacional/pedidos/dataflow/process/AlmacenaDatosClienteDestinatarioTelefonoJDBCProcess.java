package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteTelefonoContacto;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AlmacenaDatosClienteDestinatarioTelefonoJDBCProcess extends DoFn<RegistraPedido, RegistraPedido> {

	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteDestinatarioTelefonoJDBCProcess.class);
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
	private final String stepName = "AlmacenaDatosClienteDestinatarioTelefono";


	public AlmacenaDatosClienteDestinatarioTelefonoJDBCProcess(
			String jdbcUrl,
			String jdbcUsuario,
			String jdbcPassword,
			String consulInsertClienteTelefonoContacto,
			String instanceNameSM,
			String applicationName,
			Integer connectionPoolMaxSize,
			Integer connectionPoolMinIdle,
			Integer connectionPoolTimeout,
			Integer connectionPoolIdleTimeout,
			Integer connectionPoolMaxLifetime
	){
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
//				LOG.info("Conexion creada para Cliente Destinatario Telefono");
//			} catch(SQLException sqlex) {
//				LOG.error("Error al crear conexion de base de datos para ClienteTelefonoContacto Destinatario:" + sqlex.getMessage(), sqlex);
//			}
//		}
	}


	@ProcessElement
	public void processElement(DoFn<RegistraPedido, RegistraPedido>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Datos Cliente Destinatario Telefono");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		RegistraPedido registraPedido = c.element();
		Pedido pedido = registraPedido.getPedido();
		RegistraPedido copyRegistraPedido = (RegistraPedido) registraPedido.clone();
		Pedido copyPedido = (Pedido) pedido.clone();


		try {
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." + conn.toString());

			List<ClienteTelefonoContacto> elementList = registraPedido.getClienteDestinatario() != null ? registraPedido.getClienteDestinatario().getTelefono_de_contacto() : new ArrayList<>();
			Integer clienteDestinararioTmp = registraPedido.getClienteDestinatario() != null ? registraPedido.getClienteDestinatario().getId_cliente() : null;

			for (ClienteTelefonoContacto element : elementList) {

				statement = conn.prepareStatement(consulInsertClienteTelefonoContacto);
				LOG.info("Creando el statement." + statement.toString());
				if (element.getOperacion_crud() == null || element.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}

				if (!(element.getOperacion_crud() == null)) {
					if (element.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						if (element.getId_cliente_telefonos_de_contacto() == null)
							statement.setNull(2, Types.INTEGER);
						else
							statement.setInt(2, element.getId_cliente_telefonos_de_contacto());
					}
				}

				if (element.getOperacion_crud() == null || element.getOperacion_crud().equals("NONE")) {
					statement.setString(1, "NONE");
					statement.setInt(2, 0);
				}

				statement.setInt(3, clienteDestinararioTmp);

				if (element.getId_sistema_origen() == null)
					statement.setNull(4, Types.INTEGER);
				else
					statement.setInt(4, element.getId_sistema_origen());

				if (element.getTelefono() == null)
					statement.setNull(5, Types.VARCHAR);
				else
					statement.setString(5, element.getTelefono());

				if (element.getAlias_telefono() == null)
					statement.setNull(6, Types.VARCHAR);
				else
					statement.setString(6, element.getAlias_telefono());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if (rowsInserted) {
					resultSet = statement.getResultSet();

					if (resultSet.next()) {

						Integer id_cliente_telefono = resultSet.getInt(1);
						LOG.info("id_cliente_telefono {}", id_cliente_telefono);

						if (element.getAlias_telefono().equals("CASA")) {
							copyPedido.setId_cliente_telefonos_de_contacto_fijo(id_cliente_telefono);
						} else if (element.getAlias_telefono().equals("OFICINA")) {
							copyPedido.setId_cliente_telefonos_de_contacto_oficina(id_cliente_telefono);
						} else if (element.getAlias_telefono().equals("CELULAR")) {
							copyPedido.setId_cliente_telefonos_de_contacto_celular(id_cliente_telefono);
						}
					}
				}

			}
		}catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Destinatario Telefono Contacto en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente Destinatario Telefono Contacto:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Datos Cliente Destinatario Telefono Contacto");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Datos Cliente Destinatario Telefono Contacto:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Datos Cliente Destinatario Telefono Contacto:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Datos Cliente Destinatario Telefono Contacto:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Datos Cliente Destinatario Telefono Contacto:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Cliente Destinatario Telefono Contacto:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Datos Cliente Destinatario Telefono Contacto:" + en.getMessage(), en);
			}
			LOG.info("Sali del Finally de Datos Cliente Destinatario Telefono Contacto");
		}

		if(pedido.getId_cliente_remitente() == null){
			copyPedido.setId_cliente_remitente(copyPedido.getId_cliente_destinatario());
		}

		if(copyRegistraPedido!=null && copyPedido!=null){
			copyRegistraPedido.setPedido(copyPedido);
			c.output(copyRegistraPedido);
		}
		LOG.info("Finaliza process en Datos Cliente Destinatario Telefono");
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


