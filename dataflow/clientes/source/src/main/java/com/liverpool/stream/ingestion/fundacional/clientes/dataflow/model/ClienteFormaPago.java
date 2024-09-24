package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteFormaPago   implements Cloneable {

	@Nullable private Integer id_cliente_tarjeta;
    @Nullable private String numero_tarjeta_cliente;    
    @Nullable private String tipo_tarjeta;      
    @Nullable private String esta_borrado;
    @Nullable private String hash1_tarjeta;  
    @Nullable private Integer id_cliente;
    @Nullable private String fecha_actualizacion;
    @Nullable private Integer id_banco_emisor_tarjeta;
    @Nullable private Integer id_sistema_origen;
    @Nullable private String fecha_creacion;
	@Nullable private String operacion_crud;
	@Nullable private Integer id_direccion_de_tarjeta;
    
    public ClienteFormaPago() {}
    

    
   

	public ClienteFormaPago(Integer id_cliente_tarjeta, String numero_tarjeta_cliente, String tipo_tarjeta,
							String esta_borrado, String hash1_tarjeta, Integer id_cliente, String fecha_actualizacion,
							Integer id_banco_emisor_tarjeta, Integer id_sistema_origen, String fecha_creacion, String operacion_crud, Integer id_direccion_de_tarjeta) {
		super();
		this.id_cliente_tarjeta = id_cliente_tarjeta;
		this.numero_tarjeta_cliente = numero_tarjeta_cliente;
		this.tipo_tarjeta = tipo_tarjeta;
		this.esta_borrado = esta_borrado;
		this.hash1_tarjeta = hash1_tarjeta;
		this.id_cliente = id_cliente;
		this.fecha_actualizacion = fecha_actualizacion;
		this.id_banco_emisor_tarjeta = id_banco_emisor_tarjeta;
		this.id_sistema_origen = id_sistema_origen;
		this.fecha_creacion = fecha_creacion;
		this.operacion_crud = operacion_crud;
		this.id_direccion_de_tarjeta = id_direccion_de_tarjeta;
	}





	public @Nullable Integer getId_cliente_tarjeta() {
		return id_cliente_tarjeta;
	}







	public void setId_cliente_tarjeta(@Nullable Integer id_cliente_tarjeta) {
		this.id_cliente_tarjeta = id_cliente_tarjeta;
	}







	public @Nullable String getNumero_tarjeta_cliente() {
		return numero_tarjeta_cliente;
	}







	public void setNumero_tarjeta_cliente(@Nullable String numero_tarjeta_cliente) {
		this.numero_tarjeta_cliente = numero_tarjeta_cliente;
	}







	public @Nullable String getTipo_tarjeta() {
		return tipo_tarjeta;
	}







	public void setTipo_tarjeta(@Nullable String tipo_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
	}







	public @Nullable String getEsta_borrado() {
		return esta_borrado;
	}







	public void setEsta_borrado(@Nullable String esta_borrado) {
		this.esta_borrado = esta_borrado;
	}







	public @Nullable String getHash1_tarjeta() {
		return hash1_tarjeta;
	}







	public void setHash1_tarjeta(@Nullable String hash1_tarjeta) {
		this.hash1_tarjeta = hash1_tarjeta;
	}







	public @Nullable Integer getId_cliente() {
		return id_cliente;
	}







	public void setId_cliente(@Nullable Integer id_cliente) {
		this.id_cliente = id_cliente;
	}







	public @Nullable String getFecha_actualizacion() {
		return fecha_actualizacion;
	}







	public void setFecha_actualizacion(@Nullable String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}







	public @Nullable Integer getId_banco_emisor_tarjeta() {
		return id_banco_emisor_tarjeta;
	}







	public void setId_banco_emisor_tarjeta(@Nullable Integer id_banco_emisor_tarjeta) {
		this.id_banco_emisor_tarjeta = id_banco_emisor_tarjeta;
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







	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}





	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}



	public 	@Nullable Integer getId_direccion_de_tarjeta() {
		return id_direccion_de_tarjeta;
	}

	public void setId_direccion_de_tarjeta(@Nullable Integer id_direccion_de_tarjeta) {
		this.id_direccion_de_tarjeta = id_direccion_de_tarjeta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esta_borrado == null) ? 0 : esta_borrado.hashCode());
		result = prime * result + ((fecha_actualizacion == null) ? 0 : fecha_actualizacion.hashCode());
		result = prime * result + ((fecha_creacion == null) ? 0 : fecha_creacion.hashCode());
		result = prime * result + ((hash1_tarjeta == null) ? 0 : hash1_tarjeta.hashCode());
		result = prime * result + ((id_banco_emisor_tarjeta == null) ? 0 : id_banco_emisor_tarjeta.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((id_cliente_tarjeta == null) ? 0 : id_cliente_tarjeta.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
		result = prime * result + ((numero_tarjeta_cliente == null) ? 0 : numero_tarjeta_cliente.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((tipo_tarjeta == null) ? 0 : tipo_tarjeta.hashCode());
		result = prime * result + ((id_direccion_de_tarjeta == null) ? 0 : id_direccion_de_tarjeta.hashCode());
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
		ClienteFormaPago other = (ClienteFormaPago) obj;
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
		if (hash1_tarjeta == null) {
			if (other.hash1_tarjeta != null)
				return false;
		} else if (!hash1_tarjeta.equals(other.hash1_tarjeta))
			return false;
		if (id_banco_emisor_tarjeta == null) {
			if (other.id_banco_emisor_tarjeta != null)
				return false;
		} else if (!id_banco_emisor_tarjeta.equals(other.id_banco_emisor_tarjeta))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_tarjeta == null) {
			if (other.id_cliente_tarjeta != null)
				return false;
		} else if (!id_cliente_tarjeta.equals(other.id_cliente_tarjeta))
			return false;
		if (id_sistema_origen == null) {
			if (other.id_sistema_origen != null)
				return false;
		} else if (!id_sistema_origen.equals(other.id_sistema_origen))
			return false;
		if (numero_tarjeta_cliente == null) {
			if (other.numero_tarjeta_cliente != null)
				return false;
		} else if (!numero_tarjeta_cliente.equals(other.numero_tarjeta_cliente))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (tipo_tarjeta == null) {
			if (other.tipo_tarjeta != null)
				return false;
		} else if (!tipo_tarjeta.equals(other.tipo_tarjeta))
			return false;

		if (id_direccion_de_tarjeta == null) {
			if (other.id_direccion_de_tarjeta != null)
				return false;
		} else if (!id_direccion_de_tarjeta.equals(other.id_direccion_de_tarjeta))
			return false;
		return true;
	}



	


	@Override
	public String toString() {
		return "ClienteFormaPago [id_cliente_tarjeta=" + id_cliente_tarjeta + ", numero_tarjeta_cliente="
				+ numero_tarjeta_cliente + ", tipo_tarjeta=" + tipo_tarjeta + ", esta_activa=" + esta_borrado
				+ ", hash1_tarjeta=" + hash1_tarjeta + ", id_cliente=" + id_cliente + ", fecha_actualizacion="
				+ fecha_actualizacion + ", id_banco_emisor_tarjeta=" + id_banco_emisor_tarjeta + ", id_sistema_origen="
				+ id_sistema_origen + ", fecha_creacion=" + fecha_creacion + ", operacion_crud=" + operacion_crud + "]";
	}





	@Override
	public Object clone() {
		try {
	        return (ClienteFormaPago)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteFormaPago(
	        		this.id_cliente_tarjeta,
	        		this.numero_tarjeta_cliente,
	        		this.tipo_tarjeta,
	        		this.esta_borrado,
	        		this.hash1_tarjeta,
	        		this.id_cliente,
	        		this.fecha_actualizacion,
	        		this.id_banco_emisor_tarjeta,
	        		this.id_sistema_origen,
	        		this.fecha_creacion,
	        		this.operacion_crud,
					this.id_direccion_de_tarjeta
	        		);
	    }
	}
	
    
}
