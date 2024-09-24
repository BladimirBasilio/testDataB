package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteRemitente;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteDestinatario;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteId;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

public class SeparaDatosClienteIdProcess extends DoFn<RegistraPedido, ClienteId> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteIdProcess.class);
	private static final long serialVersionUID = 1L;

	@ProcessElement
	public void processElement(ProcessContext c) {

		try {

			RegistraPedido RegistraPedido = c.element();
			ClienteDestinatario clienteDestinatario = null;
			ClienteId clienteIdRemitente = null;


			if( RegistraPedido.getClienteDestinatario() != null && (!(RegistraPedido.getClienteDestinatario().getId_cliente() == null ||
					RegistraPedido.getClienteDestinatario().getOperacion_crud().equals("NONE"))) ) {

				clienteDestinatario = RegistraPedido.getClienteDestinatario();
				ClienteId clienteIdDestinatario = clienteDestinatario.getCliente_id();

				if(clienteIdDestinatario != null) {
					ClienteId copyClienteIdDestinatario =
							(ClienteId) clienteIdDestinatario.clone();
					copyClienteIdDestinatario.setId_cliente(clienteDestinatario.getId_cliente());
					c.output(copyClienteIdDestinatario);
				}
			}

			if(RegistraPedido.getClienteRemitente() != null){
				ClienteRemitente clienteRemitente = RegistraPedido.getClienteRemitente();
				clienteIdRemitente = clienteRemitente.getCliente_id();

				if(!(clienteRemitente.getId_cliente() == null ||
						clienteRemitente.getOperacion_crud().equals("NONE"))) {
					ClienteId copyClienteIdRemitente =
							(ClienteId) clienteIdRemitente.clone();
					copyClienteIdRemitente.setId_cliente(clienteRemitente.getId_cliente());
					c.output(copyClienteIdRemitente);
				}

			}else if (RegistraPedido.getClienteRemitente() == null && RegistraPedido.getClienteDestinatario() != null){
				clienteIdRemitente = clienteDestinatario.getCliente_id();
			}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Id:" + e.getMessage(), e);
		}
	}
}