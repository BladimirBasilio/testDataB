package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleAbonosTarjeta;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AlmacenaDatosBoletaDetalleAbonosTarjetaJDBCProcess extends DoFn<BoletaDetalleAbonosTarjeta, String>    {

	private static final long serialVersionUID = 7629862296813019697L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetalleAbonosTarjetaJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertBoletaDetalleAbonosTarjeta;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private String instanceNameSM;
	private String applicationName;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosBoletaDetalleAbonosTarjeta";

	private ObjectMapper mapper;
	private JavaTimeModule javaTimeModule;

	public AlmacenaDatosBoletaDetalleAbonosTarjetaJDBCProcess(String jdbcUrl,
															  String jdbcUsuario,
															  String jdbcPassword,
															  String consulInsertBoletaDetalleAbonosTarjeta,
															  Integer connectionPoolMaxSize,
															  Integer connectionPoolMinIdle,
															  Integer connectionPoolTimeout,
															  Integer connectionPoolIdleTimeout,
															  Integer connectionPoolMaxLifetime,
															  String instanceNameSM
	)
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consulInsertBoletaDetalleAbonosTarjeta = consulInsertBoletaDetalleAbonosTarjeta;
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
	public void processElement(ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Boleta Detalle Abonos Tarjeta");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		BoletaDetalleAbonosTarjeta  boletaDetalleAbonosTarjeta = c.element();

		try {

			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consulInsertBoletaDetalleAbonosTarjeta);
			//LOG.info("Creando el statement." + statement.toString());

			if(boletaDetalleAbonosTarjeta != null){

				statement.setInt(1, boletaDetalleAbonosTarjeta.getId_linea_detalle());
				statement.setString(2, boletaDetalleAbonosTarjeta.getId_terminal_pos_boleta_cabeceras());
				statement.setString(3, boletaDetalleAbonosTarjeta.getNumero_boleta_boleta_cabeceras());;
				statement.setDate(4, Date.valueOf(boletaDetalleAbonosTarjeta.getFecha_fin_transaccion_boleta_cabeceras()));
				statement.setTime(5, Time.valueOf(boletaDetalleAbonosTarjeta.getHora_fin_transaccion_boleta_cabeceras()));
				statement.setString(6, boletaDetalleAbonosTarjeta.getId_tienda_origen_boleta_cabeceras());
				statement.setString(7, boletaDetalleAbonosTarjeta.getId_tienda_reconocimiento_boleta_cabeceras());

				if(boletaDetalleAbonosTarjeta.getNumero_tarjeta() == null)
					statement.setNull(8, java.sql.Types.VARCHAR);
				else
					statement.setString(8, boletaDetalleAbonosTarjeta.getNumero_tarjeta());


				if(boletaDetalleAbonosTarjeta.getTotal_pagado() == null)
					statement.setNull(9, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(9, boletaDetalleAbonosTarjeta.getTotal_pagado());

				if(boletaDetalleAbonosTarjeta.getId_cliente_tarjeta() == null)
					statement.setNull(10, java.sql.Types.INTEGER);
				else
					statement.setInt(10, boletaDetalleAbonosTarjeta.getId_cliente_tarjeta());

				if(boletaDetalleAbonosTarjeta.getAutorizacion_de_abono() == null)
					statement.setNull(11, java.sql.Types.VARCHAR);
				else
					statement.setString(11, boletaDetalleAbonosTarjeta.getAutorizacion_de_abono());

				if(boletaDetalleAbonosTarjeta.getCuenta_de_abono() == null)
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, boletaDetalleAbonosTarjeta.getCuenta_de_abono());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int valorAlmacenaDatosBoletaDetalleAbonoTarjeta = resultSet.getInt(1);
						//LOG.info("valorAlmacenaDatosBoletaDetalleAbonoTarjeta {}",valorAlmacenaDatosBoletaDetalleAbonoTarjeta );

					}
				}

			}

		} catch(Exception e) {
			LOG.error("Error al procesar Boleta Detalle Abonos Tarjeta:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entre al Finally de Almacena Datos Boleta Detalle Abonos Tarjeta");
			try {
				if (resultSet != null) {
					resultSet.close();
					//LOG.info("Cerrando el resultset en Almacena Datos Boleta Detalle Abonos Tarjeta:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Boleta Detalle Abonos Tarjeta:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos Boleta Detalle Abonos Tarjeta:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Boleta Detalle Abonos Tarjeta:" + es.getMessage(), es);
			} try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos Boleta Detalle Abonos Tarjeta:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Detalle Abonos Tarjeta:" + en.getMessage(), en);
			}
			//LOG.info("Sali al Finally de Almacena Datos Boleta Detalle Abonos Tarjeta");

			if(boletaDetalleAbonosTarjeta != null){
				c.output("{\"boleta_detalle_abonos_tarjeta\": " + mapper.writeValueAsString(boletaDetalleAbonosTarjeta) + "}");
			}

		}
		LOG.info("Finalizando process en Almacena Datos Boleta Detalle Abonos Tarjeta");

	}

}