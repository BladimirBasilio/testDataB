package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleTarjetasPrepago;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;


public class SeparaDatosBoletaDetalleTarjetasPrepagoProcess extends DoFn<RegistraBoleta, BoletaDetalleTarjetasPrepago> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1400411661827564468L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetalleTarjetasPrepagoProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraBoleta registraBoleta = c.element();
				if(registraBoleta.getBoleta_detalle_tarjetas_prepago()!=null){
					for(BoletaDetalleTarjetasPrepago boletaDetalleTarjetasPrepago: registraBoleta.getBoleta_detalle_tarjetas_prepago())
					{
						if(!(boletaDetalleTarjetasPrepago == null)){
							c.output(boletaDetalleTarjetasPrepago);
						}
					}
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalle Tarjetas Prepago:" + e.getMessage(), e);
		} 
	}
}