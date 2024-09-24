package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteFormaPago;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SeparaDatosClienteFormaPagoProcess extends DoFn<Cliente, ClienteFormaPago> {
    private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosClienteFormaPagoProcess.class);
    private static final long serialVersionUID = 1L;
    private static final String Tipo_de_direccion = "B";

    @ProcessElement
    public void processElement(ProcessContext c) {

        try {

            Cliente cliente = c.element();
            ClienteFormaPago clienteFormaPago = cliente.getCliente_formas_de_pago();

            Integer id_cliente = cliente.getId_cliente();

            Integer id_cliente_direccion;
			id_cliente_direccion=Objects.isNull(cliente.getDireccion())?null:
					cliente.getDireccion().getId_direccion_del_cliente();
            //LOG.info("id_cliente_direccion {} getTipo_de_direccion {}", id_cliente_direccion, cliente.getDireccion().getTipo_de_direccion());
            if (clienteFormaPago != null) {
                if (!(clienteFormaPago.getTipo_tarjeta() == null)) {
                    ClienteFormaPago copyClienteFormaPago =
                            (ClienteFormaPago) clienteFormaPago.clone();
                    //LOG.info("clienteFormaPago {}",clienteFormaPago.toString() );
                    if (Objects.nonNull(id_cliente))
                        copyClienteFormaPago.setId_cliente(id_cliente);

                    if (cliente.getDireccion() != null && cliente.getDireccion().getTipo_de_direccion() != null &&
                            cliente.getDireccion().getTipo_de_direccion().equals(Tipo_de_direccion)) {
                        //LOG.info("validation IF");
                        copyClienteFormaPago.setId_direccion_de_tarjeta(id_cliente_direccion);
                    } else {
                        //LOG.info("validation ELSE");
                        copyClienteFormaPago.setId_direccion_de_tarjeta(null);
                    }
                    c.output(copyClienteFormaPago);
                }
            }
        } catch (Exception e) {
            LOG.error("Error al separar datos Cliente Forma Pago:" + e.getMessage(), e);
        }
    }
}