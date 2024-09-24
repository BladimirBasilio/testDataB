package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@DefaultSchema(JavaBeanSchema.class)
public class ClienteAtg implements Cloneable {

	@Nullable private Integer id_cliente_atg;
    @Nullable private String id_atg;    
    @Nullable private String sistema_fuente_atg;      
    @Nullable private Integer id_cliente;
    @Nullable private String realm_id;    
    @Nullable private String id_facebook;
    @Nullable private Integer id_sistema_operativo_dispositivo;
    @Nullable private String id_apple;
	@Nullable private String baja_de_tarjeta;
	@Nullable private String alta_de_tarjeta;
	@Nullable private String id_tipo_tarjeta;
	@Nullable private String hash_1;
	@Nullable private String operacion_crud;
    
    public ClienteAtg() {}
    

	public ClienteAtg(Integer id_cliente_atg, String id_atg, String sistema_fuente_atg, Integer id_cliente,
			String realm_id, String id_facebook, Integer id_sistema_operativo_dispositivo, String id_apple
					  , String baja_de_tarjeta, String alta_de_tarjeta, String id_tipo_tarjeta, String hash_1, String operacion_crud
	) {
		super();
		this.id_cliente_atg = id_cliente_atg;
		this.id_atg = id_atg;
		this.sistema_fuente_atg = sistema_fuente_atg;
		this.id_cliente = id_cliente;
		this.realm_id = realm_id;
		this.id_facebook = id_facebook;
		this.id_sistema_operativo_dispositivo = id_sistema_operativo_dispositivo;
		this.id_apple = id_apple;
		this.baja_de_tarjeta = baja_de_tarjeta;
		this.alta_de_tarjeta = alta_de_tarjeta;
		this.id_tipo_tarjeta = id_tipo_tarjeta;
		this.hash_1 = hash_1;
		this.operacion_crud = operacion_crud;
	}



	
	



	public @Nullable Integer getId_cliente_atg() {
		return id_cliente_atg;
	}






	public void setId_cliente_atg(@Nullable Integer id_cliente_atg) {
		this.id_cliente_atg = id_cliente_atg;
	}






	public @Nullable String getId_atg() {
		return id_atg;
	}






	public void setId_atg(@Nullable String id_atg) {
		this.id_atg = id_atg;
	}






	public @Nullable String getSistema_fuente_atg() {
		return sistema_fuente_atg;
	}






	public void setSistema_fuente_atg(@Nullable String sistema_fuente_atg) {
		this.sistema_fuente_atg = sistema_fuente_atg;
	}






	public @Nullable Integer getId_cliente() {
		return id_cliente;
	}






	public void setId_cliente(@Nullable Integer id_cliente) {
		this.id_cliente = id_cliente;
	}






	public @Nullable String getRealm_id() {
		return realm_id;
	}






	public void setRealm_id(@Nullable String realm_id) {
		this.realm_id = realm_id;
	}






	public @Nullable String getId_facebook() {
		return id_facebook;
	}






	public void setId_facebook(@Nullable String id_facebook) {
		this.id_facebook = id_facebook;
	}






	public @Nullable Integer getId_sistema_operativo_dispositivo() {
		return id_sistema_operativo_dispositivo;
	}






	public void setId_sistema_operativo_dispositivo(@Nullable Integer id_sistema_operativo_dispositivo) {
		this.id_sistema_operativo_dispositivo = id_sistema_operativo_dispositivo;
	}






	public @Nullable String getId_apple() {
		return id_apple;
	}






	public void setId_apple(@Nullable String id_apple) {
		this.id_apple = id_apple;
	}



	public @Nullable String getBaja_de_tarjeta() {
		return baja_de_tarjeta;
	}

	public void setBaja_de_tarjeta(@Nullable String baja_de_tarjeta) {
		this.baja_de_tarjeta = baja_de_tarjeta;
	}


	public @Nullable String getAlta_de_tarjeta() {
		return alta_de_tarjeta;
	}

