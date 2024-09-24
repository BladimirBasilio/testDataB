package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteMerge;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

public class SeparaDatosClienteMergeProcess extends DoFn<RegistraCliente, ClienteMerge> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteMergeProcess.class);
	private static final long serialVersionUID = 1L;
	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraCliente registraCliente = c.element();
				Cliente cliente = registraCliente.getCliente();
				ClienteMerge clienteMerge = registraCliente.getCliente_merge();

				//LOG.info("clienteMerge {}",clienteMerge.toString() );
				int id_cliente= cliente.getId_cliente();
				if(clienteMerge != null) {
					if(!(clienteMerge.getRowid() == null)) {
						//LOG.info("validation IF");
							ClienteMerge copyClienteMerge = 
										(ClienteMerge) clienteMerge.clone();
							copyClienteMerge.setId_cliente(id_cliente);
							c.output(copyClienteMerge);
					}
				}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Unico:" + e.getMessage(), e);
		} 
	}
}