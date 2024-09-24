package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.util.FluentBackoff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesSkuDescuentos;

import javax.sql.DataSource;


public class AlmacenaDatosBoletaDetallesSkuDescuentosJDBCProcess   extends DoFn<BoletaDetallesSkuDescuentos, String>    {


	private static final long serialVersionUID = -7524635539494416446L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetallesSkuDescuentosJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertBoletaDetallesSkuDescuentos;
	private Properties props;
	private DataSource dataSource;


	private static FluentBackoff retryBackOff;
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
	private final String stepName = "AlmacenaDatosBoletaDetallesSkuDescuentosJDBCProcess";


	public AlmacenaDatosBoletaDetallesSkuDescuentosJDBCProcess(String jdbcUrl,
															   String jdbcUsuario,
															   String jdbcPassword,
															   String consulInsertBoletaDetallesSkuDescuentos,
															   String instanceNameSM,
															   Integer connectionPoolMaxSize,
															   Integer connectionPoolMinIdle,
															   Integer connectionPoolTimeout,
															   Integer connectionPoolIdleTimeout,
															   Integer connectionPoolMaxLifetime
	)	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consulInsertBoletaDetallesSkuDescuentos = consulInsertBoletaDetallesSkuDescuentos;
		this.instanceNameSM=instanceNameSM;
		this.connectionPoolMaxSize=connectionPoolMaxSize;
		this.connectionPoolMinIdle=connectionPoolMinIdle;
		this.connectionPoolTimeout=connectionPoolTimeout;
		this.connectionPoolIdleTimeout=connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime=connectionPoolMaxLifetime;

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
	public void processElement(DoFn<BoletaDetallesSkuDescuentos, String>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Boleta Detalles Sku Descuentos");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		BoletaDetallesSkuDescuentos boletaDetallesSkuDescuentos = c.element();
		try{
			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consulInsertBoletaDetallesSkuDescuentos);
			//LOG.info("Creando el statement." + statement.toString());

			if(boletaDetallesSkuDescuentos!=null) {
				statement.setInt(1, boletaDetallesSkuDescuentos.getId_linea_detalle_boleta_detalles_sku());
				statement.setString(2, boletaDetallesSkuDescuentos.getId_terminal_pos_boleta_detalles_sku());
				statement.setString(3, boletaDetallesSkuDescuentos.getNumero_boleta_boleta_detalles_sku());
				statement.setDate(4, Date.valueOf(boletaDetallesSkuDescuentos.getFecha_fin_transaccion_boleta_detalles_sku()));
				statement.setTime(5, Time.valueOf(boletaDetallesSkuDescuentos.getHora_fin_transaccion_boleta_detalles_sku()));
				statement.setString(6, boletaDetallesSkuDescuentos.getId_tienda_origen_boleta_detalles_sku());
				statement.setString(7, boletaDetallesSkuDescuentos.getId_tienda_reconocimiento_boleta_detalles_sku());
				statement.setInt(8, boletaDetallesSkuDescuentos.getId_descuento());

				if (boletaDetallesSkuDescuentos.getTotal_descuento_sku() == null)
					statement.setNull(9, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(9, boletaDetallesSkuDescuentos.getTotal_descuento_sku());

				if (boletaDetallesSkuDescuentos.getPorcentaje_descuento_sku() == null)
					statement.setNull(10, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(10, boletaDetallesSkuDescuentos.getPorcentaje_descuento_sku());

				if (boletaDetallesSkuDescuentos.getDescuento_sku_promocion() == null)
					statement.setNull(11, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(11, boletaDetallesSkuDescuentos.getDescuento_sku_promocion());

				if (boletaDetallesSkuDescuentos.getDescripcion_descuento() == null)
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, boletaDetallesSkuDescuentos.getDescripcion_descuento());

				if (boletaDetallesSkuDescuentos.getId_linea_detalle() == null)
					statement.setNull(13, java.sql.Types.VARCHAR);
				else
					statement.setInt(13, boletaDetallesSkuDescuentos.getId_linea_detalle());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					int valorAlmacenaDatosBoletaDetallesSku = resultSet.getInt(1);
					//LOG.info("valorAlmacenaDatosBoletaDetallesSku {}",valorAlmacenaDatosBoletaDetallesSku );
				}

			}

		} catch(SQLException sqlex){
			LOG.error("Error al intentar query Boleta Detalles Sku Descuentos en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Boleta Detalles Sku Descuentos:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entrando a Finally de Almacena Datos Boleta Detalles Sku Descuentos");

			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos Boleta Detalles Sku Descuentos:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Boleta Detalles Sku Descuentos:" + es.getMessage(), es);
			} try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos Boleta Detalles Sku Descuentos:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Detalles Sku Descuentos:" + en.getMessage(), en);
			}
			//LOG.info("Salida de Finally - Almacena Datos Boleta Detalles Sku Descuentos");
			if(boletaDetallesSkuDescuentos != null){
				c.output("{\"boleta_detalles_sku_descuentos\": " + mapper.writeValueAsString(boletaDetallesSkuDescuentos) + "}");
			}

		}
		LOG.info("Finalizando process en Almacena Datos Boleta Detalles Sku Descuentos");
	}

}