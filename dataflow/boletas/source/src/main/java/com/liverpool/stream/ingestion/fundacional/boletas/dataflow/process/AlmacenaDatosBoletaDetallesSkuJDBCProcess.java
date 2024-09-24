package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.TupleTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesSku;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;

import javax.sql.DataSource;


public class AlmacenaDatosBoletaDetallesSkuJDBCProcess extends DoFn<BoletaDetallesSku, BoletaDetallesSku>    {

	/**
	 *
	 */
	private static final long serialVersionUID = 5283024641764493745L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetallesSkuJDBCProcess.class);
	private static final String tabla_nombre_descripcion = "Boleta Detalles Sku";
	private Connection conn;
	private PreparedStatement statement;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertBoletaDetallesSku;
	private DataSource dataSource;

	private final TupleTag<BoletaDetallesSku> boletaDetallesSkuSuccessTag;
	private final TupleTag<String> boletaDetallesSkuErrorTag;

	private ObjectMapper mapper;
	private JavaTimeModule javaTimeModule;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private String poolName;
	private final String stepName = "AlmacenaDatosBoletaDetallesSkuJDBCProcess";


	public AlmacenaDatosBoletaDetallesSkuJDBCProcess(String jdbcUrl,
													 String jdbcUsuario,
													 String jdbcPassword,
													 String consultaInsertBoletaDetallesSku,
													 TupleTag<BoletaDetallesSku> boletaDetallesSkuSuccessTag,
													 TupleTag<String> boletaDetallesSkuErrorTag,

													 String instanceNameSM,
													 String applicationName,
													 Integer connectionPoolMaxSize,
													 Integer connectionPoolMinIdle,
													 Integer connectionPoolTimeout,
													 Integer connectionPoolIdleTimeout,
													 Integer connectionPoolMaxLifetime,
													 String poolName)
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertBoletaDetallesSku = consultaInsertBoletaDetallesSku;
		this.boletaDetallesSkuSuccessTag = boletaDetallesSkuSuccessTag;
		this.boletaDetallesSkuErrorTag = boletaDetallesSkuErrorTag;
		this.instanceNameSM=instanceNameSM;
		this.applicationName=applicationName;
		this.connectionPoolMaxSize=connectionPoolMaxSize;
		this.connectionPoolMinIdle=connectionPoolMinIdle;
		this.connectionPoolTimeout=connectionPoolTimeout;
		this.connectionPoolIdleTimeout=connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime=connectionPoolMaxLifetime;
		this.poolName=poolName;
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

		mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		javaTimeModule = new JavaTimeModule();

		javaTimeModule.addSerializer(LocalTime.class,
				new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
		javaTimeModule.addDeserializer(LocalDate.class,
				new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		mapper.registerModule(javaTimeModule);
	}

	@ProcessElement
	public void processElement(DoFn<BoletaDetallesSku, BoletaDetallesSku>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Boleta Detalles Sku");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		BoletaDetallesSku boletaDetallesSku = c.element();
		Boolean banderaEjecucionCorrecta = false;

		try {
			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertBoletaDetallesSku);
			//LOG.info("Creando el statement." + statement.toString());

			if(boletaDetallesSku!=null){

				statement.setInt(1, boletaDetallesSku.getId_linea_detalle());
				statement.setString(2, boletaDetallesSku.getId_terminal_pos_boleta_cabeceras());
				statement.setString(3, boletaDetallesSku.getNumero_boleta_boleta_cabeceras());
				statement.setDate(4, Date.valueOf(boletaDetallesSku.getFecha_fin_transaccion_boleta_cabeceras()));
				statement.setTime(5, Time.valueOf(boletaDetallesSku.getHora_fin_transaccion_boleta_cabeceras()));
				statement.setString(6, boletaDetallesSku.getId_tienda_origen_boleta_cabeceras());
				statement.setString(7, boletaDetallesSku.getId_tienda_reconocimiento_boleta_cabeceras());
				statement.setInt(8, boletaDetallesSku.getId_sku());
				if(boletaDetallesSku.getId_pep_r3() == null)
					statement.setNull(9, java.sql.Types.VARCHAR);
				else
					statement.setString(9, boletaDetallesSku.getId_pep_r3());

				if(boletaDetallesSku.getTelefono_cliente_garantia_extendida() == null)
					statement.setNull(10, java.sql.Types.VARCHAR);
				else
					statement.setString(10, boletaDetallesSku.getTelefono_cliente_garantia_extendida());

				if(boletaDetallesSku.getNumero_orden_optica() == null)
					statement.setNull(11, java.sql.Types.VARCHAR);
				else
					statement.setString(11, boletaDetallesSku.getNumero_orden_optica());

				if(boletaDetallesSku.getOrden_de_reparacion() == null)
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, boletaDetallesSku.getOrden_de_reparacion());

				if(boletaDetallesSku.getNumero_telefono_o_folio() == null)
					statement.setNull(13, java.sql.Types.VARCHAR);
				else
					statement.setString(13, boletaDetallesSku.getNumero_telefono_o_folio());

				if(boletaDetallesSku.getNumero_comanda_restaurant() == null)
					statement.setNull(14, java.sql.Types.VARCHAR);
				else
					statement.setString(14, boletaDetallesSku.getNumero_comanda_restaurant());

				if(boletaDetallesSku.getNumero_poliza() == null)
					statement.setNull(15, java.sql.Types.VARCHAR);
				else
					statement.setString(15, boletaDetallesSku.getNumero_poliza());

				if(boletaDetallesSku.getCantidad_articulos() == null)
					statement.setNull(16, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(16, boletaDetallesSku.getCantidad_articulos());

				if(boletaDetallesSku.getPrecio_subtotal_neto() == null)
					statement.setNull(17, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(17, boletaDetallesSku.getPrecio_subtotal_neto());

				if(boletaDetallesSku.getId_motivo_devolucion() == null)
					statement.setNull(18, java.sql.Types.INTEGER);
				else
					statement.setInt(18, boletaDetallesSku.getId_motivo_devolucion());

				if(boletaDetallesSku.getId_seccion() == null)
					statement.setNull(19, java.sql.Types.INTEGER);
				else
					statement.setInt(19, boletaDetallesSku.getId_seccion());

				if(boletaDetallesSku.getPrecio_unitario_del_sku() == null)
					statement.setNull(20, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(20, boletaDetallesSku.getPrecio_unitario_del_sku());

				if(boletaDetallesSku.getFisico_o_virtual() == null)
					statement.setNull(21, java.sql.Types.VARCHAR);
				else
					statement.setString(21, boletaDetallesSku.getFisico_o_virtual());

				if(boletaDetallesSku.getEs_marca_liverpool() == null)
					statement.setNull(22, java.sql.Types.VARCHAR);
				else
					statement.setString(22, boletaDetallesSku.getEs_marca_liverpool());

				if(boletaDetallesSku.getIndicador_tipo_precio() == null)
					statement.setNull(23, java.sql.Types.VARCHAR);
				else
					statement.setString(23, boletaDetallesSku.getIndicador_tipo_precio());

				if(boletaDetallesSku.getDescripcion_descuento_sku() == null)
					statement.setNull(24, java.sql.Types.VARCHAR);
				else
					statement.setString(24, boletaDetallesSku.getDescripcion_descuento_sku());

				if(boletaDetallesSku.getFecha_promesa_de_entrega_inicial() == null)
					statement.setNull(25, java.sql.Types.DATE);
				else
					statement.setString(25, boletaDetallesSku.getFecha_promesa_de_entrega_inicial());

				if(boletaDetallesSku.getFecha_promesa_de_entrega_fin() == null)
					statement.setNull(26, java.sql.Types.DATE);
				else
					statement.setString(26, boletaDetallesSku.getFecha_promesa_de_entrega_fin());

				if(boletaDetallesSku.getSeller_marketplace() == null)
					statement.setNull(27, java.sql.Types.VARCHAR);
				else
					statement.setString(27, boletaDetallesSku.getSeller_marketplace());

				if(boletaDetallesSku.getError_id_sku() == null)
					statement.setNull(28, java.sql.Types.VARCHAR);
				else
					statement.setString(28, boletaDetallesSku.getError_id_sku());

				if(boletaDetallesSku.getError_id_seccion() == null)
					statement.setNull(29, java.sql.Types.VARCHAR);
				else
					statement.setString(29, boletaDetallesSku.getError_id_seccion());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);
				banderaEjecucionCorrecta = true;

			}

		} catch(SQLException sqlex){
			LOG.error("Error al intentar query Boleta Detalles Sku en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Boleta Detalles Sku:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entrando a Finally de Almacena Datos Boleta Detalles Sku");

			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos Boleta Detalles Sku:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Boleta Detalles Sku:" + es.getMessage(), es);
			} try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos Boleta Detalles Sku:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Detalles Sku:" + en.getMessage(), en);
			}
			//LOG.info("Salida de Finally - Almacena Datos Boleta Detalles Sku");
			if(banderaEjecucionCorrecta && boletaDetallesSku!=null){
				c.output(boletaDetallesSkuSuccessTag, boletaDetallesSku);
			}else{

				if(boletaDetallesSku != null){
					c.output(boletaDetallesSkuErrorTag, "{\"boleta_detalles_sku\": " + mapper.writeValueAsString(boletaDetallesSku) + "}");
				}

			}

		}
		LOG.info("Finalizando process en Almacena Datos Boleta Detalles Sku");
	}

}