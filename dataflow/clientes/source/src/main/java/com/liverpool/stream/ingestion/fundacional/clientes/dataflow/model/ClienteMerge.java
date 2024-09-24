package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteMerge   implements Cloneable {

    @Nullable private Integer id_cliente_unico;
    @Nullable private String rowid;    
    @Nullable private Integer id_cliente;
    @Nullable private String fuente; 
    @Nullable private String fecha_de_carga; 
    
    public ClienteMerge() {}
    




	public ClienteMerge(Integer id_cliente_unico, String rowid, Integer id_cliente, String fuente,
			String fecha_de_carga) {
		super();
		this.id_cliente_unico = id_cliente_unico;
		this.rowid = rowid;
		this.id_cliente = id_cliente;
		this.fuente = fuente;
		this.fecha_de_carga = fecha_de_carga;
	}


	



	public @Nullable Integer getId_cliente_unico() {
		return id_cliente_unico;
	}





	public void setId_cliente_unico(@Nullable Integer id_cliente_unico) {
		this.id_cliente_unico = id_cliente_unico;
	}





	public @Nullable String getRowid() {
		return rowid;
	}





	public void setRowid(@Nullable String rowid) {
		this.rowid = rowid;
	}





	public @Nullable Integer getId_cliente() {
		return id_cliente;
	}





	public void setId_cliente(@Nullable Integer id_cliente) {
		this.id_cliente = id_cliente;
	}





	public @Nullable String getFuente() {
		return fuente;
	}





	public void setFuente(@Nullable String fuente) {
		this.fuente = fuente;
	}





	public @Nullable String getFecha_de_carga() {
		return fecha_de_carga;
	}





	public void setFecha_de_carga(@Nullable String fecha_de_carga) {
		this.fecha_de_carga = fecha_de_carga;
	}





	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha_de_carga == null) ? 0 : fecha_de_carga.hashCode());
		result = prime * result + ((fuente == null) ? 0 : fuente.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((id_cliente_unico == null) ? 0 : id_cliente_unico.hashCode());
		result = prime * result + ((rowid == null) ? 0 : rowid.hashCode());
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
		ClienteMerge other = (ClienteMerge) obj;
		if (fecha_de_carga == null) {
			if (other.fecha_de_carga != null)
				return false;
		} else if (!fecha_de_carga.equals(other.fecha_de_carga))
			return false;
		if (fuente == null) {
			if (other.fuente != null)
				return false;
		} else if (!fuente.equals(other.fuente))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_unico == null) {
			if (other.id_cliente_unico != null)
				return false;
		} else if (!id_cliente_unico.equals(other.id_cliente_unico))
			return false;
		if (rowid == null) {
			if (other.rowid != null)
				return false;
		} else if (!rowid.equals(other.rowid))
			return false;
		return true;
	}



	
	
	


	@Override
	public String toString() {
		return "ClienteMerge [id_cliente_unico=" + id_cliente_unico + ", rowid=" + rowid + ", id_cliente=" + id_cliente
				+ ", fuente=" + fuente + ", fecha_de_carga=" + fecha_de_carga + "]";
	}





	@Override
	public Object clone() {
		try {
	        return (ClienteMerge)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteMerge(
	        		this.id_cliente_unico,
	        		this.rowid,
	        		this.id_cliente,
	        		this.fuente,
	        		this.fecha_de_carga
	        		);
	    }
	}
	
    
}
