package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.PedidoDetalleSku;

public class SeparaDatosPedidoDetalleSkuProcess extends DoFn<RegistraPedido, PedidoDetalleSku> {
	private static final Logger LOG = LoggerFactory.getLogger(SeparaDatosPedidoDetalleSkuProcess.class);
	private static final long serialVersionUID = 1L;

	@ProcessElement
	public void processElement(ProcessContext c) {
	
		try {
				
				RegistraPedido registraPedido = c.element();
				if(registraPedido.getPedido_detalle_sku() != null){
					for(PedidoDetalleSku pedidoDetalleSku: registraPedido.getPedido_detalle_sku())
					{
						if(!(pedidoDetalleSku == null))
							c.output(pedidoDetalleSku);
					}
				}

		}
		catch (Exception e) {
			LOG.error("Error al separar datos Pedido Detalle Sku:" + e.getMessage(), e);
		} 
	}
}