package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.option.RegistraPedidoOptions;
import org.apache.beam.sdk.transforms.DoFn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.CargaConfiguracionesPipeline;
import javax.sql.DataSource;

public class ConvierteJsonAPojoPedidoProcess extends DoFn<String, RegistraPedido>  {


	private static final long serialVersionUID = 1462827258689031685L;
	private static final Logger LOG = LoggerFactory.getLogger(ConvierteJsonAPojoPedidoProcess.class);
	private ObjectMapper mapper;
	private JavaTimeModule javaTimeModule;
	private DataSource dataSource;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private final String stepName = "RegistraPedidosPipeline";

	public ConvierteJsonAPojoPedidoProcess(
			String jdbcUrl,
			String jdbcUsuario,
			String jdbcPassword,
			String instanceNameSM,
			String applicationName,
			Integer connectionPoolMaxSize,
			Integer connectionPoolMinIdle,
			Integer connectionPoolTimeout,
			Integer connectionPoolIdleTimeout,
			Integer connectionPoolMaxLifetime
	){
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
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

		mapper = new ObjectMapper();
		javaTimeModule = new JavaTimeModule();
		
		javaTimeModule.addSerializer(LocalTime.class, 
				new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
		javaTimeModule.addDeserializer(LocalDate.class, 
				new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		mapper.registerModule(javaTimeModule);

		//Prueba de inicializacion de pool
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			if (conn.isValid(10)){
				LOG.info("Pool Listo");
			}
		}catch (Exception e){
			LOG.warn("Pool no preparado para escuchar mensajes");
		}finally {
			try {
				if (conn != null) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Cliente Pedido:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Convierte Json A Pojo Pedido:" + en.getMessage(), en);
			}
		}
			//MyOptions ops = c.getPipelineOptions().as(MyOptions.class);
	}
	
	@ProcessElement
	public void processElement(DoFn<String, RegistraPedido>.ProcessContext c) throws Exception {

		//RegistraPedidoOptions options = c.getPipelineOptions().as(RegistraPedidoOptions.class);
		try {

			RegistraPedido registraPedido = mapper.readValue(c.element(), RegistraPedido.class);
			if(registraPedido!=null){
			c.output(registraPedido);}
			else{LOG.info("Sin datos para registraPedido.");}
		
		} catch(Exception e)
		{
			LOG.error("Error al intentar convertir Json to Pojo:" + e.getMessage(), e);
		}
	}
	
}
