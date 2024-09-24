package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;


import java.math.BigDecimal;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetalleTarjetasPrepago {


	@Nullable private String id_terminal_pos;
	@Nullable private String numero_boleta;
	@Nullable private String fecha_fin_transaccion;
	@Nullable private String hora_fin_transaccion;
	@Nullable private String id_tienda_origen;
	@Nullable private String id_tienda_reconocimiento;
	@Nullable private String codigo_upc;
	@Nullable private BigDecimal importe_tarjeta_prepago;
	
	
	public BoletaDetalleTarjetasPrepago() {}
	

	public BoletaDetalleTarjetasPrepago(String id_terminal_pos, String numero_boleta, String fecha_fin_transaccion,
			String hora_fin_transaccion, String id_tienda_origen, String id_tienda_reconocimiento, String codigo_upc,
			BigDecimal importe_tarjeta_prepago) {
		super();
		this.id_terminal_pos = id_terminal_pos;
		this.numero_boleta = numero_boleta;
		this.fecha_fin_transaccion = fecha_fin_transaccion;
		this.hora_fin_transaccion = hora_fin_transaccion;
		this.id_tienda_origen = id_tienda_origen;
		this.id_tienda_reconocimiento = id_tienda_reconocimiento;
		this.codigo_upc = codigo_upc;
		this.importe_tarjeta_prepago = importe_tarjeta_prepago;
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


	public @Nullable String getCodigo_upc() {
		return codigo_upc;
	}


	public void setCodigo_upc(@Nullable String codigo_upc) {
		this.codigo_upc = codigo_upc;
	}


	public @Nullable BigDecimal getImporte_tarjeta_prepago() {
		return importe_tarjeta_prepago;
	}


	public void setImporte_tarjeta_prepago(@Nullable BigDecimal importe_tarjeta_prepago) {
		this.importe_tarjeta_prepago = importe_tarjeta_prepago;
	}


	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_upc == null) ? 0 : codigo_upc.hashCode());
		result = prime * result + ((fecha_fin_transaccion == null) ? 0 : fecha_fin_transaccion.hashCode());
		result = prime * result + ((hora_fin_transaccion == null) ? 0 : hora_fin_transaccion.hashCode());
		result = prime * result + ((id_terminal_pos == null) ? 0 : id_terminal_pos.hashCode());
		result = prime * result + ((id_tienda_origen == null) ? 0 : id_tienda_origen.hashCode());
		result = prime * result + ((id_tienda_reconocimiento == null) ? 0 : id_tienda_reconocimiento.hashCode());
		result = prime * result + ((importe_tarjeta_prepago == null) ? 0 : importe_tarjeta_prepago.hashCode());
		result = prime * result + ((numero_boleta == null) ? 0 : numero_boleta.hashCode());
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
		BoletaDetalleTarjetasPrepago other = (BoletaDetalleTarjetasPrepago) obj;
		if (codigo_upc == null) {
			if (other.codigo_upc != null)
				return false;
		} else if (!codigo_upc.equals(other.codigo_upc))
			return false;
		if (fecha_fin_transaccion == null) {
			if (other.fecha_fin_transaccion != null)
				return false;
		} else if (!fecha_fin_transaccion.equals(other.fecha_fin_transaccion))
			return false;
		if (hora_fin_transaccion == null) {
			if (other.hora_fin_transaccion != null)
				return false;
		} else if (!hora_fin_transaccion.equals(other.hora_fin_transaccion))
			return false;
		if (id_terminal_pos == null) {
			if (other.id_terminal_pos != null)
				return false;
		} else if (!id_terminal_pos.equals(other.id_terminal_pos))
			return false;
		if (id_tienda_origen == null) {
			if (other.id_tienda_origen != null)
				return false;
		} else if (!id_tienda_origen.equals(other.id_tienda_origen))
			return false;
		if (id_tienda_reconocimiento == null) {
			if (other.id_tienda_reconocimiento != null)
				return false;
		} else if (!id_tienda_reconocimiento.equals(other.id_tienda_reconocimiento))
			return false;
		if (importe_tarjeta_prepago == null) {
			if (other.importe_tarjeta_prepago != null)
				return false;
		} else if (!importe_tarjeta_prepago.equals(other.importe_tarjeta_prepago))
			return false;
		if (numero_boleta == null) {
			if (other.numero_boleta != null)
				return false;
		} else if (!numero_boleta.equals(other.numero_boleta))
			return false;
		return true;
	}


	
	
	
	@Override
	public String toString() {
		return "BoletaDetalleTarjetasPrepago [id_terminal_pos=" + id_terminal_pos + ", numero_boleta=" + numero_boleta
				+ ", fecha_fin_transaccion=" + fecha_fin_transaccion + ", hora_fin_transaccion=" + hora_fin_transaccion
				+ ", id_tienda_origen=" + id_tienda_origen + ", id_tienda_reconocimiento=" + id_tienda_reconocimiento
				+ ", codigo_upc=" + codigo_upc + ", importe_tarjeta_prepago=" + importe_tarjeta_prepago + "]";
	}


	@Override
	public Object clone() {
		try {
	        return (BoletaDetalleTarjetasPrepago)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaDetalleTarjetasPrepago(
						        		this.id_terminal_pos,
						        		this.numero_boleta,
						        		this.fecha_fin_transaccion,
						        		this.hora_fin_transaccion,
						        		this.id_tienda_origen,
						        		this.id_tienda_reconocimiento,
						        		this.codigo_upc,
						        		this.importe_tarjeta_prepago
	        		);
	    }
	}
	
}
