package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;


import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetallesSkuDescuentos {

	
	@Nullable private Integer id_linea_detalle_boleta_detalles_sku;
	@Nullable private String id_terminal_pos_boleta_detalles_sku;
	@Nullable private String numero_boleta_boleta_detalles_sku;
	@Nullable private String fecha_fin_transaccion_boleta_detalles_sku;
	@Nullable private String hora_fin_transaccion_boleta_detalles_sku;
	@Nullable private String id_tienda_origen_boleta_detalles_sku;
	@Nullable private String id_tienda_reconocimiento_boleta_detalles_sku;
	@Nullable private Integer id_descuento;
	@Nullable private BigDecimal total_descuento_sku;
	@Nullable private BigDecimal porcentaje_descuento_sku; // no usado


	@Nullable private BigDecimal descuento_sku_promocion;
	@Nullable private String descripcion_descuento;
	@Nullable private Integer id_linea_detalle;
	
	
	
	public BoletaDetallesSkuDescuentos() {}
	


	public BoletaDetallesSkuDescuentos(Integer id_linea_detalle_boleta_detalles_sku,
			String id_terminal_pos_boleta_detalles_sku, String numero_boleta_boleta_detalles_sku,
			String fecha_fin_transaccion_boleta_detalles_sku, String hora_fin_transaccion_boleta_detalles_sku,
			String id_tienda_origen_boleta_detalles_sku, String id_tienda_reconocimiento_boleta_detalles_sku,
			Integer id_descuento, BigDecimal total_descuento_sku, BigDecimal porcentaje_descuento_sku,
			BigDecimal descuento_sku_promocion, 	String descripcion_descuento, Integer id_linea_detalle
	) {
		super();
		this.id_linea_detalle_boleta_detalles_sku = id_linea_detalle_boleta_detalles_sku;
		this.id_terminal_pos_boleta_detalles_sku = id_terminal_pos_boleta_detalles_sku;
		this.numero_boleta_boleta_detalles_sku = numero_boleta_boleta_detalles_sku;
		this.fecha_fin_transaccion_boleta_detalles_sku = fecha_fin_transaccion_boleta_detalles_sku;
		this.hora_fin_transaccion_boleta_detalles_sku = hora_fin_transaccion_boleta_detalles_sku;
		this.id_tienda_origen_boleta_detalles_sku = id_tienda_origen_boleta_detalles_sku;
		this.id_tienda_reconocimiento_boleta_detalles_sku = id_tienda_reconocimiento_boleta_detalles_sku;
		this.id_descuento = id_descuento;
		this.total_descuento_sku = total_descuento_sku;
		this.porcentaje_descuento_sku = porcentaje_descuento_sku;
		this.descuento_sku_promocion = descuento_sku_promocion;
		this.descripcion_descuento = descripcion_descuento;
		this.id_linea_detalle = id_linea_detalle;
	}




	public @Nullable Integer getId_linea_detalle_boleta_detalles_sku() {
		return id_linea_detalle_boleta_detalles_sku;
	}
	public void setId_linea_detalle_boleta_detalles_sku(@Nullable Integer id_linea_detalle_boleta_detalles_sku) {
		this.id_linea_detalle_boleta_detalles_sku = id_linea_detalle_boleta_detalles_sku;
	}
	public @Nullable String getId_terminal_pos_boleta_detalles_sku() {
		return id_terminal_pos_boleta_detalles_sku;
	}
	public void setId_terminal_pos_boleta_detalles_sku(@Nullable String id_terminal_pos_boleta_detalles_sku) {
		this.id_terminal_pos_boleta_detalles_sku = id_terminal_pos_boleta_detalles_sku;
	}
	public @Nullable String getNumero_boleta_boleta_detalles_sku() {
		return numero_boleta_boleta_detalles_sku;
	}
	public void setNumero_boleta_boleta_detalles_sku(@Nullable String numero_boleta_boleta_detalles_sku) {
		this.numero_boleta_boleta_detalles_sku = numero_boleta_boleta_detalles_sku;
	}
	public @Nullable String getFecha_fin_transaccion_boleta_detalles_sku() {
		return fecha_fin_transaccion_boleta_detalles_sku;
	}
	public void setFecha_fin_transaccion_boleta_detalles_sku(@Nullable String fecha_fin_transaccion_boleta_detalles_sku) {
		this.fecha_fin_transaccion_boleta_detalles_sku = fecha_fin_transaccion_boleta_detalles_sku;
	}
	public @Nullable String getHora_fin_transaccion_boleta_detalles_sku() {
		return hora_fin_transaccion_boleta_detalles_sku;
	}
	public void setHora_fin_transaccion_boleta_detalles_sku(@Nullable String hora_fin_transaccion_boleta_detalles_sku) {
		this.hora_fin_transaccion_boleta_detalles_sku = hora_fin_transaccion_boleta_detalles_sku;
	}
	public @Nullable String getId_tienda_origen_boleta_detalles_sku() {
		return id_tienda_origen_boleta_detalles_sku;
	}
	public void setId_tienda_origen_boleta_detalles_sku(@Nullable String id_tienda_origen_boleta_detalles_sku) {
		this.id_tienda_origen_boleta_detalles_sku = id_tienda_origen_boleta_detalles_sku;
	}
	public @Nullable String getId_tienda_reconocimiento_boleta_detalles_sku() {
		return id_tienda_reconocimiento_boleta_detalles_sku;
	}
	public void setId_tienda_reconocimiento_boleta_detalles_sku(@Nullable String id_tienda_reconocimiento_boleta_detalles_sku) {
		this.id_tienda_reconocimiento_boleta_detalles_sku = id_tienda_reconocimiento_boleta_detalles_sku;
	}

	public @Nullable Integer getId_descuento() {
		return id_descuento;
	}



	public void setId_descuento(@Nullable Integer id_descuento) {
		this.id_descuento = id_descuento;
	}



	public @Nullable BigDecimal getTotal_descuento_sku() {
		return total_descuento_sku;
	}



	public void setTotal_descuento_sku(@Nullable BigDecimal total_descuento_sku) {
		this.total_descuento_sku = total_descuento_sku;
	}



	public @Nullable BigDecimal getPorcentaje_descuento_sku() {
		return porcentaje_descuento_sku;
	}



	public void setPorcentaje_descuento_sku(@Nullable BigDecimal porcentaje_descuento_sku) {
		this.porcentaje_descuento_sku = porcentaje_descuento_sku;
	}



	public 	@Nullable BigDecimal getDescuento_sku_promocion() {
		return descuento_sku_promocion;
	}

	public void setDescuento_sku_promocion(@Nullable BigDecimal descuento_sku_promocion) {
		this.descuento_sku_promocion = descuento_sku_promocion;
	}


	public @Nullable String getDescripcion_descuento() {
		return descripcion_descuento;
	}

	public void setDescripcion_descuento(@Nullable String descripcion_descuento) {
		this.descripcion_descuento = descripcion_descuento;
	}


	public @Nullable Integer getId_linea_detalle() {
		return id_linea_detalle;
	}

	public void setId_linea_detalle(@Nullable Integer id_linea_detalle) {
		this.id_linea_detalle = id_linea_detalle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BoletaDetallesSkuDescuentos that = (BoletaDetallesSkuDescuentos) o;
		return Objects.equals(id_linea_detalle_boleta_detalles_sku, that.id_linea_detalle_boleta_detalles_sku) &&
				Objects.equals(id_terminal_pos_boleta_detalles_sku, that.id_terminal_pos_boleta_detalles_sku) &&
				Objects.equals(numero_boleta_boleta_detalles_sku, that.numero_boleta_boleta_detalles_sku) &&
				Objects.equals(fecha_fin_transaccion_boleta_detalles_sku, that.fecha_fin_transaccion_boleta_detalles_sku) &&
				Objects.equals(hora_fin_transaccion_boleta_detalles_sku, that.hora_fin_transaccion_boleta_detalles_sku) &&
				Objects.equals(id_tienda_origen_boleta_detalles_sku, that.id_tienda_origen_boleta_detalles_sku) &&
				Objects.equals(id_tienda_reconocimiento_boleta_detalles_sku, that.id_tienda_reconocimiento_boleta_detalles_sku) &&
				Objects.equals(id_descuento, that.id_descuento) &&
				Objects.equals(total_descuento_sku, that.total_descuento_sku) &&
				Objects.equals(porcentaje_descuento_sku, that.porcentaje_descuento_sku) &&
				Objects.equals(descuento_sku_promocion, that.descuento_sku_promocion) &&
				Objects.equals(descripcion_descuento, that.descripcion_descuento) &&
				Objects.equals(id_linea_detalle, that.id_linea_detalle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_linea_detalle_boleta_detalles_sku, id_terminal_pos_boleta_detalles_sku, numero_boleta_boleta_detalles_sku, fecha_fin_transaccion_boleta_detalles_sku, hora_fin_transaccion_boleta_detalles_sku, id_tienda_origen_boleta_detalles_sku, id_tienda_reconocimiento_boleta_detalles_sku, id_descuento, total_descuento_sku, porcentaje_descuento_sku, descuento_sku_promocion, descripcion_descuento, id_linea_detalle);
	}

	@Override
	public String toString() {
		return "BoletaDetallesSkuDescuentos{" +
				"id_linea_detalle_boleta_detalles_sku=" + id_linea_detalle_boleta_detalles_sku +
				", id_terminal_pos_boleta_detalles_sku='" + id_terminal_pos_boleta_detalles_sku + '\'' +
				", numero_boleta_boleta_detalles_sku='" + numero_boleta_boleta_detalles_sku + '\'' +
				", fecha_fin_transaccion_boleta_detalles_sku='" + fecha_fin_transaccion_boleta_detalles_sku + '\'' +
				", hora_fin_transaccion_boleta_detalles_sku='" + hora_fin_transaccion_boleta_detalles_sku + '\'' +
				", id_tienda_origen_boleta_detalles_sku='" + id_tienda_origen_boleta_detalles_sku + '\'' +
				", id_tienda_reconocimiento_boleta_detalles_sku='" + id_tienda_reconocimiento_boleta_detalles_sku + '\'' +
				", id_descuento=" + id_descuento +
				", total_descuento_sku=" + total_descuento_sku +
				", porcentaje_descuento_sku=" + porcentaje_descuento_sku +
				", descuento_sku_promocion=" + descuento_sku_promocion +
				", descripcion_descuento='" + descripcion_descuento + '\'' +
				", id_linea_detalle=" + id_linea_detalle +
				'}';
	}

	@Override
	public Object clone() {
		try {
	        return (BoletaDetallesSkuDescuentos)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaDetallesSkuDescuentos(
								        		this.id_linea_detalle_boleta_detalles_sku,
								        		this.id_terminal_pos_boleta_detalles_sku,
								        		this.numero_boleta_boleta_detalles_sku,
								        		this.fecha_fin_transaccion_boleta_detalles_sku,
								        		this.hora_fin_transaccion_boleta_detalles_sku,
								        		this.id_tienda_origen_boleta_detalles_sku,
								        		this.id_tienda_reconocimiento_boleta_detalles_sku,
								        		this.id_descuento,
								        		this.total_descuento_sku,
								        		this.porcentaje_descuento_sku,
												this.descuento_sku_promocion,
												this.descripcion_descuento,
												this.id_linea_detalle
	        		);
	    }
	}	
}
