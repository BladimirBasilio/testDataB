package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

import javax.sql.DataSource;

public class ConvierteJsonAPojoRegistraClienteProcess extends DoFn<String, RegistraCliente>  {


	private static final long serialVersionUID = 1462827258689031685L;
	private static final Logger LOG = LoggerFactory.getLogger(ConvierteJsonAPojoRegistraClienteProcess.class);
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
	private final String stepName = "RegistraClientesPipeline";


	public ConvierteJsonAPojoRegistraClienteProcess(String jdbcUrl,
													String jdbcUsuario,
													String jdbcPassword,
													String instanceNameSM,
													Integer connectionPoolMaxSize,
													Integer connectionPoolMinIdle,
													Integer connectionPoolTimeout,
													Integer connectionPoolIdleTimeout,
													Integer connectionPoolMaxLifetime)
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
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
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Datos Cliente Pedido:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Convierte Json A Pojo Pedido:" + en.getMessage(), en);
			}
		}
		
    }
	
	@ProcessElement
	public void processElement(DoFn<String, RegistraCliente>.ProcessContext c) throws Exception {

		try {

			RegistraCliente registraCliente = mapper.readValue(c.element(), RegistraCliente.class);
			LOG.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registraCliente));
			if(Objects.nonNull(registraCliente)){
				c.output(registraCliente);
			}else{
				LOG.info("Sin datos para RegistraCliente");
			}
		
		} catch(Exception e)
		{
			LOG.error("Error al intentar convertir Json to Pojo:" + e.getMessage(), e);
		}
	}
	
}
