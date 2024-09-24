package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaBeanSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@DefaultSchema(JavaBeanSchema.class)
public class BoletaCabecera implements Cloneable {

	@Nullable private String id_terminal_pos;
	@Nullable private String numero_boleta;
	@Nullable private String fecha_fin_transaccion;
	@Nullable private String hora_fin_transaccion;
	@Nullable private String id_tienda_origen;
	@Nullable private String id_tienda_reconocimiento;
	@Nullable private Integer id_tipo_transaccion;
	@Nullable private Integer id_canal_de_venta;
	@Nullable private Integer id_venta_catalogo_extendido_y_otro;
	@Nullable private Integer id_vendedor;
	@Nullable private Integer id_tienda_original;
	@Nullable private Integer id_tipo_de_evento;
	@Nullable private Integer id_cliente;
	@Nullable private Integer id_terminal_pos_cancelacion;
	@Nullable private BigDecimal cuenta_empleado;
	@Nullable private BigDecimal monto_emitido; // no usado
	@Nullable private String codigo_facturacion;
	@Nullable private String referencia_folio_agencia_de_viajes;
	@Nullable private String numero_evento;
	@Nullable private String numero_boleta_cancelacion;
	@Nullable private BigDecimal monto_boleta;
	@Nullable private BigDecimal total_cancelacion;
	@Nullable private Integer id_numero_centro_de_servicio;
	@Nullable private String numero_paqueteria;
	@Nullable private String leyenda_facturacion; // no usado
	@Nullable private String codigo_de_barras;
	@Nullable private String fecha_nacimiento_garantia_extendida;
	@Nullable private String fecha_garantia_extemporanea;
	@Nullable private String numero_indicador_marketplace;
	@Nullable private String leyenda_tentativa;
	@Nullable private Integer id_tipo_de_descuento_al_total; // no usado
	@Nullable private String clave_homologada;
	@Nullable private Integer id_direccion_fiscal;
	@Nullable private String id_atg;
	@Nullable private String id_mdm;
	@Nullable private Integer id_tienda_origen_gcp;
	@Nullable private Integer id_terminal_pos_gcp;
	@Nullable private Integer id_vendedor_original;
	@Nullable private String original_terminal;
	@Nullable private String fecha_original_compra;
	@Nullable private String tienda_original;
	@Nullable private String nombre_vendedor;
	@Nullable private String tipo_entrega;
	@Nullable private String se_imprimio_ticket;
	@Nullable private String confirmacion_tiempo_aire;
	@Nullable private String email_donde_envio_ticket_electronico;
	@Nullable private Integer error_id_cliente;
	@Nullable private String indicador_de_borrado;
	@Nullable private String id_atg_del_cliente;
	@Nullable private String id_mdm_del_cliente;
	@Nullable private String numero_boleta_devolucion;
	public BoletaCabecera() {}
	


