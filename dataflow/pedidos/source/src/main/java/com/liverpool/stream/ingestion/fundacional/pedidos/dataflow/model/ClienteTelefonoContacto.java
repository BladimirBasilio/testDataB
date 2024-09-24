package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteTelefonoContacto   implements Cloneable {

	@Nullable private Integer id_cliente_telefonos_de_contacto;
	@Nullable private Integer id_cliente;
	@Nullable private Integer id_sistema_origen;
	@Nullable private String telefono;
	@Nullable private String alias_telefono;
	@Nullable private String operacion_crud;

	
	
    public ClienteTelefonoContacto() {}
    




	public ClienteTelefonoContacto(Integer id_cliente_telefonos_de_contacto, Integer id_cliente,
			Integer id_sistema_origen, String telefono, String alias_telefono, String operacion_crud) {
		super();
		this.id_cliente_telefonos_de_contacto = id_cliente_telefonos_de_contacto;
		this.id_cliente = id_cliente;
		this.id_sistema_origen = id_sistema_origen;
		this.telefono = telefono;
		this.alias_telefono = alias_telefono;
		this.operacion_crud = operacion_crud;
	}





	public @Nullable Integer getId_cliente_telefonos_de_contacto() {
		return id_cliente_telefonos_de_contacto;
	}







	public void setId_cliente_telefonos_de_contacto(@Nullable Integer id_cliente_telefonos_de_contacto) {
		this.id_cliente_telefonos_de_contacto = id_cliente_telefonos_de_contacto;
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







	public @Nullable String getTelefono() {
		return telefono;
	}







	public void setTelefono(@Nullable String telefono) {
		this.telefono = telefono;
	}







	public @Nullable String getAlias_telefono() {
		return alias_telefono;
	}







	public void setAlias_telefono(@Nullable String alias_telefono) {
		this.alias_telefono = alias_telefono;
	}


	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}


	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias_telefono == null) ? 0 : alias_telefono.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result
				+ ((id_cliente_telefonos_de_contacto == null) ? 0 : id_cliente_telefonos_de_contacto.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		ClienteTelefonoContacto other = (ClienteTelefonoContacto) obj;
		if (alias_telefono == null) {
			if (other.alias_telefono != null)
				return false;
		} else if (!alias_telefono.equals(other.alias_telefono))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_telefonos_de_contacto == null) {
			if (other.id_cliente_telefonos_de_contacto != null)
				return false;
		} else if (!id_cliente_telefonos_de_contacto.equals(other.id_cliente_telefonos_de_contacto))
			return false;
		if (id_sistema_origen == null) {
			if (other.id_sistema_origen != null)
				return false;
		} else if (!id_sistema_origen.equals(other.id_sistema_origen))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "ClienteTelefonoContacto [id_cliente_telefonos_de_contacto=" + id_cliente_telefonos_de_contacto
				+ ", id_cliente=" + id_cliente + ", id_sistema_origen=" + id_sistema_origen + ", telefono=" + telefono
				+ ", alias_telefono=" + alias_telefono + ", operacion_crud=" + operacion_crud + "]";
	}





	@Override
	public Object clone() {
		try {
	        return (ClienteTelefonoContacto)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteTelefonoContacto(
	        		this.id_cliente_telefonos_de_contacto,
	        		this.id_cliente,
	        		this.id_sistema_origen,
	        		this.telefono,
	        		this.alias_telefono,
	        		this.operacion_crud
	        		);
	    }
	}
}
