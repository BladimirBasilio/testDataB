package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
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

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallePagos;

import javax.sql.DataSource;
import java.sql.*;


public class AlmacenaDatosBoletaDetallePagosJDBCProcess extends DoFn<BoletaDetallePagos, String> {

	private static final long serialVersionUID = 4452377857922037430L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetallePagosJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertBoletaDetallePagos;
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
	private final String stepName = "AlmacenaDatosBoletaDetallePagosJDBCProcess";


	public AlmacenaDatosBoletaDetallePagosJDBCProcess(String jdbcUrl,
													  String jdbcUsuario,
													  String jdbcPassword,
													  String consulInsertBoletaDetallePagos,
													  String instanceNameSM,
													  Integer connectionPoolMaxSize,
													  Integer connectionPoolMinIdle,
													  Integer connectionPoolTimeout,
													  Integer connectionPoolIdleTimeout,
													  Integer connectionPoolMaxLifetime
	) {
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consulInsertBoletaDetallePagos = consulInsertBoletaDetallePagos;
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
	public void processElement(DoFn<BoletaDetallePagos, String>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Boleta Detalle Pagos");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		BoletaDetallePagos boletaDetallePagos = c.element();

		try {

			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." + conn.toString());

			statement = conn.prepareStatement(this.consulInsertBoletaDetallePagos);
			//LOG.info("Creando el statement." + statement.toString());

			if (boletaDetallePagos != null) {

				statement.setInt(1, boletaDetallePagos.getId_linea_detalle_pagos());
				statement.setString(2, boletaDetallePagos.getId_terminal_pos_boleta_cabeceras());
				statement.setString(3, boletaDetallePagos.getNumero_boleta_boleta_cabeceras());
				statement.setDate(4, Date.valueOf(boletaDetallePagos.getFecha_fin_transaccion_boleta_cabeceras()));
				statement.setTime(5, Time.valueOf(boletaDetallePagos.getHora_fin_transaccion_boleta_cabeceras()));
				statement.setString(6, boletaDetallePagos.getId_tienda_origen_boleta_cabeceras());
				statement.setString(7, boletaDetallePagos.getId_tienda_reconocimiento_boleta_cabeceras());
				statement.setInt(8, boletaDetallePagos.getId_medios_de_pago());

				if(boletaDetallePagos.getMonto_monedero() == null)
					statement.setNull(9, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(9, boletaDetallePagos.getMonto_monedero());

				if(boletaDetallePagos.getId_cliente_tarjeta() == null)
					statement.setNull(10, java.sql.Types.INTEGER);
				else
					statement.setInt(10, boletaDetallePagos.getId_cliente_tarjeta());

				if(boletaDetallePagos.getNumero_autorizacion() == null)
					statement.setNull(11, java.sql.Types.VARCHAR);
				else
					statement.setString(11, boletaDetallePagos.getNumero_autorizacion());

				if(boletaDetallePagos.getNumero_orden_soms() == null)
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, boletaDetallePagos.getNumero_orden_soms());

				if(boletaDetallePagos.getTotal_pagado_otromedio_o_cambio_en_efectivo() == null)
					statement.setNull(13, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(13, boletaDetallePagos.getTotal_pagado_otromedio_o_cambio_en_efectivo());


				if(boletaDetallePagos.getPlan_de_credito() == null)
					statement.setNull(14, java.sql.Types.VARCHAR);
				else
					statement.setString(14, boletaDetallePagos.getPlan_de_credito());

				if(boletaDetallePagos.getTotal_pagado_efectivo() == null)
					statement.setNull(15, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(15, boletaDetallePagos.getTotal_pagado_efectivo());

				if(boletaDetallePagos.getHash_tarjeta_externa() == null)
					statement.setNull(16, java.sql.Types.VARCHAR);
				else
					statement.setString(16, boletaDetallePagos.getHash_tarjeta_externa());

				if(boletaDetallePagos.getNumero_monedero() == null)
					statement.setNull(17, java.sql.Types.VARCHAR);
				else
					statement.setString(17, boletaDetallePagos.getNumero_monedero());

				if(boletaDetallePagos.getMonto_redimido_monedero() == null)
					statement.setNull(18, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(18, boletaDetallePagos.getMonto_redimido_monedero());

				if(boletaDetallePagos.getSaldo_anterior_monedero() == null)
					statement.setNull(19, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(19, boletaDetallePagos.getSaldo_anterior_monedero());

				if(boletaDetallePagos.getMonto_obtenido_monedero() == null)
					statement.setNull(20, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(20, boletaDetallePagos.getMonto_obtenido_monedero());

				if(boletaDetallePagos.getSaldo_actual_monedero() == null)
					statement.setNull(21, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(21, boletaDetallePagos.getSaldo_actual_monedero());

				if(boletaDetallePagos.getNumero_de_cupon() == null)
					statement.setNull(22, java.sql.Types.VARCHAR);
				else
					statement.setString(22, boletaDetallePagos.getNumero_de_cupon());

				if(boletaDetallePagos.getId_modo_ingreso() == null)
					statement.setNull(23, java.sql.Types.INTEGER);
				else
					statement.setInt(23, boletaDetallePagos.getId_modo_ingreso());

				// scope changes
				if(boletaDetallePagos.getBanda_cheque() == null)
					statement.setNull(24, java.sql.Types.VARCHAR);
				else
					statement.setString(24, boletaDetallePagos.getBanda_cheque());

				if(boletaDetallePagos.getProducto_bancario() == null)
					statement.setNull(25, java.sql.Types.VARCHAR);
				else
					statement.setString(25, boletaDetallePagos.getProducto_bancario());

				if(boletaDetallePagos.getDescripcion_formapago() == null)
					statement.setNull(26, java.sql.Types.VARCHAR);
				else
					statement.setString(26, boletaDetallePagos.getDescripcion_formapago());

				if(boletaDetallePagos.getNombrecliente_tarjeta() == null)
					statement.setNull(27, java.sql.Types.VARCHAR);
				else
					statement.setString(27, boletaDetallePagos.getNombrecliente_tarjeta());

				if(boletaDetallePagos.getMonto_moneda_extranjera() == null)
					statement.setNull(28, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(28, boletaDetallePagos.getMonto_moneda_extranjera());

				if(boletaDetallePagos.getFolio_brightstar() == null)
					statement.setNull(29, java.sql.Types.VARCHAR);
				else
					statement.setString(29, boletaDetallePagos.getFolio_brightstar());

				if(boletaDetallePagos.getTelefono_codi() == null)
					statement.setNull(30, java.sql.Types.VARCHAR);
				else
					statement.setString(30, boletaDetallePagos.getTelefono_codi());

				if(boletaDetallePagos.getId_codigo_cancelacion() == null)
					statement.setNull(31, java.sql.Types.INTEGER);
				else
					statement.setInt(31, boletaDetallePagos.getId_codigo_cancelacion());

				if(boletaDetallePagos.getFolio_promass() == null)
					statement.setNull(32, java.sql.Types.VARCHAR);
				else
					statement.setString(32, boletaDetallePagos.getFolio_promass());

				if(boletaDetallePagos.getMascara_tarjeta() == null)
					statement.setNull(33, java.sql.Types.VARCHAR);
				else
					statement.setString(33, boletaDetallePagos.getMascara_tarjeta());

				//LOG.info("Ready in SetParameters in Statement");

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					int valorAlmacenaDatosDetallePagos = resultSet.getInt(1);
					//LOG.info("valorAlmacenaDatosDetallePagos {}",valorAlmacenaDatosDetallePagos );
				}

			}


		} catch (SQLException sqlex) {
			LOG.error("Error al intentar query Boleta Detalle Pagos en la base de datos:" + sqlex.getMessage(), sqlex);

			//LOG.info("Datos Boleta Detalle Pagos : " + boletaDetallePagos.toString());

			throw new Exception(sqlex.getMessage());
		} catch (Exception e) {
			LOG.error("Error al procesar Boleta Detalle Pagos:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entrando a Finally de Almacena Datos Boleta Detalle Pagos");

			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos Boleta Detalle Pagos:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Boleta Detalle Pagos:" + es.getMessage(), es);
			}
			try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos Boleta Detalle Pagos:");
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Detalle Pagos:" + en.getMessage(), en);
			}
			//LOG.info("Salida de Finally - Almacena Datos Boleta Detalle Pagos");

			if(boletaDetallePagos != null){
				c.output("{\"boleta_detalle_pagos\": " + mapper.writeValueAsString(boletaDetallePagos) + "}");
			}

		}
		LOG.info("Finalizando process en Almacena Datos Boleta Detalle Pagos");
	}

}

