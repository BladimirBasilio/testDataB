package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import java.sql.*;
import java.util.Properties;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.PedidoDetalleSku;

import javax.sql.DataSource;

public class AlmacenaDatosPedidoDetalleSkuJDBCProcess extends DoFn<PedidoDetalleSku, String> {

	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosPedidoDetalleSkuJDBCProcess.class);
	private static final long serialVersionUID = 147123567L;
	private DataSource dataSource;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertPedidoDetalleSku;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private final String stepName = "AlmacenaDatosPedidoDetalleSku";


	public AlmacenaDatosPedidoDetalleSkuJDBCProcess(String jdbcUrl,
													String jdbcUsuario,
													String jdbcPassword,
													String consulInsertPedidoDetalleSku,
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
		this.consulInsertPedidoDetalleSku = consulInsertPedidoDetalleSku;
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
//				statement = conn.prepareStatement(this.consulInsertPedidoDetalleSku);
//				LOG.info("Conexion creada para Pedido Detalles SKU");
//			} catch(SQLException sqlex) {
//				LOG.error("Error al crear conexion de base de datos para PedidoDetalleSku:" + sqlex.getMessage(), sqlex);
//			}
//		}
	}


	@ProcessElement
	public void processElement(DoFn<PedidoDetalleSku, String>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Datos Pedido Detalle Sku");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." + conn.toString());

			statement = conn.prepareStatement(this.consulInsertPedidoDetalleSku);
			LOG.info("Creando el statement." + statement.toString());

			PedidoDetalleSku element = c.element();

			if(element.getOperacion_crud() == null || element.getOperacion_crud().equals("INSERT")) {
				statement.setString(1, "INSERT");
			}
			if( element.getOperacion_crud() != null && element.getOperacion_crud().equals("UPDATE")) {
					statement.setString(1, "UPDATE");
			}
			if( element.getOperacion_crud() != null && element.getOperacion_crud().equals("NONE")) {
					statement.setString(1, "NONE");
			}

			//key fields
			statement.setString(2, element.getNumero_del_documento_pedidos());

			if(element.getEstado_orden_pedidos() == null)
				statement.setNull(3, java.sql.Types.VARCHAR);
			else
				statement.setString(3, element.getEstado_orden_pedidos());

			if(element.getHora_modificacion_pedidos() == null)
				statement.setNull(4, java.sql.Types.DATE);
			else
				statement.setDate(4, Date.valueOf(element.getFecha_modificacion_pedidos()));

			if(element.getHora_modificacion_pedidos() == null)
				statement.setNull(5, java.sql.Types.TIME);
			else
				statement.setTime(5, Time.valueOf(element.getHora_modificacion_pedidos()));

			if(element.getProveedor_de_mensajeria() == null)
				statement.setNull(6, java.sql.Types.VARCHAR);
			else
				statement.setString(6, element.getProveedor_de_mensajeria());

			if(element.getFecha_real_de_entrega() == null)
				statement.setNull(7, java.sql.Types.DATE);
			else
				statement.setDate(7, Date.valueOf(element.getFecha_real_de_entrega()));

			if(element.getId_locacion_surte() == null)
				statement.setNull(8, java.sql.Types.INTEGER);
			else
				statement.setInt(8, element.getId_locacion_surte());

			if(element.getId_producto_productos() == null)
				statement.setNull(9, java.sql.Types.INTEGER);
			else
				statement.setInt(9, element.getId_producto_productos());

			if(element.getFecha_actualizacion_estado() == null)
				statement.setNull(10, java.sql.Types.DATE);
			else
				statement.setDate(10, Date.valueOf(element.getFecha_actualizacion_estado()));

			if(element.getPiezas() == null)
				statement.setNull(11, java.sql.Types.NUMERIC);
			else
				statement.setBigDecimal(11, element.getPiezas());

			if(element.getNumero_de_guia() == null)
				statement.setNull(12, java.sql.Types.VARCHAR);
			else
				statement.setString(12, element.getNumero_de_guia());

			//key field
			statement.setInt(13, element.getId_linea_detalle());


			if(element.getFecha_promesa_de_entrega_inicial() == null)
				statement.setNull(14, java.sql.Types.DATE);
			else
				statement.setDate(14,  Date.valueOf(element.getFecha_promesa_de_entrega_inicial()));

			if(element.getHora_actualizacion_estado() == null)
				statement.setNull(15, java.sql.Types.TIME);
			else
				statement.setTime(15, Time.valueOf(element.getHora_actualizacion_estado()));

			if(element.getFecha_ultimo_intento() == null)
				statement.setNull(16, java.sql.Types.DATE);
			else
				statement.setDate(16, Date.valueOf(element.getFecha_ultimo_intento()));

			if(element.getIndicador_marketplace() == null)
				statement.setNull(17, java.sql.Types.VARCHAR);
			else
				statement.setString(17, element.getIndicador_marketplace());

			if(element.getCantidad_recogida_orden_de_venta() == null)
				statement.setNull(18, java.sql.Types.NUMERIC);
			else
				statement.setBigDecimal(18, element.getCantidad_recogida_orden_de_venta());

			if(element.getFecha_de_surtido() == null)
				statement.setNull(19, java.sql.Types.DATE);
			else
				statement.setDate(19, Date.valueOf(element.getFecha_de_surtido()));

			if(element.getNumero_de_intentos_de_entrega() == null)
				statement.setNull(20, java.sql.Types.NUMERIC);
			else
				statement.setBigDecimal(20, element.getNumero_de_intentos_de_entrega());

			if(element.getCantidad_cancelada() == null)
				statement.setNull(21, java.sql.Types.NUMERIC);
			else
				statement.setBigDecimal(21, element.getCantidad_cancelada());

			if(element.getCantidad_entregada() == null)
				statement.setNull(22, java.sql.Types.NUMERIC);
			else
				statement.setBigDecimal(22, element.getCantidad_entregada());

			if(element.getId_causa_de_noentrega() == null)
				statement.setNull(23, java.sql.Types.INTEGER);
			else
				statement.setInt(23, element.getId_causa_de_noentrega());

			if(element.getUsuario_actualizador_de_estado() == null)
				statement.setNull(24, java.sql.Types.VARCHAR);
			else
				statement.setString(24, element.getUsuario_actualizador_de_estado());

			if(element.getFecha_recalculada() == null)
				statement.setNull(25, java.sql.Types.DATE);
			else
				statement.setDate(25, Date.valueOf(element.getFecha_recalculada()));

			if(element.getFecha_promesa_de_entrega_final() == null)
				statement.setNull(26, java.sql.Types.DATE);
			else
				statement.setDate(26,  Date.valueOf(element.getFecha_promesa_de_entrega_final()));

			if(element.getId_estado_por_linea() == null)
				statement.setNull(27, java.sql.Types.INTEGER);
			else
				statement.setInt(27, element.getId_estado_por_linea());

			if(element.getFecha_de_compra_pedidos() == null)
				statement.setNull(28, java.sql.Types.DATE);
			else
				statement.setDate(28, Date.valueOf(element.getFecha_de_compra_pedidos()));

			if(element.getSku() == null)
				statement.setNull(29, java.sql.Types.VARCHAR);
			else
				statement.setString(29, element.getSku());

			statement.execute();
			LOG.info("Executando el statement");

		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query Pedido Detalle Sku en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Pedido Detalle Sku:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Datos Pedido Detalle Sku.");
			try {
				if (resultSet != null) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Datos Pedido Detalle Sku:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Datos Pedido Detalle Sku:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					LOG.info("Cerrando el statement en Datos Pedido Detalle Sku:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Datos Pedido Detalle Sku:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Pedido Detalle Sku:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en DatosPedido:" + en.getMessage(), en);
			}
			LOG.info("Entre al Finally de Datos Pedido Detalle Sku.");
		}
		LOG.info("Finaliza process en Datos Pedido Detalle Sku");
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
