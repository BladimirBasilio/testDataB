package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteEmailContacto;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

public class SeparaDatosClienteEmailContactoProcess extends DoFn<RegistraCliente, ClienteEmailContacto> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteEmailContactoProcess.class);
	private static final long serialVersionUID = 1L;
	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraCliente registraCliente = c.element();
				Cliente cliente = registraCliente.getCliente();
				
				int id_cliente= cliente.getId_cliente();

			for(ClienteEmailContacto clienteEmailContacto: cliente.getEmail_de_contacto())
			{
				//LOG.error("error" + cliente.getEmail_de_contacto().toString());
				//LOG.error("13.10.2021");
				if(!(clienteEmailContacto == null)) {
					ClienteEmailContacto copyClienteEmailContacto =
							(ClienteEmailContacto) clienteEmailContacto.clone();
					copyClienteEmailContacto.setId_cliente(id_cliente);
					c.output(copyClienteEmailContacto);
				}
			}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Email Contacto:" + e.getMessage(), e);
		}
	}
}