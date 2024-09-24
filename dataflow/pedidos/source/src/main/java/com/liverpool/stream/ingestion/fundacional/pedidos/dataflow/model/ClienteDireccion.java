package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteDireccion {

	@Nullable private Integer id_direccion_del_cliente;
	@Nullable private Integer id_cliente;
	@Nullable private String calle;
	@Nullable private String numero_exterior;
	@Nullable private String numero_interior;
	@Nullable private String codigo_postal;
	@Nullable private Integer id_colonia;
	@Nullable private Integer id_municipio;
	@Nullable private Integer id_estado_del_pais;
	@Nullable private String entre_calle_1;
	@Nullable private String entre_calle_2;
	@Nullable private String operacion_crud;
	@Nullable private Integer id_sistema_origen;
	@Nullable private Integer id_cliente_destinatario;
	@Nullable private String ciudad;
	@Nullable private String edificio;
	
    
    public ClienteDireccion() {}
  
    

	public ClienteDireccion(Integer id_direccion_del_cliente, Integer id_cliente, String calle, String numero_exterior,
			String numero_interior, String codigo_postal, Integer id_colonia, Integer id_municipio,
			Integer id_estado_del_pais, String entre_calle_1, String entre_calle_2, String operacion_crud,
			Integer id_cliente_destinatario,Integer id_sistema_origen, String ciudad, String edificio ) {
		super();
		this.id_direccion_del_cliente = id_direccion_del_cliente;
		this.id_cliente = id_cliente;
		this.calle = calle;
		this.numero_exterior = numero_exterior;
		this.numero_interior = numero_interior;
		this.codigo_postal = codigo_postal;
		this.id_colonia = id_colonia;
		this.id_municipio = id_municipio;
		this.id_estado_del_pais = id_estado_del_pais;
		this.entre_calle_1 = entre_calle_1;
		this.entre_calle_2 = entre_calle_2;
		this.operacion_crud = operacion_crud;
		this.id_cliente_destinatario = id_cliente_destinatario;
		this.id_sistema_origen = id_sistema_origen;
		this.ciudad = ciudad;
		this.edificio = edificio;
	}




	public @Nullable Integer getId_direccion_del_cliente() {
		return id_direccion_del_cliente;
	}




	public void setId_direccion_del_cliente(@Nullable Integer id_direccion_del_cliente) {
		this.id_direccion_del_cliente = id_direccion_del_cliente;
	}




	public @Nullable Integer getId_cliente() {
		return id_cliente;
	}




	public void setId_cliente(@Nullable Integer id_cliente) {
		this.id_cliente = id_cliente;
	}




	public @Nullable String getCalle() {
		return calle;
	}




	public void setCalle(@Nullable String calle) {
		this.calle = calle;
	}




	public @Nullable String getNumero_exterior() {
		return numero_exterior;
	}




	public void setNumero_exterior(@Nullable String numero_exterior) {
		this.numero_exterior = numero_exterior;
	}




	public @Nullable String getNumero_interior() {
		return numero_interior;
	}




	public void setNumero_interior(@Nullable String numero_interior) {
		this.numero_interior = numero_interior;
	}




	public @Nullable String getCodigo_postal() {
		return codigo_postal;
	}




	public void setCodigo_postal(@Nullable String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}




	public @Nullable Integer getId_colonia() {
		return id_colonia;
	}




	public void setId_colonia(@Nullable Integer id_colonia) {
		this.id_colonia = id_colonia;
	}




	public @Nullable Integer getId_municipio() {
		return id_municipio;
	}




	public void setId_municipio(@Nullable Integer id_municipio) {
		this.id_municipio = id_municipio;
	}




	public @Nullable Integer getId_estado_del_pais() {
		return id_estado_del_pais;
	}




	public void setId_estado_del_pais(@Nullable Integer id_estado_del_pais) {
		this.id_estado_del_pais = id_estado_del_pais;
	}




	public @Nullable String getEntre_calle_1() {
		return entre_calle_1;
	}




	public void setEntre_calle_1(@Nullable String entre_calle_1) {
		this.entre_calle_1 = entre_calle_1;
	}




	public @Nullable String getEntre_calle_2() {
		return entre_calle_2;
	}




	public void setEntre_calle_2(@Nullable String entre_calle_2) {
		this.entre_calle_2 = entre_calle_2;
	}


	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}


	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}
	
	

	public @Nullable Integer getId_cliente_destinatario() {
		return id_cliente_destinatario;
	}



	public void setId_cliente_destinatario(@Nullable Integer id_cliente_destinatario) {
		this.id_cliente_destinatario = id_cliente_destinatario;
	}



	public @Nullable Integer getId_sistema_origen() {
		return id_sistema_origen;
	}

	public void setId_sistema_origen(@Nullable Integer id_sistema_origen) {
		this.id_sistema_origen = id_sistema_origen;
	}


	public @Nullable String getCiudad() {
		return ciudad;
	}

	public void setCiudad(@Nullable String ciudad) {
		this.ciudad = ciudad;
	}


	public @Nullable String getEdificio() {
		return edificio;
	}

	public void setEdificio(@Nullable String edificio) {
		this.edificio = edificio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((codigo_postal == null) ? 0 : codigo_postal.hashCode());
		result = prime * result + ((entre_calle_1 == null) ? 0 : entre_calle_1.hashCode());
		result = prime * result + ((entre_calle_2 == null) ? 0 : entre_calle_2.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((id_cliente_destinatario == null) ? 0 : id_cliente_destinatario.hashCode());
		result = prime * result + ((id_colonia == null) ? 0 : id_colonia.hashCode());
		result = prime * result + ((id_direccion_del_cliente == null) ? 0 : id_direccion_del_cliente.hashCode());
		result = prime * result + ((id_estado_del_pais == null) ? 0 : id_estado_del_pais.hashCode());
		result = prime * result + ((id_municipio == null) ? 0 : id_municipio.hashCode());
		result = prime * result + ((numero_exterior == null) ? 0 : numero_exterior.hashCode());
		result = prime * result + ((numero_interior == null) ? 0 : numero_interior.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((edificio == null) ? 0 : edificio.hashCode());
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
		ClienteDireccion other = (ClienteDireccion) obj;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (codigo_postal == null) {
			if (other.codigo_postal != null)
				return false;
		} else if (!codigo_postal.equals(other.codigo_postal))
			return false;
		if (entre_calle_1 == null) {
			if (other.entre_calle_1 != null)
				return false;
		} else if (!entre_calle_1.equals(other.entre_calle_1))
			return false;
		if (entre_calle_2 == null) {
			if (other.entre_calle_2 != null)
				return false;
		} else if (!entre_calle_2.equals(other.entre_calle_2))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_cliente_destinatario == null) {
			if (other.id_cliente_destinatario != null)
				return false;
		} else if (!id_cliente_destinatario.equals(other.id_cliente_destinatario))
			return false;
		if (id_colonia == null) {
			if (other.id_colonia != null)
				return false;
		} else if (!id_colonia.equals(other.id_colonia))
			return false;
		if (id_direccion_del_cliente == null) {
			if (other.id_direccion_del_cliente != null)
				return false;
		} else if (!id_direccion_del_cliente.equals(other.id_direccion_del_cliente))
			return false;
		if (id_estado_del_pais == null) {
			if (other.id_estado_del_pais != null)
				return false;
		} else if (!id_estado_del_pais.equals(other.id_estado_del_pais))
			return false;
		if (id_municipio == null) {
			if (other.id_municipio != null)
				return false;
		} else if (!id_municipio.equals(other.id_municipio))
			return false;
		if (numero_exterior == null) {
			if (other.numero_exterior != null)
				return false;
		} else if (!numero_exterior.equals(other.numero_exterior))
			return false;
		if (numero_interior == null) {
			if (other.numero_interior != null)
				return false;
		} else if (!numero_interior.equals(other.numero_interior))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (id_sistema_origen == null) {
			if (other.id_sistema_origen != null)
				return false;
		} else if (!id_sistema_origen.equals(other.id_sistema_origen))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (edificio == null) {
			if (other.edificio != null)
				return false;
		} else if (!edificio.equals(other.edificio))
			return false;
		return true;
	}


	
	

	@Override
	public String toString() {
		return "ClienteDireccion [id_direccion_del_cliente=" + id_direccion_del_cliente + ", id_cliente=" + id_cliente
				+ ", calle=" + calle + ", numero_exterior=" + numero_exterior + ", numero_interior=" + numero_interior
				+ ", codigo_postal=" + codigo_postal + ", id_colonia=" + id_colonia + ", id_municipio=" + id_municipio
				+ ", id_estado_del_pais=" + id_estado_del_pais + ", entre_calle_1=" + entre_calle_1 + ", entre_calle_2="
				+ entre_calle_2 + ", operacion_crud=" + operacion_crud + ", id_cliente_destinatario="
				+ id_cliente_destinatario + ", id_sistema_origen="
				+ id_sistema_origen + ", ciudad =" + ciudad + ", edificio =" + edificio + "]";
	}



	@Override
	public Object clone() {
		try {
	        return (ClienteDireccion)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new ClienteDireccion(
	        		this.id_direccion_del_cliente,
	        		this.id_cliente,
	        		this.calle,
	        		this.numero_exterior,
	        		this.numero_interior,
	        		this.codigo_postal,
	        		this.id_colonia,
	        		this.id_municipio,
	        		this.id_estado_del_pais,
	        		this.entre_calle_1,
	        		this.entre_calle_2,
	        		this.operacion_crud,
	        		this.id_cliente_destinatario,
					this.id_sistema_origen,
					this.ciudad,
					this.edificio
	        		);
	    }
	}
    
    
    
    
}
