package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;


import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Objects;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetalleAbonosTarjeta {

	@Nullable private Integer id_linea_detalle;
	@Nullable private String id_terminal_pos_boleta_cabeceras;
	@Nullable private String numero_boleta_boleta_cabeceras;
	@Nullable private String fecha_fin_transaccion_boleta_cabeceras;
	@Nullable private String hora_fin_transaccion_boleta_cabeceras;
	@Nullable private String id_tienda_origen_boleta_cabeceras;
	@Nullable private String id_tienda_reconocimiento_boleta_cabeceras;
	@Nullable private String numero_tarjeta;
	@Nullable private BigDecimal total_pagado;
	@Nullable private Integer id_cliente_tarjeta;
	@Nullable private String autorizacion_de_abono;
	@Nullable private String cuenta_de_abono;
	public BoletaDetalleAbonosTarjeta() {}

	public BoletaDetalleAbonosTarjeta(@Nullable Integer id_linea_detalle, @Nullable String id_terminal_pos_boleta_cabeceras, @Nullable String numero_boleta_boleta_cabeceras, @Nullable String fecha_fin_transaccion_boleta_cabeceras, @Nullable String hora_fin_transaccion_boleta_cabeceras, @Nullable String id_tienda_origen_boleta_cabeceras, @Nullable String id_tienda_reconocimiento_boleta_cabeceras, @Nullable String numero_tarjeta, @Nullable BigDecimal total_pagado, @Nullable Integer id_cliente_tarjeta, @Nullable String autorizacion_de_abono, @Nullable String cuenta_de_abono) {
		this.id_linea_detalle = id_linea_detalle;
		this.id_terminal_pos_boleta_cabeceras = id_terminal_pos_boleta_cabeceras;
		this.numero_boleta_boleta_cabeceras = numero_boleta_boleta_cabeceras;
		this.fecha_fin_transaccion_boleta_cabeceras = fecha_fin_transaccion_boleta_cabeceras;
		this.hora_fin_transaccion_boleta_cabeceras = hora_fin_transaccion_boleta_cabeceras;
		this.id_tienda_origen_boleta_cabeceras = id_tienda_origen_boleta_cabeceras;
		this.id_tienda_reconocimiento_boleta_cabeceras = id_tienda_reconocimiento_boleta_cabeceras;
		this.numero_tarjeta = numero_tarjeta;
		this.total_pagado = total_pagado;
		this.id_cliente_tarjeta = id_cliente_tarjeta;
		this.autorizacion_de_abono = autorizacion_de_abono;
		this.cuenta_de_abono = cuenta_de_abono;
	}


	@Nullable
	public Integer getId_linea_detalle() {
		return id_linea_detalle;
	}

	public void setId_linea_detalle(@Nullable Integer id_linea_detalle) {
		this.id_linea_detalle = id_linea_detalle;
	}

	@Nullable
	public String getId_terminal_pos_boleta_cabeceras() {
		return id_terminal_pos_boleta_cabeceras;
	}

	public void setId_terminal_pos_boleta_cabeceras(@Nullable String id_terminal_pos_boleta_cabeceras) {
		this.id_terminal_pos_boleta_cabeceras = id_terminal_pos_boleta_cabeceras;
	}

	@Nullable
	public String getNumero_boleta_boleta_cabeceras() {
		return numero_boleta_boleta_cabeceras;
	}

	public void setNumero_boleta_boleta_cabeceras(@Nullable String numero_boleta_boleta_cabeceras) {
		this.numero_boleta_boleta_cabeceras = numero_boleta_boleta_cabeceras;
	}

	@Nullable
	public String getFecha_fin_transaccion_boleta_cabeceras() {
		return fecha_fin_transaccion_boleta_cabeceras;
	}

	public void setFecha_fin_transaccion_boleta_cabeceras(@Nullable String fecha_fin_transaccion_boleta_cabeceras) {
		this.fecha_fin_transaccion_boleta_cabeceras = fecha_fin_transaccion_boleta_cabeceras;
	}

	@Nullable
	public String getHora_fin_transaccion_boleta_cabeceras() {
		return hora_fin_transaccion_boleta_cabeceras;
	}

	public void setHora_fin_transaccion_boleta_cabeceras(@Nullable String hora_fin_transaccion_boleta_cabeceras) {
		this.hora_fin_transaccion_boleta_cabeceras = hora_fin_transaccion_boleta_cabeceras;
	}

	@Nullable
	public String getId_tienda_origen_boleta_cabeceras() {
		return id_tienda_origen_boleta_cabeceras;
	}

	public void setId_tienda_origen_boleta_cabeceras(@Nullable String id_tienda_origen_boleta_cabeceras) {
		this.id_tienda_origen_boleta_cabeceras = id_tienda_origen_boleta_cabeceras;
	}

	@Nullable
	public String getId_tienda_reconocimiento_boleta_cabeceras() {
		return id_tienda_reconocimiento_boleta_cabeceras;
	}

	public void setId_tienda_reconocimiento_boleta_cabeceras(@Nullable String id_tienda_reconocimiento_boleta_cabeceras) {
		this.id_tienda_reconocimiento_boleta_cabeceras = id_tienda_reconocimiento_boleta_cabeceras;
	}

	@Nullable
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}

	public void setNumero_tarjeta(@Nullable String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}

	@Nullable
	public BigDecimal getTotal_pagado() {
		return total_pagado;
	}

	public void setTotal_pagado(@Nullable BigDecimal total_pagado) {
		this.total_pagado = total_pagado;
	}

	@Nullable
	public Integer getId_cliente_tarjeta() {
		return id_cliente_tarjeta;
	}

	public void setId_cliente_tarjeta(@Nullable Integer id_cliente_tarjeta) {
		this.id_cliente_tarjeta = id_cliente_tarjeta;
	}

	@Nullable
	public String getAutorizacion_de_abono() {
		return autorizacion_de_abono;
	}

	public void setAutorizacion_de_abono(@Nullable String autorizacion_de_abono) {
		this.autorizacion_de_abono = autorizacion_de_abono;
	}

	@Nullable
	public String getCuenta_de_abono() {
		return cuenta_de_abono;
	}

	public void setCuenta_de_abono(@Nullable String cuenta_de_abono) {
		this.cuenta_de_abono = cuenta_de_abono;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BoletaDetalleAbonosTarjeta that = (BoletaDetalleAbonosTarjeta) o;
		return Objects.equals(id_linea_detalle, that.id_linea_detalle) &&
				Objects.equals(id_terminal_pos_boleta_cabeceras, that.id_terminal_pos_boleta_cabeceras) &&
				Objects.equals(numero_boleta_boleta_cabeceras, that.numero_boleta_boleta_cabeceras) &&
				Objects.equals(fecha_fin_transaccion_boleta_cabeceras, that.fecha_fin_transaccion_boleta_cabeceras) &&
				Objects.equals(hora_fin_transaccion_boleta_cabeceras, that.hora_fin_transaccion_boleta_cabeceras) &&
				Objects.equals(id_tienda_origen_boleta_cabeceras, that.id_tienda_origen_boleta_cabeceras) &&
				Objects.equals(id_tienda_reconocimiento_boleta_cabeceras, that.id_tienda_reconocimiento_boleta_cabeceras) &&
				Objects.equals(numero_tarjeta, that.numero_tarjeta) &&
				Objects.equals(total_pagado, that.total_pagado) &&
				Objects.equals(id_cliente_tarjeta, that.id_cliente_tarjeta) &&
				Objects.equals(autorizacion_de_abono, that.autorizacion_de_abono) &&
				Objects.equals(cuenta_de_abono, that.cuenta_de_abono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_linea_detalle, id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras, numero_tarjeta, total_pagado, id_cliente_tarjeta, autorizacion_de_abono, cuenta_de_abono);
	}

	@Override
	public String toString() {
		return "BoletaDetalleAbonosTarjeta{" +
				"id_linea_detalle=" + id_linea_detalle +
				", id_terminal_pos_boleta_cabeceras=" + id_terminal_pos_boleta_cabeceras +
				", numero_boleta_boleta_cabeceras='" + numero_boleta_boleta_cabeceras + '\'' +
				", fecha_fin_transaccion_boleta_cabeceras='" + fecha_fin_transaccion_boleta_cabeceras + '\'' +
				", hora_fin_transaccion_boleta_cabeceras='" + hora_fin_transaccion_boleta_cabeceras + '\'' +
				", id_tienda_origen_boleta_cabeceras='" + id_tienda_origen_boleta_cabeceras + '\'' +
				", id_tienda_reconocimiento_boleta_cabeceras='" + id_tienda_reconocimiento_boleta_cabeceras + '\'' +
				", numero_tarjeta='" + numero_tarjeta + '\'' +
				", total_pagado=" + total_pagado +
				", id_cliente_tarjeta=" + id_cliente_tarjeta +
				", autorizacion_de_abono='" + autorizacion_de_abono + '\'' +
				", cuenta_de_abono='" + cuenta_de_abono + '\'' +
				'}';
	}
}
