package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model;

import java.util.List;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import javax.annotation.Nullable;

@DefaultSchema(JavaBeanSchema.class)
public class RegistraPedido implements Cloneable {

	private Pedido pedido;
	@Nullable private ClienteRemitente clienteRemitente;
	@Nullable private ClienteDestinatario clienteDestinatario;
	@Nullable private List<PedidoDetalleSku> pedido_detalle_sku;
 
    
    
	public RegistraPedido() {}
	
	
	public RegistraPedido(Pedido pedido, ClienteRemitente clienteRemitente, ClienteDestinatario clienteDestinatario,
			List<PedidoDetalleSku> pedido_detalle_sku) {
		super();
		this.pedido = pedido;
		this.clienteRemitente = clienteRemitente;
		this.clienteDestinatario = clienteDestinatario;
		this.pedido_detalle_sku = pedido_detalle_sku;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public @Nullable ClienteRemitente getClienteRemitente() {
		return clienteRemitente;
	}

	public void setClienteRemitente(@Nullable ClienteRemitente clienteRemitente) {
		this.clienteRemitente = clienteRemitente;
	}

	public @Nullable ClienteDestinatario getClienteDestinatario() {
		return clienteDestinatario;
	}

	public void setClienteDestinatario(@Nullable ClienteDestinatario clienteDestinatario) {
		this.clienteDestinatario = clienteDestinatario;
	}

	public @Nullable List<PedidoDetalleSku> getPedido_detalle_sku() {
		return pedido_detalle_sku;
	}

	public void setPedido_detalle_sku(@Nullable List<PedidoDetalleSku> pedido_detalle_sku) {
		this.pedido_detalle_sku = pedido_detalle_sku;
	}

	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteDestinatario == null) ? 0 : clienteDestinatario.hashCode());
		result = prime * result + ((clienteRemitente == null) ? 0 : clienteRemitente.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((pedido_detalle_sku == null) ? 0 : pedido_detalle_sku.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistraPedido other = (RegistraPedido) obj;
		if (clienteDestinatario == null) {
			if (other.clienteDestinatario != null)
				return false;
		} else if (!clienteDestinatario.equals(other.clienteDestinatario))
			return false;
		if (clienteRemitente == null) {
			if (other.clienteRemitente != null)
				return false;
		} else if (!clienteRemitente.equals(other.clienteRemitente))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (pedido_detalle_sku == null) {
			if (other.pedido_detalle_sku != null)
				return false;
		} else if (!pedido_detalle_sku.equals(other.pedido_detalle_sku))
			return false;
		return true;
	}

	
	
	
	@Override
	public String toString() {
		return "RegistraPedido [pedido=" + pedido + ", clienteRemitente=" + clienteRemitente + ", clienteDestinatario="
				+ clienteDestinatario + ", pedido_detalle_sku=" + pedido_detalle_sku + "]";
	}

	@Override
	public Object clone() {
	    try {
	        return (RegistraPedido)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new RegistraPedido(this.pedido,
			this.clienteRemitente,
			this.clienteDestinatario,
			this.pedido_detalle_sku);
	    }
	}
	
}
