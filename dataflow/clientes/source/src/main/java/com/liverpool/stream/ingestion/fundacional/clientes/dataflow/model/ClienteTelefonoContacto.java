package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteTelefonoContacto   implements Cloneable {
    
    @Nullable private Integer id_cliente_telefonos_de_contacto; 
    @Nullable private Integer id_cliente; 
    @Nullable private Integer id_sistema_origen; 
    @Nullable private String fecha_creacion; 
    @Nullable private String alias_telefono; 
    @Nullable private String telefono; 
    @Nullable private String fecha_actualizacion; 
    @Nullable private String esta_borrado; 
    @Nullable private String id_transaccion; 
    @Nullable private String id_operacion; 
    @Nullable private String lada; 
    @Nullable private String extension_telefonica; 
    @Nullable private String codigo_zip; 
    @Nullable private Integer id_provincia; 
    @Nullable private Integer id_ciudad; 
    @Nullable private String salida_numero_de_telefono; 
    @Nullable private String salida_tipo_de_telefono; 
    @Nullable private String bandera_telefono; 
    @Nullable private String salida_extension_telefonica; 
    @Nullable private String fecha_de_operacion;
    @Nullable private String operacion_crud;
    
    
    public ClienteTelefonoContacto() {}
    



	public ClienteTelefonoContacto(Integer id_cliente_telefonos_de_contacto, Integer id_cliente,
			Integer id_sistema_origen, String fecha_creacion, String alias_telefono, String telefono,
			String fecha_actualizacion, String esta_borrado, String id_transaccion, String id_operacion, String lada,
			String extension_telefonica, String codigo_zip, Integer id_provincia, Integer id_ciudad,
			String salida_numero_de_telefono, String salida_tipo_de_telefono, String bandera_telefono,
			String salida_extension_telefonica, String fecha_de_operacion, String operacion_crud) {
		super();
		this.id_cliente_telefonos_de_contacto = id_cliente_telefonos_de_contacto;
		this.id_cliente = id_cliente;
		this.id_sistema_origen = id_sistema_origen;
		this.fecha_creacion = fecha_creacion;
		this.alias_telefono = alias_telefono;
		this.telefono = telefono;
		this.fecha_actualizacion = fecha_actualizacion;
		this.esta_borrado = esta_borrado;
		this.id_transaccion = id_transaccion;
		this.id_operacion = id_operacion;
		this.lada = lada;
		this.extension_telefonica = extension_telefonica;
		this.codigo_zip = codigo_zip;
		this.id_provincia = id_provincia;
		this.id_ciudad = id_ciudad;
		this.salida_numero_de_telefono = salida_numero_de_telefono;
		this.salida_tipo_de_telefono = salida_tipo_de_telefono;
		this.bandera_telefono = bandera_telefono;
		this.salida_extension_telefonica = salida_extension_telefonica;
		this.fecha_de_operacion = fecha_de_operacion;
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





	public @Nullable String getFecha_creacion() {
		return fecha_creacion;
	}





	public void setFecha_creacion(@Nullable String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}





	public @Nullable String getAlias_telefono() {
		return alias_telefono;
	}





	public void setAlias_telefono(@Nullable String alias_telefono) {
		this.alias_telefono = alias_telefono;
	}





	public @Nullable String getTelefono() {
		return telefono;
	}





	public void setTelefono(@Nullable String telefono) {
		this.telefono = telefono;
	}





	public @Nullable String getFecha_actualizacion() {
		return fecha_actualizacion;
	}





	public void setFecha_actualizacion(@Nullable String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
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





	public @Nullable String getLada() {
		return lada;
	}





	public void setLada(@Nullable String lada) {
		this.lada = lada;
	}





	public @Nullable String getExtension_telefonica() {
		return extension_telefonica;
	}





	public void setExtension_telefonica(@Nullable String extension_telefonica) {
		this.extension_telefonica = extension_telefonica;
	}





	public @Nullable String getCodigo_zip() {
		return codigo_zip;
	}





	public void setCodigo_zip(@Nullable String codigo_zip) {
		this.codigo_zip = codigo_zip;
	}





	public @Nullable Integer getId_provincia() {
		return id_provincia;
	}





	public void setId_provincia(@Nullable Integer id_provincia) {
		this.id_provincia = id_provincia;
	}





	public @Nullable Integer getId_ciudad() {
		return id_ciudad;
	}





	public void setId_ciudad(@Nullable Integer id_ciudad) {
		this.id_ciudad = id_ciudad;
	}





	public @Nullable String getSalida_numero_de_telefono() {
		return salida_numero_de_telefono;
	}





	public void setSalida_numero_de_telefono(@Nullable String salida_numero_de_telefono) {
		this.salida_numero_de_telefono = salida_numero_de_telefono;
	}





	public @Nullable String getSalida_tipo_de_telefono() {
		return salida_tipo_de_telefono;
	}





	public void setSalida_tipo_de_telefono(@Nullable String salida_tipo_de_telefono) {
		this.salida_tipo_de_telefono = salida_tipo_de_telefono;
	}





	public @Nullable String getBandera_telefono() {
		return bandera_telefono;
	}





	public void setBandera_telefono(@Nullable String bandera_telefono) {
		this.bandera_telefono = bandera_telefono;
	}





	public @Nullable String getSalida_extension_telefonica() {
		return salida_extension_telefonica;
	}





	public void setSalida_extension_telefonica(@Nullable String salida_extension_telefonica) {
		this.salida_extension_telefonica = salida_extension_telefonica;
	}





	public @Nullable String getFecha_de_operacion() {
		return fecha_de_operacion;
	}





	public void setFecha_de_operacion(@Nullable String fecha_de_operacion) {
		this.fecha_de_operacion = fecha_de_operacion;
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
		result = prime * result + ((bandera_telefono == null) ? 0 : bandera_telefono.hashCode());
		result = prime * result + ((codigo_zip == null) ? 0 : codigo_zip.hashCode());
		result = prime * result + ((esta_borrado == null) ? 0 : esta_borrado.hashCode());
		result = prime * result + ((extension_telefonica == null) ? 0 : extension_telefonica.hashCode());
		result = prime * result + ((fecha_actualizacion == null) ? 0 : fecha_actualizacion.hashCode());
		result = prime * result + ((fecha_creacion == null) ? 0 : fecha_creacion.hashCode());
		result = prime * result + ((fecha_de_operacion == null) ? 0 : fecha_de_operacion.hashCode());
		result = prime * result + ((id_ciudad == null) ? 0 : id_ciudad.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result
				+ ((id_cliente_telefonos_de_contacto == null) ? 0 : id_cliente_telefonos_de_contacto.hashCode());
		result = prime * result + ((id_operacion == null) ? 0 : id_operacion.hashCode());
		result = prime * result + ((id_provincia == null) ? 0 : id_provincia.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
		result = prime * result + ((id_transaccion == null) ? 0 : id_transaccion.hashCode());
		result = prime * result + ((lada == null) ? 0 : lada.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((salida_extension_telefonica == null) ? 0 : salida_extension_telefonica.hashCode());
		result = prime * result + ((salida_numero_de_telefono == null) ? 0 : salida_numero_de_telefono.hashCode());
		result = prime * result + ((salida_tipo_de_telefono == null) ? 0 : salida_tipo_de_telefono.hashCode());
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
		if (bandera_telefono == null) {
			if (other.bandera_telefono != null)
				return false;
		} else if (!bandera_telefono.equals(other.bandera_telefono))
			return false;
		if (codigo_zip == null) {
			if (other.codigo_zip != null)
				return false;
		} else if (!codigo_zip.equals(other.codigo_zip))
			return false;
		if (esta_borrado == null) {
			if (other.esta_borrado != null)
				return false;
		} else if (!esta_borrado.equals(other.esta_borrado))
			return false;
		if (extension_telefonica == null) {
			if (other.extension_telefonica != null)
				return false;
		} else if (!extension_telefonica.equals(other.extension_telefonica))
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
		if (fecha_de_operacion == null) {
			if (other.fecha_de_operacion != null)
				return false;
		} else if (!fecha_de_operacion.equals(other.fecha_de_operacion))
			return false;
		if (id_ciudad == null) {
			if (other.id_ciudad != null)
				return false;
		} else if (!id_ciudad.equals(other.id_ciudad))
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
		if (id_operacion == null) {
			if (other.id_operacion != null)
				return false;
		} else if (!id_operacion.equals(other.id_operacion))
			return false;
		if (id_provincia == null) {
			if (other.id_provincia != null)
				return false;
		} else if (!id_provincia.equals(other.id_provincia))
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
		if (lada == null) {
			if (other.lada != null)
				return false;
		} else if (!lada.equals(other.lada))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (salida_extension_telefonica == null) {
			if (other.salida_extension_telefonica != null)
				return false;
		} else if (!salida_extension_telefonica.equals(other.salida_extension_telefonica))
			return false;
		if (salida_numero_de_telefono == null) {
			if (other.salida_numero_de_telefono != null)
				return false;
		} else if (!salida_numero_de_telefono.equals(other.salida_numero_de_telefono))
			return false;
		if (salida_tipo_de_telefono == null) {
			if (other.salida_tipo_de_telefono != null)
				return false;
		} else if (!salida_tipo_de_telefono.equals(other.salida_tipo_de_telefono))
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
				+ ", id_cliente=" + id_cliente + ", id_sistema_origen=" + id_sistema_origen + ", fecha_creacion="
				+ fecha_creacion + ", alias_telefono=" + alias_telefono + ", telefono=" + telefono
				+ ", fecha_actualizacion=" + fecha_actualizacion + ", esta_borrado=" + esta_borrado
				+ ", id_transaccion=" + id_transaccion + ", id_operacion=" + id_operacion + ", lada=" + lada
				+ ", extension_telefonica=" + extension_telefonica + ", codigo_zip=" + codigo_zip + ", id_provincia="
				+ id_provincia + ", id_ciudad=" + id_ciudad + ", salida_numero_de_telefono=" + salida_numero_de_telefono
				+ ", salida_tipo_de_telefono=" + salida_tipo_de_telefono + ", bandera_telefono=" + bandera_telefono
				+ ", salida_extension_telefonica=" + salida_extension_telefonica + ", fecha_de_operacion="
				+ fecha_de_operacion + ", operacion_crud=" + operacion_crud + "]";
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
	        		this.fecha_creacion,
	        		this.alias_telefono,
	        		this.telefono,
	        		this.fecha_actualizacion,
	        		this.esta_borrado,
	        		this.id_transaccion,
	        		this.id_operacion,
	        		this.lada,
	        		this.extension_telefonica,
	        		this.codigo_zip,
	        		this.id_provincia,
	        		this.id_ciudad,
	        		this.salida_numero_de_telefono,
	        		this.salida_tipo_de_telefono,
	        		this.bandera_telefono,
	        		this.salida_extension_telefonica,
	        		this.fecha_de_operacion,
	        		this.operacion_crud
	        		);
	    }
	}
	
    
}
