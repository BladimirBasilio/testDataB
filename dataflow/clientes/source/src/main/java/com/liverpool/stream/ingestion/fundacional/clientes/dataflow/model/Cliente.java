package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model;

import java.util.List;
import javax.annotation.Nullable;
import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;



@DefaultSchema(JavaBeanSchema.class)
public class Cliente implements Cloneable  {


	@Nullable private Integer id_cliente; 
	@Nullable private String primer_nombre; 
	@Nullable private String segundo_nombre; 
	@Nullable private String apellido_paterno; 
	@Nullable private String apellido_materno; 
	@Nullable private String fecha_de_nacimiento; 
	@Nullable private String genero; 
	@Nullable private String rfc; 
	@Nullable private String numero_de_seguro_social; 
	@Nullable private String fecha_de_registro; 
	@Nullable private Integer id_sistema_origen; 
	@Nullable private String id_transaccion; 
	@Nullable private String id_operacion; 
	@Nullable private String salida_primer_nombre; 
	@Nullable private String salida_apellido_materno;
	@Nullable private String bandera_del_nombre; 
	@Nullable private String salida_genero; 
	@Nullable private String salida_fecha_de_nacimiento; 
	@Nullable private String salida_rfc; 
	@Nullable private String salida_apellido_paterno; 
	@Nullable private String bandera_rfc; 
	@Nullable private String bandera_ucm; 
	@Nullable private String nombre_de_token; 
	@Nullable private String fecha_de_la_operacion; 
	@Nullable private String salida_bandera_fecha; 
	@Nullable private String salida_token_primer_nombre; 
	@Nullable private String salida_token_apellido_paterno; 
	@Nullable private String salida_token_apellido_materno;
	@Nullable private String salida_bandera_rfc; 
	@Nullable private Integer id_pais;
	@Nullable private String operacion_crud;
	@Nullable private String row_id;
	@Nullable private String alias_row_id;
	@Nullable private String id_mdm;
	@Nullable private String alias_id_mdm;
	@Nullable private String fecha_de_nacimiento_area_gris;
	@Nullable private String fecha_creacion;
	@Nullable private String fecha_actualizacion;
	@Nullable private String id_sistema_externo;
	@Nullable private String nombre_sistema_externo;
	@Nullable private List<ClienteTelefonoContacto> telefono_de_contacto;
	@Nullable private List<ClienteEmailContacto> email_de_contacto;
	@Nullable private ClienteDireccion direccion;
	@Nullable private ClienteFormaPago cliente_formas_de_pago;
	@Nullable private String id_atg;

    
    

	public Cliente() {}



