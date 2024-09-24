package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteAtg;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

public class SeparaDatosClienteAtgProcess extends DoFn<RegistraCliente, ClienteAtg> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteAtgProcess.class);
	private static final long serialVersionUID = 1L;
	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraCliente registraCliente = c.element();
				Cliente cliente = registraCliente.getCliente();
				ClienteAtg clienteAtg = registraCliente.getCliente_atg();

				
				int id_cliente= cliente.getId_cliente();

				if(clienteAtg != null) {
					if(!(clienteAtg.getId_atg() == null)) {
							ClienteAtg copyClienteAtg = 
										(ClienteAtg) clienteAtg.clone();
							copyClienteAtg.setId_cliente(id_cliente);
							c.output(copyClienteAtg);
					}
				}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente ATG:" + e.getMessage(), e);
		} 
	}
}