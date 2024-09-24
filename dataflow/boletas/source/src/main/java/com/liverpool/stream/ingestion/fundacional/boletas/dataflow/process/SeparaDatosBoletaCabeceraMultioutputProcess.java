package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.TupleTag;
import org.apache.beam.sdk.values.TupleTagList;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;

public class SeparaDatosBoletaCabeceraMultioutputProcess extends 
			PTransform<PCollection<RegistraBoleta>, PCollectionTuple> {
	private static final long serialVersionUID = -32264589815211298L;

	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertBoletaCabecera;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private String poolName;
	
	private final TupleTag<RegistraBoleta> boletaCabeceraSuccessTag =
            new TupleTag<RegistraBoleta>("boletaCabeceraSuccessTag"){};
    private final TupleTag<String> boletaCabeceraErrorTag =
            new TupleTag<String>("boletaCabeceraErrorTag"){};

        	
	public SeparaDatosBoletaCabeceraMultioutputProcess(String jdbcUrl,
			  String jdbcUsuario,
			  String jdbcPassword,
			  String consultaInsertBoletaCabecera,
		   String instanceNameSM,
		   Integer connectionPoolMaxSize,
		   Integer connectionPoolMinIdle,
		   Integer connectionPoolTimeout,
		   Integer connectionPoolIdleTimeout,
		   Integer connectionPoolMaxLifetime,
		   String poolName
			  )
	{
			this.jdbcUrl = jdbcUrl;
			this.jdbcUsuario = jdbcUsuario;
			this.jdbcPassword = jdbcPassword;
			this.consultaInsertBoletaCabecera = consultaInsertBoletaCabecera;
		this.instanceNameSM=instanceNameSM;
		this.connectionPoolMaxSize=connectionPoolMaxSize;
		this.connectionPoolMinIdle=connectionPoolMinIdle;
		this.connectionPoolTimeout=connectionPoolTimeout;
		this.connectionPoolIdleTimeout=connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime=connectionPoolMaxLifetime;
		this.poolName=poolName;
	}
	
	


    	            
	@Override
	public PCollectionTuple expand(PCollection<RegistraBoleta> input) {

		 PCollectionTuple boletaCabecera = 
					input.apply("Crea Boleta Cabecera", 
							ParDo.of(new AlmacenaDatosBoletaCabeceraJDBCProcess(
									this.jdbcUrl,
									this.jdbcUsuario,
									this.jdbcPassword,
									this.instanceNameSM,
									this.consultaInsertBoletaCabecera,
									this.connectionPoolMaxSize,
									this.connectionPoolMinIdle,
									this.connectionPoolTimeout,
									this.connectionPoolIdleTimeout,
									this.connectionPoolMaxLifetime,
									boletaCabeceraSuccessTag,
									boletaCabeceraErrorTag
									)).withOutputTags(boletaCabeceraSuccessTag, TupleTagList.of(boletaCabeceraErrorTag)));
			

		 PCollection<RegistraBoleta> boletaCabeceraSuccess = 
				 boletaCabecera.get(boletaCabeceraSuccessTag);
		 
		 PCollection<String> boletaCabeceraError = 
				 boletaCabecera.get(boletaCabeceraErrorTag);
		 
    		  
		    return PCollectionTuple.of(boletaCabeceraSuccessTag, boletaCabeceraSuccess)
		                           .and(boletaCabeceraErrorTag, boletaCabeceraError);
	}


}
