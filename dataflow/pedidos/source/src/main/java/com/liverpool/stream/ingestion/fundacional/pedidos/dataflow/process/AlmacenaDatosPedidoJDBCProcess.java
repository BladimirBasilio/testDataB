package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.PubsubMessage;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.TopicName;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.services.IRedisCache;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.services.impl.RedisCache;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.apache.beam.sdk.transforms.DoFn.Setup;
import org.apache.beam.sdk.transforms.DoFn.Teardown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

import javax.sql.DataSource;

public class AlmacenaDatosPedidoJDBCProcess extends DoFn<RegistraPedido, RegistraPedido>    {


	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosPedidoJDBCProcess.class);
	private DataSource dataSource;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertPedidos;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private String redisHost;
	private Integer redisPort;
	private Integer redisTimeout;
	private String redisPassword;
	private Integer redisMaxActive;
	private Integer redisMaxIdle;
	private Integer redisMinIdle;

	private String vinculacionTopic;
	private String vinculacionTopicProjectId;
	private final String stepName = "AlmacenaDatosPedidos";


	public AlmacenaDatosPedidoJDBCProcess(	String jdbcUrl,
											  String jdbcUsuario,
											  String jdbcPassword,
											  String consultaInsertPedidos,
											  String instanceNameSM,
											  String applicationName,
											  Integer connectionPoolMaxSize,
											  Integer connectionPoolMinIdle,
											  Integer connectionPoolTimeout,
											  Integer connectionPoolIdleTimeout,
											  Integer connectionPoolMaxLifetime,
											  String redisHost,
											  Integer redisPort,
											  Integer redisTimeout,
											  String redisPassword,
											  Integer redisMaxActive,
											  Integer redisMaxIdle,
											  Integer redisMinIdle,
											  String vinculacionTopic,
											  String vinculacionTopicProjectId
										  )
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertPedidos = consultaInsertPedidos;
		this.instanceNameSM = instanceNameSM;
		this.applicationName = applicationName;
		this.connectionPoolMaxSize = connectionPoolMaxSize;
		this.connectionPoolMinIdle = connectionPoolMinIdle;
		this.connectionPoolTimeout = connectionPoolTimeout;
		this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
		this.redisHost=redisHost;
		this.redisPort=redisPort;
		this.redisTimeout=redisTimeout;
		this.redisPassword=redisPassword;
		this.redisMaxActive=redisMaxActive;
		this.redisMaxIdle=redisMaxIdle;
		this.redisMinIdle=redisMinIdle;
		this.vinculacionTopic=vinculacionTopic;
		this.vinculacionTopicProjectId=vinculacionTopicProjectId;
	}
	
	
	@Setup
    public void init() {
//		if(conn == null) {
//			try {
//				Properties props = new Properties();
//				props.setProperty("user", this.jdbcUsuario);
//				props.setProperty("password", this.jdbcPassword);
//				conn = DriverManager.getConnection(this.jdbcUrl, props);
//				statement = conn.prepareStatement(this.consultaInsertCliente);
//				LOG.info("Conexion creada para Pedido");
//			} catch(SQLException sqlex) {
//				LOG.error("Error al crear conexion de base de datos para Pedido:" + sqlex.getMessage(), sqlex);
//			}
//		}
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
		LOG.info("Iniciando process en Datos Pedido");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		RegistraPedido registraPedido = c.element();
		Pedido pedido = registraPedido.getPedido();
		RegistraPedido registraPedidoReturn = (RegistraPedido) registraPedido.clone();

		try{
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." + conn.toString());

			statement = conn.prepareStatement(this.consultaInsertPedidos);
			LOG.info("Creando el statement." + statement.toString());

				if (pedido.getOperacion_crud() == null ||
						pedido.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					LOG.info("Pedido en PubSub INSERT"+registraPedido.toString());
				}
				if (!(pedido.getOperacion_crud() == null)) {
					if (pedido.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						LOG.info("Pedido en PubSub UPDATE"+registraPedido.toString());
					}
				}

				if (!(pedido.getOperacion_crud() == null)) {
					if (pedido.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
					}
				}
				//key fields
				statement.setString(2, pedido.getNumero_del_documento());

				if (pedido.getEstado_orden() == null)
					statement.setNull(3, java.sql.Types.VARCHAR);
				else
					statement.setString(3, pedido.getEstado_orden());

				if (pedido.getFecha_modificacion() == null)
					statement.setNull(4, java.sql.Types.DATE);
				else
					statement.setDate(4, Date.valueOf(pedido.getFecha_modificacion()));

				if (pedido.getHora_modificacion() == null)
					statement.setNull(5, java.sql.Types.TIME);
				else
					statement.setTime(5, Time.valueOf(pedido.getHora_modificacion()));

				if (pedido.getEstado_renglon_bitacora() == null)
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, pedido.getEstado_renglon_bitacora());

				if (pedido.getFecha_emision() == null)
					statement.setNull(7, java.sql.Types.DATE);
				else
					statement.setDate(7, Date.valueOf(pedido.getFecha_emision()));

				if (pedido.getHora_emision() == null)
					statement.setNull(8, java.sql.Types.TIME);
				else
					statement.setTime(8, Time.valueOf(pedido.getHora_emision()));

				if (pedido.getOrden_original() == null)
					statement.setNull(9, java.sql.Types.VARCHAR);
				else
					statement.setString(9, pedido.getOrden_original());

				if (pedido.getId_tipo_entrega() == null)
					statement.setNull(10, java.sql.Types.INTEGER);
				else
					statement.setInt(10, pedido.getId_tipo_entrega());

				if (pedido.getId_tipo_de_venta() == null)
					statement.setNull(11, java.sql.Types.INTEGER);
				else
					statement.setInt(11, pedido.getId_tipo_de_venta());

				if (pedido.getId_cliente_remitente() == null)
					statement.setNull(12, java.sql.Types.INTEGER);
				else
					statement.setInt(12, pedido.getId_cliente_remitente());

				if (pedido.getId_direcciones_destinatario() == null)
					statement.setNull(13, java.sql.Types.INTEGER);
				else
					statement.setInt(13, pedido.getId_direcciones_destinatario());

				if (pedido.getId_tipo_de_act() == null)
					statement.setNull(14, java.sql.Types.INTEGER);
				else
					statement.setInt(14, pedido.getId_tipo_de_act());

				if (pedido.getId_tipo_de_documento() == null)
					statement.setNull(15, java.sql.Types.INTEGER);
				else
					statement.setInt(15, pedido.getId_tipo_de_documento());

				if (pedido.getId_periodicidad() == null)
					statement.setNull(16, java.sql.Types.INTEGER);
				else
					statement.setInt(16, pedido.getId_periodicidad());

				if (pedido.getObservaciones() == null)
					statement.setNull(17, java.sql.Types.VARCHAR);
				else
					statement.setString(17, pedido.getObservaciones());

				if (pedido.getId_tienda_y_locacion_destino() == null)
					statement.setNull(18, java.sql.Types.INTEGER);
				else
					statement.setInt(18, pedido.getId_tienda_y_locacion_destino());

				if (pedido.getId_frecuencia_visita() == null)
					statement.setNull(19, java.sql.Types.INTEGER);
				else
					statement.setInt(19, pedido.getId_frecuencia_visita());

				if (pedido.getFecha_de_compra() == null)
					statement.setNull(20, java.sql.Types.DATE);
				else
					statement.setDate(20, Date.valueOf(pedido.getFecha_de_compra()));

				if (pedido.getId_estado_del_documento() == null)
					statement.setNull(21, java.sql.Types.INTEGER);
				else
					statement.setInt(21, pedido.getId_estado_del_documento());

				if (pedido.getUsuario_actualizador_de_estado() == null)
					statement.setNull(22, java.sql.Types.VARCHAR);
				else
					statement.setString(22, pedido.getUsuario_actualizador_de_estado());

				if (pedido.getId_cliente_destinatario() == null)
					statement.setNull(23, java.sql.Types.INTEGER);
				else
					statement.setInt(23, pedido.getId_cliente_destinatario());

				if (pedido.getId_cliente_emails_de_contacto() == null)
					statement.setNull(24, java.sql.Types.INTEGER);
				else
					statement.setInt(24, pedido.getId_cliente_emails_de_contacto());

				if (pedido.getId_cliente_telefonos_de_contacto_celular() == null)
					statement.setNull(25, java.sql.Types.INTEGER);
				else
					statement.setInt(25, pedido.getId_cliente_telefonos_de_contacto_celular());

				if (pedido.getId_cliente_telefonos_de_contacto_fijo() == null)
					statement.setNull(26, java.sql.Types.INTEGER);
				else
					statement.setInt(26, pedido.getId_cliente_telefonos_de_contacto_fijo());

				if (pedido.getId_cliente_telefonos_de_contacto_oficina() == null)
					statement.setNull(27, java.sql.Types.INTEGER);
				else
					statement.setInt(27, pedido.getId_cliente_telefonos_de_contacto_oficina());


				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				//TODO revisar con Dani porque el registra pedido es el mismo en el if que en el else
				if (rowsInserted) {
					 resultSet = statement.getResultSet();
					if (resultSet.next() &&
							pedido.getOperacion_crud().equals("INSERT")) {
						String numero_del_documento = resultSet.getString(1);
						LOG.info("numero_del_documento {}", numero_del_documento);

						registraPedidoReturn = registraPedido;
						//c.output(registraPedido);
					} else {
						//c.output(registraPedido);
						registraPedidoReturn = registraPedido;
					}
				}
				IRedisCache redisCache = RedisCache.getInstance(redisHost,redisPort,redisTimeout,redisPassword,
						redisMaxActive,redisMaxIdle,redisMinIdle);
				if(redisCache.exists(pedido.getNumero_del_documento())){
					String boletaData = redisCache.get(pedido.getNumero_del_documento());
					LOG.info("Boleta lleg? primero que documento {} {}",pedido.getNumero_del_documento(),boletaData);
					TopicName topicName = TopicName.of(vinculacionTopicProjectId,vinculacionTopic);
					Publisher publisher = Publisher.newBuilder(topicName).build();
					ByteString message = ByteString.copyFromUtf8(boletaData);
					PubsubMessage pubSubMessage = PubsubMessage.newBuilder()
							.setData(message)
							.build();
					ApiFuture<String> publish = publisher.publish(pubSubMessage);
					LOG.info("Mensaje publicado:"+publish.get());
					publisher.shutdown();
				}else{
					LOG.info("Documento llego primero que boleta");
				}

		}catch(SQLException sqlex) {
			LOG.error("Error al intentar query Pedido en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		}
		catch(Exception e) {
			LOG.error("Error al procesar Pedido:{}",e.getStackTrace());
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Datos Pedido");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Datos Pedido:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Datos Pedido:" + er.getMessage(), er);
				throw new Exception(er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Datos Pedido:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Datos Pedido:" + es.getMessage(), es);
				throw new Exception(es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Pedido:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Datos Pedido:" + en.getMessage(), en);
				throw new Exception(en);
			}
			LOG.info("Salí al Finally de Datos Pedido");
			if(registraPedidoReturn!=null){
				c.output(registraPedidoReturn);
			}
		}

		LOG.info("Finaliza process en Datos Pedido");
	}
	
//	@Teardown
//    public void tearDown() {
//		if(conn != null) {
//			try {
//				statement.close();
//				conn.close();
//			} catch(SQLException sqlex) {
//				LOG.error("Exception in shutting database connection:" + sqlex.getMessage(), sqlex);
//			}
//		}
//    }
}


