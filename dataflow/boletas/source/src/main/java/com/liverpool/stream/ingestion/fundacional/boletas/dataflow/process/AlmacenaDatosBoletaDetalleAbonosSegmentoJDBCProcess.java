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
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleAbonosSegmento;

import javax.sql.DataSource;


public class AlmacenaDatosBoletaDetalleAbonosSegmentoJDBCProcess extends DoFn<BoletaDetalleAbonosSegmento, String>    {

	private static final long serialVersionUID = 7629862296813019697L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetalleAbonosSegmentoJDBCProcess.class);
	private static final String tabla_nombre_descripcion = "Boleta Detalle Abonos Segmento";
	private Connection conn;
	private PreparedStatement statement;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertBoletaDetalleAbonosSegmento;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private String instanceNameSM;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosBoletaDetalleAbonosSegmento";
	private ObjectMapper mapper;
	private JavaTimeModule javaTimeModule;
	public AlmacenaDatosBoletaDetalleAbonosSegmentoJDBCProcess(String jdbcUrl,
															   String jdbcUsuario,
															   String jdbcPassword,
															   String consulInsertBoletaDetalleAbonosSegmento,
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
		this.consulInsertBoletaDetalleAbonosSegmento = consulInsertBoletaDetalleAbonosSegmento;
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
	public void processElement(DoFn<BoletaDetalleAbonosSegmento, String>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos BoletasDetalle Abonos Segmento");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		BoletaDetalleAbonosSegmento boletaDetalleAbonosSegmento = c.element();

		try {

			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consulInsertBoletaDetalleAbonosSegmento);
			//LOG.info("Creando el statement." + statement.toString());

			if(boletaDetalleAbonosSegmento != null ){

				statement.setInt(1, boletaDetalleAbonosSegmento.getId_linea_detalle_abonos_segmento());
				statement.setString(2, boletaDetalleAbonosSegmento.getId_terminal_pos());
				statement.setString(3, boletaDetalleAbonosSegmento.getNumero_boleta());
				statement.setDate(4, Date.valueOf(boletaDetalleAbonosSegmento.getFecha_fin_transaccion()));
				statement.setTime(5, Time.valueOf(boletaDetalleAbonosSegmento.getHora_fin_transaccion()));
				statement.setString(6, boletaDetalleAbonosSegmento.getId_tienda_origen());
				statement.setString(7, boletaDetalleAbonosSegmento.getId_tienda_reconocimiento());
				statement.setInt(8, boletaDetalleAbonosSegmento.getId_segmento());


				if(boletaDetalleAbonosSegmento.getImporte_abonado_al_segmento() == null)
					statement.setNull(9, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(9, boletaDetalleAbonosSegmento.getImporte_abonado_al_segmento());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int valorBoletaDetalleAbonoSeguimiento = resultSet.getInt(1);
						//LOG.info("valorBoletaDetalleAbonoSeguimiento {}",valorBoletaDetalleAbonoSeguimiento );

					}
				}

			}

		} catch(SQLException sqlex){
			LOG.error("Error al intentar query Cliente ATG en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Boleta Detalle Abono Segmento:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entre al Finally de Almacena Datos BoletasDetalle Abonos Segmento");
			try {
				if (resultSet != null) {
					resultSet.close();
					//LOG.info("Cerrando el resultset en Almacena Datos BoletasDetalle Abonos Segmento:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos BoletasDetalle Abonos Segmento:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos BoletasDetalle Abonos Segmento:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos BoletasDetalle Abonos Segmento:" + es.getMessage(), es);
			} try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos BoletasDetalle Abonos Segmento:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos BoletasDetalle Abonos Segmento:" + en.getMessage(), en);
			}
			//LOG.info("Sali al Finally de Almacena Datos BoletasDetalle Abonos Segmento");
			if(boletaDetalleAbonosSegmento != null){
				c.output("{\"boleta_detalle_abonos_segmento\": " + mapper.writeValueAsString(boletaDetalleAbonosSegmento) +"}");
			}

		}
		LOG.info("Finalizando process en Almacena Datos BoletasDetalle Abonos Segmento");

	}

}