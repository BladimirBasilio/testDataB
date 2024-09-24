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
import org.apache.beam.sdk.util.FluentBackoff;
import org.apache.beam.sdk.values.TupleTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaCabecera;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;

import javax.sql.DataSource;

public class AlmacenaDatosBoletaCabeceraJDBCProcess extends DoFn<RegistraBoleta, RegistraBoleta>    {


	private static final long serialVersionUID = 2158817508368979922L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaCabeceraJDBCProcess.class);
	private static final String tabla_nombre_descripcion = "Boleta Cabecera";
	private Connection conn;
	private PreparedStatement statement;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertBoletaCabecera;
	private String instanceNameSM;
	private DataSource dataSource;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private final TupleTag<RegistraBoleta> boletaCabeceraSuccessTag;
	private final TupleTag<String> boletaCabeceraErrorTag;
	private final String stepName = "AlmacenaDatosBoletaCabecera";

	private JavaTimeModule javaTimeModule;
	private ObjectMapper mapper;

	public AlmacenaDatosBoletaCabeceraJDBCProcess(String jdbcUrl,
												  String jdbcUsuario,
												  String jdbcPassword,
												  String instanceNameSM,
												  String consultaInsertBoletaCabecera,
												  Integer connectionPoolMaxSize,
												  Integer connectionPoolMinIdle,
												  Integer connectionPoolTimeout,
												  Integer connectionPoolIdleTimeout,
												  Integer connectionPoolMaxLifetime,
												  TupleTag<RegistraBoleta> boletaCabeceraSuccessTag,
												  TupleTag<String> boletaCabeceraErrorTag
	)
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.instanceNameSM = instanceNameSM;
		this.consultaInsertBoletaCabecera = consultaInsertBoletaCabecera;
		this.connectionPoolMaxSize = connectionPoolMaxSize;
		this.connectionPoolMinIdle = connectionPoolMinIdle;
		this.connectionPoolTimeout = connectionPoolTimeout;
		this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
		this.boletaCabeceraSuccessTag = boletaCabeceraSuccessTag;
		this.boletaCabeceraErrorTag = boletaCabeceraErrorTag;
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
	public void processElement(DoFn<RegistraBoleta, RegistraBoleta>.ProcessContext c) throws Exception {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Boolean banderaEjecucionCorrecta = false;

		RegistraBoleta registraBoleta = c.element();
		LOG.info("Iniciando process en Almacena Datos Boleta Cabecera: BOLETA: " + registraBoleta.toString());
		try {

			conn = dataSource.getConnection();
			//LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertBoletaCabecera);
			//LOG.info("Creando el statement." + statement.toString());


			if(registraBoleta != null && registraBoleta.getBoleta_cabeceras() != null ){

				BoletaCabecera boletaCabecera = registraBoleta.getBoleta_cabeceras();

				//key fields
				statement.setString(1, boletaCabecera.getId_terminal_pos());
				statement.setString(2, boletaCabecera.getNumero_boleta());
				statement.setDate(3, Date.valueOf(boletaCabecera.getFecha_fin_transaccion()));
				statement.setTime(4, Time.valueOf(boletaCabecera.getHora_fin_transaccion()));
				statement.setString(5, boletaCabecera.getId_tienda_origen());
				statement.setString(6, boletaCabecera.getId_tienda_reconocimiento());
				statement.setInt(7, boletaCabecera.getId_tipo_transaccion());

				if(boletaCabecera.getId_canal_de_venta() == null)
					statement.setNull(8, java.sql.Types.INTEGER);
				else
					statement.setInt(8, boletaCabecera.getId_canal_de_venta());

				if(boletaCabecera.getId_venta_catalogo_extendido_y_otro() == null)
					statement.setNull(9, java.sql.Types.INTEGER);
				else
					statement.setInt(9, boletaCabecera.getId_venta_catalogo_extendido_y_otro());

				if(boletaCabecera.getId_vendedor() == null)
					statement.setNull(10, java.sql.Types.INTEGER);
				else
					statement.setInt(10, boletaCabecera.getId_vendedor());

				if(boletaCabecera.getId_tienda_original() == null)
					statement.setNull(11, java.sql.Types.INTEGER);
				else
					statement.setInt(11, boletaCabecera.getId_tienda_original());

				if(boletaCabecera.getId_tipo_de_evento() == null)
					statement.setNull(12, java.sql.Types.INTEGER);
				else
					statement.setInt(12, boletaCabecera.getId_tipo_de_evento());

				if(boletaCabecera.getId_cliente() == null)
					statement.setNull(13, java.sql.Types.INTEGER);
				else
					statement.setInt(13, boletaCabecera.getId_cliente());

				if(boletaCabecera.getId_terminal_pos_cancelacion() == null)
					statement.setNull(14, java.sql.Types.INTEGER);
				else
					statement.setInt(14, boletaCabecera.getId_terminal_pos_cancelacion());

				if(boletaCabecera.getCuenta_empleado() == null)
					statement.setNull(15, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(15, boletaCabecera.getCuenta_empleado());

				if(boletaCabecera.getMonto_emitido() == null)
					statement.setNull(16, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(16, boletaCabecera.getMonto_emitido());

				if(boletaCabecera.getCodigo_facturacion() == null)
					statement.setNull(17, java.sql.Types.VARCHAR);
				else
					statement.setString(17, boletaCabecera.getCodigo_facturacion());

				if(boletaCabecera.getReferencia_folio_agencia_de_viajes() == null)
					statement.setNull(18, java.sql.Types.VARCHAR);
				else
					statement.setString(18, boletaCabecera.getReferencia_folio_agencia_de_viajes());

				if(boletaCabecera.getNumero_evento() == null)
					statement.setNull(19, java.sql.Types.VARCHAR);
				else
					statement.setString(19, boletaCabecera.getNumero_evento());

				if(boletaCabecera.getNumero_boleta_cancelacion() == null)
					statement.setNull(20, java.sql.Types.VARCHAR);
				else
					statement.setString(20, boletaCabecera.getNumero_boleta_cancelacion());


				if(boletaCabecera.getMonto_boleta() == null)
					statement.setNull(21, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(21, boletaCabecera.getMonto_boleta());

				if(boletaCabecera.getTotal_cancelacion() == null)
					statement.setNull(22, java.sql.Types.NUMERIC);
				else
					statement.setBigDecimal(22, boletaCabecera.getTotal_cancelacion());

				if(boletaCabecera.getId_numero_centro_de_servicio() == null)
					statement.setNull(23, java.sql.Types.INTEGER);
				else
					statement.setInt(23, boletaCabecera.getId_numero_centro_de_servicio());

				if(boletaCabecera.getNumero_paqueteria() == null)
					statement.setNull(24, java.sql.Types.VARCHAR);
				else
					statement.setString(24, boletaCabecera.getNumero_paqueteria());

				if(boletaCabecera.getLeyenda_facturacion() == null)
					statement.setNull(25, java.sql.Types.VARCHAR);
				else
					statement.setString(25, boletaCabecera.getLeyenda_facturacion());

				if(boletaCabecera.getCodigo_de_barras() == null)
					statement.setNull(26, java.sql.Types.VARCHAR);
				else
					statement.setString(26, boletaCabecera.getCodigo_de_barras());

				if(boletaCabecera.getFecha_nacimiento_garantia_extendida() == null)
					statement.setNull(27, java.sql.Types.DATE);
				else
					statement.setDate(27, Date.valueOf(boletaCabecera.getFecha_nacimiento_garantia_extendida()));

				if(boletaCabecera.getFecha_garantia_extemporanea() == null)
					statement.setNull(28, java.sql.Types.DATE);
				else
					statement.setDate(28, Date.valueOf(boletaCabecera.getFecha_garantia_extemporanea()));


				if(boletaCabecera.getNumero_indicador_marketplace() == null)
					statement.setNull(29, java.sql.Types.VARCHAR);
				else
					statement.setString(29, boletaCabecera.getNumero_indicador_marketplace());

				if(boletaCabecera.getLeyenda_tentativa() == null)
					statement.setNull(30, java.sql.Types.VARCHAR);
				else
					statement.setString(30, boletaCabecera.getLeyenda_tentativa());

				if(boletaCabecera.getId_tipo_de_descuento_al_total() == null)
					statement.setNull(31, java.sql.Types.INTEGER);
				else
					statement.setInt(31, boletaCabecera.getId_tipo_de_descuento_al_total());

				if(boletaCabecera.getClave_homologada() == null)
					statement.setNull(32, java.sql.Types.VARCHAR);
				else
					statement.setString(32, boletaCabecera.getClave_homologada());

				if(boletaCabecera.getId_direccion_fiscal() == null)
					statement.setNull(33, java.sql.Types.INTEGER);
				else
					statement.setInt(33, boletaCabecera.getId_direccion_fiscal());

				if(boletaCabecera.getId_atg() == null)
					statement.setNull(34, java.sql.Types.VARCHAR);
				else
					statement.setString(34, boletaCabecera.getId_atg());

				if(boletaCabecera.getId_mdm() == null)
					statement.setNull(35, java.sql.Types.VARCHAR);
				else
					statement.setString(35, boletaCabecera.getId_mdm());

				if(boletaCabecera.getId_tienda_origen_gcp() == null)
					statement.setNull(36, java.sql.Types.INTEGER);
				else
					statement.setInt(36, boletaCabecera.getId_tienda_origen_gcp());

				if(boletaCabecera.getId_terminal_pos_gcp() == null)
					statement.setNull(37, java.sql.Types.INTEGER);
				else
					statement.setInt(37, boletaCabecera.getId_terminal_pos_gcp());

				if(boletaCabecera.getId_vendedor_original() == null)
					statement.setNull(38, java.sql.Types.INTEGER);
				else
					statement.setInt(38, boletaCabecera.getId_vendedor_original());


				if(boletaCabecera.getOriginal_terminal() == null)
					statement.setNull(39, java.sql.Types.VARCHAR);
				else
					statement.setString(39, boletaCabecera.getOriginal_terminal());

				if(boletaCabecera.getFecha_original_compra() == null)
					statement.setNull(40, java.sql.Types.DATE);
				else
					statement.setDate(40, Date.valueOf(boletaCabecera.getFecha_original_compra()));

				if(boletaCabecera.getNumero_boleta_devolucion() == null)
					statement.setNull(41, java.sql.Types.VARCHAR);
				else
					statement.setString(41, ("0000" + boletaCabecera.getNumero_boleta_devolucion()).substring(boletaCabecera.getNumero_boleta_devolucion().length()));

				if(boletaCabecera.getNombre_vendedor() == null)
					statement.setNull(42, java.sql.Types.VARCHAR);
				else
					statement.setString(42, boletaCabecera.getNombre_vendedor());

				if(boletaCabecera.getTipo_entrega() == null)
					statement.setNull(43, java.sql.Types.VARCHAR);
				else
					statement.setString(43, boletaCabecera.getTipo_entrega());

				if(boletaCabecera.getSe_imprimio_ticket() == null)
					statement.setNull(44, java.sql.Types.VARCHAR);
				else
					statement.setString(44, boletaCabecera.getSe_imprimio_ticket());

				if(boletaCabecera.getConfirmacion_tiempo_aire() == null)
					statement.setNull(45, java.sql.Types.VARCHAR);
				else
					statement.setString(45, boletaCabecera.getConfirmacion_tiempo_aire());

				if(boletaCabecera.getEmail_donde_envio_ticket_electronico() == null)
					statement.setNull(46, java.sql.Types.VARCHAR);
				else
					statement.setString(46, boletaCabecera.getEmail_donde_envio_ticket_electronico());

				if(boletaCabecera.getError_id_cliente() == null)
					statement.setNull(47, java.sql.Types.INTEGER);
				else
					statement.setInt(47, boletaCabecera.getError_id_cliente());

				if(boletaCabecera.getIndicador_de_borrado() == null)
					statement.setNull(48, java.sql.Types.VARCHAR);
				else
					statement.setString(48, boletaCabecera.getIndicador_de_borrado());

				if(boletaCabecera.getId_atg_del_cliente() == null)
					statement.setNull(49, java.sql.Types.VARCHAR);
				else
					statement.setString(49, boletaCabecera.getId_atg_del_cliente());

				if(boletaCabecera.getId_mdm_del_cliente() == null)
					statement.setNull(50, java.sql.Types.VARCHAR);
				else
					statement.setString(50, boletaCabecera.getId_mdm_del_cliente());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int valorAlmacenaDatosBoletaCabecera = resultSet.getInt(1);
						//LOG.info("valorAlmacenaDatosBoletaCabecera {}",valorAlmacenaDatosBoletaCabecera );

						banderaEjecucionCorrecta = true;
						//c.output(boletaCabeceraSuccessTag, registraBoleta);

					}
				}

			}

		} catch(Exception e) {
			LOG.error("Error al procesar Boleta Cabecera:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			//LOG.info("Entre al Finally de Almacena Datos Boleta Cabecera");
			try {
				if (resultSet != null) {
					resultSet.close();
					//LOG.info("Cerrando el resultset en Almacena Datos Boleta Cabecera:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Boleta Cabecera:" + er.getMessage(), er);
			}
			try {
				if (statement != null) {
					//LOG.info("Cerrando el statement en Almacena Datos Boleta Cabecera:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Boleta Cabecera:" + es.getMessage(), es);
			} try {
				if (conn != null) {
					conn.close();
					//LOG.info("Cerrando el conn en Almacena Datos Boleta Cabecera:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Cabecera:" + en.getMessage(), en);
			}
			//LOG.info("Sali al Finally de Almacena Datos Boleta Cabecera");

			if(banderaEjecucionCorrecta){
				if(registraBoleta != null){
					c.output(boletaCabeceraSuccessTag, registraBoleta);
				}
			}else{
				if(!(registraBoleta == null)) {
					c.output(boletaCabeceraErrorTag, "{\"registra_boleta\": " + mapper.writeValueAsString(registraBoleta) + "}");
				}
			}
		}
		LOG.info("Finalizando process en Almacena Datos Boleta Cabecera");
	}

}