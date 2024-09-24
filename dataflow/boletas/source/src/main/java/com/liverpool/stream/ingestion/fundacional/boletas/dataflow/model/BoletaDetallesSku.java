package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetallesSku {

	
	@Nullable private Integer id_linea_detalle;
	@Nullable private String id_terminal_pos_boleta_cabeceras;
	@Nullable private String numero_boleta_boleta_cabeceras;
	@Nullable private String fecha_fin_transaccion_boleta_cabeceras;
	@Nullable private String hora_fin_transaccion_boleta_cabeceras;
	@Nullable private String id_tienda_origen_boleta_cabeceras;
	@Nullable private String id_tienda_reconocimiento_boleta_cabeceras;
	@Nullable private Integer id_sku;
	@Nullable private String id_pep_r3; // no usado
	@Nullable private String telefono_cliente_garantia_extendida;		
	@Nullable private String numero_orden_optica;
	@Nullable private String orden_de_reparacion;
	@Nullable private String numero_telefono_o_folio;	
	@Nullable private String numero_comanda_restaurant;
	@Nullable private String numero_poliza;
	@Nullable private BigDecimal cantidad_articulos;
	@Nullable private BigDecimal precio_subtotal_neto;
	@Nullable private Integer id_motivo_devolucion;
	@Nullable private Integer id_seccion;
	@Nullable private BigDecimal precio_unitario_del_sku;
	@Nullable private String fisico_o_virtual;
	@Nullable private String es_marca_liverpool;
	@Nullable private List<BoletaDetallesSkuDescuentos> boleta_detalles_sku_descuentos;
	@Nullable private String indicador_tipo_precio;
	@Nullable private String descripcion_descuento_sku;
	@Nullable private String fecha_promesa_de_entrega_inicial;
	@Nullable private String fecha_promesa_de_entrega_fin;
	@Nullable private String seller_marketplace;
	@Nullable private String error_id_sku;
	@Nullable private String error_id_seccion;

	public BoletaDetallesSku() {}

	public BoletaDetallesSku(Integer id_linea_detalle, String id_terminal_pos_boleta_cabeceras,
			String numero_boleta_boleta_cabeceras, String fecha_fin_transaccion_boleta_cabeceras,
			String hora_fin_transaccion_boleta_cabeceras, String id_tienda_origen_boleta_cabeceras,
			String id_tienda_reconocimiento_boleta_cabeceras, Integer id_sku, String id_pep_r3,
			String telefono_cliente_garantia_extendida, String numero_orden_optica, String orden_de_reparacion,
			String numero_telefono_o_folio, String numero_comanda_restaurant, String numero_poliza,
			BigDecimal cantidad_articulos, BigDecimal precio_subtotal_neto, Integer id_motivo_devolucion,
			Integer id_seccion, BigDecimal precio_unitario_del_sku, String fisico_o_virtual,
			String es_marca_liverpool, List<BoletaDetallesSkuDescuentos> boleta_detalles_sku_descuentos,
			 String indicador_tipo_precio, String descripcion_descuento_sku, String fecha_promesa_de_entrega_inicial,
			String fecha_promesa_de_entrega_fin ,  String seller_marketplace, String error_id_sku, String error_id_seccion
	) {
		super();
		this.id_linea_detalle = id_linea_detalle;
		this.id_terminal_pos_boleta_cabeceras = id_terminal_pos_boleta_cabeceras;
		this.numero_boleta_boleta_cabeceras = numero_boleta_boleta_cabeceras;
		this.fecha_fin_transaccion_boleta_cabeceras = fecha_fin_transaccion_boleta_cabeceras;
		this.hora_fin_transaccion_boleta_cabeceras = hora_fin_transaccion_boleta_cabeceras;
		this.id_tienda_origen_boleta_cabeceras = id_tienda_origen_boleta_cabeceras;
		this.id_tienda_reconocimiento_boleta_cabeceras = id_tienda_reconocimiento_boleta_cabeceras;
		this.id_sku = id_sku;
		this.id_pep_r3 = id_pep_r3;
		this.telefono_cliente_garantia_extendida = telefono_cliente_garantia_extendida;
		this.numero_orden_optica = numero_orden_optica;
		this.orden_de_reparacion = orden_de_reparacion;
		this.numero_telefono_o_folio = numero_telefono_o_folio;
		this.numero_comanda_restaurant = numero_comanda_restaurant;
		this.numero_poliza = numero_poliza;
		this.cantidad_articulos = cantidad_articulos;
		this.precio_subtotal_neto = precio_subtotal_neto;
		this.id_motivo_devolucion = id_motivo_devolucion;
		this.id_seccion = id_seccion;
		this.precio_unitario_del_sku = precio_unitario_del_sku;
		this.fisico_o_virtual = fisico_o_virtual;
		this.es_marca_liverpool = es_marca_liverpool;
		this.boleta_detalles_sku_descuentos = boleta_detalles_sku_descuentos;
		this.indicador_tipo_precio = indicador_tipo_precio;
		this.descripcion_descuento_sku = descripcion_descuento_sku;
		this.fecha_promesa_de_entrega_inicial = fecha_promesa_de_entrega_inicial;
		this.fecha_promesa_de_entrega_fin = fecha_promesa_de_entrega_fin;
		this.seller_marketplace = seller_marketplace;
		this.error_id_sku = error_id_sku;
		this.error_id_seccion = error_id_seccion;
	}






	public @Nullable Integer getId_linea_detalle() {
		return id_linea_detalle;
	}
	public void setId_linea_detalle(@Nullable Integer id_linea_detalle) {
		this.id_linea_detalle = id_linea_detalle;
	}
	public @Nullable String getId_terminal_pos_boleta_cabeceras() {
		return id_terminal_pos_boleta_cabeceras;
	}
	public void setId_terminal_pos_boleta_cabeceras(@Nullable String id_terminal_pos_boleta_cabeceras) {
		this.id_terminal_pos_boleta_cabeceras = id_terminal_pos_boleta_cabeceras;
	}
	public @Nullable String getNumero_boleta_boleta_cabeceras() {
		return numero_boleta_boleta_cabeceras;
	}
	public void setNumero_boleta_boleta_cabeceras(@Nullable String numero_boleta_boleta_cabeceras) {
		this.numero_boleta_boleta_cabeceras = numero_boleta_boleta_cabeceras;
	}
	public @Nullable String getFecha_fin_transaccion_boleta_cabeceras() {
		return fecha_fin_transaccion_boleta_cabeceras;
	}
	public void setFecha_fin_transaccion_boleta_cabeceras(@Nullable String fecha_fin_transaccion_boleta_cabeceras) {
		this.fecha_fin_transaccion_boleta_cabeceras = fecha_fin_transaccion_boleta_cabeceras;
	}
	public @Nullable String getHora_fin_transaccion_boleta_cabeceras() {
		return hora_fin_transaccion_boleta_cabeceras;
	}
	public void setHora_fin_transaccion_boleta_cabeceras(@Nullable String hora_fin_transaccion_boleta_cabeceras) {
		this.hora_fin_transaccion_boleta_cabeceras = hora_fin_transaccion_boleta_cabeceras;
	}
	public @Nullable String getId_tienda_origen_boleta_cabeceras() {
		return id_tienda_origen_boleta_cabeceras;
	}
	public void setId_tienda_origen_boleta_cabeceras(@Nullable String id_tienda_origen_boleta_cabeceras) {
		this.id_tienda_origen_boleta_cabeceras = id_tienda_origen_boleta_cabeceras;
	}
	public @Nullable String getId_tienda_reconocimiento_boleta_cabeceras() {
		return id_tienda_reconocimiento_boleta_cabeceras;
	}
	public void setId_tienda_reconocimiento_boleta_cabeceras(@Nullable String id_tienda_reconocimiento_boleta_cabeceras) {
		this.id_tienda_reconocimiento_boleta_cabeceras = id_tienda_reconocimiento_boleta_cabeceras;
	}

	public @Nullable Integer getId_sku() {
		return id_sku;
	}
	public void setId_sku(@Nullable Integer id_sku) {
		this.id_sku = id_sku;
	}


	public @Nullable String getId_pep_r3() {
		return id_pep_r3;
	}
	public void setId_pep_r3(@Nullable String id_pep_r3) {
		this.id_pep_r3 = id_pep_r3;
	}
	public @Nullable String getTelefono_cliente_garantia_extendida() {
		return telefono_cliente_garantia_extendida;
	}
	public void setTelefono_cliente_garantia_extendida(@Nullable String telefono_cliente_garantia_extendida) {
		this.telefono_cliente_garantia_extendida = telefono_cliente_garantia_extendida;
	}



	public @Nullable String getNumero_orden_optica() {
		return numero_orden_optica;
	}
	public void setNumero_orden_optica(@Nullable String numero_orden_optica) {
		this.numero_orden_optica = numero_orden_optica;
	}
	public @Nullable String getOrden_de_reparacion() {
		return orden_de_reparacion;
	}
	public void setOrden_de_reparacion(@Nullable String orden_de_reparacion) {
		this.orden_de_reparacion = orden_de_reparacion;
	}
	public @Nullable String getNumero_telefono_o_folio() {
		return numero_telefono_o_folio;
	}
	public void setNumero_telefono_o_folio(@Nullable String numero_telefono_o_folio) {
		this.numero_telefono_o_folio = numero_telefono_o_folio;
	}

	public @Nullable String getNumero_comanda_restaurant() {
		return numero_comanda_restaurant;
	}
	public void setNumero_comanda_restaurant(@Nullable String numero_comanda_restaurant) {
		this.numero_comanda_restaurant = numero_comanda_restaurant;
	}
	public @Nullable String getNumero_poliza() {
		return numero_poliza;
	}
	public void setNumero_poliza(@Nullable String numero_poliza) {
		this.numero_poliza = numero_poliza;
	}


	public @Nullable BigDecimal getCantidad_articulos() {
		return cantidad_articulos;
	}


	public void setCantidad_articulos(@Nullable BigDecimal cantidad_articulos) {
		this.cantidad_articulos = cantidad_articulos;
	}


	public @Nullable BigDecimal getPrecio_subtotal_neto() {
		return precio_subtotal_neto;
	}

	public void setPrecio_subtotal_neto(@Nullable BigDecimal precio_subtotal_neto) {
		this.precio_subtotal_neto = precio_subtotal_neto;
	}



	public @Nullable Integer getId_motivo_devolucion() {
		return id_motivo_devolucion;
	}



	public void setId_motivo_devolucion(@Nullable Integer id_motivo_devolucion) {
		this.id_motivo_devolucion = id_motivo_devolucion;
	}



	public @Nullable Integer getId_seccion() {
		return id_seccion;
	}



	public void setId_seccion(@Nullable Integer id_seccion) {
		this.id_seccion = id_seccion;
	}



	public @Nullable BigDecimal getPrecio_unitario_del_sku() {
		return precio_unitario_del_sku;
	}



	public void setPrecio_unitario_del_sku(@Nullable BigDecimal precio_unitario_del_sku) {
		this.precio_unitario_del_sku = precio_unitario_del_sku;
	}



	public @Nullable String getFisico_o_virtual() {
		return fisico_o_virtual;
	}



	public void setFisico_o_virtual(@Nullable String fisico_o_virtual) {
		this.fisico_o_virtual = fisico_o_virtual;
	}



	public @Nullable String getEs_marca_liverpool() {
		return es_marca_liverpool;
	}



	public void setEs_marca_liverpool(@Nullable String es_marca_liverpool) {
		this.es_marca_liverpool = es_marca_liverpool;
	}





	public @Nullable String getIndicador_tipo_precio() {
		return indicador_tipo_precio;
	}

	public void setIndicador_tipo_precio(@Nullable String indicador_tipo_precio) {
		this.indicador_tipo_precio = indicador_tipo_precio;
	}


	public @Nullable String getDescripcion_descuento_sku() {
		return descripcion_descuento_sku;
	}

	public void setDescripcion_descuento_sku(@Nullable String descripcion_descuento_sku) {
		this.descripcion_descuento_sku = descripcion_descuento_sku;
	}


	public @Nullable String getFecha_promesa_de_entrega_inicial() {
		return fecha_promesa_de_entrega_inicial;
	}

	public void setFecha_promesa_de_entrega_inicial(@Nullable String fecha_promesa_de_entrega_inicial) {
		this.fecha_promesa_de_entrega_inicial = fecha_promesa_de_entrega_inicial;
	}


	public @Nullable String getFecha_promesa_de_entrega_fin() {
		return fecha_promesa_de_entrega_fin;
	}

	public void setFecha_promesa_de_entrega_fin(@Nullable String fecha_promesa_de_entrega_fin) {
		this.fecha_promesa_de_entrega_fin = fecha_promesa_de_entrega_fin;
	}


	public @Nullable String getSeller_marketplace() {
		return seller_marketplace;
	}

	public void setSeller_marketplace(@Nullable String seller_marketplace) {
		this.seller_marketplace = seller_marketplace;
	}

	public @Nullable List<BoletaDetallesSkuDescuentos> getBoleta_detalles_sku_descuentos() {
		return boleta_detalles_sku_descuentos;
	}






	public void setBoleta_detalles_sku_descuentos(@Nullable List<BoletaDetallesSkuDescuentos> boleta_detalles_sku_descuentos) {
		this.boleta_detalles_sku_descuentos = boleta_detalles_sku_descuentos;
	}

	public @Nullable String getError_id_sku() {
		return error_id_sku;
	}
	public void setError_id_sku(@Nullable String error_id_sku) {
		this.error_id_sku = error_id_sku;
	}


	public @Nullable String getError_id_seccion() {
		return error_id_seccion;
	}
	public void setError_id_seccion(@Nullable String error_id_seccion) {
		this.error_id_seccion = error_id_seccion;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BoletaDetallesSku that = (BoletaDetallesSku) o;
		return Objects.equals(id_linea_detalle, that.id_linea_detalle) &&
				Objects.equals(id_terminal_pos_boleta_cabeceras, that.id_terminal_pos_boleta_cabeceras) &&
				Objects.equals(numero_boleta_boleta_cabeceras, that.numero_boleta_boleta_cabeceras) &&
				Objects.equals(fecha_fin_transaccion_boleta_cabeceras, that.fecha_fin_transaccion_boleta_cabeceras) &&
				Objects.equals(hora_fin_transaccion_boleta_cabeceras, that.hora_fin_transaccion_boleta_cabeceras) &&
				Objects.equals(id_tienda_origen_boleta_cabeceras, that.id_tienda_origen_boleta_cabeceras) &&
				Objects.equals(id_tienda_reconocimiento_boleta_cabeceras, that.id_tienda_reconocimiento_boleta_cabeceras) &&
				Objects.equals(id_sku, that.id_sku) &&
				Objects.equals(id_pep_r3, that.id_pep_r3) &&
				Objects.equals(telefono_cliente_garantia_extendida, that.telefono_cliente_garantia_extendida) &&
				Objects.equals(numero_orden_optica, that.numero_orden_optica) &&
				Objects.equals(orden_de_reparacion, that.orden_de_reparacion) &&
				Objects.equals(numero_telefono_o_folio, that.numero_telefono_o_folio) &&
				Objects.equals(numero_comanda_restaurant, that.numero_comanda_restaurant) &&
				Objects.equals(numero_poliza, that.numero_poliza) &&
				Objects.equals(cantidad_articulos, that.cantidad_articulos) &&
				Objects.equals(precio_subtotal_neto, that.precio_subtotal_neto) &&
				Objects.equals(id_motivo_devolucion, that.id_motivo_devolucion) &&
				Objects.equals(id_seccion, that.id_seccion) &&
				Objects.equals(precio_unitario_del_sku, that.precio_unitario_del_sku) &&
				Objects.equals(fisico_o_virtual, that.fisico_o_virtual) &&
				Objects.equals(es_marca_liverpool, that.es_marca_liverpool) &&
				Objects.equals(boleta_detalles_sku_descuentos, that.boleta_detalles_sku_descuentos) &&
				Objects.equals(indicador_tipo_precio, that.indicador_tipo_precio) &&
				Objects.equals(descripcion_descuento_sku, that.descripcion_descuento_sku) &&
				Objects.equals(fecha_promesa_de_entrega_inicial, that.fecha_promesa_de_entrega_inicial) &&
				Objects.equals(fecha_promesa_de_entrega_fin, that.fecha_promesa_de_entrega_fin) &&
				Objects.equals(seller_marketplace, that.seller_marketplace) &&
				Objects.equals(error_id_sku, that.error_id_sku) &&
				Objects.equals(error_id_seccion, that.error_id_seccion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_linea_detalle, id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras, id_sku, id_pep_r3, telefono_cliente_garantia_extendida, numero_orden_optica, orden_de_reparacion, numero_telefono_o_folio, numero_comanda_restaurant, numero_poliza, cantidad_articulos, precio_subtotal_neto, id_motivo_devolucion, id_seccion, precio_unitario_del_sku, fisico_o_virtual, es_marca_liverpool, boleta_detalles_sku_descuentos, indicador_tipo_precio, descripcion_descuento_sku, fecha_promesa_de_entrega_inicial, fecha_promesa_de_entrega_fin,  seller_marketplace, error_id_sku, error_id_seccion);
	}

	@Override
	public String toString() {
		return "BoletaDetallesSku{" +
				"id_linea_detalle=" + id_linea_detalle +
				", id_terminal_pos_boleta_cabeceras='" + id_terminal_pos_boleta_cabeceras + '\'' +
				", numero_boleta_boleta_cabeceras='" + numero_boleta_boleta_cabeceras + '\'' +
				", fecha_fin_transaccion_boleta_cabeceras='" + fecha_fin_transaccion_boleta_cabeceras + '\'' +
				", hora_fin_transaccion_boleta_cabeceras='" + hora_fin_transaccion_boleta_cabeceras + '\'' +
				", id_tienda_origen_boleta_cabeceras='" + id_tienda_origen_boleta_cabeceras + '\'' +
				", id_tienda_reconocimiento_boleta_cabeceras='" + id_tienda_reconocimiento_boleta_cabeceras + '\'' +
				", id_sku=" + id_sku +
				", id_pep_r3='" + id_pep_r3 + '\'' +
				", telefono_cliente_garantia_extendida='" + telefono_cliente_garantia_extendida + '\'' +
				", numero_orden_optica='" + numero_orden_optica + '\'' +
				", orden_de_reparacion='" + orden_de_reparacion + '\'' +
				", numero_telefono_o_folio='" + numero_telefono_o_folio + '\'' +
				", numero_comanda_restaurant='" + numero_comanda_restaurant + '\'' +
				", numero_poliza='" + numero_poliza + '\'' +
				", cantidad_articulos=" + cantidad_articulos +
				", precio_subtotal_neto=" + precio_subtotal_neto +
				", id_motivo_devolucion=" + id_motivo_devolucion +
				", id_seccion=" + id_seccion +
				", precio_unitario_del_sku=" + precio_unitario_del_sku +
				", fisico_o_virtual='" + fisico_o_virtual + '\'' +
				", es_marca_liverpool='" + es_marca_liverpool + '\'' +
				", boleta_detalles_sku_descuentos=" + boleta_detalles_sku_descuentos +
				", indicador_tipo_precio='" + indicador_tipo_precio + '\'' +
				", descripcion_descuento_sku='" + descripcion_descuento_sku + '\'' +
				", fecha_promesa_de_entrega_inicial='" + fecha_promesa_de_entrega_inicial + '\'' +
				", fecha_promesa_de_entrega_fin='" + fecha_promesa_de_entrega_fin + '\'' +
				", seller_marketplace='" + seller_marketplace + '\'' +
				", error_id_sku='" + error_id_sku + '\'' +
				", error_id_seccion='" + error_id_seccion + '\'' +
				'}';
	}

	@Override
	public Object clone() {
		try {
	        return (BoletaDetallesSku)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaDetallesSku(
						        		this.id_linea_detalle,
						        		this.id_terminal_pos_boleta_cabeceras,
						        		this.numero_boleta_boleta_cabeceras,
						        		this.fecha_fin_transaccion_boleta_cabeceras,
						        		this.hora_fin_transaccion_boleta_cabeceras,
						        		this.id_tienda_origen_boleta_cabeceras,
						        		this.id_tienda_reconocimiento_boleta_cabeceras,
						        		this.id_sku,
						        		this.id_pep_r3,
						        		this.telefono_cliente_garantia_extendida,
						        		this.numero_orden_optica,
						        		this.orden_de_reparacion,
						        		this.numero_telefono_o_folio,
						        		this.numero_comanda_restaurant,
						        		this.numero_poliza,
						        		this.cantidad_articulos,
						        		this.precio_subtotal_neto,
						        		this.id_motivo_devolucion,
						        		this.id_seccion,
						        		this.precio_unitario_del_sku,
						        		this.fisico_o_virtual,
						        		this.es_marca_liverpool,
						        		this.boleta_detalles_sku_descuentos,
										this.indicador_tipo_precio,
										this.descripcion_descuento_sku,
										this.fecha_promesa_de_entrega_inicial,
										this.fecha_promesa_de_entrega_fin,
										this.seller_marketplace,
										this.error_id_sku,
										this.error_id_seccion
	        		);
	    }
	}	
}
