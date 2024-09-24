package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleAbonosSegmento;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleAbonosTarjeta;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SeparaDatosBoletaDetalleAbonosTarjetaProcess extends DoFn<RegistraBoleta, BoletaDetalleAbonosTarjeta> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1317354715225815973L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetalleAbonosTarjetaProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraBoleta registraBoleta = c.element();
				if(registraBoleta.getBoleta_detalle_abono_tarjetas()!=null){
					for(BoletaDetalleAbonosTarjeta boletaDetalleAbonosTarjeta: registraBoleta.getBoleta_detalle_abono_tarjetas())
					{
							if(boletaDetalleAbonosTarjeta != null){
								c.output(boletaDetalleAbonosTarjeta);
							}

					}
				}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalle Abonos Tarjeta:" + e.getMessage(), e);
		} 
	}
}