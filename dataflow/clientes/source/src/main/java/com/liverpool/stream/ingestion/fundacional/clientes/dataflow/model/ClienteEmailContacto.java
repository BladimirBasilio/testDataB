package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteEmailContacto  implements Cloneable  {

	@Nullable private Integer id_cliente_emails_de_contacto; 
	@Nullable private Integer id_cliente; 
	@Nullable private Integer id_sistema_origen; 
	@Nullable private String fecha_creacion; 
	@Nullable private String fecha_actualizacion; 
	@Nullable private String tipo; 
	@Nullable private String email; 
	@Nullable private String esta_borrado; 
	@Nullable private String id_transaccion; 
	@Nullable private String id_operacion; 
	@Nullable private String salida_correo_electronico; 
	@Nullable private String salida_tipo; 
	@Nullable private String bandera_tipo; 
	@Nullable private String token_correo_electronico; 
	@Nullable private String fecha_operacion;
	@Nullable private String operacion_crud;
    
    
    public ClienteEmailContacto() {}

    


	public ClienteEmailContacto(Integer id_cliente_emails_de_contacto, Integer id_cliente, Integer id_sistema_origen,
			String fecha_creacion, String fecha_actualizacion, String tipo, String email, String esta_borrado,
			String id_transaccion, String id_operacion, String salida_correo_electronico, String salida_tipo,
			String bandera_tipo, String token_correo_electronico, String fecha_operacion, String operacion_crud) {
		super();
		this.id_cliente_emails_de_contacto = id_cliente_emails_de_contacto;
		this.id_cliente = id_cliente;
		this.id_sistema_origen = id_sistema_origen;
		this.fecha_creacion = fecha_creacion;
		this.fecha_actualizacion = fecha_actualizacion;
		this.tipo = tipo;
		this.email = email;
		this.esta_borrado = esta_borrado;
		this.id_transaccion = id_transaccion;
		this.id_operacion = id_operacion;
		this.salida_correo_electronico = salida_correo_electronico;
		this.salida_tipo = salida_tipo;
		this.bandera_tipo = bandera_tipo;
		this.token_correo_electronico = token_correo_electronico;
		this.fecha_operacion = fecha_operacion;
		this.operacion_crud = operacion_crud;
	}









	public @Nullable Integer getId_cliente_emails_de_contacto() {
		return id_cliente_emails_de_contacto;
	}



	public void setId_cliente_emails_de_contacto(@Nullable Integer id_cliente_emails_de_contacto) {
		this.id_cliente_emails_de_contacto = id_cliente_emails_de_contacto;
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



	public @Nullable String getFecha_creacion() {
		return fecha_creacion;
	}



	public void setFecha_creacion(@Nullable String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}



	public @Nullable String getFecha_actualizacion() {
		return fecha_actualizacion;
	}



	public void setFecha_actualizacion(@Nullable String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}



	public @Nullable String getTipo() {
		return tipo;
	}



	public void setTipo(@Nullable String tipo) {
		this.tipo = tipo;
	}



	public @Nullable String getEmail() {
		return email;
	}



	public void setEmail(@Nullable String email) {
		this.email = email;
	}



	public @Nullable String getEsta_borrado() {
		return esta_borrado;
	}



	public void setEsta_borrado(@Nullable String esta_borrado) {
		this.esta_borrado = esta_borrado;
	}



	public @Nullable String getId_transaccion() {
		return id_transaccion;
	}



	public void setId_transaccion(@Nullable String id_transaccion) {
		this.id_transaccion = id_transaccion;
	}



	public @Nullable String getId_operacion() {
		return id_operacion;
	}



	public void setId_operacion(@Nullable String id_operacion) {
		this.id_operacion = id_operacion;
	}



	public @Nullable String getSalida_correo_electronico() {
		return salida_correo_electronico;
	}



	public void setSalida_correo_electronico(@Nullable String salida_correo_electronico) {
		this.salida_correo_electronico = salida_correo_electronico;
	}



	public @Nullable String getSalida_tipo() {
		return salida_tipo;
	}



	public void setSalida_tipo(@Nullable String salida_tipo) {
		this.salida_tipo = salida_tipo;
	}



	public @Nullable String getBandera_tipo() {
		return bandera_tipo;
	}



	public void setBandera_tipo(@Nullable String bandera_tipo) {
		this.bandera_tipo = bandera_tipo;
	}



	public @Nullable String getToken_correo_electronico() {
		return token_correo_electronico;
	}



	public void setToken_correo_electronico(@Nullable String token_correo_electronico) {
		this.token_correo_electronico = token_correo_electronico;
	}



	public @Nullable String getFecha_operacion() {
		return fecha_operacion;
	}



	public void setFecha_operacion(@Nullable String fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
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
		result = prime * result + ((bandera_tipo == null) ? 0 : bandera_tipo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((esta_borrado == null) ? 0 : esta_borrado.hashCode());
		result = prime * result + ((fecha_actualizacion == null) ? 0 : fecha_actualizacion.hashCode());
		result = prime * result + ((fecha_creacion == null) ? 0 : fecha_creacion.hashCode());
		result = prime * result + ((fecha_operacion == null) ? 0 : fecha_operacion.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result
				+ ((id_cliente_emails_de_contacto == null) ? 0 : id_cliente_emails_de_contacto.hashCode());
		result = prime * result + ((id_operacion == null) ? 0 : id_operacion.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
		result = prime * result + ((id_transaccion == null) ? 0 : id_transaccion.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((salida_correo_electronico == null) ? 0 : salida_correo_electronico.hashCode());
		result = prime * result + ((salida_tipo == null) ? 0 : salida_tipo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((token_correo_electronico == null) ? 0 : token_correo_electronico.hashCode());
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
		ClienteEmailContacto other = (ClienteEmailContacto) obj;
		if (bandera_tipo == null) {
			if (other.bandera_tipo != null)
				return false;
		} else if (!bandera_tipo.equals(other.bandera_tipo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (esta_borrado == null) {
			if (other.esta_borrado != null)
				return false;
		} else if (!esta_borrado.equals(other.esta_borrado))
			return false;
		if (fecha_actualizacion == null) {
			if (other.fecha_actualizacion != null)
				return false;
		} else if (!fecha_actualizacion.equals(other.fecha_actualizacion))
			return false;
		if (fecha_creacion == null) {
			if (other.fecha_creacion != null)
				return false;
		} else if (!fecha_creacion.equals(other.fecha_creacion))
			return false;
		if (fecha_operacion == null) {
			if (other.fecha_operacion != null)
				return false;
		} else if (!fecha_operacion.equals(other.fecha_operacion))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_emails_de_contacto == null) {
			if (other.id_cliente_emails_de_contacto != null)
				return false;
		} else if (!id_cliente_emails_de_contacto.equals(other.id_cliente_emails_de_contacto))
			return false;
		if (id_operacion == null) {
			if (other.id_operacion != null)
				return false;
		} else if (!id_operacion.equals(other.id_operacion))
			return false;
		if (id_sistema_origen == null) {
			if (other.id_sistema_origen != null)
				return false;
		} else if (!id_sistema_origen.equals(other.id_sistema_origen))
			return false;
		if (id_transaccion == null) {
			if (other.id_transaccion != null)
				return false;
		} else if (!id_transaccion.equals(other.id_transaccion))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (salida_correo_electronico == null) {
			if (other.salida_correo_electronico != null)
				return false;
		} else if (!salida_correo_electronico.equals(other.salida_correo_electronico))
			return false;
		if (salida_tipo == null) {
			if (other.salida_tipo != null)
				return false;
		} else if (!salida_tipo.equals(other.salida_tipo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (token_correo_electronico == null) {
			if (other.token_correo_electronico != null)
				return false;
		} else if (!token_correo_electronico.equals(other.token_correo_electronico))
			return false;
		return true;
	}


	
	


	@Override
	public String toString() {
		return "ClienteEmailContacto [id_cliente_emails_de_contacto=" + id_cliente_emails_de_contacto + ", id_cliente="
				+ id_cliente + ", id_sistema_origen=" + id_sistema_origen + ", fecha_creacion=" + fecha_creacion
				+ ", fecha_actualizacion=" + fecha_actualizacion + ", tipo=" + tipo + ", email=" + email
				+ ", esta_borrado=" + esta_borrado + ", id_transaccion=" + id_transaccion + ", id_operacion="
				+ id_operacion + ", salida_correo_electronico=" + salida_correo_electronico + ", salida_tipo="
				+ salida_tipo + ", bandera_tipo=" + bandera_tipo + ", token_correo_electronico="
				+ token_correo_electronico + ", fecha_operacion=" + fecha_operacion + ", operacion_crud="
				+ operacion_crud + "]";
	}




	@Override
	public Object clone() {
		try {
	        return (ClienteEmailContacto)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteEmailContacto(
	        		this.id_cliente_emails_de_contacto,
	        		this.id_cliente,
	        		this.id_sistema_origen,
	        		this.fecha_creacion,
	        		this.fecha_actualizacion,
	        		this.tipo,
	        		this.email,
	        		this.esta_borrado,
	        		this.id_transaccion,
	        		this.id_operacion,
	        		this.salida_correo_electronico,
	        		this.salida_tipo,
	        		this.bandera_tipo,
	        		this.token_correo_electronico,
	        		this.fecha_operacion,
	        		this.operacion_crud
	        		);
	    }
	}
	
    
}