	public Cliente(Integer id_cliente, String primer_nombre, String segundo_nombre, String apellido_paterno,
				   String apellido_materno, String fecha_de_nacimiento, String genero, String rfc,
				   String numero_de_seguro_social, String fecha_de_registro, Integer id_sistema_origen, String id_transaccion,
				   String id_operacion, String salida_primer_nombre, String salida_apellido_materno, String bandera_del_nombre,
				   String salida_genero, String salida_fecha_de_nacimiento, String salida_rfc, String salida_apellido_paterno,
				   String bandera_rfc, String bandera_ucm, String nombre_de_token, String fecha_de_la_operacion,
				   String salida_bandera_fecha, String salida_token_primer_nombre, String salida_token_apellido_paterno,
				   String salida_token_apellido_materno, String salida_bandera_rfc, Integer id_pais, String operacion_crud,
				   String row_id, String alias_row_id, String id_mdm, String alias_id_mdm,
				   List<ClienteTelefonoContacto> telefono_de_contacto, List<ClienteEmailContacto> email_de_contacto,
				   ClienteDireccion direccion, ClienteFormaPago cliente_formas_de_pago, String fecha_de_nacimiento_area_gris,
				   String fecha_creacion, String fecha_actualizacion, String id_sistema_externo, String nombre_sistema_externo, String id_atg
	) {
		super();
		this.id_cliente = id_cliente;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.genero = genero;
		this.rfc = rfc;
		this.numero_de_seguro_social = numero_de_seguro_social;
		this.fecha_de_registro = fecha_de_registro;
		this.id_sistema_origen = id_sistema_origen;
		this.id_transaccion = id_transaccion;
		this.id_operacion = id_operacion;
		this.salida_primer_nombre = salida_primer_nombre;
		this.salida_apellido_materno = salida_apellido_materno;
		this.bandera_del_nombre = bandera_del_nombre;
		this.salida_genero = salida_genero;
		this.salida_fecha_de_nacimiento = salida_fecha_de_nacimiento;
		this.salida_rfc = salida_rfc;
		this.salida_apellido_paterno = salida_apellido_paterno;
		this.bandera_rfc = bandera_rfc;
		this.bandera_ucm = bandera_ucm;
		this.nombre_de_token = nombre_de_token;
		this.fecha_de_la_operacion = fecha_de_la_operacion;
		this.salida_bandera_fecha = salida_bandera_fecha;
		this.salida_token_primer_nombre = salida_token_primer_nombre;
		this.salida_token_apellido_paterno = salida_token_apellido_paterno;
		this.salida_token_apellido_materno = salida_token_apellido_materno;
		this.salida_bandera_rfc = salida_bandera_rfc;
		this.id_pais = id_pais;
		this.operacion_crud = operacion_crud;
		this.row_id = row_id;
		this.alias_row_id = alias_row_id;
		this.id_mdm = id_mdm;
		this.alias_id_mdm = alias_id_mdm;
		this.telefono_de_contacto = telefono_de_contacto;
		this.email_de_contacto = email_de_contacto;
		this.direccion = direccion;
		this.cliente_formas_de_pago = cliente_formas_de_pago;
		this.fecha_de_nacimiento_area_gris = fecha_de_nacimiento_area_gris;
		this.fecha_creacion = fecha_creacion;
		this.fecha_actualizacion = fecha_actualizacion;
		this.id_sistema_externo = id_sistema_externo;
		this.nombre_sistema_externo = nombre_sistema_externo;
		this.id_atg = id_atg;
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

	public @Nullable String getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(@Nullable String fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}

	public @Nullable String getGenero() {
		return genero;
	}

	public void setGenero(@Nullable String genero) {
		this.genero = genero;
	}

	public @Nullable String getRfc() {
		return rfc;
	}

	public void setRfc(@Nullable String rfc) {
		this.rfc = rfc;
	}

	public @Nullable String getNumero_de_seguro_social() {
		return numero_de_seguro_social;
	}

	public void setNumero_de_seguro_social(@Nullable String numero_de_seguro_social) {
		this.numero_de_seguro_social = numero_de_seguro_social;
	}

	public @Nullable String getFecha_de_registro() {
		return fecha_de_registro;
	}

	public void setFecha_de_registro(@Nullable String fecha_de_registro) {
		this.fecha_de_registro = fecha_de_registro;
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

	public @Nullable String getSalida_primer_nombre() {
		return salida_primer_nombre;
	}

	public void setSalida_primer_nombre(@Nullable String salida_primer_nombre) {
		this.salida_primer_nombre = salida_primer_nombre;
	}

	public @Nullable String getSalida_apellido_materno() {
		return salida_apellido_materno;
	}

	public void setSalida_apellido_materno(@Nullable String salida_apellido_materno) {
		this.salida_apellido_materno = salida_apellido_materno;
	}

	public @Nullable String getBandera_del_nombre() {
		return bandera_del_nombre;
	}

	public void setBandera_del_nombre(@Nullable String bandera_del_nombre) {
		this.bandera_del_nombre = bandera_del_nombre;
	}

	public @Nullable String getSalida_genero() {
		return salida_genero;
	}

	public void setSalida_genero(@Nullable String salida_genero) {
		this.salida_genero = salida_genero;
	}

	public @Nullable String getSalida_fecha_de_nacimiento() {
		return salida_fecha_de_nacimiento;
	}

	public void setSalida_fecha_de_nacimiento(@Nullable String salida_fecha_de_nacimiento) {
		this.salida_fecha_de_nacimiento = salida_fecha_de_nacimiento;
	}

	public @Nullable String getSalida_rfc() {
		return salida_rfc;
	}

	public void setSalida_rfc(@Nullable String salida_rfc) {
		this.salida_rfc = salida_rfc;
	}

	public @Nullable String getSalida_apellido_paterno() {
		return salida_apellido_paterno;
	}

	public void setSalida_apellido_paterno(@Nullable String salida_apellido_paterno) {
		this.salida_apellido_paterno = salida_apellido_paterno;
	}

	public @Nullable String getBandera_rfc() {
		return bandera_rfc;
	}

	public void setBandera_rfc(@Nullable String bandera_rfc) {
		this.bandera_rfc = bandera_rfc;
	}

	public @Nullable String getBandera_ucm() {
		return bandera_ucm;
	}

	public void setBandera_ucm(@Nullable String bandera_ucm) {
		this.bandera_ucm = bandera_ucm;
	}

	public @Nullable String getNombre_de_token() {
		return nombre_de_token;
	}

	public void setNombre_de_token(@Nullable String nombre_de_token) {
		this.nombre_de_token = nombre_de_token;
	}

	public @Nullable String getFecha_de_la_operacion() {
		return fecha_de_la_operacion;
	}

	public void setFecha_de_la_operacion(@Nullable String fecha_de_la_operacion) {
		this.fecha_de_la_operacion = fecha_de_la_operacion;
	}

	public @Nullable String getSalida_bandera_fecha() {
		return salida_bandera_fecha;
	}

	public void setSalida_bandera_fecha(@Nullable String salida_bandera_fecha) {
		this.salida_bandera_fecha = salida_bandera_fecha;
	}

	public @Nullable String getSalida_token_primer_nombre() {
		return salida_token_primer_nombre;
	}

	public void setSalida_token_primer_nombre(@Nullable String salida_token_primer_nombre) {
		this.salida_token_primer_nombre = salida_token_primer_nombre;
	}

	public @Nullable String getSalida_token_apellido_paterno() {
		return salida_token_apellido_paterno;
	}

	public void setSalida_token_apellido_paterno(@Nullable String salida_token_apellido_paterno) {
		this.salida_token_apellido_paterno = salida_token_apellido_paterno;
	}

	public @Nullable String getSalida_token_apellido_materno() {
		return salida_token_apellido_materno;
	}

	public void setSalida_token_apellido_materno(@Nullable String salida_token_apellido_materno) {
		this.salida_token_apellido_materno = salida_token_apellido_materno;
	}

	public @Nullable String getSalida_bandera_rfc() {
		return salida_bandera_rfc;
	}

	public void setSalida_bandera_rfc(@Nullable String salida_bandera_rfc) {
		this.salida_bandera_rfc = salida_bandera_rfc;
	}

	public @Nullable Integer getId_pais() {
		return id_pais;
	}

	public void setId_pais(@Nullable Integer id_pais) {
		this.id_pais = id_pais;
	}

	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}

	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}

