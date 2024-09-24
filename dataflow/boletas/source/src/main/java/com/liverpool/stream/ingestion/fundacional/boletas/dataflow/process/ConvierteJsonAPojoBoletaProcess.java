package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ConvierteJsonAPojoBoletaProcess extends DoFn<String, RegistraBoleta>  {


	private static final long serialVersionUID = 1462827258689031685L;
	private static final Logger LOG = LoggerFactory.getLogger(ConvierteJsonAPojoBoletaProcess.class);
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
	private final String stepName = "RegistraBoletasPipeline";



	public ConvierteJsonAPojoBoletaProcess(String jdbcUrl,
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
		try(Connection conn = dataSource.getConnection()){
			if (conn.isValid(10)){
				LOG.info("Pool Listo");
			}
		}catch (Exception e){
			LOG.warn("Pool no preparado para escuchar mensajes");
		}
    }
	
	@ProcessElement
	public void processElement(DoFn<String, RegistraBoleta>.ProcessContext c) {

		String numeroBoleta = "";
		
		try {

			RegistraBoleta registraBoleta = mapper.readValue(c.element(), RegistraBoleta.class);
			numeroBoleta = registraBoleta.getBoleta_cabeceras().getNumero_boleta();
			if(registraBoleta!=null){
				c.output(registraBoleta);
			}
		
		} catch(Exception e)
		{
			LOG.error("Error al intentar convertir Json to Pojo: Boleta:" + numeroBoleta 
					+ " Message:"+ e.getMessage(), e);
		}
	}
	
}
