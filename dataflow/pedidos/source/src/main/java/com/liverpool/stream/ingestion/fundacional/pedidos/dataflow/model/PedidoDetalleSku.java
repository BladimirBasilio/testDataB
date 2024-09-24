package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model;

import java.math.BigDecimal;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class PedidoDetalleSku {

	@Nullable private String numero_del_documento_pedidos;
	@Nullable private String estado_orden_pedidos;
	@Nullable private String fecha_modificacion_pedidos;
	@Nullable private String hora_modificacion_pedidos;
	@Nullable private String proveedor_de_mensajeria;
	@Nullable private String fecha_real_de_entrega;
	@Nullable private Integer id_locacion_surte;
	@Nullable private Integer id_producto_productos;
	@Nullable private String fecha_actualizacion_estado;
	@Nullable private BigDecimal piezas;
	@Nullable private String numero_de_guia;
	@Nullable private Integer id_linea_detalle;
	@Nullable private String fecha_promesa_de_entrega_inicial;
	@Nullable private String hora_actualizacion_estado;
	@Nullable private String fecha_ultimo_intento;
	@Nullable private String indicador_marketplace;
	@Nullable private BigDecimal cantidad_recogida_orden_de_venta;
	@Nullable private String fecha_de_surtido;
	@Nullable private BigDecimal numero_de_intentos_de_entrega;
	@Nullable private BigDecimal cantidad_cancelada;
	@Nullable private BigDecimal cantidad_entregada;
	@Nullable private Integer id_causa_de_noentrega;
	@Nullable private String usuario_actualizador_de_estado;
	@Nullable private String fecha_recalculada;
	@Nullable private String operacion_crud;
	@Nullable private String fecha_promesa_de_entrega_final;
	@Nullable private Integer id_estado_por_linea;
	@Nullable private String fecha_de_compra_pedidos;
	@Nullable private String sku;
	
	
    public PedidoDetalleSku() {}


	public PedidoDetalleSku(String numero_del_documento_pedidos, String estado_orden_pedidos,
			String fecha_modificacion_pedidos, String hora_modificacion_pedidos, String proveedor_de_mensajeria,
			String fecha_real_de_entrega, Integer id_locacion_surte, Integer id_producto_productos,
			String fecha_actualizacion_estado, BigDecimal piezas, String numero_de_guia, Integer id_linea_detalle,
			String fecha_promesa_de_entrega_inicial, String hora_actualizacion_estado, String fecha_ultimo_intento,
			String indicador_marketplace, BigDecimal cantidad_recogida_orden_de_venta, String fecha_de_surtido,
			BigDecimal numero_de_intentos_de_entrega, BigDecimal cantidad_cancelada, BigDecimal cantidad_entregada,
			Integer id_causa_de_noentrega, String usuario_actualizador_de_estado, String fecha_recalculada,
			String operacion_crud, String fecha_promesa_de_entrega_final, Integer id_estado_por_linea, String fecha_de_compra_pedidos, String sku) {
		super();
		this.numero_del_documento_pedidos = numero_del_documento_pedidos;
		this.estado_orden_pedidos = estado_orden_pedidos;
		this.fecha_modificacion_pedidos = fecha_modificacion_pedidos;
		this.hora_modificacion_pedidos = hora_modificacion_pedidos;
		this.proveedor_de_mensajeria = proveedor_de_mensajeria;
		this.fecha_real_de_entrega = fecha_real_de_entrega;
		this.id_locacion_surte = id_locacion_surte;
		this.id_producto_productos = id_producto_productos;
		this.fecha_actualizacion_estado = fecha_actualizacion_estado;
		this.piezas = piezas;
		this.numero_de_guia = numero_de_guia;
		this.id_linea_detalle = id_linea_detalle;
		this.fecha_promesa_de_entrega_inicial = fecha_promesa_de_entrega_inicial;
		this.hora_actualizacion_estado = hora_actualizacion_estado;
		this.fecha_ultimo_intento = fecha_ultimo_intento;
		this.indicador_marketplace = indicador_marketplace;
		this.cantidad_recogida_orden_de_venta = cantidad_recogida_orden_de_venta;
		this.fecha_de_surtido = fecha_de_surtido;
		this.numero_de_intentos_de_entrega = numero_de_intentos_de_entrega;
		this.cantidad_cancelada = cantidad_cancelada;
		this.cantidad_entregada = cantidad_entregada;
		this.id_causa_de_noentrega = id_causa_de_noentrega;
		this.usuario_actualizador_de_estado = usuario_actualizador_de_estado;
		this.fecha_recalculada = fecha_recalculada;
		this.operacion_crud = operacion_crud;
		this.fecha_promesa_de_entrega_final = fecha_promesa_de_entrega_final;
		this.id_estado_por_linea = id_estado_por_linea;
		this.fecha_de_compra_pedidos = fecha_de_compra_pedidos;
		this.sku = sku;
	}








	public @Nullable String getNumero_del_documento_pedidos() {
		return numero_del_documento_pedidos;
	}






	public void setNumero_del_documento_pedidos(@Nullable String numero_del_documento_pedidos) {
		this.numero_del_documento_pedidos = numero_del_documento_pedidos;
	}






	public @Nullable String getEstado_orden_pedidos() {
		return estado_orden_pedidos;
	}






	public void setEstado_orden_pedidos(@Nullable String estado_orden_pedidos) {
		this.estado_orden_pedidos = estado_orden_pedidos;
	}






	public @Nullable String getFecha_modificacion_pedidos() {
		return fecha_modificacion_pedidos;
	}






	public void setFecha_modificacion_pedidos(@Nullable String fecha_modificacion_pedidos) {
		this.fecha_modificacion_pedidos = fecha_modificacion_pedidos;
	}






	public @Nullable String getHora_modificacion_pedidos() {
		return hora_modificacion_pedidos;
	}






	public void setHora_modificacion_pedidos(@Nullable String hora_modificacion_pedidos) {
		this.hora_modificacion_pedidos = hora_modificacion_pedidos;
	}






	public @Nullable String getProveedor_de_mensajeria() {
		return proveedor_de_mensajeria;
	}






	public void setProveedor_de_mensajeria(@Nullable String proveedor_de_mensajeria) {
		this.proveedor_de_mensajeria = proveedor_de_mensajeria;
	}






	public @Nullable String getFecha_real_de_entrega() {
		return fecha_real_de_entrega;
	}






	public void setFecha_real_de_entrega(@Nullable String fecha_real_de_entrega) {
		this.fecha_real_de_entrega = fecha_real_de_entrega;
	}






	public @Nullable Integer getId_locacion_surte() {
		return id_locacion_surte;
	}






	public void setId_locacion_surte(@Nullable Integer id_locacion_surte) {
		this.id_locacion_surte = id_locacion_surte;
	}






	public @Nullable Integer getId_producto_productos() {
		return id_producto_productos;
	}






	public void setId_producto_productos(@Nullable Integer id_producto_productos) {
		this.id_producto_productos = id_producto_productos;
	}






	public @Nullable String getFecha_actualizacion_estado() {
		return fecha_actualizacion_estado;
	}






	public void setFecha_actualizacion_estado(@Nullable String fecha_actualizacion_estado) {
		this.fecha_actualizacion_estado = fecha_actualizacion_estado;
	}






	public @Nullable BigDecimal getPiezas() {
		return piezas;
	}






	public void setPiezas(@Nullable BigDecimal piezas) {
		this.piezas = piezas;
	}






	public @Nullable String getNumero_de_guia() {
		return numero_de_guia;
	}






	public void setNumero_de_guia(@Nullable String numero_de_guia) {
		this.numero_de_guia = numero_de_guia;
	}






	public @Nullable Integer getId_linea_detalle() {
		return id_linea_detalle;
	}






	public void setId_linea_detalle(@Nullable Integer id_linea_detalle) {
		this.id_linea_detalle = id_linea_detalle;
	}






	public @Nullable String getFecha_promesa_de_entrega_inicial() {
		return fecha_promesa_de_entrega_inicial;
	}






	public void setFecha_promesa_de_entrega_inicial(@Nullable String fecha_promesa_de_entrega_inicial) {
		this.fecha_promesa_de_entrega_inicial = fecha_promesa_de_entrega_inicial;
	}






	public @Nullable String getHora_actualizacion_estado() {
		return hora_actualizacion_estado;
	}






	public void setHora_actualizacion_estado(@Nullable String hora_actualizacion_estado) {
		this.hora_actualizacion_estado = hora_actualizacion_estado;
	}






	public @Nullable String getFecha_ultimo_intento() {
		return fecha_ultimo_intento;
	}






	public void setFecha_ultimo_intento(@Nullable String fecha_ultimo_intento) {
		this.fecha_ultimo_intento = fecha_ultimo_intento;
	}






	public @Nullable String getIndicador_marketplace() {
		return indicador_marketplace;
	}






	public void setIndicador_marketplace(@Nullable String indicador_marketplace) {
		this.indicador_marketplace = indicador_marketplace;
	}






	public @Nullable BigDecimal getCantidad_recogida_orden_de_venta() {
		return cantidad_recogida_orden_de_venta;
	}






	public void setCantidad_recogida_orden_de_venta(@Nullable BigDecimal cantidad_recogida_orden_de_venta) {
		this.cantidad_recogida_orden_de_venta = cantidad_recogida_orden_de_venta;
	}






	public @Nullable String getFecha_de_surtido() {
		return fecha_de_surtido;
	}






	public void setFecha_de_surtido(@Nullable String fecha_de_surtido) {
		this.fecha_de_surtido = fecha_de_surtido;
	}






	public @Nullable BigDecimal getNumero_de_intentos_de_entrega() {
		return numero_de_intentos_de_entrega;
	}






	public void setNumero_de_intentos_de_entrega(@Nullable BigDecimal numero_de_intentos_de_entrega) {
		this.numero_de_intentos_de_entrega = numero_de_intentos_de_entrega;
	}






	public @Nullable BigDecimal getCantidad_cancelada() {
		return cantidad_cancelada;
	}






	public void setCantidad_cancelada(@Nullable BigDecimal cantidad_cancelada) {
		this.cantidad_cancelada = cantidad_cancelada;
	}






	public @Nullable BigDecimal getCantidad_entregada() {
		return cantidad_entregada;
	}






	public void setCantidad_entregada(@Nullable BigDecimal cantidad_entregada) {
		this.cantidad_entregada = cantidad_entregada;
	}






	public @Nullable Integer getId_causa_de_noentrega() {
		return id_causa_de_noentrega;
	}






	public void setId_causa_de_noentrega(@Nullable Integer id_causa_de_noentrega) {
		this.id_causa_de_noentrega = id_causa_de_noentrega;
	}






	public @Nullable String getUsuario_actualizador_de_estado() {
		return usuario_actualizador_de_estado;
	}






	public void setUsuario_actualizador_de_estado(@Nullable String usuario_actualizador_de_estado) {
		this.usuario_actualizador_de_estado = usuario_actualizador_de_estado;
	}






	public @Nullable String getFecha_recalculada() {
		return fecha_recalculada;
	}






	public void setFecha_recalculada(@Nullable String fecha_recalculada) {
		this.fecha_recalculada = fecha_recalculada;
	}



	public @Nullable String getOperacion_crud() {
		return operacion_crud;
	}


	public void setOperacion_crud(@Nullable String operacion_crud) {
		this.operacion_crud = operacion_crud;
	}



	


	public @Nullable String getFecha_promesa_de_entrega_final() {
		return fecha_promesa_de_entrega_final;
	}



	public void setFecha_promesa_de_entrega_final(@Nullable String fecha_promesa_de_entrega_final) {
		this.fecha_promesa_de_entrega_final = fecha_promesa_de_entrega_final;
	}


	public @Nullable Integer getId_estado_por_linea() {
		return id_estado_por_linea;
	}


	public void setId_estado_por_linea(@Nullable Integer id_estado_por_linea) {
		this.id_estado_por_linea = id_estado_por_linea;
	}


	public @Nullable String getFecha_de_compra_pedidos() {
		return fecha_de_compra_pedidos;
	}

	public void setFecha_de_compra_pedidos(@Nullable String fecha_de_compra_pedidos) {
		this.fecha_de_compra_pedidos = fecha_de_compra_pedidos;
	}

	public @Nullable String getSku() {
		return sku;
	}

	public void setSku(@Nullable String sku) {
		this.sku = sku;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad_cancelada == null) ? 0 : cantidad_cancelada.hashCode());
		result = prime * result + ((cantidad_entregada == null) ? 0 : cantidad_entregada.hashCode());
		result = prime * result
				+ ((cantidad_recogida_orden_de_venta == null) ? 0 : cantidad_recogida_orden_de_venta.hashCode());
		result = prime * result + ((estado_orden_pedidos == null) ? 0 : estado_orden_pedidos.hashCode());
		result = prime * result + ((fecha_actualizacion_estado == null) ? 0 : fecha_actualizacion_estado.hashCode());
		result = prime * result + ((fecha_de_surtido == null) ? 0 : fecha_de_surtido.hashCode());
		result = prime * result + ((fecha_modificacion_pedidos == null) ? 0 : fecha_modificacion_pedidos.hashCode());
		result = prime * result
				+ ((fecha_promesa_de_entrega_final == null) ? 0 : fecha_promesa_de_entrega_final.hashCode());
		result = prime * result
				+ ((fecha_promesa_de_entrega_inicial == null) ? 0 : fecha_promesa_de_entrega_inicial.hashCode());
		result = prime * result + ((fecha_real_de_entrega == null) ? 0 : fecha_real_de_entrega.hashCode());
		result = prime * result + ((fecha_recalculada == null) ? 0 : fecha_recalculada.hashCode());
		result = prime * result + ((fecha_ultimo_intento == null) ? 0 : fecha_ultimo_intento.hashCode());
		result = prime * result + ((hora_actualizacion_estado == null) ? 0 : hora_actualizacion_estado.hashCode());
		result = prime * result + ((hora_modificacion_pedidos == null) ? 0 : hora_modificacion_pedidos.hashCode());
		result = prime * result + ((id_causa_de_noentrega == null) ? 0 : id_causa_de_noentrega.hashCode());
		result = prime * result + ((id_estado_por_linea == null) ? 0 : id_estado_por_linea.hashCode());
		result = prime * result + ((id_linea_detalle == null) ? 0 : id_linea_detalle.hashCode());
		result = prime * result + ((id_locacion_surte == null) ? 0 : id_locacion_surte.hashCode());
		result = prime * result + ((id_producto_productos == null) ? 0 : id_producto_productos.hashCode());
		result = prime * result + ((indicador_marketplace == null) ? 0 : indicador_marketplace.hashCode());
		result = prime * result + ((numero_de_guia == null) ? 0 : numero_de_guia.hashCode());
		result = prime * result
				+ ((numero_de_intentos_de_entrega == null) ? 0 : numero_de_intentos_de_entrega.hashCode());
		result = prime * result
				+ ((numero_del_documento_pedidos == null) ? 0 : numero_del_documento_pedidos.hashCode());
		result = prime * result + ((operacion_crud == null) ? 0 : operacion_crud.hashCode());
		result = prime * result + ((piezas == null) ? 0 : piezas.hashCode());
		result = prime * result + ((proveedor_de_mensajeria == null) ? 0 : proveedor_de_mensajeria.hashCode());
		result = prime * result
				+ ((usuario_actualizador_de_estado == null) ? 0 : usuario_actualizador_de_estado.hashCode());
		result = prime * result + ((fecha_de_compra_pedidos == null) ? 0 : fecha_de_compra_pedidos.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		PedidoDetalleSku other = (PedidoDetalleSku) obj;
		if (cantidad_cancelada == null) {
			if (other.cantidad_cancelada != null)
				return false;
		} else if (!cantidad_cancelada.equals(other.cantidad_cancelada))
			return false;
		if (cantidad_entregada == null) {
			if (other.cantidad_entregada != null)
				return false;
		} else if (!cantidad_entregada.equals(other.cantidad_entregada))
			return false;
		if (cantidad_recogida_orden_de_venta == null) {
			if (other.cantidad_recogida_orden_de_venta != null)
				return false;
		} else if (!cantidad_recogida_orden_de_venta.equals(other.cantidad_recogida_orden_de_venta))
			return false;
		if (estado_orden_pedidos == null) {
			if (other.estado_orden_pedidos != null)
				return false;
		} else if (!estado_orden_pedidos.equals(other.estado_orden_pedidos))
			return false;
		if (fecha_actualizacion_estado == null) {
			if (other.fecha_actualizacion_estado != null)
				return false;
		} else if (!fecha_actualizacion_estado.equals(other.fecha_actualizacion_estado))
			return false;
		if (fecha_de_surtido == null) {
			if (other.fecha_de_surtido != null)
				return false;
		} else if (!fecha_de_surtido.equals(other.fecha_de_surtido))
			return false;
		if (fecha_modificacion_pedidos == null) {
			if (other.fecha_modificacion_pedidos != null)
				return false;
		} else if (!fecha_modificacion_pedidos.equals(other.fecha_modificacion_pedidos))
			return false;
		if (fecha_promesa_de_entrega_final == null) {
			if (other.fecha_promesa_de_entrega_final != null)
				return false;
		} else if (!fecha_promesa_de_entrega_final.equals(other.fecha_promesa_de_entrega_final))
			return false;
		if (fecha_promesa_de_entrega_inicial == null) {
			if (other.fecha_promesa_de_entrega_inicial != null)
				return false;
		} else if (!fecha_promesa_de_entrega_inicial.equals(other.fecha_promesa_de_entrega_inicial))
			return false;
		if (fecha_real_de_entrega == null) {
			if (other.fecha_real_de_entrega != null)
				return false;
		} else if (!fecha_real_de_entrega.equals(other.fecha_real_de_entrega))
			return false;
		if (fecha_recalculada == null) {
			if (other.fecha_recalculada != null)
				return false;
		} else if (!fecha_recalculada.equals(other.fecha_recalculada))
			return false;
		if (fecha_ultimo_intento == null) {
			if (other.fecha_ultimo_intento != null)
				return false;
		} else if (!fecha_ultimo_intento.equals(other.fecha_ultimo_intento))
			return false;
		if (hora_actualizacion_estado == null) {
			if (other.hora_actualizacion_estado != null)
				return false;
		} else if (!hora_actualizacion_estado.equals(other.hora_actualizacion_estado))
			return false;
		if (hora_modificacion_pedidos == null) {
			if (other.hora_modificacion_pedidos != null)
				return false;
		} else if (!hora_modificacion_pedidos.equals(other.hora_modificacion_pedidos))
			return false;
		if (id_causa_de_noentrega == null) {
			if (other.id_causa_de_noentrega != null)
				return false;
		} else if (!id_causa_de_noentrega.equals(other.id_causa_de_noentrega))
			return false;
		if (id_estado_por_linea == null) {
			if (other.id_estado_por_linea != null)
				return false;
		} else if (!id_estado_por_linea.equals(other.id_estado_por_linea))
			return false;
		if (id_linea_detalle == null) {
			if (other.id_linea_detalle != null)
				return false;
		} else if (!id_linea_detalle.equals(other.id_linea_detalle))
			return false;
		if (id_locacion_surte == null) {
			if (other.id_locacion_surte != null)
				return false;
		} else if (!id_locacion_surte.equals(other.id_locacion_surte))
			return false;
		if (id_producto_productos == null) {
			if (other.id_producto_productos != null)
				return false;
		} else if (!id_producto_productos.equals(other.id_producto_productos))
			return false;
		if (indicador_marketplace == null) {
			if (other.indicador_marketplace != null)
				return false;
		} else if (!indicador_marketplace.equals(other.indicador_marketplace))
			return false;
		if (numero_de_guia == null) {
			if (other.numero_de_guia != null)
				return false;
		} else if (!numero_de_guia.equals(other.numero_de_guia))
			return false;
		if (numero_de_intentos_de_entrega == null) {
			if (other.numero_de_intentos_de_entrega != null)
				return false;
		} else if (!numero_de_intentos_de_entrega.equals(other.numero_de_intentos_de_entrega))
			return false;
		if (numero_del_documento_pedidos == null) {
			if (other.numero_del_documento_pedidos != null)
				return false;
		} else if (!numero_del_documento_pedidos.equals(other.numero_del_documento_pedidos))
			return false;
		if (operacion_crud == null) {
			if (other.operacion_crud != null)
				return false;
		} else if (!operacion_crud.equals(other.operacion_crud))
			return false;
		if (piezas == null) {
			if (other.piezas != null)
				return false;
		} else if (!piezas.equals(other.piezas))
			return false;
		if (proveedor_de_mensajeria == null) {
			if (other.proveedor_de_mensajeria != null)
				return false;
		} else if (!proveedor_de_mensajeria.equals(other.proveedor_de_mensajeria))
			return false;
		if (usuario_actualizador_de_estado == null) {
			if (other.usuario_actualizador_de_estado != null)
				return false;
		} else if (!usuario_actualizador_de_estado.equals(other.usuario_actualizador_de_estado))
			return false;
		if (fecha_de_compra_pedidos == null) {
			if (other.fecha_de_compra_pedidos != null)
				return false;
		} else if (!fecha_de_compra_pedidos.equals(other.fecha_de_compra_pedidos))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PedidoDetalleSku [numero_del_documento_pedidos=" + numero_del_documento_pedidos
				+ ", estado_orden_pedidos=" + estado_orden_pedidos + ", fecha_modificacion_pedidos="
				+ fecha_modificacion_pedidos + ", hora_modificacion_pedidos=" + hora_modificacion_pedidos
				+ ", proveedor_de_mensajeria=" + proveedor_de_mensajeria + ", fecha_real_de_entrega="
				+ fecha_real_de_entrega + ", id_locacion_surte=" + id_locacion_surte + ", id_producto_productos="
				+ id_producto_productos + ", fecha_actualizacion_estado=" + fecha_actualizacion_estado + ", piezas="
				+ piezas + ", numero_de_guia=" + numero_de_guia + ", id_linea_detalle=" + id_linea_detalle
				+ ", fecha_promesa_de_entrega_inicial=" + fecha_promesa_de_entrega_inicial
				+ ", hora_actualizacion_estado=" + hora_actualizacion_estado + ", fecha_ultimo_intento="
				+ fecha_ultimo_intento + ", indicador_marketplace=" + indicador_marketplace
				+ ", cantidad_recogida_orden_de_venta=" + cantidad_recogida_orden_de_venta + ", fecha_de_surtido="
				+ fecha_de_surtido + ", numero_de_intentos_de_entrega=" + numero_de_intentos_de_entrega
				+ ", cantidad_cancelada=" + cantidad_cancelada + ", cantidad_entregada=" + cantidad_entregada
				+ ", id_causa_de_noentrega=" + id_causa_de_noentrega + ", usuario_actualizador_de_estado="
				+ usuario_actualizador_de_estado + ", fecha_recalculada=" + fecha_recalculada + ", operacion_crud="
				+ operacion_crud + ", fecha_promesa_de_entrega_final=" + fecha_promesa_de_entrega_final
				+ ", id_estado_por_linea=" + id_estado_por_linea + ", fecha_de_compras_pedidos=" + fecha_de_compra_pedidos
				+ ", sku=" + sku
				+ "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		try {
	        return (PedidoDetalleSku)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new PedidoDetalleSku(
	        		this.numero_del_documento_pedidos,
	        		this.estado_orden_pedidos,
	        		this.fecha_modificacion_pedidos,
	        		this.hora_modificacion_pedidos,
	        		this.proveedor_de_mensajeria,
	        		this.fecha_real_de_entrega,
	        		this.id_locacion_surte,
	        		this.id_producto_productos,
	        		this.fecha_actualizacion_estado,
	        		this.piezas,
	        		this.numero_de_guia,
	        		this.id_linea_detalle,
	        		this.fecha_promesa_de_entrega_inicial,
	        		this.hora_actualizacion_estado,
	        		this.fecha_ultimo_intento,
	        		this.indicador_marketplace,
	        		this.cantidad_recogida_orden_de_venta,
	        		this.fecha_de_surtido,
	        		this.numero_de_intentos_de_entrega,
	        		this.cantidad_cancelada,
	        		this.cantidad_entregada,
	        		this.id_causa_de_noentrega,
	        		this.usuario_actualizador_de_estado,
	        		this.fecha_recalculada,
	        		this.operacion_crud,
	        		this.fecha_promesa_de_entrega_final,
	        		this.id_estado_por_linea,
					this.fecha_de_compra_pedidos,
					this.sku
	        		);
	    }
	}
}
