package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteDestinatario;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteRemitente;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.ClienteTelefonoContacto;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.PedidoDetalleSku;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

public class SeparaDatosClienteTelefonoContactoProcess extends DoFn<RegistraPedido, ClienteTelefonoContacto> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteTelefonoContactoProcess.class);
	private static final long serialVersionUID = 1L;

	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
			RegistraPedido registraPedido = c.element();
			if( registraPedido.getClienteRemitente() != null ){

				ClienteRemitente clienteRemitente = registraPedido.getClienteRemitente();

				int id_cliente_remitente = clienteRemitente.getId_cliente();

				if(registraPedido.getClienteRemitente().getTelefono_de_contacto()!= null) {
					for(ClienteTelefonoContacto remitenteTelefonoContacto: registraPedido.getClienteRemitente().getTelefono_de_contacto())
					{
						if(!(remitenteTelefonoContacto == null ||
								remitenteTelefonoContacto.getOperacion_crud().equals("NONE"))) {
							ClienteTelefonoContacto clienteRemitenteTelefonoContacto =
									(ClienteTelefonoContacto) remitenteTelefonoContacto.clone();
							clienteRemitenteTelefonoContacto.setId_cliente(id_cliente_remitente);
							c.output(clienteRemitenteTelefonoContacto);
						}
					}
				}

			}
		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Telefono Contacto:" + e.getMessage(), e);
		} 
	}
}