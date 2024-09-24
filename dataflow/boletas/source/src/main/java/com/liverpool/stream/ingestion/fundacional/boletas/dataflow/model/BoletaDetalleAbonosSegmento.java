package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;


import java.math.BigDecimal;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetalleAbonosSegmento {

	
	@Nullable private Integer id_linea_detalle_abonos_segmento;
	@Nullable private String id_terminal_pos;
	@Nullable private String numero_boleta;
	@Nullable private String fecha_fin_transaccion;
	@Nullable private String hora_fin_transaccion;
	@Nullable private String id_tienda_origen;
	@Nullable private String id_tienda_reconocimiento;
	@Nullable private Integer id_segmento;
	@Nullable private BigDecimal importe_abonado_al_segmento;
	
	
	public BoletaDetalleAbonosSegmento() {}
	

	public BoletaDetalleAbonosSegmento(Integer id_linea_detalle_abonos_segmento, String id_terminal_pos,
			String numero_boleta, String fecha_fin_transaccion, String hora_fin_transaccion, String id_tienda_origen,
			String id_tienda_reconocimiento, Integer id_segmento, BigDecimal importe_abonado_al_segmento) {
		super();
		this.id_linea_detalle_abonos_segmento = id_linea_detalle_abonos_segmento;
		this.id_terminal_pos = id_terminal_pos;
		this.numero_boleta = numero_boleta;
		this.fecha_fin_transaccion = fecha_fin_transaccion;
		this.hora_fin_transaccion = hora_fin_transaccion;
		this.id_tienda_origen = id_tienda_origen;
		this.id_tienda_reconocimiento = id_tienda_reconocimiento;
		this.id_segmento = id_segmento;
		this.importe_abonado_al_segmento = importe_abonado_al_segmento;
	}







	public @Nullable Integer getId_linea_detalle_abonos_segmento() {
		return id_linea_detalle_abonos_segmento;
	}
	public void setId_linea_detalle_abonos_segmento(@Nullable Integer id_linea_detalle_abonos_segmento) {
		this.id_linea_detalle_abonos_segmento = id_linea_detalle_abonos_segmento;
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

	



	
	
	
	public @Nullable Integer getId_segmento() {
		return id_segmento;
	}


	public void setId_segmento(@Nullable Integer id_segmento) {
		this.id_segmento = id_segmento;
	}


	public @Nullable BigDecimal getImporte_abonado_al_segmento() {
		return importe_abonado_al_segmento;
	}


	public void setImporte_abonado_al_segmento(@Nullable BigDecimal importe_abonado_al_segmento) {
		this.importe_abonado_al_segmento = importe_abonado_al_segmento;
	}


	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha_fin_transaccion == null) ? 0 : fecha_fin_transaccion.hashCode());
		result = prime * result + ((hora_fin_transaccion == null) ? 0 : hora_fin_transaccion.hashCode());
		result = prime * result
				+ ((id_linea_detalle_abonos_segmento == null) ? 0 : id_linea_detalle_abonos_segmento.hashCode());
		result = prime * result + ((id_segmento == null) ? 0 : id_segmento.hashCode());
		result = prime * result + ((id_terminal_pos == null) ? 0 : id_terminal_pos.hashCode());
		result = prime * result + ((id_tienda_origen == null) ? 0 : id_tienda_origen.hashCode());
		result = prime * result + ((id_tienda_reconocimiento == null) ? 0 : id_tienda_reconocimiento.hashCode());
		result = prime * result + ((importe_abonado_al_segmento == null) ? 0 : importe_abonado_al_segmento.hashCode());
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
		BoletaDetalleAbonosSegmento other = (BoletaDetalleAbonosSegmento) obj;
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
		if (id_linea_detalle_abonos_segmento == null) {
			if (other.id_linea_detalle_abonos_segmento != null)
				return false;
		} else if (!id_linea_detalle_abonos_segmento.equals(other.id_linea_detalle_abonos_segmento))
			return false;
		if (id_segmento == null) {
			if (other.id_segmento != null)
				return false;
		} else if (!id_segmento.equals(other.id_segmento))
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
		if (importe_abonado_al_segmento == null) {
			if (other.importe_abonado_al_segmento != null)
				return false;
		} else if (!importe_abonado_al_segmento.equals(other.importe_abonado_al_segmento))
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
		return "BoletaDetalleAbonosSegmento [id_linea_detalle_abonos_segmento=" + id_linea_detalle_abonos_segmento
				+ ", id_terminal_pos=" + id_terminal_pos + ", numero_boleta=" + numero_boleta
				+ ", fecha_fin_transaccion=" + fecha_fin_transaccion + ", hora_fin_transaccion=" + hora_fin_transaccion
				+ ", id_tienda_origen=" + id_tienda_origen + ", id_tienda_reconocimiento=" + id_tienda_reconocimiento
				+ ", id_segmento=" + id_segmento + ", importe_abonado_al_segmento=" + importe_abonado_al_segmento + "]";
	}


	@Override
	public Object clone() {
		try {
	        return (BoletaDetalleAbonosSegmento)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaDetalleAbonosSegmento(
								        		this.id_linea_detalle_abonos_segmento,
								        		this.id_terminal_pos,
								        		this.numero_boleta,
								        		this.fecha_fin_transaccion,
								        		this.hora_fin_transaccion,
								        		this.id_tienda_origen,
								        		this.id_tienda_reconocimiento,
								        		this.id_segmento,
								        		this.importe_abonado_al_segmento
	        		);
	    }
	}
	
}
