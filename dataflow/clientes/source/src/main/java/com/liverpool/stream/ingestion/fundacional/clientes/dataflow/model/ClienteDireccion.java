package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class ClienteDireccion   implements Cloneable  {

	@Nullable private Integer id_direccion_del_cliente; 
	@Nullable private Integer id_cliente; 
	@Nullable private String entre_calle_2; 
	@Nullable private String esta_borrado; 
	@Nullable private String alias_de_direccion; 
	@Nullable private String edificio; 
	@Nullable private Integer id_colonia; 
	@Nullable private Integer id_municipio; 
	@Nullable private Integer id_estado_del_pais; 
	@Nullable private String calle; 
	@Nullable private String fecha_actualizacion; 
	@Nullable private String fecha_creacion; 
	@Nullable private String numero_interior; 
	@Nullable private String entre_calle_1; 
	@Nullable private Integer id_tipo_direccion_cliente; 
	@Nullable private String codigo_postal; 
	@Nullable private String numero_exterior; 
	@Nullable private Integer id_sistema_origen; 
	@Nullable private String id_transaccion; 
	@Nullable private String id_operacion; 
	@Nullable private String direccion_2; 
	@Nullable private String direccion_3; 
	@Nullable private String direccion_4; 
	@Nullable private String direccion_5; 
	@Nullable private String tipo_de_direccion; 
	@Nullable private String codigo_zip; 
	@Nullable private Integer id_distrito; 
	@Nullable private Integer id_provincia; 
	@Nullable private Integer id_condado; 
	@Nullable private String salida_numero_exterior; 
	@Nullable private String salida_calle; 
	@Nullable private String salida_direccion_2; 
	@Nullable private String salida_direccion_3; 
	@Nullable private String salida_direccion_4; 
	@Nullable private String salida_direccion_5; 
	@Nullable private String salida_tipo; 
	@Nullable private String salida_codigo_zip; 
	@Nullable private Integer salida_id_distrito; 
	@Nullable private Integer salida_id_provincia; 
	@Nullable private Integer salida_id_estado_del_pais; 
	@Nullable private Integer salida_id_condado; 
	@Nullable private String bandera_calle; 
	@Nullable private String bandera_emcccp; 
	@Nullable private String bandera_termino; 
	@Nullable private String token_calle; 
	@Nullable private String fecha_de_operacion; 
	@Nullable private String baja_domicilio;
	@Nullable private Integer id_pais; 
	@Nullable private Integer id_catalogo_catalogos; 
	@Nullable private String operacion_crud;
	@Nullable private String numero_de_apartamento;
	@Nullable private String id_direccion_atg;

	public ClienteDireccion() {}

    


	public ClienteDireccion(Integer id_direccion_del_cliente, Integer id_cliente, String entre_calle_2,
			String esta_borrado, String alias_de_direccion, String edificio, Integer id_colonia, Integer id_municipio,
			Integer id_estado_del_pais, String calle, String fecha_actualizacion, String fecha_creacion,
			String numero_interior, String entre_calle_1, Integer id_tipo_direccion_cliente, String codigo_postal,
			String numero_exterior, Integer id_sistema_origen, String id_transaccion, String id_operacion,
			String direccion_2, String direccion_3, String direccion_4, String direccion_5, String tipo_de_direccion,
			String codigo_zip, Integer id_distrito, Integer id_provincia, Integer id_condado,
			String salida_numero_exterior, String salida_calle, String salida_direccion_2, String salida_direccion_3,
			String salida_direccion_4, String salida_direccion_5, String salida_tipo, String salida_codigo_zip,
			Integer salida_id_distrito, Integer salida_id_provincia, Integer salida_id_estado_del_pais,
			Integer salida_id_condado, String bandera_calle, String bandera_emcccp, String bandera_termino,
			String token_calle, String fecha_de_operacion, String baja_domicilio, Integer id_pais,
			Integer id_catalogo_catalogos, String operacion_crud, String numero_apartamento, String id_direccion_atg) {
		super();
		this.id_direccion_del_cliente = id_direccion_del_cliente;
		this.id_cliente = id_cliente;
		this.entre_calle_2 = entre_calle_2;
		this.esta_borrado = esta_borrado;
		this.alias_de_direccion = alias_de_direccion;
		this.edificio = edificio;
		this.id_colonia = id_colonia;
		this.id_municipio = id_municipio;
		this.id_estado_del_pais = id_estado_del_pais;
		this.calle = calle;
		this.fecha_actualizacion = fecha_actualizacion;
		this.fecha_creacion = fecha_creacion;
		this.numero_interior = numero_interior;
		this.entre_calle_1 = entre_calle_1;
		this.id_tipo_direccion_cliente = id_tipo_direccion_cliente;
		this.codigo_postal = codigo_postal;
		this.numero_exterior = numero_exterior;
		this.id_sistema_origen = id_sistema_origen;
		this.id_transaccion = id_transaccion;
		this.id_operacion = id_operacion;
		this.direccion_2 = direccion_2;
		this.direccion_3 = direccion_3;
		this.direccion_4 = direccion_4;
		this.direccion_5 = direccion_5;
		this.tipo_de_direccion = tipo_de_direccion;
		this.codigo_zip = codigo_zip;
		this.id_distrito = id_distrito;
		this.id_provincia = id_provincia;
		this.id_condado = id_condado;
		this.salida_numero_exterior = salida_numero_exterior;
		this.salida_calle = salida_calle;
		this.salida_direccion_2 = salida_direccion_2;
		this.salida_direccion_3 = salida_direccion_3;
		this.salida_direccion_4 = salida_direccion_4;
		this.salida_direccion_5 = salida_direccion_5;
		this.salida_tipo = salida_tipo;
		this.salida_codigo_zip = salida_codigo_zip;
		this.salida_id_distrito = salida_id_distrito;
		this.salida_id_provincia = salida_id_provincia;
		this.salida_id_estado_del_pais = salida_id_estado_del_pais;
		this.salida_id_condado = salida_id_condado;
		this.bandera_calle = bandera_calle;
		this.bandera_emcccp = bandera_emcccp;
		this.bandera_termino = bandera_termino;
		this.token_calle = token_calle;
		this.fecha_de_operacion = fecha_de_operacion;
		this.baja_domicilio = baja_domicilio;
		this.id_pais = id_pais;
		this.id_catalogo_catalogos = id_catalogo_catalogos;
		this.operacion_crud = operacion_crud;
		this.numero_de_apartamento = numero_apartamento;
		this.id_direccion_atg = id_direccion_atg;
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








	public @Nullable String getEntre_calle_2() {
		return entre_calle_2;
	}








	public void setEntre_calle_2(@Nullable String entre_calle_2) {
		this.entre_calle_2 = entre_calle_2;
	}








	public @Nullable String getEsta_borrado() {
		return esta_borrado;
	}








	public void setEsta_borrado(@Nullable String esta_borrado) {
		this.esta_borrado = esta_borrado;
	}








	public @Nullable String getAlias_de_direccion() {
		return alias_de_direccion;
	}








	public void setAlias_de_direccion(@Nullable String alias_de_direccion) {
		this.alias_de_direccion = alias_de_direccion;
	}








	public @Nullable String getEdificio() {
		return edificio;
	}








	public void setEdificio(@Nullable String edificio) {
		this.edificio = edificio;
	}



	public @Nullable String getId_direccion_atg() {
		return id_direccion_atg;
	}

	public void setId_direccion_atg(@Nullable String id_direccion_atg) {
		this.id_direccion_atg = id_direccion_atg;
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








	public @Nullable String getCalle() {
		return calle;
	}








	public void setCalle(@Nullable String calle) {
		this.calle = calle;
	}








	public @Nullable String getFecha_actualizacion() {
		return fecha_actualizacion;
	}








	public void setFecha_actualizacion(@Nullable String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}








	public @Nullable String getFecha_creacion() {
		return fecha_creacion;
	}








	public void setFecha_creacion(@Nullable String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}








	public @Nullable String getNumero_interior() {
		return numero_interior;
	}








	public void setNumero_interior(@Nullable String numero_interior) {
		this.numero_interior = numero_interior;
	}








	public @Nullable String getEntre_calle_1() {
		return entre_calle_1;
	}








	public void setEntre_calle_1(@Nullable String entre_calle_1) {
		this.entre_calle_1 = entre_calle_1;
	}








	public @Nullable Integer getId_tipo_direccion_cliente() {
		return id_tipo_direccion_cliente;
	}








	public void setId_tipo_direccion_cliente(@Nullable Integer id_tipo_direccion_cliente) {
		this.id_tipo_direccion_cliente = id_tipo_direccion_cliente;
	}








	public @Nullable String getCodigo_postal() {
		return codigo_postal;
	}








	public void setCodigo_postal(@Nullable String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}








	public @Nullable String getNumero_exterior() {
		return numero_exterior;
	}








	public void setNumero_exterior(@Nullable String numero_exterior) {
		this.numero_exterior = numero_exterior;
	}








	public @Nullable Integer getId_sistema_origen() {
		return id_sistema_origen;
	}








	public void setId_sistema_origen(@Nullable Integer id_sistema_origen) {
		this.id_sistema_origen = id_sistema_origen;
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








	public @Nullable String getDireccion_2() {
		return direccion_2;
	}








	public void setDireccion_2(@Nullable String direccion_2) {
		this.direccion_2 = direccion_2;
	}








	public @Nullable String getDireccion_3() {
		return direccion_3;
	}








	public void setDireccion_3(@Nullable String direccion_3) {
		this.direccion_3 = direccion_3;
	}








	public @Nullable String getDireccion_4() {
		return direccion_4;
	}








	public void setDireccion_4(@Nullable String direccion_4) {
		this.direccion_4 = direccion_4;
	}








	public @Nullable String getDireccion_5() {
		return direccion_5;
	}








	public void setDireccion_5(@Nullable String direccion_5) {
		this.direccion_5 = direccion_5;
	}








	public @Nullable String getTipo_de_direccion() {
		return tipo_de_direccion;
	}








	public void setTipo_de_direccion(@Nullable String tipo_de_direccion) {
		this.tipo_de_direccion = tipo_de_direccion;
	}








	public @Nullable String getCodigo_zip() {
		return codigo_zip;
	}








	public void setCodigo_zip(@Nullable String codigo_zip) {
		this.codigo_zip = codigo_zip;
	}








	public @Nullable Integer getId_distrito() {
		return id_distrito;
	}








	public void setId_distrito(@Nullable Integer id_distrito) {
		this.id_distrito = id_distrito;
	}








	public @Nullable Integer getId_provincia() {
		return id_provincia;
	}








	public void setId_provincia(@Nullable Integer id_provincia) {
		this.id_provincia = id_provincia;
	}








	public @Nullable Integer getId_condado() {
		return id_condado;
	}








	public void setId_condado(@Nullable Integer id_condado) {
		this.id_condado = id_condado;
	}








	public @Nullable String getSalida_numero_exterior() {
		return salida_numero_exterior;
	}








	public void setSalida_numero_exterior(@Nullable String salida_numero_exterior) {
		this.salida_numero_exterior = salida_numero_exterior;
	}








	public @Nullable String getSalida_calle() {
		return salida_calle;
	}








	public void setSalida_calle(@Nullable String salida_calle) {
		this.salida_calle = salida_calle;
	}








	public @Nullable String getSalida_direccion_2() {
		return salida_direccion_2;
	}








	public void setSalida_direccion_2(@Nullable String salida_direccion_2) {
		this.salida_direccion_2 = salida_direccion_2;
	}








	public @Nullable String getSalida_direccion_3() {
		return salida_direccion_3;
	}








	public void setSalida_direccion_3(@Nullable String salida_direccion_3) {
		this.salida_direccion_3 = salida_direccion_3;
	}








	public @Nullable String getSalida_direccion_4() {
		return salida_direccion_4;
	}








	public void setSalida_direccion_4(@Nullable String salida_direccion_4) {
		this.salida_direccion_4 = salida_direccion_4;
	}








	public @Nullable String getSalida_direccion_5() {
		return salida_direccion_5;
	}








	public void setSalida_direccion_5(@Nullable String salida_direccion_5) {
		this.salida_direccion_5 = salida_direccion_5;
	}








	public @Nullable String getSalida_tipo() {
		return salida_tipo;
	}








	public void setSalida_tipo(@Nullable String salida_tipo) {
		this.salida_tipo = salida_tipo;
	}








	public @Nullable String getSalida_codigo_zip() {
		return salida_codigo_zip;
	}








	public void setSalida_codigo_zip(@Nullable String salida_codigo_zip) {
		this.salida_codigo_zip = salida_codigo_zip;
	}








	public @Nullable Integer getSalida_id_distrito() {
		return salida_id_distrito;
	}








	public void setSalida_id_distrito(@Nullable Integer salida_id_distrito) {
		this.salida_id_distrito = salida_id_distrito;
	}








	public @Nullable Integer getSalida_id_provincia() {
		return salida_id_provincia;
	}








	public void setSalida_id_provincia(@Nullable Integer salida_id_provincia) {
		this.salida_id_provincia = salida_id_provincia;
	}








	public @Nullable Integer getSalida_id_estado_del_pais() {
		return salida_id_estado_del_pais;
	}








	public void setSalida_id_estado_del_pais(@Nullable Integer salida_id_estado_del_pais) {
		this.salida_id_estado_del_pais = salida_id_estado_del_pais;
	}








	public @Nullable Integer getSalida_id_condado() {
		return salida_id_condado;
	}








	public void setSalida_id_condado(@Nullable Integer salida_id_condado) {
		this.salida_id_condado = salida_id_condado;
	}








	public @Nullable String getBandera_calle() {
		return bandera_calle;
	}








	public void setBandera_calle(@Nullable String bandera_calle) {
		this.bandera_calle = bandera_calle;
	}








	public @Nullable String getBandera_emcccp() {
		return bandera_emcccp;
	}








	public void setBandera_emcccp(@Nullable String bandera_emcccp) {
		this.bandera_emcccp = bandera_emcccp;
	}








	public @Nullable String getBandera_termino() {
		return bandera_termino;
	}








	public void setBandera_termino(@Nullable String bandera_termino) {
		this.bandera_termino = bandera_termino;
	}








	public @Nullable String getToken_calle() {
		return token_calle;
	}








	public void setToken_calle(@Nullable String token_calle) {
		this.token_calle = token_calle;
	}








	public @Nullable String getFecha_de_operacion() {
		return fecha_de_operacion;
	}








	public void setFecha_de_operacion(@Nullable String fecha_de_operacion) {
		this.fecha_de_operacion = fecha_de_operacion;
	}








	public @Nullable String getBaja_domicilio() {
		return baja_domicilio;
	}








	public void setBaja_domicilio(@Nullable String baja_domicilio) {
		this.baja_domicilio = baja_domicilio;
	}








	public @Nullable Integer getId_pais() {
		return id_pais;
	}








	public void setId_pais(@Nullable Integer id_pais) {
		this.id_pais = id_pais;
	}




	public @Nullable Integer getId_catalogo_catalogos() {
		return id_catalogo_catalogos;
	}




	public void setId_catalogo_catalogos(@Nullable Integer id_catalogo_catalogos) {
		this.id_catalogo_catalogos = id_catalogo_catalogos;
	}



	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}




	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}



	public @Nullable String getNumero_de_apartamento() {
		return numero_de_apartamento;
	}




	public void setNumero_de_apartamento(@Nullable String numero_de_apartamento) {
		this.numero_de_apartamento = numero_de_apartamento;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias_de_direccion == null) ? 0 : alias_de_direccion.hashCode());
		result = prime * result + ((baja_domicilio == null) ? 0 : baja_domicilio.hashCode());
		result = prime * result + ((bandera_calle == null) ? 0 : bandera_calle.hashCode());
		result = prime * result + ((bandera_emcccp == null) ? 0 : bandera_emcccp.hashCode());
		result = prime * result + ((bandera_termino == null) ? 0 : bandera_termino.hashCode());
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((codigo_postal == null) ? 0 : codigo_postal.hashCode());
		result = prime * result + ((codigo_zip == null) ? 0 : codigo_zip.hashCode());
		result = prime * result + ((direccion_2 == null) ? 0 : direccion_2.hashCode());
		result = prime * result + ((direccion_3 == null) ? 0 : direccion_3.hashCode());
		result = prime * result + ((direccion_4 == null) ? 0 : direccion_4.hashCode());
		result = prime * result + ((direccion_5 == null) ? 0 : direccion_5.hashCode());
		result = prime * result + ((edificio == null) ? 0 : edificio.hashCode());
		result = prime * result + ((entre_calle_1 == null) ? 0 : entre_calle_1.hashCode());
		result = prime * result + ((entre_calle_2 == null) ? 0 : entre_calle_2.hashCode());
		result = prime * result + ((esta_borrado == null) ? 0 : esta_borrado.hashCode());
		result = prime * result + ((fecha_actualizacion == null) ? 0 : fecha_actualizacion.hashCode());
		result = prime * result + ((fecha_creacion == null) ? 0 : fecha_creacion.hashCode());
		result = prime * result + ((fecha_de_operacion == null) ? 0 : fecha_de_operacion.hashCode());
		result = prime * result + ((id_catalogo_catalogos == null) ? 0 : id_catalogo_catalogos.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((id_colonia == null) ? 0 : id_colonia.hashCode());
		result = prime * result + ((id_condado == null) ? 0 : id_condado.hashCode());
		result = prime * result + ((id_direccion_del_cliente == null) ? 0 : id_direccion_del_cliente.hashCode());
		result = prime * result + ((id_distrito == null) ? 0 : id_distrito.hashCode());
		result = prime * result + ((id_estado_del_pais == null) ? 0 : id_estado_del_pais.hashCode());
		result = prime * result + ((id_municipio == null) ? 0 : id_municipio.hashCode());
		result = prime * result + ((id_operacion == null) ? 0 : id_operacion.hashCode());
		result = prime * result + ((id_pais == null) ? 0 : id_pais.hashCode());
		result = prime * result + ((id_provincia == null) ? 0 : id_provincia.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
		result = prime * result + ((id_tipo_direccion_cliente == null) ? 0 : id_tipo_direccion_cliente.hashCode());
		result = prime * result + ((id_transaccion == null) ? 0 : id_transaccion.hashCode());
		result = prime * result + ((numero_exterior == null) ? 0 : numero_exterior.hashCode());
		result = prime * result + ((numero_interior == null) ? 0 : numero_interior.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((salida_calle == null) ? 0 : salida_calle.hashCode());
		result = prime * result + ((salida_codigo_zip == null) ? 0 : salida_codigo_zip.hashCode());
		result = prime * result + ((salida_direccion_2 == null) ? 0 : salida_direccion_2.hashCode());
		result = prime * result + ((salida_direccion_3 == null) ? 0 : salida_direccion_3.hashCode());
		result = prime * result + ((salida_direccion_4 == null) ? 0 : salida_direccion_4.hashCode());
		result = prime * result + ((salida_direccion_5 == null) ? 0 : salida_direccion_5.hashCode());
		result = prime * result + ((salida_id_condado == null) ? 0 : salida_id_condado.hashCode());
		result = prime * result + ((salida_id_distrito == null) ? 0 : salida_id_distrito.hashCode());
		result = prime * result + ((salida_id_estado_del_pais == null) ? 0 : salida_id_estado_del_pais.hashCode());
		result = prime * result + ((salida_id_provincia == null) ? 0 : salida_id_provincia.hashCode());
		result = prime * result + ((salida_numero_exterior == null) ? 0 : salida_numero_exterior.hashCode());
		result = prime * result + ((salida_tipo == null) ? 0 : salida_tipo.hashCode());
		result = prime * result + ((tipo_de_direccion == null) ? 0 : tipo_de_direccion.hashCode());
		result = prime * result + ((token_calle == null) ? 0 : token_calle.hashCode());
		result = prime * result + ((numero_de_apartamento == null) ? 0 : numero_de_apartamento.hashCode());
		result = prime * result + ((id_direccion_atg == null) ? 0 : id_direccion_atg.hashCode());
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
		if (alias_de_direccion == null) {
			if (other.alias_de_direccion != null)
				return false;
		} else if (!alias_de_direccion.equals(other.alias_de_direccion))
			return false;
		if (baja_domicilio == null) {
			if (other.baja_domicilio != null)
				return false;
		} else if (!baja_domicilio.equals(other.baja_domicilio))
			return false;
		if (bandera_calle == null) {
			if (other.bandera_calle != null)
				return false;
		} else if (!bandera_calle.equals(other.bandera_calle))
			return false;
		if (bandera_emcccp == null) {
			if (other.bandera_emcccp != null)
				return false;
		} else if (!bandera_emcccp.equals(other.bandera_emcccp))
			return false;
		if (bandera_termino == null) {
			if (other.bandera_termino != null)
				return false;
		} else if (!bandera_termino.equals(other.bandera_termino))
			return false;
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
		if (codigo_zip == null) {
			if (other.codigo_zip != null)
				return false;
		} else if (!codigo_zip.equals(other.codigo_zip))
			return false;
		if (direccion_2 == null) {
			if (other.direccion_2 != null)
				return false;
		} else if (!direccion_2.equals(other.direccion_2))
			return false;
		if (direccion_3 == null) {
			if (other.direccion_3 != null)
				return false;
		} else if (!direccion_3.equals(other.direccion_3))
			return false;
		if (direccion_4 == null) {
			if (other.direccion_4 != null)
				return false;
		} else if (!direccion_4.equals(other.direccion_4))
			return false;
		if (direccion_5 == null) {
			if (other.direccion_5 != null)
				return false;
		} else if (!direccion_5.equals(other.direccion_5))
			return false;
		if (edificio == null) {
			if (other.edificio != null)
				return false;
		} else if (!edificio.equals(other.edificio))
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
		if (fecha_de_operacion == null) {
			if (other.fecha_de_operacion != null)
				return false;
		} else if (!fecha_de_operacion.equals(other.fecha_de_operacion))
			return false;
		if (id_catalogo_catalogos == null) {
			if (other.id_catalogo_catalogos != null)
				return false;
		} else if (!id_catalogo_catalogos.equals(other.id_catalogo_catalogos))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_colonia == null) {
			if (other.id_colonia != null)
				return false;
		} else if (!id_colonia.equals(other.id_colonia))
			return false;
		if (id_condado == null) {
			if (other.id_condado != null)
				return false;
		} else if (!id_condado.equals(other.id_condado))
			return false;
		if (id_direccion_del_cliente == null) {
			if (other.id_direccion_del_cliente != null)
				return false;
		} else if (!id_direccion_del_cliente.equals(other.id_direccion_del_cliente))
			return false;
		if (id_distrito == null) {
			if (other.id_distrito != null)
				return false;
		} else if (!id_distrito.equals(other.id_distrito))
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
		if (id_operacion == null) {
			if (other.id_operacion != null)
				return false;
		} else if (!id_operacion.equals(other.id_operacion))
			return false;
		if (id_pais == null) {
			if (other.id_pais != null)
				return false;
		} else if (!id_pais.equals(other.id_pais))
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
		if (id_tipo_direccion_cliente == null) {
			if (other.id_tipo_direccion_cliente != null)
				return false;
		} else if (!id_tipo_direccion_cliente.equals(other.id_tipo_direccion_cliente))
			return false;
		if (id_transaccion == null) {
			if (other.id_transaccion != null)
				return false;
		} else if (!id_transaccion.equals(other.id_transaccion))
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
		if (salida_calle == null) {
			if (other.salida_calle != null)
				return false;
		} else if (!salida_calle.equals(other.salida_calle))
			return false;
		if (salida_codigo_zip == null) {
			if (other.salida_codigo_zip != null)
				return false;
		} else if (!salida_codigo_zip.equals(other.salida_codigo_zip))
			return false;
		if (salida_direccion_2 == null) {
			if (other.salida_direccion_2 != null)
				return false;
		} else if (!salida_direccion_2.equals(other.salida_direccion_2))
			return false;
		if (salida_direccion_3 == null) {
			if (other.salida_direccion_3 != null)
				return false;
		} else if (!salida_direccion_3.equals(other.salida_direccion_3))
			return false;
		if (salida_direccion_4 == null) {
			if (other.salida_direccion_4 != null)
				return false;
		} else if (!salida_direccion_4.equals(other.salida_direccion_4))
			return false;
		if (salida_direccion_5 == null) {
			if (other.salida_direccion_5 != null)
				return false;
		} else if (!salida_direccion_5.equals(other.salida_direccion_5))
			return false;
		if (salida_id_condado == null) {
			if (other.salida_id_condado != null)
				return false;
		} else if (!salida_id_condado.equals(other.salida_id_condado))
			return false;
		if (salida_id_distrito == null) {
			if (other.salida_id_distrito != null)
				return false;
		} else if (!salida_id_distrito.equals(other.salida_id_distrito))
			return false;
		if (salida_id_estado_del_pais == null) {
			if (other.salida_id_estado_del_pais != null)
				return false;
		} else if (!salida_id_estado_del_pais.equals(other.salida_id_estado_del_pais))
			return false;
		if (salida_id_provincia == null) {
			if (other.salida_id_provincia != null)
				return false;
		} else if (!salida_id_provincia.equals(other.salida_id_provincia))
			return false;
		if (salida_numero_exterior == null) {
			if (other.salida_numero_exterior != null)
				return false;
		} else if (!salida_numero_exterior.equals(other.salida_numero_exterior))
			return false;
		if (salida_tipo == null) {
			if (other.salida_tipo != null)
				return false;
		} else if (!salida_tipo.equals(other.salida_tipo))
			return false;
		if (tipo_de_direccion == null) {
			if (other.tipo_de_direccion != null)
				return false;
		} else if (!tipo_de_direccion.equals(other.tipo_de_direccion))
			return false;
		if (token_calle == null) {
			if (other.token_calle != null)
				return false;
		} else if (!token_calle.equals(other.token_calle))
			return false;
		if (numero_de_apartamento == null) {
			if (other.numero_de_apartamento != null)
				return false;
		} else if (!numero_de_apartamento.equals(other.numero_de_apartamento))
			return false;

		if (id_direccion_atg == null) {
			if (other.id_direccion_atg != null)
				return false;
		} else if (!id_direccion_atg.equals(other.id_direccion_atg))
			return false;
		return true;
	}



	
	
	

	@Override
	public String toString() {
		return "ClienteDireccion [id_direccion_del_cliente=" + id_direccion_del_cliente + ", id_cliente=" + id_cliente
				+ ", entre_calle_2=" + entre_calle_2 + ", esta_borrado=" + esta_borrado + ", alias_de_direccion="
				+ alias_de_direccion + ", edificio=" + edificio + ", id_colonia=" + id_colonia + ", id_municipio="
				+ id_municipio + ", id_estado_del_pais=" + id_estado_del_pais + ", calle=" + calle
				+ ", fecha_actualizacion=" + fecha_actualizacion + ", fecha_creacion=" + fecha_creacion
				+ ", numero_interior=" + numero_interior + ", entre_calle_1=" + entre_calle_1
				+ ", id_tipo_direccion_cliente=" + id_tipo_direccion_cliente + ", codigo_postal=" + codigo_postal
				+ ", numero_exterior=" + numero_exterior + ", id_sistema_origen=" + id_sistema_origen
				+ ", id_transaccion=" + id_transaccion + ", id_operacion=" + id_operacion + ", direccion_2="
				+ direccion_2 + ", direccion_3=" + direccion_3 + ", direccion_4=" + direccion_4 + ", direccion_5="
				+ direccion_5 + ", tipo_de_direccion=" + tipo_de_direccion + ", codigo_zip=" + codigo_zip
				+ ", id_distrito=" + id_distrito + ", id_provincia=" + id_provincia + ", id_condado=" + id_condado
				+ ", salida_numero_exterior=" + salida_numero_exterior + ", salida_calle=" + salida_calle
				+ ", salida_direccion_2=" + salida_direccion_2 + ", salida_direccion_3=" + salida_direccion_3
				+ ", salida_direccion_4=" + salida_direccion_4 + ", salida_direccion_5=" + salida_direccion_5
				+ ", salida_tipo=" + salida_tipo + ", salida_codigo_zip=" + salida_codigo_zip + ", salida_id_distrito="
				+ salida_id_distrito + ", salida_id_provincia=" + salida_id_provincia + ", salida_id_estado_del_pais="
				+ salida_id_estado_del_pais + ", salida_id_condado=" + salida_id_condado + ", bandera_calle="
				+ bandera_calle + ", bandera_emcccp=" + bandera_emcccp + ", bandera_termino=" + bandera_termino
				+ ", token_calle=" + token_calle + ", fecha_de_operacion=" + fecha_de_operacion + ", baja_domicilio="
				+ baja_domicilio + ", id_pais=" + id_pais + ", id_catalogo_catalogos=" + id_catalogo_catalogos
				+ ", operacion_crud=" + operacion_crud + ", numero_apartamento=" + numero_de_apartamento +"]";
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
	        		this.entre_calle_2, 
	        		this.esta_borrado, 
	        		this.alias_de_direccion, 
	        		this.edificio, 
	        		this.id_colonia, 
	        		this.id_municipio, 
	        		this.id_estado_del_pais, 
	        		this.calle, 
	        		this.fecha_actualizacion, 
	        		this.fecha_creacion, 
	        		this.numero_interior, 
	        		this.entre_calle_1, 
	        		this.id_tipo_direccion_cliente, 
	        		this.codigo_postal, 
	        		this.numero_exterior, 
	        		this.id_sistema_origen, 
	        		this.id_transaccion, 
	        		this.id_operacion, 
	        		this.direccion_2, 
	        		this.direccion_3, 
	        		this.direccion_4, 
	        		this.direccion_5, 
	        		this.tipo_de_direccion, 
	        		this.codigo_zip, 
	        		this.id_distrito, 
	        		this.id_provincia, 
	        		this.id_condado, 
	        		this.salida_numero_exterior, 
	        		this.salida_calle, 
	        		this.salida_direccion_2, 
	        		this.salida_direccion_3, 
	        		this.salida_direccion_4, 
	        		this.salida_direccion_5, 
	        		this.salida_tipo, 
	        		this.salida_codigo_zip, 
	        		this.salida_id_distrito, 
	        		this.salida_id_provincia, 
	        		this.salida_id_estado_del_pais, 
	        		this.salida_id_condado, 
	        		this.bandera_calle, 
	        		this.bandera_emcccp, 
	        		this.bandera_termino, 
	        		this.token_calle, 
	        		this.fecha_de_operacion, 
	        		this.baja_domicilio,
	        		this.id_pais,
	        		this.id_catalogo_catalogos,
	        		this.operacion_crud,
					this.numero_de_apartamento,
					this.id_direccion_atg
	        		);
	    }
	}
    
    
    
    
}
