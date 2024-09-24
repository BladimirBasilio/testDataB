package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteTelefonoContacto;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

public class SeparaDatosClienteTelefonoContactoProcess extends DoFn<RegistraCliente, ClienteTelefonoContacto> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteTelefonoContactoProcess.class);
	private static final long serialVersionUID = 1L;
	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraCliente registraCliente = c.element();
				Cliente cliente = registraCliente.getCliente();
				
				int id_cliente= cliente.getId_cliente();
				//LOG.info("id_cliente {}",id_cliente );
				for(ClienteTelefonoContacto clienteTelefonoContacto: cliente.getTelefono_de_contacto())
				{
					//LOG.error("error" + cliente.getTelefono_de_contacto().toString());
					//LOG.error("13.10.2021");
					//LOG.info("clienteTelefonoContacto {}",clienteTelefonoContacto.toString() );
					if(!(clienteTelefonoContacto == null)) {
						//LOG.info("validation IF init" );
						ClienteTelefonoContacto copyClienteTelefonoContacto = 
									(ClienteTelefonoContacto) clienteTelefonoContacto.clone();
						copyClienteTelefonoContacto.setId_cliente(id_cliente);
						//LOG.info("validation IF end" );
						c.output(copyClienteTelefonoContacto);

					}
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Telefono Contacto:" + e.getMessage(), e);
		} 
	}
}