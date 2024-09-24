package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesSku;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;


public class SeparaDatosBoletaDetallesSkuProcess extends DoFn<RegistraBoleta, BoletaDetallesSku> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7336513867487052894L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetallesSkuProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraBoleta registraBoleta = c.element();
				if(registraBoleta.getBoleta_detalles_sku()!=null){
					for(BoletaDetallesSku boletaDetallesSku: registraBoleta.getBoleta_detalles_sku())
					{
							if(!(boletaDetallesSku == null)) {
								c.output(boletaDetallesSku);
							}
					}
				}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalles Sku:" + e.getMessage(), e);
		} 
	}
}