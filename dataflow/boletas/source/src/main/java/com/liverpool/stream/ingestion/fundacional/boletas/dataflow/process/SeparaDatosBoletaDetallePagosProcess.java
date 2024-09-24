package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallePagos;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;


public class SeparaDatosBoletaDetallePagosProcess extends DoFn<RegistraBoleta, BoletaDetallePagos> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -582008941536278198L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetallePagosProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraBoleta registraBoleta = c.element();
				if(registraBoleta.getBoleta_detalle_pagos()!=null){
					for(BoletaDetallePagos boletaDetallePagos: registraBoleta.getBoleta_detalle_pagos())
					{
						if(!(boletaDetallePagos == null)){
							c.output(boletaDetallePagos);}
					}
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalle Pagos:" + e.getMessage(), e);
		} 
	}
}