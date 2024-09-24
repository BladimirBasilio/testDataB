package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;


import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetallesMonedero {

	

	@Nullable private String id_terminal_pos;
	@Nullable private String numero_boleta;
	@Nullable private String fecha_fin_transaccion;
	@Nullable private String hora_fin_transaccion;
	@Nullable private String id_tienda_origen;
	@Nullable private String id_tienda_reconocimiento;
	@Nullable private String numero_monedero;
	@Nullable private BigDecimal saldo_anterior_monedero;
	@Nullable private BigDecimal monto_monedero;
	@Nullable private BigDecimal monto_obtenido_monedero;
	@Nullable private BigDecimal saldo_actual_monedero;

	@Nullable private BigDecimal monto_utilizado;
	
	public BoletaDetallesMonedero() {}
	


	public BoletaDetallesMonedero(String id_terminal_pos, String numero_boleta, String fecha_fin_transaccion,
			String hora_fin_transaccion, String id_tienda_origen, String id_tienda_reconocimiento,
			String numero_monedero, BigDecimal saldo_anterior_monedero, BigDecimal monto_monedero,
			BigDecimal monto_obtenido_monedero, BigDecimal saldo_actual_monedero, BigDecimal monto_utilizado) {
		super();
		this.id_terminal_pos = id_terminal_pos;
		this.numero_boleta = numero_boleta;
		this.fecha_fin_transaccion = fecha_fin_transaccion;
		this.hora_fin_transaccion = hora_fin_transaccion;
		this.id_tienda_origen = id_tienda_origen;
		this.id_tienda_reconocimiento = id_tienda_reconocimiento;
		this.numero_monedero = numero_monedero;
		this.saldo_anterior_monedero = saldo_anterior_monedero;
		this.monto_monedero = monto_monedero;
		this.monto_obtenido_monedero = monto_obtenido_monedero;
		this.saldo_actual_monedero = saldo_actual_monedero;
		this.monto_utilizado = monto_utilizado;
	}








	public @Nullable String getId_terminal_pos() {
		return id_terminal_pos;
	}
	public void setId_terminal_pos(@Nullable String id_terminal_pos) {
		this.id_terminal_pos = id_terminal_pos;
	}
	public @Nullable String getNumero_boleta() {
		return numero_boleta;
	}
	public void setNumero_boleta(@Nullable String numero_boleta) {
		this.numero_boleta = numero_boleta;
	}
	public @Nullable String getFecha_fin_transaccion() {
		return fecha_fin_transaccion;
	}
	public void setFecha_fin_transaccion(@Nullable String fecha_fin_transaccion) {
		this.fecha_fin_transaccion = fecha_fin_transaccion;
	}
	public @Nullable String getHora_fin_transaccion() {
		return hora_fin_transaccion;
	}
	public void setHora_fin_transaccion(@Nullable String hora_fin_transaccion) {
		this.hora_fin_transaccion = hora_fin_transaccion;
	}
	public @Nullable String getId_tienda_origen() {
		return id_tienda_origen;
	}
	public void setId_tienda_origen(@Nullable String id_tienda_origen) {
		this.id_tienda_origen = id_tienda_origen;
	}
	public @Nullable String getId_tienda_reconocimiento() {
		return id_tienda_reconocimiento;
	}
	public void setId_tienda_reconocimiento(@Nullable String id_tienda_reconocimiento) {
		this.id_tienda_reconocimiento = id_tienda_reconocimiento;
	}

	public String getNumero_monedero() {
		return numero_monedero;
	}



	public void setNumero_monedero(String numero_monedero) {
		this.numero_monedero = numero_monedero;
	}



	public BigDecimal getSaldo_anterior_monedero() {
		return saldo_anterior_monedero;
	}



	public void setSaldo_anterior_monedero(BigDecimal saldo_anterior_monedero) {
		this.saldo_anterior_monedero = saldo_anterior_monedero;
	}



	public BigDecimal getMonto_monedero() {
		return monto_monedero;
	}



	public void setMonto_monedero(BigDecimal monto_monedero) {
		this.monto_monedero = monto_monedero;
	}



	public BigDecimal getMonto_obtenido_monedero() {
		return monto_obtenido_monedero;
	}



	public void setMonto_obtenido_monedero(BigDecimal monto_obtenido_monedero) {
		this.monto_obtenido_monedero = monto_obtenido_monedero;
	}



	public BigDecimal getSaldo_actual_monedero() {
		return saldo_actual_monedero;
	}



	public void setSaldo_actual_monedero(BigDecimal saldo_actual_monedero) {
		this.saldo_actual_monedero = saldo_actual_monedero;
	}



	public @Nullable BigDecimal getMonto_utilizado() {
		return monto_utilizado;
	}

	public void setMonto_utilizado(@Nullable BigDecimal monto_utilizado) {
		this.monto_utilizado = monto_utilizado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BoletaDetallesMonedero that = (BoletaDetallesMonedero) o;
		return Objects.equals(id_terminal_pos, that.id_terminal_pos) &&
				Objects.equals(numero_boleta, that.numero_boleta) &&
				Objects.equals(fecha_fin_transaccion, that.fecha_fin_transaccion) &&
				Objects.equals(hora_fin_transaccion, that.hora_fin_transaccion) &&
				Objects.equals(id_tienda_origen, that.id_tienda_origen) &&
				Objects.equals(id_tienda_reconocimiento, that.id_tienda_reconocimiento) &&
				Objects.equals(numero_monedero, that.numero_monedero) &&
				Objects.equals(saldo_anterior_monedero, that.saldo_anterior_monedero) &&
				Objects.equals(monto_monedero, that.monto_monedero) &&
				Objects.equals(monto_obtenido_monedero, that.monto_obtenido_monedero) &&
				Objects.equals(saldo_actual_monedero, that.saldo_actual_monedero) &&
				Objects.equals(monto_utilizado, that.monto_utilizado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, numero_monedero, saldo_anterior_monedero, monto_monedero, monto_obtenido_monedero, saldo_actual_monedero, monto_utilizado);
	}


	@Override
	public String toString() {
		return "BoletaDetallesMonedero{" +
				"id_terminal_pos='" + id_terminal_pos + '\'' +
				", numero_boleta='" + numero_boleta + '\'' +
				", fecha_fin_transaccion='" + fecha_fin_transaccion + '\'' +
				", hora_fin_transaccion='" + hora_fin_transaccion + '\'' +
				", id_tienda_origen='" + id_tienda_origen + '\'' +
				", id_tienda_reconocimiento='" + id_tienda_reconocimiento + '\'' +
				", numero_monedero='" + numero_monedero + '\'' +
				", saldo_anterior_monedero=" + saldo_anterior_monedero +
				", monto_monedero=" + monto_monedero +
				", monto_obtenido_monedero=" + monto_obtenido_monedero +
				", saldo_actual_monedero=" + saldo_actual_monedero +
				", monto_utilizado=" + monto_utilizado +
				'}';
	}

	@Override
	public Object clone() {
		try {
	        return (BoletaDetallesMonedero)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaDetallesMonedero(
	        		this.id_terminal_pos,
	        		this.numero_boleta,
	        		this.fecha_fin_transaccion,
	        		this.hora_fin_transaccion,
	        		this.id_tienda_origen,
	        		this.id_tienda_reconocimiento,
	        		this.numero_monedero,
	        		this.saldo_anterior_monedero,
	        		this.monto_monedero,
	        		this.monto_obtenido_monedero,
	        		this.saldo_actual_monedero,
					this.monto_utilizado
	        		);
	    }
	}
	
}
