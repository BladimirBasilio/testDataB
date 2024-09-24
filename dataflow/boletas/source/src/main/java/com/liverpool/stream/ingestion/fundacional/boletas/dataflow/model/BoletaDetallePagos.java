package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;


import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetallePagos {

	@Nullable private Integer id_linea_detalle_pagos;
	@Nullable private String id_terminal_pos_boleta_cabeceras;
	@Nullable private String numero_boleta_boleta_cabeceras;
	@Nullable private String fecha_fin_transaccion_boleta_cabeceras;
	@Nullable private String hora_fin_transaccion_boleta_cabeceras;
	@Nullable private String id_tienda_origen_boleta_cabeceras;
	@Nullable private String id_tienda_reconocimiento_boleta_cabeceras;
	@Nullable private Integer id_medios_de_pago;
	@Nullable private BigDecimal monto_monedero;
	@Nullable private Integer id_cliente_tarjeta;
	@Nullable private String numero_autorizacion;
	@Nullable private String numero_orden_soms;
	@Nullable private BigDecimal total_pagado_otromedio_o_cambio_en_efectivo;
	@Nullable private String plan_de_credito;
	@Nullable private BigDecimal total_pagado_efectivo;
	@Nullable private String hash_tarjeta_externa;
	@Nullable private String numero_monedero;
	@Nullable private BigDecimal monto_redimido_monedero;
	@Nullable private BigDecimal saldo_anterior_monedero;
	@Nullable private BigDecimal monto_obtenido_monedero;
	@Nullable private BigDecimal saldo_actual_monedero;
	@Nullable private String numero_de_cupon;
	@Nullable private Integer id_modo_ingreso;

	@Nullable private Integer id_codigo_cancelacion;
	@Nullable private String banda_cheque;
	@Nullable private String folio_promass;
	@Nullable private String producto_bancario;
	@Nullable private String descripcion_formapago;
	@Nullable private String nombrecliente_tarjeta;
	@Nullable private BigDecimal monto_moneda_extranjera;
	@Nullable private String folio_brightstar;
	@Nullable private String telefono_codi;
	@Nullable private String mascara_tarjeta;

	public BoletaDetallePagos() {}


	public BoletaDetallePagos(@Nullable Integer id_linea_detalle_pagos, @Nullable String id_terminal_pos_boleta_cabeceras, @Nullable String numero_boleta_boleta_cabeceras, @Nullable String fecha_fin_transaccion_boleta_cabeceras, @Nullable String hora_fin_transaccion_boleta_cabeceras, @Nullable String id_tienda_origen_boleta_cabeceras, @Nullable String id_tienda_reconocimiento_boleta_cabeceras, @Nullable Integer id_medios_de_pago, @Nullable BigDecimal monto_monedero, @Nullable Integer id_cliente_tarjeta, @Nullable String numero_autorizacion, @Nullable String numero_orden_soms, @Nullable BigDecimal total_pagado_otromedio_o_cambio_en_efectivo, @Nullable String plan_de_credito, @Nullable BigDecimal total_pagado_efectivo, @Nullable String hash_tarjeta_externa, @Nullable String numero_monedero, @Nullable BigDecimal monto_redimido_monedero, @Nullable BigDecimal saldo_anterior_monedero, @Nullable BigDecimal monto_obtenido_monedero, @Nullable BigDecimal saldo_actual_monedero, @Nullable String numero_de_cupon, @Nullable Integer id_modo_ingreso, @Nullable Integer id_codigo_cancelacion, @Nullable String banda_cheque, @Nullable String folio_promass, @Nullable String producto_bancario, @Nullable String descripcion_formapago, @Nullable String nombrecliente_tarjeta, @Nullable BigDecimal monto_moneda_extranjera, @Nullable String folio_brightstar, @Nullable String telefono_codi, @Nullable String mascara_tarjeta) {
		super();
		this.id_linea_detalle_pagos = id_linea_detalle_pagos;
		this.id_terminal_pos_boleta_cabeceras = id_terminal_pos_boleta_cabeceras;
		this.numero_boleta_boleta_cabeceras = numero_boleta_boleta_cabeceras;
		this.fecha_fin_transaccion_boleta_cabeceras = fecha_fin_transaccion_boleta_cabeceras;
		this.hora_fin_transaccion_boleta_cabeceras = hora_fin_transaccion_boleta_cabeceras;
		this.id_tienda_origen_boleta_cabeceras = id_tienda_origen_boleta_cabeceras;
		this.id_tienda_reconocimiento_boleta_cabeceras = id_tienda_reconocimiento_boleta_cabeceras;
		this.id_medios_de_pago = id_medios_de_pago;
		this.monto_monedero = monto_monedero;
		this.id_cliente_tarjeta = id_cliente_tarjeta;
		this.numero_autorizacion = numero_autorizacion;
		this.numero_orden_soms = numero_orden_soms;
		this.total_pagado_otromedio_o_cambio_en_efectivo = total_pagado_otromedio_o_cambio_en_efectivo;
		this.plan_de_credito = plan_de_credito;
		this.total_pagado_efectivo = total_pagado_efectivo;
		this.hash_tarjeta_externa = hash_tarjeta_externa;
		this.numero_monedero = numero_monedero;
		this.monto_redimido_monedero = monto_redimido_monedero;
		this.saldo_anterior_monedero = saldo_anterior_monedero;
		this.monto_obtenido_monedero = monto_obtenido_monedero;
		this.saldo_actual_monedero = saldo_actual_monedero;
		this.numero_de_cupon = numero_de_cupon;
		this.id_modo_ingreso = id_modo_ingreso;
		this.id_codigo_cancelacion = id_codigo_cancelacion;
		this.banda_cheque = banda_cheque;
		this.folio_promass = folio_promass;
		this.producto_bancario = producto_bancario;
		this.descripcion_formapago = descripcion_formapago;
		this.nombrecliente_tarjeta = nombrecliente_tarjeta;
		this.monto_moneda_extranjera = monto_moneda_extranjera;
		this.folio_brightstar = folio_brightstar;
		this.telefono_codi = telefono_codi;
		this.mascara_tarjeta = mascara_tarjeta;
	}

	public @Nullable Integer getId_linea_detalle_pagos() {
		return id_linea_detalle_pagos;
	}
	public void setId_linea_detalle_pagos(@Nullable Integer id_linea_detalle_pagos) {
		this.id_linea_detalle_pagos = id_linea_detalle_pagos;
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
	public @Nullable Integer getId_medios_de_pago() {
		return id_medios_de_pago;
	}
	public void setId_medios_de_pago(@Nullable Integer id_medios_de_pago) {
		this.id_medios_de_pago = id_medios_de_pago;
	}

	public @Nullable BigDecimal getMonto_monedero() {
		return monto_monedero;
	}
	public void setMonto_monedero(@Nullable BigDecimal monto_monedero) {
		this.monto_monedero = monto_monedero;
	}
	public @Nullable Integer getId_cliente_tarjeta() {
		return id_cliente_tarjeta;
	}
	public void setId_cliente_tarjeta(@Nullable Integer id_cliente_tarjeta) {
		this.id_cliente_tarjeta = id_cliente_tarjeta;
	}
	public @Nullable String getNumero_autorizacion() {
		return numero_autorizacion;
	}
	public void setNumero_autorizacion(@Nullable String numero_autorizacion) {
		this.numero_autorizacion = numero_autorizacion;
	}


	public @Nullable String getNumero_orden_soms() {
		return numero_orden_soms;
	}
	public void setNumero_orden_soms(@Nullable String numero_orden_soms) {
		this.numero_orden_soms = numero_orden_soms;
	}

	public @Nullable BigDecimal getTotal_pagado_otromedio_o_cambio_en_efectivo() {
		return total_pagado_otromedio_o_cambio_en_efectivo;
	}
	public void setTotal_pagado_otromedio_o_cambio_en_efectivo(@Nullable BigDecimal total_pagado_otromedio_o_cambio_en_efectivo) {
		this.total_pagado_otromedio_o_cambio_en_efectivo = total_pagado_otromedio_o_cambio_en_efectivo;
	}

	public @Nullable String getPlan_de_credito() {
		return plan_de_credito;
	}
	public void setPlan_de_credito(@Nullable String plan_de_credito) {
		this.plan_de_credito = plan_de_credito;
	}

	public @Nullable BigDecimal getTotal_pagado_efectivo() {
		return total_pagado_efectivo;
	}
	public void setTotal_pagado_efectivo(@Nullable BigDecimal total_pagado_efectivo) {
		this.total_pagado_efectivo = total_pagado_efectivo;
	}


	public @Nullable String getHash_tarjeta_externa() {
		return hash_tarjeta_externa;
	}







	public void setHash_tarjeta_externa(@Nullable String hash_tarjeta_externa) {
		this.hash_tarjeta_externa = hash_tarjeta_externa;
	}







	public @Nullable String getNumero_monedero() {
		return numero_monedero;
	}







	public void setNumero_monedero(@Nullable String numero_monedero) {
		this.numero_monedero = numero_monedero;
	}







	public @Nullable BigDecimal getMonto_redimido_monedero() {
		return monto_redimido_monedero;
	}







	public void setMonto_redimido_monedero(@Nullable BigDecimal monto_redimido_monedero) {
		this.monto_redimido_monedero = monto_redimido_monedero;
	}







	public @Nullable BigDecimal getSaldo_anterior_monedero() {
		return saldo_anterior_monedero;
	}







	public void setSaldo_anterior_monedero(@Nullable BigDecimal saldo_anterior_monedero) {
		this.saldo_anterior_monedero = saldo_anterior_monedero;
	}







	public @Nullable BigDecimal getMonto_obtenido_monedero() {
		return monto_obtenido_monedero;
	}







	public void setMonto_obtenido_monedero(@Nullable BigDecimal monto_obtenido_monedero) {
		this.monto_obtenido_monedero = monto_obtenido_monedero;
	}







	public @Nullable BigDecimal getSaldo_actual_monedero() {
		return saldo_actual_monedero;
	}







	public void setSaldo_actual_monedero(@Nullable BigDecimal saldo_actual_monedero) {
		this.saldo_actual_monedero = saldo_actual_monedero;
	}







	public @Nullable String getNumero_de_cupon() {
		return numero_de_cupon;
	}







	public void setNumero_de_cupon(@Nullable String numero_de_cupon) {
		this.numero_de_cupon = numero_de_cupon;
	}







	public @Nullable Integer getId_modo_ingreso() {
		return id_modo_ingreso;
	}







	public void setId_modo_ingreso(@Nullable Integer id_modo_ingreso) {
		this.id_modo_ingreso = id_modo_ingreso;
	}



	public 	@Nullable Integer getId_codigo_cancelacion() {
		return id_codigo_cancelacion;
	}

	public void setId_codigo_cancelacion(@Nullable Integer id_codigo_cancelacion) {
		this.id_codigo_cancelacion = id_codigo_cancelacion;
	}


	public @Nullable String getBanda_cheque() {
		return banda_cheque;
	}

	public void setBanda_cheque(@Nullable String banda_cheque) {
		this.banda_cheque = banda_cheque;
	}


	public @Nullable String getFolio_promass() {
		return folio_promass;
	}

	public void setFolio_promass(@Nullable String folio_promass) {
		this.folio_promass = folio_promass;
	}


	public 	@Nullable String getProducto_bancario() {
		return producto_bancario;
	}

	public void setProducto_bancario(@Nullable String producto_bancario) {
		this.producto_bancario = producto_bancario;
	}


	public @Nullable String getDescripcion_formapago() {
		return descripcion_formapago;
	}

	public void setDescripcion_formapago(@Nullable String descripcion_formapago) {
		this.descripcion_formapago = descripcion_formapago;
	}


	public @Nullable String getNombrecliente_tarjeta() {
		return nombrecliente_tarjeta;
	}

	public void setNombrecliente_tarjeta(@Nullable String nombrecliente_tarjeta) {
		this.nombrecliente_tarjeta = nombrecliente_tarjeta;
	}


	public @Nullable BigDecimal getMonto_moneda_extranjera() {
		return monto_moneda_extranjera;
	}

	public void setMonto_moneda_extranjera(@Nullable BigDecimal monto_moneda_extranjera) {
		this.monto_moneda_extranjera = monto_moneda_extranjera;
	}


	public @Nullable String getFolio_brightstar() {
		return folio_brightstar;
	}

	public void setFolio_brightstar(@Nullable String folio_brightstar) {
		this.folio_brightstar = folio_brightstar;
	}


	public @Nullable String getTelefono_codi() {
		return telefono_codi;
	}

	public void setTelefono_codi(@Nullable String telefono_codi) {
		this.telefono_codi = telefono_codi;
	}

	public @Nullable String getMascara_tarjeta() {
		return mascara_tarjeta;
	}

	public void setMascara_tarjeta(@Nullable String mascara_tarjeta) {
		this.mascara_tarjeta = mascara_tarjeta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BoletaDetallePagos that = (BoletaDetallePagos) o;
		return Objects.equals(id_linea_detalle_pagos, that.id_linea_detalle_pagos) &&
				Objects.equals(id_terminal_pos_boleta_cabeceras, that.id_terminal_pos_boleta_cabeceras) &&
				Objects.equals(numero_boleta_boleta_cabeceras, that.numero_boleta_boleta_cabeceras) &&
				Objects.equals(fecha_fin_transaccion_boleta_cabeceras, that.fecha_fin_transaccion_boleta_cabeceras) &&
				Objects.equals(hora_fin_transaccion_boleta_cabeceras, that.hora_fin_transaccion_boleta_cabeceras) &&
				Objects.equals(id_tienda_origen_boleta_cabeceras, that.id_tienda_origen_boleta_cabeceras) &&
				Objects.equals(id_tienda_reconocimiento_boleta_cabeceras, that.id_tienda_reconocimiento_boleta_cabeceras) &&
				Objects.equals(id_medios_de_pago, that.id_medios_de_pago) &&
				Objects.equals(monto_monedero, that.monto_monedero) &&
				Objects.equals(id_cliente_tarjeta, that.id_cliente_tarjeta) &&
				Objects.equals(numero_autorizacion, that.numero_autorizacion) &&
				Objects.equals(numero_orden_soms, that.numero_orden_soms) &&
				Objects.equals(total_pagado_otromedio_o_cambio_en_efectivo, that.total_pagado_otromedio_o_cambio_en_efectivo) &&
				Objects.equals(plan_de_credito, that.plan_de_credito) &&
				Objects.equals(total_pagado_efectivo, that.total_pagado_efectivo) &&
				Objects.equals(hash_tarjeta_externa, that.hash_tarjeta_externa) &&
				Objects.equals(numero_monedero, that.numero_monedero) &&
				Objects.equals(monto_redimido_monedero, that.monto_redimido_monedero) &&
				Objects.equals(saldo_anterior_monedero, that.saldo_anterior_monedero) &&
				Objects.equals(monto_obtenido_monedero, that.monto_obtenido_monedero) &&
				Objects.equals(saldo_actual_monedero, that.saldo_actual_monedero) &&
				Objects.equals(numero_de_cupon, that.numero_de_cupon) &&
				Objects.equals(id_modo_ingreso, that.id_modo_ingreso) &&
				Objects.equals(id_codigo_cancelacion, that.id_codigo_cancelacion) &&
				Objects.equals(banda_cheque, that.banda_cheque) &&
				Objects.equals(folio_promass, that.folio_promass) &&
				Objects.equals(producto_bancario, that.producto_bancario) &&
				Objects.equals(descripcion_formapago, that.descripcion_formapago) &&
				Objects.equals(nombrecliente_tarjeta, that.nombrecliente_tarjeta) &&
				Objects.equals(monto_moneda_extranjera, that.monto_moneda_extranjera) &&
				Objects.equals(folio_brightstar, that.folio_brightstar) &&
				Objects.equals(telefono_codi, that.telefono_codi) &&
				Objects.equals(mascara_tarjeta, that.mascara_tarjeta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_linea_detalle_pagos, id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras, id_medios_de_pago, monto_monedero, id_cliente_tarjeta, numero_autorizacion, numero_orden_soms, total_pagado_otromedio_o_cambio_en_efectivo, plan_de_credito, total_pagado_efectivo, hash_tarjeta_externa, numero_monedero, monto_redimido_monedero, saldo_anterior_monedero, monto_obtenido_monedero, saldo_actual_monedero, numero_de_cupon, id_modo_ingreso, id_codigo_cancelacion, banda_cheque, folio_promass, producto_bancario, descripcion_formapago, nombrecliente_tarjeta, monto_moneda_extranjera, folio_brightstar, telefono_codi, mascara_tarjeta);
	}


	@Override
	public String toString() {
		return "BoletaDetallePagos{" +
				"id_linea_detalle_pagos=" + id_linea_detalle_pagos +
				", id_terminal_pos_boleta_cabeceras='" + id_terminal_pos_boleta_cabeceras + '\'' +
				", numero_boleta_boleta_cabeceras='" + numero_boleta_boleta_cabeceras + '\'' +
				", fecha_fin_transaccion_boleta_cabeceras='" + fecha_fin_transaccion_boleta_cabeceras + '\'' +
				", hora_fin_transaccion_boleta_cabeceras='" + hora_fin_transaccion_boleta_cabeceras + '\'' +
				", id_tienda_origen_boleta_cabeceras='" + id_tienda_origen_boleta_cabeceras + '\'' +
				", id_tienda_reconocimiento_boleta_cabeceras='" + id_tienda_reconocimiento_boleta_cabeceras + '\'' +
				", id_medios_de_pago=" + id_medios_de_pago +
				", monto_monedero=" + monto_monedero +
				", id_cliente_tarjeta=" + id_cliente_tarjeta +
				", numero_autorizacion='" + numero_autorizacion + '\'' +
				", numero_orden_soms='" + numero_orden_soms + '\'' +
				", total_pagado_otromedio_o_cambio_en_efectivo=" + total_pagado_otromedio_o_cambio_en_efectivo +
				", plan_de_credito='" + plan_de_credito + '\'' +
				", total_pagado_efectivo=" + total_pagado_efectivo +
				", hash_tarjeta_externa='" + hash_tarjeta_externa + '\'' +
				", numero_monedero='" + numero_monedero + '\'' +
				", monto_redimido_monedero=" + monto_redimido_monedero +
				", saldo_anterior_monedero=" + saldo_anterior_monedero +
				", monto_obtenido_monedero=" + monto_obtenido_monedero +
				", saldo_actual_monedero=" + saldo_actual_monedero +
				", numero_de_cupon='" + numero_de_cupon + '\'' +
				", id_modo_ingreso=" + id_modo_ingreso +
				", id_codigo_cancelacion=" + id_codigo_cancelacion +
				", banda_cheque='" + banda_cheque + '\'' +
				", folio_promass='" + folio_promass + '\'' +
				", producto_bancario='" + producto_bancario + '\'' +
				", descripcion_formapago='" + descripcion_formapago + '\'' +
				", nombrecliente_tarjeta='" + nombrecliente_tarjeta + '\'' +
				", monto_moneda_extranjera=" + monto_moneda_extranjera +
				", folio_brightstar='" + folio_brightstar + '\'' +
				", telefono_codi='" + telefono_codi + '\'' +
				", mascara_tarjeta='" + mascara_tarjeta + '\'' +
				'}';
	}

	@Override
	public Object clone() {
		try {
	        return (BoletaDetallePagos)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaDetallePagos(
	        		this.id_linea_detalle_pagos,
	        		this.id_terminal_pos_boleta_cabeceras,
	        		this.numero_boleta_boleta_cabeceras,
	        		this.fecha_fin_transaccion_boleta_cabeceras,
	        		this.hora_fin_transaccion_boleta_cabeceras,
	        		this.id_tienda_origen_boleta_cabeceras,
	        		this.id_tienda_reconocimiento_boleta_cabeceras,
	        		this.id_medios_de_pago,
	        		this.monto_monedero,
	        		this.id_cliente_tarjeta,
	        		this.numero_autorizacion,
	        		this.numero_orden_soms,
	        		this.total_pagado_otromedio_o_cambio_en_efectivo,
	        		this.plan_de_credito,
	        		this.total_pagado_efectivo,
	        		this.hash_tarjeta_externa,
	        		this.numero_monedero,
	        		this.monto_redimido_monedero,
	        		this.saldo_anterior_monedero,
	        		this.monto_obtenido_monedero,
	        		this.saldo_actual_monedero,
	        		this.numero_de_cupon,
	        		this.id_modo_ingreso,
					this.id_codigo_cancelacion,
					this.banda_cheque,
					this.folio_promass,
					this.producto_bancario,
					this.descripcion_formapago,
					this.nombrecliente_tarjeta,
					this.monto_moneda_extranjera,
					this.folio_brightstar,
					this.telefono_codi,
					this.mascara_tarjeta
	        		);
	    }
	}	
}
