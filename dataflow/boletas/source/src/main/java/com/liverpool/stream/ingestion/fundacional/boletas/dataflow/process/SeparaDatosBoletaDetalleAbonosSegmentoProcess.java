package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleAbonosSegmento;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;


public class SeparaDatosBoletaDetalleAbonosSegmentoProcess extends DoFn<RegistraBoleta, BoletaDetalleAbonosSegmento> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1317354715225815973L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetalleAbonosSegmentoProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraBoleta registraBoleta = c.element();
				if(registraBoleta.getBoleta_detalle_abonos_segmento()!=null){
					for(BoletaDetalleAbonosSegmento boletaDetalleAbonosSegmento: registraBoleta.getBoleta_detalle_abonos_segmento())
					{
						if(boletaDetalleAbonosSegmento != null){
							c.output(boletaDetalleAbonosSegmento);
						}
					}
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalle Abonos Segmento:" + e.getMessage(), e);
		} 
	}
}