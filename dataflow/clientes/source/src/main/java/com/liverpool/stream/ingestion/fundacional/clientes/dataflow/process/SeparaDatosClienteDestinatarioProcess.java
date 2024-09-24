package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteDestinatario;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

public class SeparaDatosClienteDestinatarioProcess extends DoFn<RegistraCliente, ClienteDestinatario> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteDestinatarioProcess.class);
	private static final long serialVersionUID = 1L;
	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraCliente registraCliente = c.element();
				Cliente cliente = registraCliente.getCliente();
				ClienteDestinatario clienteDestinatario = registraCliente.getClienteDestinatario();

				//LOG.info("clienteFormaPago {}",clienteDestinatario.toString() );
				int id_cliente= cliente.getId_cliente();
				if(clienteDestinatario != null) {
					if(!(clienteDestinatario.getPrimer_nombre() == null)) {
							//LOG.info("validation IF");
							ClienteDestinatario copyClienteDestinatario = 
										(ClienteDestinatario) clienteDestinatario.clone();
							copyClienteDestinatario.setId_cliente_padre_del_destinatario(id_cliente);
							c.output(copyClienteDestinatario);
					}
				}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Destinatario:" + e.getMessage(), e);
		} 
	}
}