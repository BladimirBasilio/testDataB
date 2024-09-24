package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import java.sql.Timestamp;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteId   implements Cloneable {

    @Nullable private Integer id_cliente_ids;
    @Nullable private String id_origen;    
    @Nullable private Integer id_cliente;
    @Nullable private Integer id_sistema_origen; 
    @Nullable private Timestamp fecha_de_actualizacion;
    @Nullable private Timestamp fecha_de_creacion;
    
    public ClienteId() {}
    


	public ClienteId(Integer id_cliente_ids, String id_origen, Integer id_cliente, Integer id_sistema_origen,
					 Timestamp fecha_de_actualizacion, Timestamp fecha_de_creacion) {
		super();
		this.id_cliente_ids = id_cliente_ids;
		this.id_origen = id_origen;
		this.id_cliente = id_cliente;
		this.id_sistema_origen = id_sistema_origen;
		this.fecha_de_actualizacion = fecha_de_actualizacion;
		this.fecha_de_creacion = fecha_de_creacion;
	}



	

	


	public @Nullable Integer getId_cliente_ids() {
		return id_cliente_ids;
	}





	public void setId_cliente_ids(@Nullable Integer id_cliente_ids) {
		this.id_cliente_ids = id_cliente_ids;
	}





	public @Nullable String getId_origen() {
		return id_origen;
	}





	public void setId_origen(@Nullable String id_origen) {
		this.id_origen = id_origen;
	}





	public @Nullable Integer getId_cliente() {
		return id_cliente;
	}





	public void setId_cliente(@Nullable Integer id_cliente) {
		this.id_cliente = id_cliente;
	}





	public @Nullable Integer getId_sistema_origen() {
		return id_sistema_origen;
	}





	public void setId_sistema_origen(@Nullable Integer id_sistema_origen) {
		this.id_sistema_origen = id_sistema_origen;
	}





	public @Nullable Timestamp getFecha_de_actualizacion() {
		return fecha_de_actualizacion;
	}





	public void setFecha_de_actualizacion(@Nullable Timestamp fecha_de_actualizacion) {
		this.fecha_de_actualizacion = fecha_de_actualizacion;
	}





	public @Nullable Timestamp getFecha_de_creacion() {
		return fecha_de_creacion;
	}





	public void setFecha_de_creacion(@Nullable Timestamp fecha_de_creacion) {
		this.fecha_de_creacion = fecha_de_creacion;
	}






	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha_de_actualizacion == null) ? 0 : fecha_de_actualizacion.hashCode());
		result = prime * result + ((fecha_de_creacion == null) ? 0 : fecha_de_creacion.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((id_cliente_ids == null) ? 0 : id_cliente_ids.hashCode());
		result = prime * result + ((id_origen == null) ? 0 : id_origen.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
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
		ClienteId other = (ClienteId) obj;
		if (fecha_de_actualizacion == null) {
			if (other.fecha_de_actualizacion != null)
				return false;
		} else if (!fecha_de_actualizacion.equals(other.fecha_de_actualizacion))
			return false;
		if (fecha_de_creacion == null) {
			if (other.fecha_de_creacion != null)
				return false;
		} else if (!fecha_de_creacion.equals(other.fecha_de_creacion))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_ids == null) {
			if (other.id_cliente_ids != null)
				return false;
		} else if (!id_cliente_ids.equals(other.id_cliente_ids))
			return false;
		if (id_origen == null) {
			if (other.id_origen != null)
				return false;
		} else if (!id_origen.equals(other.id_origen))
			return false;
		if (id_sistema_origen == null) {
			if (other.id_sistema_origen != null)
				return false;
		} else if (!id_sistema_origen.equals(other.id_sistema_origen))
			return false;
		return true;
	}


	
	

	@Override
	public String toString() {
		return "ClienteId [id_cliente_ids=" + id_cliente_ids + ", id_origen=" + id_origen + ", id_cliente=" + id_cliente
				+ ", id_sistema_origen=" + id_sistema_origen + ", fecha_de_actualizacion=" + fecha_de_actualizacion
				+ ", fecha_de_creacion=" + fecha_de_creacion + "]";
	}



	@Override
	public Object clone() {
		try {
	        return (ClienteId)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteId(
	        		this.id_cliente_ids,
	        		this.id_origen,
	        		this.id_cliente,
	        		this.id_sistema_origen,
	        		this.fecha_de_actualizacion,
	        		this.fecha_de_creacion
	        		);
	    }
	}
	
    
}
