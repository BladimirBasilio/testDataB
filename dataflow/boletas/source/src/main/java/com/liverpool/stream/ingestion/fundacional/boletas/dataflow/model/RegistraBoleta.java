package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;

import java.util.List;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class RegistraBoleta implements Cloneable {

	private BoletaCabecera boleta_cabeceras;
	private List<BoletaDetallesSku> boleta_detalles_sku;
	private List<BoletaDetallePagos> boleta_detalle_pagos;
	private List<BoletaDetalleAbonosSegmento> boleta_detalle_abonos_segmento;
	private List<BoletaDetallesMonedero> boleta_detalles_monedero;
	private List<BoletaDetalleCupones> boleta_detalle_cupones;
	private List<BoletaDetalleTarjetasPrepago> boleta_detalle_tarjetas_prepago;
	private List<BoletaDetalleAbonosTarjeta> boleta_detalle_abono_tarjetas;
	
	
	public RegistraBoleta() {}



	public RegistraBoleta(BoletaCabecera boleta_cabeceras, List<BoletaDetallesSku> boleta_detalles_sku,
			List<BoletaDetallePagos> boleta_detalle_pagos,
			List<BoletaDetalleAbonosSegmento> boleta_detalle_abonos_segmento,
			List<BoletaDetallesMonedero> boleta_detalles_monedero, List<BoletaDetalleCupones> boleta_detalle_cupones,
			List<BoletaDetalleTarjetasPrepago> boleta_detalle_tarjetas_prepago,
			List<BoletaDetalleAbonosTarjeta> boleta_detalle_abono_tarjetas
	) {
		super();
		this.boleta_cabeceras = boleta_cabeceras;
		this.boleta_detalles_sku = boleta_detalles_sku;
		this.boleta_detalle_pagos = boleta_detalle_pagos;
		this.boleta_detalle_abonos_segmento = boleta_detalle_abonos_segmento;
		this.boleta_detalles_monedero = boleta_detalles_monedero;
		this.boleta_detalle_cupones = boleta_detalle_cupones;
		this.boleta_detalle_tarjetas_prepago = boleta_detalle_tarjetas_prepago;
		this.boleta_detalle_abono_tarjetas = boleta_detalle_abono_tarjetas;
	}












	public BoletaCabecera getBoleta_cabeceras() {
		return boleta_cabeceras;
	}


	public void setBoleta_cabeceras(BoletaCabecera boleta_cabeceras) {
		this.boleta_cabeceras = boleta_cabeceras;
	}


	public List<BoletaDetallesSku> getBoleta_detalles_sku() {
		return boleta_detalles_sku;
	}


	public void setBoleta_detalles_sku(List<BoletaDetallesSku> boleta_detalles_sku) {
		this.boleta_detalles_sku = boleta_detalles_sku;
	}


	public List<BoletaDetallePagos> getBoleta_detalle_pagos() {
		return boleta_detalle_pagos;
	}


	public void setBoleta_detalle_pagos(List<BoletaDetallePagos> boleta_detalle_pagos) {
		this.boleta_detalle_pagos = boleta_detalle_pagos;
	}


	
	
	
	public List<BoletaDetalleAbonosSegmento> getBoleta_detalle_abonos_segmento() {
		return boleta_detalle_abonos_segmento;
	}




	public void setBoleta_detalle_abonos_segmento(List<BoletaDetalleAbonosSegmento> boleta_detalle_abonos_segmento) {
		this.boleta_detalle_abonos_segmento = boleta_detalle_abonos_segmento;
	}




	public List<BoletaDetallesMonedero> getBoleta_detalles_monedero() {
		return boleta_detalles_monedero;
	}




	public void setBoleta_detalles_monedero(List<BoletaDetallesMonedero> boleta_detalles_monedero) {
		this.boleta_detalles_monedero = boleta_detalles_monedero;
	}




	public List<BoletaDetalleCupones> getBoleta_detalle_cupones() {
		return boleta_detalle_cupones;
	}




	public void setBoleta_detalle_cupones(List<BoletaDetalleCupones> boleta_detalle_cupones) {
		this.boleta_detalle_cupones = boleta_detalle_cupones;
	}




	public List<BoletaDetalleTarjetasPrepago> getBoleta_detalle_tarjetas_prepago() {
		return boleta_detalle_tarjetas_prepago;
	}




	public void setBoleta_detalle_tarjetas_prepago(List<BoletaDetalleTarjetasPrepago> boleta_detalle_tarjetas_prepago) {
		this.boleta_detalle_tarjetas_prepago = boleta_detalle_tarjetas_prepago;
	}

	public List<BoletaDetalleAbonosTarjeta> getBoleta_detalle_abono_tarjetas() {
		return boleta_detalle_abono_tarjetas;
	}

	public void setBoleta_detalle_abono_tarjetas(List<BoletaDetalleAbonosTarjeta> boleta_detalle_abono_tarjetas) {
		this.boleta_detalle_abono_tarjetas = boleta_detalle_abono_tarjetas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boleta_cabeceras == null) ? 0 : boleta_cabeceras.hashCode());
		result = prime * result
				+ ((boleta_detalle_abonos_segmento == null) ? 0 : boleta_detalle_abonos_segmento.hashCode());
		result = prime * result + ((boleta_detalle_cupones == null) ? 0 : boleta_detalle_cupones.hashCode());
		result = prime * result + ((boleta_detalle_pagos == null) ? 0 : boleta_detalle_pagos.hashCode());
		result = prime * result
				+ ((boleta_detalle_tarjetas_prepago == null) ? 0 : boleta_detalle_tarjetas_prepago.hashCode());
		result = prime * result + ((boleta_detalles_monedero == null) ? 0 : boleta_detalles_monedero.hashCode());
		result = prime * result + ((boleta_detalles_sku == null) ? 0 : boleta_detalles_sku.hashCode());
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
		RegistraBoleta other = (RegistraBoleta) obj;
		if (boleta_cabeceras == null) {
			if (other.boleta_cabeceras != null)
				return false;
		} else if (!boleta_cabeceras.equals(other.boleta_cabeceras))
			return false;
		if (boleta_detalle_abonos_segmento == null) {
			if (other.boleta_detalle_abonos_segmento != null)
				return false;
		} else if (!boleta_detalle_abonos_segmento.equals(other.boleta_detalle_abonos_segmento))
			return false;
		if (boleta_detalle_cupones == null) {
			if (other.boleta_detalle_cupones != null)
				return false;
		} else if (!boleta_detalle_cupones.equals(other.boleta_detalle_cupones))
			return false;
		if (boleta_detalle_pagos == null) {
			if (other.boleta_detalle_pagos != null)
				return false;
		} else if (!boleta_detalle_pagos.equals(other.boleta_detalle_pagos))
			return false;
		if (boleta_detalle_tarjetas_prepago == null) {
			if (other.boleta_detalle_tarjetas_prepago != null)
				return false;
		} else if (!boleta_detalle_tarjetas_prepago.equals(other.boleta_detalle_tarjetas_prepago))
			return false;
		if (boleta_detalles_monedero == null) {
			if (other.boleta_detalles_monedero != null)
				return false;
		} else if (!boleta_detalles_monedero.equals(other.boleta_detalles_monedero))
			return false;
		if (boleta_detalles_sku == null) {
			if (other.boleta_detalles_sku != null)
				return false;
		} else if (!boleta_detalles_sku.equals(other.boleta_detalles_sku))
			return false;
		return true;
	}


	
	

	@Override
	public String toString() {
		return "RegistraBoleta [boleta_cabeceras=" + boleta_cabeceras + ", boleta_detalles_sku=" + boleta_detalles_sku
				+ ", boleta_detalle_pagos=" + boleta_detalle_pagos + ", boleta_detalle_abonos_segmento="
				+ boleta_detalle_abonos_segmento + ", boleta_detalles_monedero=" + boleta_detalles_monedero
				+ ", boleta_detalle_cupones=" + boleta_detalle_cupones + ", boleta_detalle_tarjetas_prepago="
				+ boleta_detalle_abonos_segmento +  ", boleta_detalle_tarjetas_prepago="
				+ boleta_detalle_tarjetas_prepago + ", boleta_detalle_abono_tarjetas="
				+ boleta_detalle_abono_tarjetas+"]";
	}



	@Override
	public Object clone() {
	    try {
	        return (RegistraBoleta)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new RegistraBoleta(
				        		this.boleta_cabeceras,
				        		this.boleta_detalles_sku,
				        		this.boleta_detalle_pagos,
				        		this.boleta_detalle_abonos_segmento,
				        		this.boleta_detalles_monedero,
				        		this.boleta_detalle_cupones,
				        		this.boleta_detalle_tarjetas_prepago,
								this.boleta_detalle_abono_tarjetas
			);
	    }
	}
}
