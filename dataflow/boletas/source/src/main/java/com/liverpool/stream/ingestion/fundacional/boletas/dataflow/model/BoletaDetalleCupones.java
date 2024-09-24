package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaDetalleCupones {

	

	@Nullable private String id_terminal_pos;
	@Nullable private String numero_boleta;
	@Nullable private String fecha_fin_transaccion;
	@Nullable private String hora_fin_transaccion;
	@Nullable private String id_tienda_origen;
	@Nullable private String id_tienda_reconocimiento;
	@Nullable private String codigo_cupon;
	@Nullable private String es_redimido;
	
	
	public BoletaDetalleCupones() {}
	

	public BoletaDetalleCupones(String id_terminal_pos, String numero_boleta, String fecha_fin_transaccion,
			String hora_fin_transaccion, String id_tienda_origen, String id_tienda_reconocimiento, String codigo_cupon,
			String es_redimido) {
		super();
		this.id_terminal_pos = id_terminal_pos;
		this.numero_boleta = numero_boleta;
		this.fecha_fin_transaccion = fecha_fin_transaccion;
		this.hora_fin_transaccion = hora_fin_transaccion;
		this.id_tienda_origen = id_tienda_origen;
		this.id_tienda_reconocimiento = id_tienda_reconocimiento;
		this.codigo_cupon = codigo_cupon;
		this.es_redimido = es_redimido;
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



	public @Nullable String getCodigo_cupon() {
		return codigo_cupon;
	}


	public void setCodigo_cupon(@Nullable String codigo_cupon) {
		this.codigo_cupon = codigo_cupon;
	}


	public @Nullable String getEs_redimido() {
		return es_redimido;
	}


	public void setEs_redimido(@Nullable String es_redimido) {
		this.es_redimido = es_redimido;
	}


	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_cupon == null) ? 0 : codigo_cupon.hashCode());
		result = prime * result + ((es_redimido == null) ? 0 : es_redimido.hashCode());
		result = prime * result + ((fecha_fin_transaccion == null) ? 0 : fecha_fin_transaccion.hashCode());
		result = prime * result + ((hora_fin_transaccion == null) ? 0 : hora_fin_transaccion.hashCode());
		result = prime * result + ((id_terminal_pos == null) ? 0 : id_terminal_pos.hashCode());
		result = prime * result + ((id_tienda_origen == null) ? 0 : id_tienda_origen.hashCode());
		result = prime * result + ((id_tienda_reconocimiento == null) ? 0 : id_tienda_reconocimiento.hashCode());
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
		BoletaDetalleCupones other = (BoletaDetalleCupones) obj;
		if (codigo_cupon == null) {
			if (other.codigo_cupon != null)
				return false;
		} else if (!codigo_cupon.equals(other.codigo_cupon))
			return false;
		if (es_redimido == null) {
			if (other.es_redimido != null)
				return false;
		} else if (!es_redimido.equals(other.es_redimido))
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
		if (numero_boleta == null) {
			if (other.numero_boleta != null)
				return false;
		} else if (!numero_boleta.equals(other.numero_boleta))
			return false;
		return true;
	}


	
	
	
	@Override
	public String toString() {
		return "BoletaDetalleCupones [id_terminal_pos=" + id_terminal_pos + ", numero_boleta=" + numero_boleta
				+ ", fecha_fin_transaccion=" + fecha_fin_transaccion + ", hora_fin_transaccion=" + hora_fin_transaccion
				+ ", id_tienda_origen=" + id_tienda_origen + ", id_tienda_reconocimiento=" + id_tienda_reconocimiento
				+ ", codigo_cupon=" + codigo_cupon + ", es_redimido=" + es_redimido + "]";
	}


	
	@Override
	public Object clone() {
		try {
	        return (BoletaDetalleCupones)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaDetalleCupones(
						        		this.id_terminal_pos,
						        		this.numero_boleta,
						        		this.fecha_fin_transaccion,
						        		this.hora_fin_transaccion,
						        		this.id_tienda_origen,
						        		this.id_tienda_reconocimiento,
						        		this.codigo_cupon,
						        		this.es_redimido
	        		);
	    }
	}
	
}
