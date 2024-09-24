package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model;

import java.util.List;

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
	@Nullable private String clave_homologada;
	@Nullable private String operacion_crud;
	@Nullable private Integer id_sistema_origen;
	@Nullable private List<ClienteTelefonoContacto> telefono_de_contacto;
	@Nullable private ClienteEmailContacto email_de_contacto;
	@Nullable private ClienteDireccion direccion; 
	@Nullable private ClienteId cliente_id;
    
	public ClienteDestinatario() {}
	


	public ClienteDestinatario(Integer id_cliente, String primer_nombre, String segundo_nombre, String apellido_paterno,
			String apellido_materno, String clave_homologada, String operacion_crud,
			List<ClienteTelefonoContacto> telefono_de_contacto, ClienteEmailContacto email_de_contacto,
			ClienteDireccion direccion, ClienteId cliente_id,Integer id_sistema_origen ) {
		super();
		this.id_cliente = id_cliente;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.clave_homologada = clave_homologada;
		this.operacion_crud = operacion_crud;
		this.telefono_de_contacto = telefono_de_contacto;
		this.email_de_contacto = email_de_contacto;
		this.direccion = direccion;
		this.cliente_id = cliente_id;
		this.id_sistema_origen = id_sistema_origen;
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



	
	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}




	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}


	public @Nullable List<ClienteTelefonoContacto> getTelefono_de_contacto() {
		return telefono_de_contacto;
	}




	public void setTelefono_de_contacto(@Nullable List<ClienteTelefonoContacto> telefono_de_contacto) {
		this.telefono_de_contacto = telefono_de_contacto;
	}




	public @Nullable ClienteEmailContacto getEmail_de_contacto() {
		return email_de_contacto;
	}




	public void setEmail_de_contacto(@Nullable ClienteEmailContacto email_de_contacto) {
		this.email_de_contacto = email_de_contacto;
	}




	public @Nullable ClienteDireccion getDireccion() {
		return direccion;
	}




	public void setDireccion(@Nullable ClienteDireccion direccion) {
		this.direccion = direccion;
	}



	public @Nullable ClienteId getCliente_id() {
		return cliente_id;
	}


	public void setCliente_id(@Nullable ClienteId cliente_id) {
		this.cliente_id = cliente_id;
	}


	public @Nullable String getClave_homologada() {
		return clave_homologada;
	}



	public void setClave_homologada(@Nullable String clave_homologada) {
		this.clave_homologada = clave_homologada;
	}


	public 	@Nullable Integer getId_sistema_origen() {
		return id_sistema_origen;
	}

	public void setId_sistema_origen(@Nullable Integer id_sistema_origen) {
		this.id_sistema_origen = id_sistema_origen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido_materno == null) ? 0 : apellido_materno.hashCode());
		result = prime * result + ((apellido_paterno == null) ? 0 : apellido_paterno.hashCode());
		result = prime * result + ((clave_homologada == null) ? 0 : clave_homologada.hashCode());
		result = prime * result + ((cliente_id == null) ? 0 : cliente_id.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((email_de_contacto == null) ? 0 : email_de_contacto.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((primer_nombre == null) ? 0 : primer_nombre.hashCode());
		result = prime * result + ((segundo_nombre == null) ? 0 : segundo_nombre.hashCode());
		result = prime * result + ((telefono_de_contacto == null) ? 0 : telefono_de_contacto.hashCode());
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
		if (clave_homologada == null) {
			if (other.clave_homologada != null)
				return false;
		} else if (!clave_homologada.equals(other.clave_homologada))
			return false;
		if (cliente_id == null) {
			if (other.cliente_id != null)
				return false;
		} else if (!cliente_id.equals(other.cliente_id))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (email_de_contacto == null) {
			if (other.email_de_contacto != null)
				return false;
		} else if (!email_de_contacto.equals(other.email_de_contacto))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
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
		if (telefono_de_contacto == null) {
			if (other.telefono_de_contacto != null)
				return false;
		} else if (!telefono_de_contacto.equals(other.telefono_de_contacto))
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
		return "ClienteDestinatario [id_cliente=" + id_cliente + ", primer_nombre=" + primer_nombre
				+ ", segundo_nombre=" + segundo_nombre + ", apellido_paterno=" + apellido_paterno
				+ ", apellido_materno=" + apellido_materno + ", clave_homologada=" + clave_homologada
				+ ", operacion_crud=" + operacion_crud + ", telefono_de_contacto=" + telefono_de_contacto
				+ ", email_de_contacto=" + email_de_contacto + ", direccion=" + direccion + ", cliente_id=" + cliente_id
				+ ", id_sistema_origen=" + id_sistema_origen
				+ "]";
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
	        		this.clave_homologada,
	        		this.operacion_crud,
	        		this.telefono_de_contacto,
	        		this.email_de_contacto,
	        		this.direccion,
	        		this.cliente_id,
					this.id_sistema_origen
	        		);
	    }
	}



}
