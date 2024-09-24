package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteDireccion;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

public class SeparaDatosClienteDireccionProcess extends DoFn<RegistraCliente, ClienteDireccion> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteDireccionProcess.class);
	private static final long serialVersionUID = 1L;
	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraCliente registraCliente = c.element();
				Cliente cliente = registraCliente.getCliente();
				ClienteDireccion clienteDireccion = cliente.getDireccion();
				//LOG.info("clienteFormaPago {}",clienteDireccion.toString() );
				int id_cliente= cliente.getId_cliente();

				if(!(clienteDireccion == null)) {
						//LOG.info("validation IF");
						ClienteDireccion copyClienteDireccion = 
									(ClienteDireccion) clienteDireccion.clone();
						copyClienteDireccion.setId_cliente(id_cliente);
						c.output(copyClienteDireccion);
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Cliente Telefono Contacto:" + e.getMessage(), e);
		} 
	}
}