package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesSku;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesSkuDescuentos;



public class SeparaDatosBoletaDetallesSkuDescuentosProcess extends DoFn<BoletaDetallesSku, BoletaDetallesSkuDescuentos> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3504725829148556355L;
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosBoletaDetallesSkuDescuentosProcess.class);


	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				BoletaDetallesSku boletaDetallesSku = c.element();
				if(boletaDetallesSku.getBoleta_detalles_sku_descuentos()!=null){
					for(BoletaDetallesSkuDescuentos boletaDetallesSkuDescuentos: boletaDetallesSku.getBoleta_detalles_sku_descuentos())
					{
						if(!(boletaDetallesSkuDescuentos == null)){
							c.output(boletaDetallesSkuDescuentos);
						}
					}
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Boleta Detalles Sku Descuentos:" + e.getMessage(), e);
		} 
	}
}