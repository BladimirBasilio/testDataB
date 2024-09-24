package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class Pedido  implements Cloneable  {

	@Nullable private String numero_del_documento;
	@Nullable private String estado_orden;
	@Nullable private String fecha_modificacion;
	@Nullable private String hora_modificacion;
	@Nullable private String estado_renglon_bitacora;
	@Nullable private String fecha_emision;
	@Nullable private String hora_emision;
	@Nullable private String orden_original;
	@Nullable private Integer id_tipo_entrega;
	@Nullable private Integer id_tipo_de_venta;
	@Nullable private Integer id_cliente_remitente;
	@Nullable private Integer id_direcciones_destinatario;
	@Nullable private Integer id_tipo_de_act;
	@Nullable private Integer id_tipo_de_documento;
	@Nullable private Integer id_periodicidad;
	@Nullable private String observaciones;
	@Nullable private Integer id_tienda_y_locacion_destino;
	@Nullable private Integer id_frecuencia_visita;
	@Nullable private String fecha_de_compra;
	@Nullable private Integer id_estado_del_documento;
	@Nullable private String usuario_actualizador_de_estado;
	@Nullable private String operacion_crud;
	@Nullable private Integer id_cliente_destinatario;
	@Nullable private Integer id_cliente_emails_de_contacto;
	@Nullable private Integer id_cliente_telefonos_de_contacto_celular;
	@Nullable private Integer id_cliente_telefonos_de_contacto_fijo;
	@Nullable private Integer id_cliente_telefonos_de_contacto_oficina;
	@Nullable private String clave_homologada;


	public Pedido() {}

    



	public Pedido(String numero_del_documento, String estado_orden, String fecha_modificacion, String hora_modificacion,
			String estado_renglon_bitacora, String fecha_emision, String hora_emision, String orden_original, Integer id_tipo_entrega, Integer id_tipo_de_venta, Integer id_cliente_remitente,
			Integer id_direcciones_destinatario, Integer id_tipo_de_act, Integer id_tipo_de_documento,
			Integer id_periodicidad, String observaciones, Integer id_tienda_y_locacion_destino,
			Integer id_frecuencia_visita, String fecha_de_compra, Integer id_estado_del_documento,
			String usuario_actualizador_de_estado, String operacion_crud, Integer id_cliente_destinatario, Integer id_cliente_emails_de_contacto,
			Integer id_cliente_telefonos_de_contacto_celular, Integer id_cliente_telefonos_de_contacto_fijo, Integer id_cliente_telefonos_de_contacto_oficina,
				  String clave_homologada) {
		super();
		this.numero_del_documento = numero_del_documento;
		this.estado_orden = estado_orden;
		this.fecha_modificacion = fecha_modificacion;
		this.hora_modificacion = hora_modificacion;
		this.estado_renglon_bitacora = estado_renglon_bitacora;
		this.fecha_emision = fecha_emision;
		this.hora_emision = hora_emision;
		this.orden_original = orden_original;
		this.id_tipo_entrega = id_tipo_entrega;
		this.id_tipo_de_venta = id_tipo_de_venta;
		this.id_cliente_remitente = id_cliente_remitente;
		this.id_direcciones_destinatario = id_direcciones_destinatario;
		this.id_tipo_de_act = id_tipo_de_act;
		this.id_tipo_de_documento = id_tipo_de_documento;
		this.id_periodicidad = id_periodicidad;
		this.observaciones = observaciones;
		this.id_tienda_y_locacion_destino = id_tienda_y_locacion_destino;
		this.id_frecuencia_visita = id_frecuencia_visita;
		this.fecha_de_compra = fecha_de_compra;
		this.id_estado_del_documento = id_estado_del_documento;
		this.usuario_actualizador_de_estado = usuario_actualizador_de_estado;
		this.operacion_crud = operacion_crud;
		this.id_cliente_destinatario = id_cliente_destinatario;
		this.id_cliente_emails_de_contacto = id_cliente_emails_de_contacto;
		this.id_cliente_telefonos_de_contacto_celular = id_cliente_telefonos_de_contacto_celular;
		this.id_cliente_telefonos_de_contacto_fijo = id_cliente_telefonos_de_contacto_fijo;
		this.id_cliente_telefonos_de_contacto_oficina = id_cliente_telefonos_de_contacto_oficina;
		this.clave_homologada = clave_homologada;
	}













	public @Nullable String getNumero_del_documento() {
		return numero_del_documento;
	}




	public void setNumero_del_documento(@Nullable String numero_del_documento) {
		this.numero_del_documento = numero_del_documento;
	}




	public @Nullable String getEstado_orden() {
		return estado_orden;
	}




	public void setEstado_orden(@Nullable String estado_orden) {
		this.estado_orden = estado_orden;
	}




	public @Nullable String getFecha_modificacion() {
		return fecha_modificacion;
	}




	public void setFecha_modificacion(@Nullable String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}




	public @Nullable String getHora_modificacion() {
		return hora_modificacion;
	}




	public void setHora_modificacion(@Nullable String hora_modificacion) {
		this.hora_modificacion = hora_modificacion;
	}




	public @Nullable String getEstado_renglon_bitacora() {
		return estado_renglon_bitacora;
	}




	public void setEstado_renglon_bitacora(@Nullable String estado_renglon_bitacora) {
		this.estado_renglon_bitacora = estado_renglon_bitacora;
	}


	public @Nullable String getFecha_emision() {
		return fecha_emision;
	}




	public void setFecha_emision(@Nullable String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	

	public @Nullable String getHora_emision() {
		return hora_emision;
	}




	public void setHora_emision(@Nullable String hora_emision) {
		this.hora_emision = hora_emision;
	}




	public @Nullable String getOrden_original() {
		return orden_original;
	}




	public void setOrden_original(@Nullable String orden_original) {
		this.orden_original = orden_original;
	}




	public @Nullable Integer getId_tipo_entrega() {
		return id_tipo_entrega;
	}




	public void setId_tipo_entrega(@Nullable Integer id_tipo_entrega) {
		this.id_tipo_entrega = id_tipo_entrega;
	}




	public @Nullable Integer getId_tipo_de_venta() {
		return id_tipo_de_venta;
	}




	public void setId_tipo_de_venta(@Nullable Integer id_tipo_de_venta) {
		this.id_tipo_de_venta = id_tipo_de_venta;
	}




	public @Nullable Integer getId_cliente_remitente() {
		return id_cliente_remitente;
	}




	public void setId_cliente_remitente(@Nullable Integer id_cliente_remitente) {
		this.id_cliente_remitente = id_cliente_remitente;
	}



	public @Nullable Integer getId_direcciones_destinatario() {
		return id_direcciones_destinatario;
	}




	public void setId_direcciones_destinatario(@Nullable Integer id_direcciones_destinatario) {
		this.id_direcciones_destinatario = id_direcciones_destinatario;
	}




	public @Nullable Integer getId_tipo_de_act() {
		return id_tipo_de_act;
	}




	public void setId_tipo_de_act(@Nullable Integer id_tipo_de_act) {
		this.id_tipo_de_act = id_tipo_de_act;
	}




	public @Nullable Integer getId_tipo_de_documento() {
		return id_tipo_de_documento;
	}




	public void setId_tipo_de_documento(@Nullable Integer id_tipo_de_documento) {
		this.id_tipo_de_documento = id_tipo_de_documento;
	}




	public @Nullable Integer getId_periodicidad() {
		return id_periodicidad;
	}




	public void setId_periodicidad(@Nullable Integer id_periodicidad) {
		this.id_periodicidad = id_periodicidad;
	}




	public @Nullable String getObservaciones() {
		return observaciones;
	}




	public void setObservaciones(@Nullable String observaciones) {
		this.observaciones = observaciones;
	}




	public @Nullable Integer getId_tienda_y_locacion_destino() {
		return id_tienda_y_locacion_destino;
	}




	public void setId_tienda_y_locacion_destino(@Nullable Integer id_tienda_y_locacion_destino) {
		this.id_tienda_y_locacion_destino = id_tienda_y_locacion_destino;
	}




	public @Nullable Integer getId_frecuencia_visita() {
		return id_frecuencia_visita;
	}




	public void setId_frecuencia_visita(@Nullable Integer id_frecuencia_visita) {
		this.id_frecuencia_visita = id_frecuencia_visita;
	}




	public @Nullable String getFecha_de_compra() {
		return fecha_de_compra;
	}




	public void setFecha_de_compra(@Nullable String fecha_de_compra) {
		this.fecha_de_compra = fecha_de_compra;
	}




	public @Nullable Integer getId_estado_del_documento() {
		return id_estado_del_documento;
	}




	public void setId_estado_del_documento(@Nullable Integer id_estado_del_documento) {
		this.id_estado_del_documento = id_estado_del_documento;
	}

	public @Nullable String getUsuario_actualizador_de_estado() {
		return usuario_actualizador_de_estado;
	}


	public void setUsuario_actualizador_de_estado(@Nullable String usuario_actualizador_de_estado) {
		this.usuario_actualizador_de_estado = usuario_actualizador_de_estado;
	}
	
	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}


	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}

	@Nullable
	public Integer getId_cliente_destinatario() {
		return id_cliente_destinatario;
	}

	public void setId_cliente_destinatario(@Nullable Integer id_cliente_destinatario) {
		this.id_cliente_destinatario = id_cliente_destinatario;
	}

	@Nullable
	public Integer getId_cliente_emails_de_contacto() {
		return id_cliente_emails_de_contacto;
	}

	public void setId_cliente_emails_de_contacto(@Nullable Integer id_cliente_emails_de_contacto) {
		this.id_cliente_emails_de_contacto = id_cliente_emails_de_contacto;
	}

	@Nullable
	public Integer getId_cliente_telefonos_de_contacto_celular() {
		return id_cliente_telefonos_de_contacto_celular;
	}

	public void setId_cliente_telefonos_de_contacto_celular(@Nullable Integer id_cliente_telefonos_de_contacto_celular) {
		this.id_cliente_telefonos_de_contacto_celular = id_cliente_telefonos_de_contacto_celular;
	}

	@Nullable
	public Integer getId_cliente_telefonos_de_contacto_fijo() {
		return id_cliente_telefonos_de_contacto_fijo;
	}

	public void setId_cliente_telefonos_de_contacto_fijo(@Nullable Integer id_cliente_telefonos_de_contacto_fijo) {
		this.id_cliente_telefonos_de_contacto_fijo = id_cliente_telefonos_de_contacto_fijo;
	}

	@Nullable
	public Integer getId_cliente_telefonos_de_contacto_oficina() {
		return id_cliente_telefonos_de_contacto_oficina;
	}

	public void setId_cliente_telefonos_de_contacto_oficina(@Nullable Integer id_cliente_telefonos_de_contacto_oficina) {
		this.id_cliente_telefonos_de_contacto_oficina = id_cliente_telefonos_de_contacto_oficina;
	}

	public @Nullable String getClave_homologada() {
		return clave_homologada;
	}
	
	public void setClave_homologada(@Nullable String clave_homologada) {
		this.clave_homologada = clave_homologada;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado_orden == null) ? 0 : estado_orden.hashCode());
		result = prime * result + ((estado_renglon_bitacora == null) ? 0 : estado_renglon_bitacora.hashCode());
		result = prime * result + ((fecha_de_compra == null) ? 0 : fecha_de_compra.hashCode());
		result = prime * result + ((fecha_emision == null) ? 0 : fecha_emision.hashCode());
		result = prime * result + ((fecha_modificacion == null) ? 0 : fecha_modificacion.hashCode());
		result = prime * result + ((hora_emision == null) ? 0 : hora_emision.hashCode());
		result = prime * result + ((hora_modificacion == null) ? 0 : hora_modificacion.hashCode());
		result = prime * result + ((id_cliente_remitente == null) ? 0 : id_cliente_remitente.hashCode());
		result = prime * result + ((id_direcciones_destinatario == null) ? 0 : id_direcciones_destinatario.hashCode());
		result = prime * result + ((id_estado_del_documento == null) ? 0 : id_estado_del_documento.hashCode());
		result = prime * result + ((id_frecuencia_visita == null) ? 0 : id_frecuencia_visita.hashCode());
		result = prime * result + ((id_periodicidad == null) ? 0 : id_periodicidad.hashCode());
		result = prime * result
				+ ((id_tienda_y_locacion_destino == null) ? 0 : id_tienda_y_locacion_destino.hashCode());
		result = prime * result + ((id_tipo_de_act == null) ? 0 : id_tipo_de_act.hashCode());
		result = prime * result + ((id_tipo_de_documento == null) ? 0 : id_tipo_de_documento.hashCode());
		result = prime * result + ((id_tipo_de_venta == null) ? 0 : id_tipo_de_venta.hashCode());
		result = prime * result + ((id_tipo_entrega == null) ? 0 : id_tipo_entrega.hashCode());
		result = prime * result + ((numero_del_documento == null) ? 0 : numero_del_documento.hashCode());
		result = prime * result + ((observaciones == null) ? 0 : observaciones.hashCode());
		result = prime * result + ((usuario_actualizador_de_estado == null) ? 0 : usuario_actualizador_de_estado.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((orden_original == null) ? 0 : orden_original.hashCode());
		result = prime * result + ((id_cliente_destinatario == null) ? 0 : id_cliente_destinatario.hashCode());
		result = prime * result + ((id_cliente_emails_de_contacto == null) ? 0 : id_cliente_emails_de_contacto.hashCode());
		result = prime * result + ((id_cliente_telefonos_de_contacto_fijo == null) ? 0 : id_cliente_telefonos_de_contacto_fijo.hashCode());
		result = prime * result + ((id_cliente_telefonos_de_contacto_celular == null) ? 0 : id_cliente_telefonos_de_contacto_celular.hashCode());
		result = prime * result + ((id_cliente_telefonos_de_contacto_oficina == null) ? 0 : id_cliente_telefonos_de_contacto_oficina.hashCode());
		result = prime * result + ((clave_homologada == null) ? 0 : clave_homologada.hashCode());
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
		Pedido other = (Pedido) obj;
		if (estado_orden == null) {
			if (other.estado_orden != null)
				return false;
		} else if (!estado_orden.equals(other.estado_orden))
			return false;
		if (estado_renglon_bitacora == null) {
			if (other.estado_renglon_bitacora != null)
				return false;
		} else if (!estado_renglon_bitacora.equals(other.estado_renglon_bitacora))
			return false;
		if (fecha_de_compra == null) {
			if (other.fecha_de_compra != null)
				return false;
		} else if (!fecha_de_compra.equals(other.fecha_de_compra))
			return false;
		if (fecha_emision == null) {
			if (other.fecha_emision != null)
				return false;
		} else if (!fecha_emision.equals(other.fecha_emision))
			return false;
		if (fecha_modificacion == null) {
			if (other.fecha_modificacion != null)
				return false;
		} else if (!fecha_modificacion.equals(other.fecha_modificacion))
			return false;
		if (hora_emision == null) {
			if (other.hora_emision != null)
				return false;
		} else if (!hora_emision.equals(other.hora_emision))
			return false;
		if (hora_modificacion == null) {
			if (other.hora_modificacion != null)
				return false;
		} else if (!hora_modificacion.equals(other.hora_modificacion))
			return false;
		if (id_cliente_remitente == null) {
			if (other.id_cliente_remitente != null)
				return false;
		} else if (!id_cliente_remitente.equals(other.id_cliente_remitente))
			return false;
		if (id_direcciones_destinatario == null) {
			if (other.id_direcciones_destinatario != null)
				return false;
		} else if (!id_direcciones_destinatario.equals(other.id_direcciones_destinatario))
			return false;
		if (id_estado_del_documento == null) {
			if (other.id_estado_del_documento != null)
				return false;
		} else if (!id_estado_del_documento.equals(other.id_estado_del_documento))
			return false;
		if (id_frecuencia_visita == null) {
			if (other.id_frecuencia_visita != null)
				return false;
		} else if (!id_frecuencia_visita.equals(other.id_frecuencia_visita))
			return false;
		if (id_periodicidad == null) {
			if (other.id_periodicidad != null)
				return false;
		} else if (!id_periodicidad.equals(other.id_periodicidad))
			return false;
		if (id_tienda_y_locacion_destino == null) {
			if (other.id_tienda_y_locacion_destino != null)
				return false;
		} else if (!id_tienda_y_locacion_destino.equals(other.id_tienda_y_locacion_destino))
			return false;
		if (id_tipo_de_act == null) {
			if (other.id_tipo_de_act != null)
				return false;
		} else if (!id_tipo_de_act.equals(other.id_tipo_de_act))
			return false;
		if (id_tipo_de_documento == null) {
			if (other.id_tipo_de_documento != null)
				return false;
		} else if (!id_tipo_de_documento.equals(other.id_tipo_de_documento))
			return false;
		if (id_tipo_de_venta == null) {
			if (other.id_tipo_de_venta != null)
				return false;
		} else if (!id_tipo_de_venta.equals(other.id_tipo_de_venta))
			return false;
		if (id_tipo_entrega == null) {
			if (other.id_tipo_entrega != null)
				return false;
		} else if (!id_tipo_entrega.equals(other.id_tipo_entrega))
			return false;
		if (numero_del_documento == null) {
			if (other.numero_del_documento != null)
				return false;
		} else if (!numero_del_documento.equals(other.numero_del_documento))
			return false;
		if (observaciones == null) {
			if (other.observaciones != null)
				return false;
		} else if (!observaciones.equals(other.observaciones))
			return false;
		if (usuario_actualizador_de_estado == null) {
			if (other.usuario_actualizador_de_estado != null)
				return false;
		} else if (!usuario_actualizador_de_estado.equals(other.usuario_actualizador_de_estado))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (orden_original == null) {
			if (other.orden_original != null)
				return false;
		} else if (!orden_original.equals(other.orden_original))
			return false;
		if (id_cliente_destinatario == null) {
			if (other.id_cliente_destinatario != null)
				return false;
		} else if (!id_cliente_destinatario.equals(other.id_cliente_destinatario))
			return false;
		if (id_cliente_emails_de_contacto == null) {
			if (other.id_cliente_emails_de_contacto != null)
				return false;
		} else if (!id_cliente_emails_de_contacto.equals(other.id_cliente_emails_de_contacto))
			return false;
		if (id_cliente_telefonos_de_contacto_fijo == null) {
			if (other.id_cliente_telefonos_de_contacto_fijo != null)
				return false;
		} else if (!id_cliente_telefonos_de_contacto_fijo.equals(other.id_cliente_telefonos_de_contacto_fijo))
			return false;
		if (id_cliente_telefonos_de_contacto_celular == null) {
			if (other.id_cliente_telefonos_de_contacto_celular != null)
				return false;
		} else if (!id_cliente_telefonos_de_contacto_celular.equals(other.id_cliente_telefonos_de_contacto_celular))
			return false;
		if (id_cliente_telefonos_de_contacto_oficina == null) {
			if (other.id_cliente_telefonos_de_contacto_oficina != null)
				return false;
		} else if (!id_cliente_telefonos_de_contacto_oficina.equals(other.id_cliente_telefonos_de_contacto_oficina))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Pedido{" +
				"numero_del_documento='" + numero_del_documento + '\'' +
				", estado_orden='" + estado_orden + '\'' +
				", fecha_modificacion='" + fecha_modificacion + '\'' +
				", hora_modificacion='" + hora_modificacion + '\'' +
				", estado_renglon_bitacora='" + estado_renglon_bitacora + '\'' +
				", fecha_emision='" + fecha_emision + '\'' +
				", hora_emision='" + hora_emision + '\'' +
				", orden_original='" + orden_original + '\'' +
				", id_tipo_entrega=" + id_tipo_entrega +
				", id_tipo_de_venta=" + id_tipo_de_venta +
				", id_cliente_remitente=" + id_cliente_remitente +
				", id_direcciones_destinatario=" + id_direcciones_destinatario +
				", id_tipo_de_act=" + id_tipo_de_act +
				", id_tipo_de_documento=" + id_tipo_de_documento +
				", id_periodicidad=" + id_periodicidad +
				", observaciones='" + observaciones + '\'' +
				", id_tienda_y_locacion_destino=" + id_tienda_y_locacion_destino +
				", id_frecuencia_visita=" + id_frecuencia_visita +
				", fecha_de_compra='" + fecha_de_compra + '\'' +
				", id_estado_del_documento=" + id_estado_del_documento +
				", usuario_actualizador_de_estado='" + usuario_actualizador_de_estado + '\'' +
				", operacion_crud='" + operacion_crud + '\'' +
				", id_cliente_destinatario=" + id_cliente_destinatario +
				", id_cliente_emails_de_contacto=" + id_cliente_emails_de_contacto +
				", id_cliente_telefonos_de_contacto_celular=" + id_cliente_telefonos_de_contacto_celular +
				", id_cliente_telefonos_de_contacto_fijo=" + id_cliente_telefonos_de_contacto_fijo +
				", id_cliente_telefonos_de_contacto_oficina=" + id_cliente_telefonos_de_contacto_oficina +
				", clave_homologada=" + clave_homologada +
				'}';
	}

	@Override
	public Object clone() {
		try {
	        return (Pedido)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new Pedido(
	        		this.numero_del_documento,
	        		this.estado_orden,
	        		this.fecha_modificacion,
	        		this.hora_modificacion,
	        		this.estado_renglon_bitacora,
	        		this.fecha_emision,
	        		this.hora_emision,
	        		this.orden_original,
	        		this.id_tipo_entrega,
	        		this.id_tipo_de_venta,
	        		this.id_cliente_remitente,
	        		this.id_direcciones_destinatario,
	        		this.id_tipo_de_act,
	        		this.id_tipo_de_documento,
	        		this.id_periodicidad,
	        		this.observaciones,
	        		this.id_tienda_y_locacion_destino,
	        		this.id_frecuencia_visita,
	        		this.fecha_de_compra,
	        		this.id_estado_del_documento,
	        		this.usuario_actualizador_de_estado,
	        		this.operacion_crud,
					this.id_cliente_destinatario,
					this.id_cliente_emails_de_contacto,
					this.id_cliente_telefonos_de_contacto_celular,
					this.id_cliente_telefonos_de_contacto_fijo,
					this.id_cliente_telefonos_de_contacto_oficina,
					this.clave_homologada
	        		);
	    }
	}

}