	public BoletaCabecera(String id_terminal_pos, String numero_boleta, String fecha_fin_transaccion,
			String hora_fin_transaccion, String id_tienda_origen, String id_tienda_reconocimiento,
			Integer id_tipo_transaccion, Integer id_canal_de_venta, Integer id_venta_catalogo_extendido_y_otro,
			Integer id_vendedor, Integer id_tienda_original, Integer id_tipo_de_evento, Integer id_cliente,
			Integer id_terminal_pos_cancelacion, BigDecimal cuenta_empleado, BigDecimal monto_emitido,
			String codigo_facturacion, String referencia_folio_agencia_de_viajes, String numero_evento,
			String numero_boleta_cancelacion, BigDecimal monto_boleta, BigDecimal total_cancelacion,
			Integer id_numero_centro_de_servicio, String numero_paqueteria, String leyenda_facturacion,
			String codigo_de_barras, String fecha_nacimiento_garantia_extendida, String fecha_garantia_extemporanea,
			String numero_indicador_marketplace, String leyenda_tentativa, Integer id_tipo_de_descuento_al_total,
			String clave_homologada, Integer id_direccion_fiscal, String id_atg, String id_mdm,
			Integer id_tienda_origen_gcp, Integer id_terminal_pos_gcp, Integer id_vendedor_original,
			String original_terminal, String fecha_original_compra, String tienda_original, String nombre_vendedor,
			String tipo_entrega, String se_imprimio_ticket, String confirmacion_tiempo_aire, String email_donde_envio_ticket_electronico,
			Integer error_id_cliente, String indicador_de_borrado, String id_atg_del_cliente, String id_mdm_del_cliente, String numero_boleta_devolucion
	) {
		super();
		this.id_terminal_pos = id_terminal_pos;
		this.numero_boleta = numero_boleta;
		this.fecha_fin_transaccion = fecha_fin_transaccion;
		this.hora_fin_transaccion = hora_fin_transaccion;
		this.id_tienda_origen = id_tienda_origen;
		this.id_tienda_reconocimiento = id_tienda_reconocimiento;
		this.id_tipo_transaccion = id_tipo_transaccion;
		this.id_canal_de_venta = id_canal_de_venta;
		this.id_venta_catalogo_extendido_y_otro = id_venta_catalogo_extendido_y_otro;
		this.id_vendedor = id_vendedor;
		this.id_tienda_original = id_tienda_original;
		this.id_tipo_de_evento = id_tipo_de_evento;
		this.id_cliente = id_cliente;
		this.id_terminal_pos_cancelacion = id_terminal_pos_cancelacion;
		this.cuenta_empleado = cuenta_empleado;
		this.monto_emitido = monto_emitido;
		this.codigo_facturacion = codigo_facturacion;
		this.referencia_folio_agencia_de_viajes = referencia_folio_agencia_de_viajes;
		this.numero_evento = numero_evento;
		this.numero_boleta_cancelacion = numero_boleta_cancelacion;
		this.monto_boleta = monto_boleta;
		this.total_cancelacion = total_cancelacion;
		this.id_numero_centro_de_servicio = id_numero_centro_de_servicio;
		this.numero_paqueteria = numero_paqueteria;
		this.leyenda_facturacion = leyenda_facturacion;
		this.codigo_de_barras = codigo_de_barras;
		this.fecha_nacimiento_garantia_extendida = fecha_nacimiento_garantia_extendida;
		this.fecha_garantia_extemporanea = fecha_garantia_extemporanea;
		this.numero_indicador_marketplace = numero_indicador_marketplace;
		this.leyenda_tentativa = leyenda_tentativa;
		this.id_tipo_de_descuento_al_total = id_tipo_de_descuento_al_total;
		this.clave_homologada = clave_homologada;
		this.id_direccion_fiscal = id_direccion_fiscal;
		this.id_atg = id_atg;
		this.id_mdm = id_mdm;
		this.id_tienda_origen_gcp = id_tienda_origen_gcp;
		this.id_terminal_pos_gcp = id_terminal_pos_gcp;
		this.id_vendedor_original = id_vendedor_original;
		this.original_terminal = original_terminal;
		this.fecha_original_compra = fecha_original_compra;
		this.tienda_original = tienda_original;
		this.nombre_vendedor = nombre_vendedor;
		this.tipo_entrega = tipo_entrega;
		this.se_imprimio_ticket = se_imprimio_ticket;
		this.confirmacion_tiempo_aire = confirmacion_tiempo_aire;
		this.email_donde_envio_ticket_electronico = email_donde_envio_ticket_electronico;
		this.error_id_cliente = error_id_cliente;
		this.indicador_de_borrado = indicador_de_borrado;
		this.id_atg_del_cliente = id_atg_del_cliente;
		this.id_mdm_del_cliente = id_mdm_del_cliente;
		this.numero_boleta_devolucion = numero_boleta_devolucion;
	}





