package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;


import javax.annotation.Nullable;
import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;



@DefaultSchema(JavaBeanSchema.class)
public class ClienteDestinatario implements Cloneable  {


	@Nullable private Integer id_cliente; 
	@Nullable private String primer_nombre; 
	@Nullable private String segundo_nombre; 
	@Nullable private String apellido_paterno; 
	@Nullable private String apellido_materno;
	@Nullable private Integer id_cliente_padre_del_destinatario; 
	@Nullable private String operacion_crud;
	
	public ClienteDestinatario() {}



	public ClienteDestinatario(Integer id_cliente, String primer_nombre, String segundo_nombre, String apellido_paterno,
			String apellido_materno, Integer id_cliente_padre_del_destinatario, String operacion_crud) {
		super();
		this.id_cliente = id_cliente;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.id_cliente_padre_del_destinatario = id_cliente_padre_del_destinatario;
		this.operacion_crud = operacion_crud;
	}










	public @Nullable Integer getId_cliente() {
		return id_cliente;
	}




	public void setId_cliente(@Nullable Integer id_cliente) {
		this.id_cliente = id_cliente;
	}




	public @Nullable String getPrimer_nombre() {
		return primer_nombre;
	}




	public void setPrimer_nombre(@Nullable String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}




	public @Nullable String getSegundo_nombre() {
		return segundo_nombre;
	}




	public void setSegundo_nombre(@Nullable String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}




	public @Nullable String getApellido_paterno() {
		return apellido_paterno;
	}




	public void setApellido_paterno(@Nullable String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}




	public @Nullable String getApellido_materno() {
		return apellido_materno;
	}




	public void setApellido_materno(@Nullable String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}




	public @Nullable Integer getId_cliente_padre_del_destinatario() {
		return id_cliente_padre_del_destinatario;
	}




	public void setId_cliente_padre_del_destinatario(@Nullable Integer id_cliente_padre_del_destinatario) {
		this.id_cliente_padre_del_destinatario = id_cliente_padre_del_destinatario;
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
		result = prime * result + ((apellido_materno == null) ? 0 : apellido_materno.hashCode());
		result = prime * result + ((apellido_paterno == null) ? 0 : apellido_paterno.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result
				+ ((id_cliente_padre_del_destinatario == null) ? 0 : id_cliente_padre_del_destinatario.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((primer_nombre == null) ? 0 : primer_nombre.hashCode());
		result = prime * result + ((segundo_nombre == null) ? 0 : segundo_nombre.hashCode());
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
		ClienteDestinatario other = (ClienteDestinatario) obj;
		if (apellido_materno == null) {
			if (other.apellido_materno != null)
				return false;
		} else if (!apellido_materno.equals(other.apellido_materno))
			return false;
		if (apellido_paterno == null) {
			if (other.apellido_paterno != null)
				return false;
		} else if (!apellido_paterno.equals(other.apellido_paterno))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_padre_del_destinatario == null) {
			if (other.id_cliente_padre_del_destinatario != null)
				return false;
		} else if (!id_cliente_padre_del_destinatario.equals(other.id_cliente_padre_del_destinatario))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (primer_nombre == null) {
			if (other.primer_nombre != null)
				return false;
		} else if (!primer_nombre.equals(other.primer_nombre))
			return false;
		if (segundo_nombre == null) {
			if (other.segundo_nombre != null)
				return false;
		} else if (!segundo_nombre.equals(other.segundo_nombre))
			return false;
		return true;
	}


	
	
	

	@Override
	public String toString() {
		return "ClienteDestinatario [id_cliente=" + id_cliente + ", primer_nombre=" + primer_nombre
				+ ", segundo_nombre=" + segundo_nombre + ", apellido_paterno=" + apellido_paterno
				+ ", apellido_materno=" + apellido_materno + ", id_cliente_padre_del_destinatario="
				+ id_cliente_padre_del_destinatario + ", operacion_crud=" + operacion_crud + "]";
	}



	@Override
	public Object clone() {

		try {
	        return (ClienteDestinatario)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteDestinatario(
	        		this.id_cliente,
	        		this.primer_nombre,
	        		this.segundo_nombre,
	        		this.apellido_paterno,
	        		this.apellido_materno,
	        		this.id_cliente_padre_del_destinatario,
	        		this.operacion_crud
	        		);
	    }
	}



}