	public @Nullable String getRow_id() {
		return row_id;
	}

	public void setRow_id(@Nullable String row_id) {
		this.row_id = row_id;
	}

	public @Nullable String getAlias_row_id() {
		return alias_row_id;
	}

	public void setAlias_row_id(@Nullable String alias_row_id) {
		this.alias_row_id = alias_row_id;
	}

	public @Nullable String getId_mdm() {
		return id_mdm;
	}

	public void setId_mdm(@Nullable String id_mdm) {
		this.id_mdm = id_mdm;
	}

	public @Nullable String getAlias_id_mdm() {
		return alias_id_mdm;
	}

	public void setAlias_id_mdm(@Nullable String alias_id_mdm) {
		this.alias_id_mdm = alias_id_mdm;
	}

	public @Nullable List<ClienteTelefonoContacto> getTelefono_de_contacto() {
		return telefono_de_contacto;
	}

	public void setTelefono_de_contacto(@Nullable List<ClienteTelefonoContacto> telefono_de_contacto) {
		this.telefono_de_contacto = telefono_de_contacto;
	}

	@Nullable public List<ClienteEmailContacto> getEmail_de_contacto() {
		return email_de_contacto;
	}

	public void setEmail_de_contacto(@Nullable List<ClienteEmailContacto> email_de_contacto) {
		this.email_de_contacto = email_de_contacto;
	}