	public void setId_mdm_del_cliente(@Nullable String id_mdm_del_cliente) {
		this.id_mdm_del_cliente = id_mdm_del_cliente;
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

	public @Nullable Integer getId_tipo_transaccion() {
		return id_tipo_transaccion;
	}
	public void setId_tipo_transaccion(@Nullable Integer id_tipo_transaccion) {
		this.id_tipo_transaccion = id_tipo_transaccion;
	}

	public @Nullable Integer getId_canal_de_venta() {
		return id_canal_de_venta;
	}
	public void setId_canal_de_venta(@Nullable Integer id_canal_de_venta) {
		this.id_canal_de_venta = id_canal_de_venta;
	}

	public @Nullable Integer getId_venta_catalogo_extendido_y_otro() {
		return id_venta_catalogo_extendido_y_otro;
	}
	public void setId_venta_catalogo_extendido_y_otro(@Nullable Integer id_venta_catalogo_extendido_y_otro) {
		this.id_venta_catalogo_extendido_y_otro = id_venta_catalogo_extendido_y_otro;
	}
	public @Nullable Integer getId_vendedor() {
		return id_vendedor;
	}
	public void setId_vendedor(@Nullable Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	public @Nullable Integer getId_tienda_original() {
		return id_tienda_original;
	}
	public void setId_tienda_original(@Nullable Integer id_tienda_original) {
		this.id_tienda_original = id_tienda_original;
	}
	public @Nullable Integer getId_tipo_de_evento() {
		return id_tipo_de_evento;
	}
	public void setId_tipo_de_evento(@Nullable Integer id_tipo_de_evento) {
		this.id_tipo_de_evento = id_tipo_de_evento;
	}

	public @Nullable Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(@Nullable Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public @Nullable Integer getId_terminal_pos_cancelacion() {
		return id_terminal_pos_cancelacion;
	}
	public void setId_terminal_pos_cancelacion(@Nullable Integer id_terminal_pos_cancelacion) {
		this.id_terminal_pos_cancelacion = id_terminal_pos_cancelacion;
	}

	public @Nullable BigDecimal getCuenta_empleado() {
		return cuenta_empleado;
	}
	public void setCuenta_empleado(@Nullable BigDecimal cuenta_empleado) {
		this.cuenta_empleado = cuenta_empleado;
	}
	public @Nullable BigDecimal getMonto_emitido() {
		return monto_emitido;
	}
	public void setMonto_emitido(@Nullable BigDecimal monto_emitido) {
		this.monto_emitido = monto_emitido;
	}
	public @Nullable String getCodigo_facturacion() {
		return codigo_facturacion;
	}
	public void setCodigo_facturacion(@Nullable String codigo_facturacion) {
		this.codigo_facturacion = codigo_facturacion;
	}

	public @Nullable String getReferencia_folio_agencia_de_viajes() {
		return referencia_folio_agencia_de_viajes;
	}
	public void setReferencia_folio_agencia_de_viajes(@Nullable String referencia_folio_agencia_de_viajes) {
		this.referencia_folio_agencia_de_viajes = referencia_folio_agencia_de_viajes;
	}

	public @Nullable String getNumero_evento() {
		return numero_evento;
	}
	public void setNumero_evento(@Nullable String numero_evento) {
		this.numero_evento = numero_evento;
	}

	public @Nullable String getNumero_boleta_cancelacion() {
		return numero_boleta_cancelacion;
	}
	public void setNumero_boleta_cancelacion(@Nullable String numero_boleta_cancelacion) {
		this.numero_boleta_cancelacion = numero_boleta_cancelacion;
	}
	public @Nullable BigDecimal getMonto_boleta() {
		return monto_boleta;
	}
	public void setMonto_boleta(@Nullable BigDecimal monto_boleta) {
		this.monto_boleta = monto_boleta;
	}
	public @Nullable BigDecimal getTotal_cancelacion() {
		return total_cancelacion;
	}
	public void setTotal_cancelacion(@Nullable BigDecimal total_cancelacion) {
		this.total_cancelacion = total_cancelacion;
	}

	public @Nullable Integer getId_numero_centro_de_servicio() {
		return id_numero_centro_de_servicio;
	}

	public void setId_numero_centro_de_servicio(@Nullable Integer id_numero_centro_de_servicio) {
		this.id_numero_centro_de_servicio = id_numero_centro_de_servicio;
	}

	public @Nullable String getNumero_paqueteria() {
		return numero_paqueteria;
	}

	public void setNumero_paqueteria(@Nullable String numero_paqueteria) {
		this.numero_paqueteria = numero_paqueteria;
	}

	public @Nullable String getLeyenda_facturacion() {
		return leyenda_facturacion;
	}

	public void setLeyenda_facturacion(@Nullable String leyenda_facturacion) {
		this.leyenda_facturacion = leyenda_facturacion;
	}

	public @Nullable String getCodigo_de_barras() {
		return codigo_de_barras;
	}

	public void setCodigo_de_barras(@Nullable String codigo_de_barras) {
		this.codigo_de_barras = codigo_de_barras;
	}

	public @Nullable String getFecha_nacimiento_garantia_extendida() {
		return fecha_nacimiento_garantia_extendida;
	}

	public void setFecha_nacimiento_garantia_extendida(@Nullable String fecha_nacimiento_garantia_extendida) {
		this.fecha_nacimiento_garantia_extendida = fecha_nacimiento_garantia_extendida;
	}

	public @Nullable String getFecha_garantia_extemporanea() {
		return fecha_garantia_extemporanea;
	}

	public void setFecha_garantia_extemporanea(@Nullable String fecha_garantia_extemporanea) {
		this.fecha_garantia_extemporanea = fecha_garantia_extemporanea;
	}

	public @Nullable String getNumero_indicador_marketplace() {
		return numero_indicador_marketplace;
	}

	public void setNumero_indicador_marketplace(@Nullable String numero_indicador_marketplace) {
		this.numero_indicador_marketplace = numero_indicador_marketplace;
	}

	public @Nullable String getLeyenda_tentativa() {
		return leyenda_tentativa;
	}

	public void setLeyenda_tentativa(@Nullable String leyenda_tentativa) {
		this.leyenda_tentativa = leyenda_tentativa;
	}

	public @Nullable Integer getId_tipo_de_descuento_al_total() {
		return id_tipo_de_descuento_al_total;
	}

	public void setId_tipo_de_descuento_al_total(@Nullable Integer id_tipo_de_descuento_al_total) {
		this.id_tipo_de_descuento_al_total = id_tipo_de_descuento_al_total;
	}

	public @Nullable String getClave_homologada() {
		return clave_homologada;
	}

	public void setClave_homologada(@Nullable String clave_homologada) {
		this.clave_homologada = clave_homologada;
	}

	public @Nullable Integer getId_direccion_fiscal() {
		return id_direccion_fiscal;
	}

	public void setId_direccion_fiscal(@Nullable Integer id_direccion_fiscal) {
		this.id_direccion_fiscal = id_direccion_fiscal;
	}

	public @Nullable String getId_atg() {
		return id_atg;
	}

	public void setId_atg(@Nullable String id_atg) {
		this.id_atg = id_atg;
	}

	public @Nullable String getId_mdm() {
		return id_mdm;
	}

	public void setId_mdm(@Nullable String id_mdm) {
		this.id_mdm = id_mdm;
	}

	public @Nullable Integer getId_tienda_origen_gcp() {
		return id_tienda_origen_gcp;
	}

	public void setId_tienda_origen_gcp(@Nullable Integer id_tienda_origen_gcp) {
		this.id_tienda_origen_gcp = id_tienda_origen_gcp;
	}

	public @Nullable Integer getId_terminal_pos_gcp() {
		return id_terminal_pos_gcp;
	}

	public void setId_terminal_pos_gcp(@Nullable Integer id_terminal_pos_gcp) {
		this.id_terminal_pos_gcp = id_terminal_pos_gcp;
	}

	public @Nullable Integer getId_vendedor_original() {
		return id_vendedor_original;
	}

	public void setId_vendedor_original(@Nullable Integer id_vendedor_original) {
		this.id_vendedor_original = id_vendedor_original;
	}

	public 	@Nullable String getOriginal_terminal() {
		return original_terminal;
	}
	public void setOriginal_terminal(@Nullable String original_terminal) {
		this.original_terminal = original_terminal;
	}

	public @Nullable String getFecha_original_compra() {
		return fecha_original_compra;
	}
	public void setFecha_original_compra(@Nullable String fecha_original_compra) {
		this.fecha_original_compra = fecha_original_compra;
	}

	public @Nullable String getTienda_original() {
		return tienda_original;
	}
	public void setTienda_original(@Nullable String tienda_original) {
		this.tienda_original = tienda_original;
	}

	public @Nullable String getNombre_vendedor() {
		return nombre_vendedor;
	}
	public void setNombre_vendedor(@Nullable String nombre_vendedor) {
		this.nombre_vendedor = nombre_vendedor;
	}

	public @Nullable String getTipo_entrega() {
		return tipo_entrega;
	}
	public void setTipo_entrega(@Nullable String tipo_entrega) {
		this.tipo_entrega = tipo_entrega;
	}

	public @Nullable String getSe_imprimio_ticket() {
		return se_imprimio_ticket;
	}
	public void setSe_imprimio_ticket(@Nullable String se_imprimio_ticket) {
		this.se_imprimio_ticket = se_imprimio_ticket;
	}

	public @Nullable String getConfirmacion_tiempo_aire() {
		return confirmacion_tiempo_aire;
	}
	public void setConfirmacion_tiempo_aire(@Nullable String confirmacion_tiempo_aire) {
		this.confirmacion_tiempo_aire = confirmacion_tiempo_aire;
	}

	public 	@Nullable String getEmail_donde_envio_ticket_electronico() {
		return email_donde_envio_ticket_electronico;
	}
	public void setEmail_donde_envio_ticket_electronico(@Nullable String email_donde_envio_ticket_electronico) {
		this.email_donde_envio_ticket_electronico = email_donde_envio_ticket_electronico;
	}

	public 	@Nullable Integer getError_id_cliente() {
		return error_id_cliente;
	}
	public void setError_id_cliente(@Nullable Integer error_id_cliente) {
		this.error_id_cliente = error_id_cliente;
	}

	public 	@Nullable String getIndicador_de_borrado() {
		return indicador_de_borrado;
	}

	public void setIndicador_de_borrado(@Nullable String indicador_de_borrado) {
		this.indicador_de_borrado = indicador_de_borrado;
	}

	public @Nullable String getId_atg_del_cliente() {
		return id_atg_del_cliente;
	}
	public void setId_atg_del_cliente(@Nullable String id_atg_del_cliente) {
		this.id_atg_del_cliente = id_atg_del_cliente;
	}

	public @Nullable String getId_mdm_del_cliente() {
		return id_mdm_del_cliente;
	}

	public @Nullable String getNumero_boleta_devolucion(){ return  numero_boleta_devolucion; }
	public void setNumero_boleta_devolucion(@Nullable String numero_boleta_devolucion){
		this.numero_boleta_devolucion = numero_boleta_devolucion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BoletaCabecera that = (BoletaCabecera) o;
		return Objects.equals(id_terminal_pos, that.id_terminal_pos) &&
				Objects.equals(numero_boleta, that.numero_boleta) &&
				Objects.equals(fecha_fin_transaccion, that.fecha_fin_transaccion) &&
				Objects.equals(hora_fin_transaccion, that.hora_fin_transaccion) &&
				Objects.equals(id_tienda_origen, that.id_tienda_origen) &&
				Objects.equals(id_tienda_reconocimiento, that.id_tienda_reconocimiento) &&
				Objects.equals(id_tipo_transaccion, that.id_tipo_transaccion) &&
				Objects.equals(id_canal_de_venta, that.id_canal_de_venta) &&
				Objects.equals(id_venta_catalogo_extendido_y_otro, that.id_venta_catalogo_extendido_y_otro) &&
				Objects.equals(id_vendedor, that.id_vendedor) &&
				Objects.equals(id_tienda_original, that.id_tienda_original) &&
				Objects.equals(id_tipo_de_evento, that.id_tipo_de_evento) &&
				Objects.equals(id_cliente, that.id_cliente) &&
				Objects.equals(id_terminal_pos_cancelacion, that.id_terminal_pos_cancelacion) &&
				Objects.equals(cuenta_empleado, that.cuenta_empleado) &&
				Objects.equals(monto_emitido, that.monto_emitido) &&
				Objects.equals(codigo_facturacion, that.codigo_facturacion) &&
				Objects.equals(referencia_folio_agencia_de_viajes, that.referencia_folio_agencia_de_viajes) &&
				Objects.equals(numero_evento, that.numero_evento) &&
				Objects.equals(numero_boleta_cancelacion, that.numero_boleta_cancelacion) &&
				Objects.equals(monto_boleta, that.monto_boleta) &&
				Objects.equals(total_cancelacion, that.total_cancelacion) &&
				Objects.equals(id_numero_centro_de_servicio, that.id_numero_centro_de_servicio) &&
				Objects.equals(numero_paqueteria, that.numero_paqueteria) &&
				Objects.equals(leyenda_facturacion, that.leyenda_facturacion) &&
				Objects.equals(codigo_de_barras, that.codigo_de_barras) &&
				Objects.equals(fecha_nacimiento_garantia_extendida, that.fecha_nacimiento_garantia_extendida) &&
				Objects.equals(fecha_garantia_extemporanea, that.fecha_garantia_extemporanea) &&
				Objects.equals(numero_indicador_marketplace, that.numero_indicador_marketplace) &&
				Objects.equals(leyenda_tentativa, that.leyenda_tentativa) &&
				Objects.equals(id_tipo_de_descuento_al_total, that.id_tipo_de_descuento_al_total) &&
				Objects.equals(clave_homologada, that.clave_homologada) &&
				Objects.equals(id_direccion_fiscal, that.id_direccion_fiscal) &&
				Objects.equals(id_atg, that.id_atg) &&
				Objects.equals(id_mdm, that.id_mdm) &&
				Objects.equals(id_tienda_origen_gcp, that.id_tienda_origen_gcp) &&
				Objects.equals(id_terminal_pos_gcp, that.id_terminal_pos_gcp) &&
				Objects.equals(id_vendedor_original, that.id_vendedor_original) &&
				Objects.equals(original_terminal, that.original_terminal) &&
				Objects.equals(fecha_original_compra, that.fecha_original_compra) &&
				Objects.equals(tienda_original, that.tienda_original) &&
				Objects.equals(nombre_vendedor, that.nombre_vendedor) &&
				Objects.equals(tipo_entrega, that.tipo_entrega) &&
				Objects.equals(se_imprimio_ticket, that.se_imprimio_ticket) &&
				Objects.equals(confirmacion_tiempo_aire, that.confirmacion_tiempo_aire) &&
				Objects.equals(email_donde_envio_ticket_electronico, that.email_donde_envio_ticket_electronico) &&
				Objects.equals(error_id_cliente, that.error_id_cliente) &&
				Objects.equals(indicador_de_borrado, that.indicador_de_borrado) &&
				Objects.equals(id_atg_del_cliente, that.id_atg_del_cliente) &&
				Objects.equals(id_mdm_del_cliente, that.id_mdm_del_cliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, id_tipo_transaccion, id_canal_de_venta, id_venta_catalogo_extendido_y_otro, id_vendedor, id_tienda_original, id_tipo_de_evento, id_cliente, id_terminal_pos_cancelacion, cuenta_empleado, monto_emitido, codigo_facturacion, referencia_folio_agencia_de_viajes, numero_evento, numero_boleta_cancelacion, monto_boleta, total_cancelacion, id_numero_centro_de_servicio, numero_paqueteria, leyenda_facturacion, codigo_de_barras, fecha_nacimiento_garantia_extendida, fecha_garantia_extemporanea, numero_indicador_marketplace, leyenda_tentativa, id_tipo_de_descuento_al_total, clave_homologada, id_direccion_fiscal, id_atg, id_mdm, id_tienda_origen_gcp, id_terminal_pos_gcp, id_vendedor_original, original_terminal, fecha_original_compra, tienda_original, nombre_vendedor, tipo_entrega, se_imprimio_ticket, confirmacion_tiempo_aire, email_donde_envio_ticket_electronico, error_id_cliente, indicador_de_borrado, id_atg_del_cliente, id_mdm_del_cliente);
	}

	@Override
	public String toString() {
		return "BoletaCabecera{" +
				"id_terminal_pos='" + id_terminal_pos + '\'' +
				", numero_boleta='" + numero_boleta + '\'' +
				", fecha_fin_transaccion='" + fecha_fin_transaccion + '\'' +
				", hora_fin_transaccion='" + hora_fin_transaccion + '\'' +
				", id_tienda_origen='" + id_tienda_origen + '\'' +
				", id_tienda_reconocimiento='" + id_tienda_reconocimiento + '\'' +
				", id_tipo_transaccion=" + id_tipo_transaccion +
				", id_canal_de_venta=" + id_canal_de_venta +
				", id_venta_catalogo_extendido_y_otro=" + id_venta_catalogo_extendido_y_otro +
				", id_vendedor=" + id_vendedor +
				", id_tienda_original=" + id_tienda_original +
				", id_tipo_de_evento=" + id_tipo_de_evento +
				", id_cliente=" + id_cliente +
				", id_terminal_pos_cancelacion=" + id_terminal_pos_cancelacion +
				", cuenta_empleado=" + cuenta_empleado +
				", monto_emitido=" + monto_emitido +
				", codigo_facturacion='" + codigo_facturacion + '\'' +
				", referencia_folio_agencia_de_viajes='" + referencia_folio_agencia_de_viajes + '\'' +
				", numero_evento='" + numero_evento + '\'' +
				", numero_boleta_cancelacion='" + numero_boleta_cancelacion + '\'' +
				", monto_boleta=" + monto_boleta +
				", total_cancelacion=" + total_cancelacion +
				", id_numero_centro_de_servicio=" + id_numero_centro_de_servicio +
				", numero_paqueteria='" + numero_paqueteria + '\'' +
				", leyenda_facturacion='" + leyenda_facturacion + '\'' +
				", codigo_de_barras='" + codigo_de_barras + '\'' +
				", fecha_nacimiento_garantia_extendida='" + fecha_nacimiento_garantia_extendida + '\'' +
				", fecha_garantia_extemporanea='" + fecha_garantia_extemporanea + '\'' +
				", numero_indicador_marketplace='" + numero_indicador_marketplace + '\'' +
				", leyenda_tentativa='" + leyenda_tentativa + '\'' +
				", id_tipo_de_descuento_al_total=" + id_tipo_de_descuento_al_total +
				", clave_homologada='" + clave_homologada + '\'' +
				", id_direccion_fiscal=" + id_direccion_fiscal +
				", id_atg='" + id_atg + '\'' +
				", id_mdm='" + id_mdm + '\'' +
				", id_tienda_origen_gcp=" + id_tienda_origen_gcp +
				", id_terminal_pos_gcp=" + id_terminal_pos_gcp +
				", id_vendedor_original=" + id_vendedor_original +
				", original_terminal='" + original_terminal + '\'' +
				", fecha_original_compra='" + fecha_original_compra + '\'' +
				", tienda_original='" + tienda_original + '\'' +
				", nombre_vendedor='" + nombre_vendedor + '\'' +
				", tipo_entrega='" + tipo_entrega + '\'' +
				", se_imprimio_ticket='" + se_imprimio_ticket + '\'' +
				", confirmacion_tiempo_aire='" + confirmacion_tiempo_aire + '\'' +
				", email_donde_envio_ticket_electronico='" + email_donde_envio_ticket_electronico + '\'' +
				", error_id_cliente='" + error_id_cliente + '\'' +
				", indicador_de_borrado='" + indicador_de_borrado + '\'' +
				", id_atg_del_cliente='" + id_atg_del_cliente + '\'' +
				", id_mdm_del_cliente='" + id_mdm_del_cliente + '\'' +
				", numero_boleta_devolucion='" + numero_boleta_devolucion + '\'' +
				'}';
	}

	@Override
	public Object clone() {
		try {
	        return (BoletaCabecera)super.clone();
	    } catch (Exception e) {
	        // either handle the exception or throw it
	        return new BoletaCabecera(
						        		this.id_terminal_pos,
						        		this.numero_boleta,
						        		this.fecha_fin_transaccion,
						        		this.hora_fin_transaccion,
						        		this.id_tienda_origen,
						        		this.id_tienda_reconocimiento,
						        		this.id_tipo_transaccion,
						        		this.id_canal_de_venta,
						        		this.id_venta_catalogo_extendido_y_otro,
						        		this.id_vendedor,
						        		this.id_tienda_original,
						        		this.id_tipo_de_evento,
						        		this.id_cliente,
						        		this.id_terminal_pos_cancelacion,
						        		this.cuenta_empleado,
						        		this.monto_emitido,
						        		this.codigo_facturacion,
						        		this.referencia_folio_agencia_de_viajes,
						        		this.numero_evento,
						        		this.numero_boleta_cancelacion,
						        		this.monto_boleta,
						        		this.total_cancelacion,
						        		this.id_numero_centro_de_servicio,
						        		this.numero_paqueteria,
						        		this.leyenda_facturacion,
						        		this.codigo_de_barras,
						        		this.fecha_nacimiento_garantia_extendida,
						        		this.fecha_garantia_extemporanea,
						        		this.numero_indicador_marketplace,
						        		this.leyenda_tentativa,
						        		this.id_tipo_de_descuento_al_total,
						        		this.clave_homologada,
						        		this.id_direccion_fiscal,
						        		this.id_atg,
						        		this.id_mdm,
						        		this.id_tienda_origen_gcp,
						        		this.id_terminal_pos_gcp,
						        		this.id_vendedor_original,
										this.original_terminal,
										this.fecha_original_compra,
										this.tienda_original,
										this.nombre_vendedor,
										this.tipo_entrega,
										this.se_imprimio_ticket,
										this.confirmacion_tiempo_aire,
										this.email_donde_envio_ticket_electronico,
										this.error_id_cliente,
										this.indicador_de_borrado,
										this.id_atg_del_cliente,
										this.id_mdm_del_cliente,
										this.numero_boleta_devolucion
	        		);
	    }
	}

	
	
	
}
