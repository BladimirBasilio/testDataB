package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteId;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

import java.util.List;

public class SeparaDatosClienteIdProcess extends DoFn<RegistraCliente, ClienteId> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteIdProcess.class);
	private static final long serialVersionUID = 1L;
	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraCliente registraCliente = c.element();
				Cliente cliente = registraCliente.getCliente();

				int id_cliente= cliente.getId_cliente();
				//LOG.info("clienteId {}",registraCliente.getCliente_id().toString() );
				if(registraCliente.getCliente_id() != null && !registraCliente.getCliente_id().isEmpty()) {
					for (ClienteId clienteId : registraCliente.getCliente_id()) {
						//LOG.info("clienteId {}",clienteId.toString() );
						if (clienteId != null) {
							if (!(clienteId.getId_cliente() == null)) {
								//LOG.info("validation IF");
								ClienteId copyClienteId =
										(ClienteId) clienteId.clone();
								copyClienteId.setId_cliente(id_cliente);
								c.output(copyClienteId);
							}
						}
					}
				}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Id:" + e.getMessage(), e);
		} 
	}
}