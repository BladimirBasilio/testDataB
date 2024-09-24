package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.TupleTag;
import org.apache.beam.sdk.values.TupleTagList;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesSku;

public class SeparaDatosBoletaDetallesSkuMultioutputProcess extends 
			PTransform<PCollection<BoletaDetallesSku>, PCollectionTuple> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3025586040479173265L;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertBoletaDetallesSku;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	
	private final TupleTag<BoletaDetallesSku> boletaDetallesSkuSuccessTag =
            new TupleTag<BoletaDetallesSku>("boletaDetallesSkuSuccessTag"){};
    private final TupleTag<String> boletaDetallesSkuErrorTag =
            new TupleTag<String>("boletaDetallesSkuErrorTag"){};

        	
	public SeparaDatosBoletaDetallesSkuMultioutputProcess(String jdbcUrl,
			  String jdbcUsuario,
			  String jdbcPassword,
			  String consultaInsertBoletaDetallesSku,
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
			this.consultaInsertBoletaDetallesSku = consultaInsertBoletaDetallesSku;
			this.instanceNameSM = instanceNameSM;
			this.connectionPoolMaxSize = connectionPoolMaxSize;
			this.connectionPoolMinIdle = connectionPoolMinIdle;
			this.connectionPoolTimeout = connectionPoolTimeout;
			this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
			this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
	}
	
	


    	            
	@Override
	public PCollectionTuple expand(PCollection<BoletaDetallesSku> input) {

		 PCollectionTuple boletaDetallesSku = 
					input.apply("Crea Boleta Cabecera", 
							ParDo.of(new AlmacenaDatosBoletaDetallesSkuJDBCProcess(
									this.jdbcUrl,
									this.jdbcUsuario,
									this.jdbcPassword,
									this.consultaInsertBoletaDetallesSku,
									boletaDetallesSkuSuccessTag,
									boletaDetallesSkuErrorTag,
									this.instanceNameSM,
									this.applicationName,
									this.connectionPoolMaxSize,
									this.connectionPoolMinIdle,
									this.connectionPoolTimeout,
									this.connectionPoolIdleTimeout,
									this.connectionPoolMaxLifetime,
									"Separa Datos Boleta Detalle SKU"
									)).withOutputTags(boletaDetallesSkuSuccessTag, TupleTagList.of(boletaDetallesSkuErrorTag)));
			

		 PCollection<BoletaDetallesSku> boletaDetallesSkuSuccess = 
				 boletaDetallesSku.get(boletaDetallesSkuSuccessTag);
		 
		 PCollection<String> boletaDetallesSkuError = 
				 boletaDetallesSku.get(boletaDetallesSkuErrorTag);
		 
    		  
		    return PCollectionTuple.of(boletaDetallesSkuSuccessTag, boletaDetallesSkuSuccess)
		                           .and(boletaDetallesSkuErrorTag, boletaDetallesSkuError);
	}


}
