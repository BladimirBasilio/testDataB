package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesMonedero;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AlmacenaDatosBoletaDetallesMonederoJDBCProcess  extends DoFn<BoletaDetallesMonedero, String>    {

	/**
	 *
	 */
	private static final long serialVersionUID = -4546728756912487998L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetallesMonederoJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertBoletaDetallesMonedero;
	private DataSource dataSource;

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
	private final String stepName = "AlmacenaDatosBoletaDetallesMonederoJDBCProcess";



	public AlmacenaDatosBoletaDetallesMonederoJDBCProcess(String jdbcUrl,
														  String jdbcUsuario,
														  String jdbcPassword,
														  String consulInsertBoletaDetallesMonedero,
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
		this.consulInsertBoletaDetallesMonedero = consulInsertBoletaDetallesMonedero;
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
	public void processElement(DoFn<BoletaDetallesMonedero, String>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Boleta Detalles Monedero");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		BoletaDetallesMonedero boletaDetallesMonedero = c.element();

		try{

			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." +  conn.toString());


			statement = conn.prepareStatement(this.consulInsertBoletaDetallesMonedero);
			//LOG.info("Creando el statement." + statement.toString());

			if(boletaDetallesMonedero!=null) {

				statement.setString(1, boletaDetallesMonedero.getId_terminal_pos());
				statement.setString(2, boletaDetallesMonedero.getNumero_boleta());
				statement.setDate(3, Date.valueOf(boletaDetallesMonedero.getFecha_fin_transaccion()));
				statement.setTime(4, Time.valueOf(boletaDetallesMonedero.getHora_fin_transaccion()));
				statement.setString(5, boletaDetallesMonedero.getId_tienda_origen());
				statement.setString(6, boletaDetallesMonedero.getId_tienda_reconocimiento());
				statement.setString(7, boletaDetallesMonedero.getNumero_monedero());
				if(boletaDetallesMonedero.getSaldo_anterior_monedero() == null)
					statement.setNull(8, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(8, boletaDetallesMonedero.getSaldo_anterior_monedero());

				if(boletaDetallesMonedero.getMonto_monedero() == null)
					statement.setNull(9, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(9, boletaDetallesMonedero.getMonto_monedero());

				if(boletaDetallesMonedero.getMonto_obtenido_monedero() == null)
					statement.setNull(10, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(10, boletaDetallesMonedero.getMonto_obtenido_monedero());

				if(boletaDetallesMonedero.getSaldo_actual_monedero() == null)
					statement.setNull(11, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(11, boletaDetallesMonedero.getSaldo_actual_monedero());

				if(boletaDetallesMonedero.getMonto_utilizado() == null)
					statement.setNull(12, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(12, boletaDetallesMonedero.getMonto_utilizado());


				//LOG.info("Ready in SetParameters in Statement");

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					int valorAlmacenaDatosDetalleMonedero = resultSet.getInt(1);
					//LOG.info("valorAlmacenaDatosDetalleMonedero{}",valorAlmacenaDatosDetalleMonedero );
				}
			}


		} catch(SQLException sqlex){
			LOG.error("Error al intentar query Boleta Detalles Monedero en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Boleta Detalles Monedero:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entrando a Finally de Almacena Datos Boleta Detalles Monedero");

			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos Boleta Detalles Monedero:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Boleta Detalles Monedero:" + es.getMessage(), es);
			} try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos Boleta Detalles Monedero:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Detalles Monedero:" + en.getMessage(), en);
			}
			//LOG.info("Salida de Finally - Almacena Datos Boleta Detalles Monedero");

			if(boletaDetallesMonedero != null){
				c.output("{\"boleta_detalles_monedero\": " + mapper.writeValueAsString(boletaDetallesMonedero) + "}");
			}

		}
		LOG.info("Finalizando process en Almacena Datos Boleta Detalles Monedero");
	}

}