package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class RegistraCliente implements Cloneable  {

    private Cliente cliente; 
    @Nullable private ClienteAtg cliente_atg; 
    @Nullable private ClienteMerge cliente_merge;
    @Nullable private ClienteDestinatario clienteDestinatario;
    @Nullable private ArrayList<ClienteId> cliente_id;
    
	public RegistraCliente() {}

	



	public RegistraCliente(Cliente cliente, ClienteAtg cliente_atg, ClienteMerge cliente_merge,
			ClienteDestinatario clienteDestinatario, ArrayList<ClienteId> cliente_id) {
		super();
		this.cliente = cliente;
		this.cliente_atg = cliente_atg;
		this.cliente_merge = cliente_merge;
		this.clienteDestinatario = clienteDestinatario;
		this.cliente_id = cliente_id;
	}




	
	

	public Cliente getCliente() {
		return cliente;
	}





	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}





	public @Nullable ClienteAtg getCliente_atg() {
		return cliente_atg;
	}





	public void setCliente_atg(@Nullable ClienteAtg cliente_atg) {
		this.cliente_atg = cliente_atg;
	}





	public @Nullable ClienteMerge getCliente_merge() {
		return cliente_merge;
	}





	public void setCliente_merge(@Nullable ClienteMerge cliente_merge) {
		this.cliente_merge = cliente_merge;
	}





	public @Nullable ClienteDestinatario getClienteDestinatario() {
		return clienteDestinatario;
	}





	public void setClienteDestinatario(@Nullable ClienteDestinatario clienteDestinatario) {
		this.clienteDestinatario = clienteDestinatario;
	}





	public @Nullable ArrayList<ClienteId> getCliente_id() {
		return cliente_id;
	}





	public void setCliente_id(@Nullable ArrayList<ClienteId> cliente_id) {
		this.cliente_id = cliente_id;
	}



	
	
	


	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((clienteDestinatario == null) ? 0 : clienteDestinatario.hashCode());
		result = prime * result + ((cliente_atg == null) ? 0 : cliente_atg.hashCode());
		result = prime * result + ((cliente_id == null) ? 0 : cliente_id.hashCode());
		result = prime * result + ((cliente_merge == null) ? 0 : cliente_merge.hashCode());
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
		RegistraCliente other = (RegistraCliente) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (clienteDestinatario == null) {
			if (other.clienteDestinatario != null)
				return false;
		} else if (!clienteDestinatario.equals(other.clienteDestinatario))
			return false;
		if (cliente_atg == null) {
			if (other.cliente_atg != null)
				return false;
		} else if (!cliente_atg.equals(other.cliente_atg))
			return false;
		if (cliente_id == null) {
			if (other.cliente_id != null)
				return false;
		} else if (!cliente_id.equals(other.cliente_id))
			return false;
		if (cliente_merge == null) {
			if (other.cliente_merge != null)
				return false;
		} else if (!cliente_merge.equals(other.cliente_merge))
			return false;
		return true;
	}







	@Override
	public String toString() {
		return "RegistraCliente [cliente=" + cliente + ", cliente_atg=" + cliente_atg + ", cliente_merge="
				+ cliente_merge + ", clienteDestinatario=" + clienteDestinatario + ", cliente_id=" + cliente_id + "]";
	}





	@Override
	public Object clone() {

		try {
	        return (RegistraCliente)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new RegistraCliente(
	        		this.cliente,
	        		this.cliente_atg,
	        		this.cliente_merge,
	        		this.clienteDestinatario,
	        		this.cliente_id
	        		);
	    }
	}



}
