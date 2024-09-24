package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.*;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeparaDatosClienteEmailContactoProcess extends DoFn<RegistraPedido, ClienteEmailContacto> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteEmailContactoProcess.class);
	private static final long serialVersionUID = 1L;

	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
			RegistraPedido registraPedido = c.element();
			if( registraPedido.getClienteRemitente() != null ){

				ClienteRemitente clienteRemitente = registraPedido.getClienteRemitente();

				int id_cliente_remitente = clienteRemitente.getId_cliente();

				if(!(registraPedido.getClienteRemitente().getEmail_de_contacto() == null ||
						registraPedido.getClienteRemitente().getEmail_de_contacto().getOperacion_crud().equals("NONE"))){

					ClienteEmailContacto clienteEmailContacto =
							(ClienteEmailContacto) registraPedido.getClienteRemitente().getEmail_de_contacto().clone();
					clienteEmailContacto.setId_cliente(id_cliente_remitente);
					c.output(clienteEmailContacto);
				}

			}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Email Contacto:" + e.getMessage(), e);
		} 
	}
}