	public void setAlta_de_tarjeta(@Nullable String alta_de_tarjeta) {
		this.alta_de_tarjeta = alta_de_tarjeta;
	}


	public @Nullable String getId_tipo_tarjeta() {
		return id_tipo_tarjeta;
	}

	public void setId_tipo_tarjeta(@Nullable String id_tipo_tarjeta) {
		this.id_tipo_tarjeta = id_tipo_tarjeta;
	}


	public @Nullable String getHash_1() {
		return hash_1;
	}

	public void setHash_1(@Nullable String hash_1) {
		this.hash_1 = hash_1;
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
		result = prime * result + ((id_apple == null) ? 0 : id_apple.hashCode());
		result = prime * result + ((id_atg == null) ? 0 : id_atg.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((id_cliente_atg == null) ? 0 : id_cliente_atg.hashCode());
		result = prime * result + ((id_facebook == null) ? 0 : id_facebook.hashCode());
		result = prime * result
				+ ((id_sistema_operativo_dispositivo == null) ? 0 : id_sistema_operativo_dispositivo.hashCode());
		result = prime * result + ((realm_id == null) ? 0 : realm_id.hashCode());
		result = prime * result + ((sistema_fuente_atg == null) ? 0 : sistema_fuente_atg.hashCode());

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
		ClienteAtg other = (ClienteAtg) obj;
		if (id_apple == null) {
			if (other.id_apple != null)
				return false;
		} else if (!id_apple.equals(other.id_apple))
			return false;
		if (id_atg == null) {
			if (other.id_atg != null)
				return false;
		} else if (!id_atg.equals(other.id_atg))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_atg == null) {
			if (other.id_cliente_atg != null)
				return false;
		} else if (!id_cliente_atg.equals(other.id_cliente_atg))
			return false;
		if (id_facebook == null) {
			if (other.id_facebook != null)
				return false;
		} else if (!id_facebook.equals(other.id_facebook))
			return false;
		if (id_sistema_operativo_dispositivo == null) {
			if (other.id_sistema_operativo_dispositivo != null)
				return false;
		} else if (!id_sistema_operativo_dispositivo.equals(other.id_sistema_operativo_dispositivo))
			return false;
		if (realm_id == null) {
			if (other.realm_id != null)
				return false;
		} else if (!realm_id.equals(other.realm_id))
			return false;
		if (sistema_fuente_atg == null) {
			if (other.sistema_fuente_atg != null)
				return false;
		} else if (!sistema_fuente_atg.equals(other.sistema_fuente_atg))
			return false;
		return true;
	}


	@java.lang.Override
	public java.lang.String toString() {
		return "ClienteAtg{" +
				"id_cliente_atg=" + id_cliente_atg +
				", id_atg='" + id_atg + '\'' +
				", sistema_fuente_atg='" + sistema_fuente_atg + '\'' +
				", id_cliente=" + id_cliente +
				", realm_id='" + realm_id + '\'' +
				", id_facebook='" + id_facebook + '\'' +
				", id_sistema_operativo_dispositivo=" + id_sistema_operativo_dispositivo +
				", id_apple='" + id_apple + '\'' +
				", baja_de_tarjeta='" + baja_de_tarjeta + '\'' +
				", alta_de_tarjeta='" + alta_de_tarjeta + '\'' +
				", id_tipo_tarjeta='" + id_tipo_tarjeta + '\'' +
				", hash_1='" + hash_1 + '\'' +
				", operacion_crud='" + operacion_crud + '\'' +
				'}';
	}

	@Override
	public Object clone() {
		try {
	        return (ClienteAtg)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteAtg(
	        		this.id_cliente_atg,
	        		this.id_atg,
	        		this.sistema_fuente_atg,
	        		this.id_cliente,
	        		this.realm_id,
	        		this.id_facebook,
	        		this.id_sistema_operativo_dispositivo,
	        		this.id_apple,
					this.baja_de_tarjeta,
					this.alta_de_tarjeta,
					this.id_tipo_tarjeta,
					this.hash_1,
					this.operacion_crud
	        		);
	    }
	}
	
    
}
