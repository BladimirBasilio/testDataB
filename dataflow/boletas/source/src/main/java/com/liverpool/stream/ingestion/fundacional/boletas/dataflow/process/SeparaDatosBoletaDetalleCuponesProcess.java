package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleCupones;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;


public class SeparaDatosBoletaDetalleCuponesProcess extends DoFn<RegistraBoleta, BoletaDetalleCupones> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1911515400773148951L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetalleCuponesProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraBoleta registraBoleta = c.element();
				if(registraBoleta.getBoleta_detalle_cupones()!=null){
					for(BoletaDetalleCupones boletaDetalleCupones: registraBoleta.getBoleta_detalle_cupones())
					{
						if(!(boletaDetalleCupones == null)) {
							c.output(boletaDetalleCupones);
						}
					}
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalle Cupones:" + e.getMessage(), e);
		} 
	}
}