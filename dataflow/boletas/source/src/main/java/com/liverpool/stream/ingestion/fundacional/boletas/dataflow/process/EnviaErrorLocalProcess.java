package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EnviaErrorLocalProcess extends DoFn<String, Void> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3757037895447686359L;
	private static final Logger LOG = LoggerFactory.getLogger(EnviaErrorLocalProcess.class);
	private String pubSubTopicoError;

	
	public EnviaErrorLocalProcess (String pubSubTopicoError) {
		this.pubSubTopicoError = pubSubTopicoError;
	}
	

	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				if(this.pubSubTopicoError.isEmpty())
				{
					LOG.info("Envio a pubsub:" + c.element());
					
				}
				
		}
		catch (Exception e) {
			LOG.error("Error al enviar :" + e.getMessage(), e);
		} 
	}
}