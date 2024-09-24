package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleCupones;

import javax.sql.DataSource;


public class AlmacenaDatosBoletaDetalleCuponesJDBCProcess   extends DoFn<BoletaDetalleCupones, String>    {

	private static final long serialVersionUID = 2592029816946227808L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetalleCuponesJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertBoletaDetalleCupones;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private String instanceNameSM;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosBoletaDetalleCupones";
	private String applicationName;
	private ObjectMapper mapper;
	private JavaTimeModule javaTimeModule;

	public AlmacenaDatosBoletaDetalleCuponesJDBCProcess(String jdbcUrl,
														String jdbcUsuario,
														String jdbcPassword,
														String consulInsertBoletaDetalleCupones,
														String instanceNameSM,
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
		this.consulInsertBoletaDetalleCupones = consulInsertBoletaDetalleCupones;
		this.instanceNameSM = instanceNameSM;
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
	public void processElement(DoFn<BoletaDetalleCupones, String>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Boleta Detalle Cupones ");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		BoletaDetalleCupones boletaDetalleCupones = c.element();

		try {

			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." + conn.toString());

			statement = conn.prepareStatement(this.consulInsertBoletaDetalleCupones);
			//LOG.info("Creando el statement." + statement.toString());

			if(boletaDetalleCupones != null){

				statement.setString(1, boletaDetalleCupones.getId_terminal_pos());
				statement.setString(2, boletaDetalleCupones.getNumero_boleta());
				statement.setDate(3, Date.valueOf(boletaDetalleCupones.getFecha_fin_transaccion()));
				statement.setTime(4, Time.valueOf(boletaDetalleCupones.getHora_fin_transaccion()));
				statement.setString(5, boletaDetalleCupones.getId_tienda_origen());
				statement.setString(6, boletaDetalleCupones.getId_tienda_reconocimiento());
				statement.setString(7, boletaDetalleCupones.getCodigo_cupon());

				if(boletaDetalleCupones.getEs_redimido() == null)
					statement.setNull(8, java.sql.Types.VARCHAR);
				else
					statement.setString(8, boletaDetalleCupones.getEs_redimido());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);


				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int valorAlmacenaDatosBoletaDetalleCupones = resultSet.getInt(1);
						//LOG.info("valorAlmacenaDatosBoletaDetalleCupones {}",valorAlmacenaDatosBoletaDetalleCupones );

					}
				}

			}

		} catch(Exception e) {
			LOG.error("Error al procesar Boleta Detalle Cupones :" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entre al Finally de Almacena Datos Boleta Detalle Cupones ");
			try {
				if (resultSet != null) {
					resultSet.close();
					//LOG.info("Cerrando el resultset en Almacena Datos Boleta Detalle Cupones :");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Boleta Detalle Cupones :" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos Boleta Detalle Cupones :");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Boleta Detalle Cupones :" + es.getMessage(), es);
			} try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos Boleta Detalle Cupones :" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Detalle Cupones :" + en.getMessage(), en);
			}
			//LOG.info("Sali al Finally de Almacena Datos Boleta Detalle Cupones ");

			if(boletaDetalleCupones != null){
				c.output("{\"boleta_detalle_cupones\": " + mapper.writeValueAsString(boletaDetalleCupones) + "}");
			}
		}
		LOG.info("Finalizando process en Almacena Datos Boleta Detalle Cupones ");

	}

}