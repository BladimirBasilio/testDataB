package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesMonedero;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;


public class SeparaDatosBoletaDetallesMonederoProcess extends DoFn<RegistraBoleta, BoletaDetallesMonedero> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 489834561438762771L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetallesMonederoProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraBoleta registraBoleta = c.element();
				if( registraBoleta.getBoleta_detalles_monedero()!=null) {
					for (BoletaDetallesMonedero boletaDetallesMonedero : registraBoleta.getBoleta_detalles_monedero()) {
						if (!(boletaDetallesMonedero == null)) {
							c.output(boletaDetallesMonedero);
						}
					}
				}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalles Monedero:" + e.getMessage(), e);
		} 
	}
}