	@Nullable public ClienteDireccion getDireccion() {
		return direccion;
	}

	public void setDireccion(@Nullable ClienteDireccion direccion) {
		this.direccion = direccion;
	}

	@Nullable public ClienteFormaPago getCliente_formas_de_pago() {
		return cliente_formas_de_pago;
	}

	public void setCliente_formas_de_pago(@Nullable ClienteFormaPago cliente_formas_de_pago) {
		this.cliente_formas_de_pago = cliente_formas_de_pago;
	}

	public @Nullable String getFecha_de_nacimiento_area_gris() {
		return fecha_de_nacimiento_area_gris;
	}

	public void setFecha_de_nacimiento_area_gris(@Nullable String fecha_de_nacimiento_area_gris) {
		this.fecha_de_nacimiento_area_gris = fecha_de_nacimiento_area_gris;
	}

	public @Nullable String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(@Nullable String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public	@Nullable String getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(@Nullable String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public	@Nullable String getId_sistema_externo() {
		return id_sistema_externo;
	}

	public void setId_sistema_externo(@Nullable String id_sistema_externo) {
		this.id_sistema_externo = id_sistema_externo;
	}

	public	@Nullable String getNombre_sistema_externo() {
		return nombre_sistema_externo;
	}

	public void setNombre_sistema_externo(@Nullable String nombre_sistema_externo) {
		this.nombre_sistema_externo = nombre_sistema_externo;
	}

	public	@Nullable String getId_atg() {
		return id_atg;
	}

	public void setId_atg(@Nullable String id_atg) {
		this.id_atg = id_atg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido_materno == null) ? 0 : apellido_materno.hashCode());
		result = prime * result + ((apellido_paterno == null) ? 0 : apellido_paterno.hashCode());
		result = prime * result + ((bandera_del_nombre == null) ? 0 : bandera_del_nombre.hashCode());
		result = prime * result + ((bandera_rfc == null) ? 0 : bandera_rfc.hashCode());
		result = prime * result + ((bandera_ucm == null) ? 0 : bandera_ucm.hashCode());
		result = prime * result + ((cliente_formas_de_pago == null) ? 0 : cliente_formas_de_pago.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((email_de_contacto == null) ? 0 : email_de_contacto.hashCode());
		result = prime * result + ((fecha_de_la_operacion == null) ? 0 : fecha_de_la_operacion.hashCode());
		result = prime * result + ((fecha_de_nacimiento == null) ? 0 : fecha_de_nacimiento.hashCode());
		result = prime * result + ((fecha_de_nacimiento_area_gris == null) ? 0 : fecha_de_nacimiento_area_gris.hashCode());
		result = prime * result + ((fecha_creacion == null) ? 0 : fecha_creacion.hashCode());
		result = prime * result + ((fecha_actualizacion == null) ? 0 : fecha_actualizacion.hashCode());
		result = prime * result + ((fecha_de_registro == null) ? 0 : fecha_de_registro.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((id_operacion == null) ? 0 : id_operacion.hashCode());
		result = prime * result + ((id_pais == null) ? 0 : id_pais.hashCode());
		result = prime * result + ((id_sistema_origen == null) ? 0 : id_sistema_origen.hashCode());
		result = prime * result + ((id_transaccion == null) ? 0 : id_transaccion.hashCode());
		result = prime * result + ((nombre_de_token == null) ? 0 : nombre_de_token.hashCode());
		result = prime * result + ((numero_de_seguro_social == null) ? 0 : numero_de_seguro_social.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((row_id == null) ? 0 : row_id.hashCode());
		result = prime * result + ((alias_row_id == null) ? 0 : alias_row_id.hashCode());
		result = prime * result + ((id_mdm == null) ? 0 : id_mdm.hashCode());
		result = prime * result + ((alias_id_mdm == null) ? 0 : alias_id_mdm.hashCode());
		result = prime * result + ((primer_nombre == null) ? 0 : primer_nombre.hashCode());
		result = prime * result + ((rfc == null) ? 0 : rfc.hashCode());
		result = prime * result + ((salida_apellido_paterno == null) ? 0 : salida_apellido_paterno.hashCode());
		result = prime * result + ((salida_bandera_fecha == null) ? 0 : salida_bandera_fecha.hashCode());
		result = prime * result + ((salida_bandera_rfc == null) ? 0 : salida_bandera_rfc.hashCode());
		result = prime * result + ((salida_fecha_de_nacimiento == null) ? 0 : salida_fecha_de_nacimiento.hashCode());
		result = prime * result + ((salida_genero == null) ? 0 : salida_genero.hashCode());
		result = prime * result + ((salida_primer_nombre == null) ? 0 : salida_primer_nombre.hashCode());
		result = prime * result + ((salida_rfc == null) ? 0 : salida_rfc.hashCode());
		result = prime * result + ((salida_apellido_materno == null) ? 0 : salida_apellido_materno.hashCode());
		result = prime * result
				+ ((salida_token_apellido_paterno == null) ? 0 : salida_token_apellido_paterno.hashCode());
		result = prime * result + ((salida_token_primer_nombre == null) ? 0 : salida_token_primer_nombre.hashCode());
		result = prime * result + ((salida_token_apellido_materno == null) ? 0 : salida_token_apellido_materno.hashCode());
		result = prime * result + ((segundo_nombre == null) ? 0 : segundo_nombre.hashCode());
		result = prime * result + ((telefono_de_contacto == null) ? 0 : telefono_de_contacto.hashCode());
		result = prime * result + ((id_sistema_externo == null) ? 0 : id_sistema_externo.hashCode());
		result = prime * result + ((nombre_sistema_externo == null) ? 0 : nombre_sistema_externo.hashCode());
		result = prime * result + ((id_atg == null) ? 0 : id_atg.hashCode());
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
		Cliente other = (Cliente) obj;
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
		if (bandera_del_nombre == null) {
			if (other.bandera_del_nombre != null)
				return false;
		} else if (!bandera_del_nombre.equals(other.bandera_del_nombre))
			return false;
		if (bandera_rfc == null) {
			if (other.bandera_rfc != null)
				return false;
		} else if (!bandera_rfc.equals(other.bandera_rfc))
			return false;
		if (bandera_ucm == null) {
			if (other.bandera_ucm != null)
				return false;
		} else if (!bandera_ucm.equals(other.bandera_ucm))
			return false;
		if (cliente_formas_de_pago == null) {
			if (other.cliente_formas_de_pago != null)
				return false;
		} else if (!cliente_formas_de_pago.equals(other.cliente_formas_de_pago))
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
		if (fecha_de_la_operacion == null) {
			if (other.fecha_de_la_operacion != null)
				return false;
		} else if (!fecha_de_la_operacion.equals(other.fecha_de_la_operacion))
			return false;
		if (fecha_de_nacimiento == null) {
			if (other.fecha_de_nacimiento != null)
				return false;
		} else if (!fecha_de_nacimiento.equals(other.fecha_de_nacimiento))
			return false;
		if (fecha_de_registro == null) {
			if (other.fecha_de_registro != null)
				return false;
		} else if (!fecha_de_registro.equals(other.fecha_de_registro))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
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
		if (nombre_de_token == null) {
			if (other.nombre_de_token != null)
				return false;
		} else if (!nombre_de_token.equals(other.nombre_de_token))
			return false;
		if (numero_de_seguro_social == null) {
			if (other.numero_de_seguro_social != null)
				return false;
		} else if (!numero_de_seguro_social.equals(other.numero_de_seguro_social))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (row_id == null) {
			if (other.row_id != null)
				return false;
		} else if (!row_id.equals(other.row_id))
			return false;
		if (alias_row_id == null) {
			if (other.alias_row_id != null)
				return false;
		} else if (!alias_row_id.equals(other.alias_row_id))
			return false;
		if (id_mdm == null) {
			if (other.id_mdm != null)
				return false;
		} else if (!id_mdm.equals(other.id_mdm))
			return false;
		if (alias_id_mdm == null) {
			if (other.alias_id_mdm != null)
				return false;
		} else if (!alias_id_mdm.equals(other.alias_id_mdm))
			return false;
		if (primer_nombre == null) {
			if (other.primer_nombre != null)
				return false;
		} else if (!primer_nombre.equals(other.primer_nombre))
			return false;
		if (rfc == null) {
			if (other.rfc != null)
				return false;
		} else if (!rfc.equals(other.rfc))
			return false;
		if (salida_apellido_paterno == null) {
			if (other.salida_apellido_paterno != null)
				return false;
		} else if (!salida_apellido_paterno.equals(other.salida_apellido_paterno))
			return false;
		if (salida_bandera_fecha == null) {
			if (other.salida_bandera_fecha != null)
				return false;
		} else if (!salida_bandera_fecha.equals(other.salida_bandera_fecha))
			return false;
		if (salida_bandera_rfc == null) {
			if (other.salida_bandera_rfc != null)
				return false;
		} else if (!salida_bandera_rfc.equals(other.salida_bandera_rfc))
			return false;
		if (salida_fecha_de_nacimiento == null) {
			if (other.salida_fecha_de_nacimiento != null)
				return false;
		} else if (!salida_fecha_de_nacimiento.equals(other.salida_fecha_de_nacimiento))
			return false;
		if (salida_genero == null) {
			if (other.salida_genero != null)
				return false;
		} else if (!salida_genero.equals(other.salida_genero))
			return false;
		if (salida_primer_nombre == null) {
			if (other.salida_primer_nombre != null)
				return false;
		} else if (!salida_primer_nombre.equals(other.salida_primer_nombre))
			return false;
		if (salida_rfc == null) {
			if (other.salida_rfc != null)
				return false;
		} else if (!salida_rfc.equals(other.salida_rfc))
			return false;
		if (salida_apellido_materno == null) {
			if (other.salida_apellido_materno != null)
				return false;
		} else if (!salida_apellido_materno.equals(other.salida_apellido_materno))
			return false;
		if (salida_token_apellido_paterno == null) {
			if (other.salida_token_apellido_paterno != null)
				return false;
		} else if (!salida_token_apellido_paterno.equals(other.salida_token_apellido_paterno))
			return false;
		if (salida_token_primer_nombre == null) {
			if (other.salida_token_primer_nombre != null)
				return false;
		} else if (!salida_token_primer_nombre.equals(other.salida_token_primer_nombre))
			return false;
		if (salida_token_apellido_materno == null) {
			if (other.salida_token_apellido_materno != null)
				return false;
		} else if (!salida_token_apellido_materno.equals(other.salida_token_apellido_materno))
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
		if (fecha_de_nacimiento_area_gris == null) {
			if (other.fecha_de_nacimiento_area_gris != null)
				return false;
		} else if (!fecha_de_nacimiento_area_gris.equals(other.fecha_de_nacimiento_area_gris))
			return false;
		if (fecha_creacion == null) {
			if (other.fecha_creacion != null)
				return false;
		} else if (!fecha_creacion.equals(other.fecha_creacion))
			return false;
		if (fecha_actualizacion == null) {
			if (other.fecha_actualizacion != null)
				return false;
		} else if (!fecha_actualizacion.equals(other.fecha_actualizacion))
			return false;
		if (id_sistema_externo == null) {
			if (other.id_sistema_externo != null)
				return false;
		} else if (!id_sistema_externo.equals(other.id_sistema_externo))
			return false;
		if (nombre_sistema_externo == null) {
			if (other.nombre_sistema_externo != null)
				return false;
		} else if (!nombre_sistema_externo.equals(other.nombre_sistema_externo))
			return false;
		if (id_atg == null) {
			if (other.id_atg != null)
				return false;
		} else if (!id_atg.equals(other.id_atg))
			return false;
		return true;
	}











	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", primer_nombre=" + primer_nombre + ", segundo_nombre="
				+ segundo_nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno
				+ ", fecha_de_nacimiento=" + fecha_de_nacimiento + ", genero=" + genero + ", rfc=" + rfc
				+ ", numero_de_seguro_social=" + numero_de_seguro_social + ", fecha_de_registro=" + fecha_de_registro
				+ ", id_sistema_origen=" + id_sistema_origen + ", id_transaccion=" + id_transaccion + ", id_operacion="
				+ id_operacion + ", salida_primer_nombre=" + salida_primer_nombre + ", salida_segundo_nombre="
				+ salida_apellido_materno + ", bandera_del_nombre=" + bandera_del_nombre + ", salida_genero="
				+ salida_genero + ", salida_fecha_de_nacimiento=" + salida_fecha_de_nacimiento + ", salida_rfc="
				+ salida_rfc + ", salida_apellido_paterno=" + salida_apellido_paterno + ", bandera_rfc=" + bandera_rfc
				+ ", bandera_ucm=" + bandera_ucm + ", nombre_de_token=" + nombre_de_token + ", fecha_de_la_operacion="
				+ fecha_de_la_operacion + ", salida_bandera_fecha=" + salida_bandera_fecha
				+ ", salida_token_primer_nombre=" + salida_token_primer_nombre + ", salida_token_apellido_paterno="
				+ salida_token_apellido_paterno + ", salida_token_segundo_nombre=" + salida_token_apellido_materno
				+ ", salida_bandera_rfc=" + salida_bandera_rfc + ", id_pais=" + id_pais + ", operacion_crud="
				+ operacion_crud + ", row_id=" + row_id + ", alias_row_id=" + alias_row_id + ", id_mdm=" + id_mdm
				+ ", alias_id_mdm=" + alias_id_mdm +", telefono_de_contacto=" + telefono_de_contacto + ", email_de_contacto="
				+ email_de_contacto + ", direccion=" + direccion + ", cliente_formas_de_pago=" + cliente_formas_de_pago
				+ ", fecha_de_nacimiento_area_gris=" + fecha_de_nacimiento_area_gris
				+ ", fecha_creacion=" + fecha_creacion
				+ ", fecha_actualizacion=" + fecha_actualizacion
				+ ", id_sistema_externo=" + id_sistema_externo
				+ ", nombre_sistema_externo=" + nombre_sistema_externo
				+ ", id_atg=" + id_atg
				+ "]";
	}











	@Override
	public Object clone() {

		try {
	        return (Cliente)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new Cliente(
	        		this.id_cliente,
	        		this.primer_nombre,
	        		this.segundo_nombre,
	        		this.apellido_paterno,
	        		this.apellido_materno,
	        		this.fecha_de_nacimiento,
	        		this.genero,
	        		this.rfc,
	        		this.numero_de_seguro_social,
	        		this.fecha_de_registro,
	        		this.id_sistema_origen,
	        		this.id_transaccion,
	        		this.id_operacion,
	        		this.salida_primer_nombre,
	        		this.salida_apellido_materno,
	        		this.bandera_del_nombre,
	        		this.salida_genero,
	        		this.salida_fecha_de_nacimiento,
	        		this.salida_rfc,
	        		this.salida_apellido_paterno,
	        		this.bandera_rfc,
	        		this.bandera_ucm,
	        		this.nombre_de_token,
	        		this.fecha_de_la_operacion,
	        		this.salida_bandera_fecha,
	        		this.salida_token_primer_nombre,
	        		this.salida_token_apellido_paterno,
	        		this.salida_token_apellido_materno,
	        		this.salida_bandera_rfc,
	        		this.id_pais,
	        		this.operacion_crud,
	        		this.row_id,
	        		this.alias_row_id,
	        		this.id_mdm,
	        		this.alias_id_mdm,
	        		this.telefono_de_contacto,
	        		this.email_de_contacto,
	        		this.direccion,
	        		this.cliente_formas_de_pago,
					this.fecha_de_nacimiento_area_gris,
					this.fecha_creacion,
					this.fecha_actualizacion,
					this.id_sistema_externo,
					this.nombre_sistema_externo,
					this.id_atg
	        		);
	    }
	}



}
