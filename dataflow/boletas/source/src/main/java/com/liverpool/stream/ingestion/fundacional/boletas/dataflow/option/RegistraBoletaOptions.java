package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.option;

import org.apache.arrow.flatbuf.Int;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Description;


public interface RegistraBoletaOptions extends  DataflowPipelineOptions  {

	@Description("Pub/Sub Nombre Topico")
    String getNombreTopico();
	void setNombreTopico(String value);

	
	@Description("Pub/Sub Nombre Suscripcion")
    String getNombreSuscripcion();
	void setNombreSuscripcion(String value);

	
    @Description("Nombre Jdbc Driver")
    String getNombreJdbcDriver();
    void setNombreJdbcDriver(String value);
    
    
    @Description("Jdbc Url")
    String getJdbcUrl();
    void setJdbcUrl(String value);

    
	@Description("Usuario Jdbc")
    String getUsuarioJdbc();
	void setUsuarioJdbc(String value);


    @Description("Password Jdbc")
    String getPasswordJdbc();
    void setPasswordJdbc(String value);
    
    
    @Description("Consulta Insert Boleta Cabeceras")
    String getConsultaInsertBoletaCabeceras();
    void setConsultaInsertBoletaCabeceras(String value);	  
    
    
    @Description("Consulta Insert Boleta Detalles Sku")
    String getConsultaInsertBoletaDetallesSku();
    void setConsultaInsertBoletaDetallesSku(String value);
    
    
    @Description("Consulta Insert Boleta Detalle Abonos Segmento")
    String getConsultaInsertBoletaDetalleAbonosSegmento();
    void setConsultaInsertBoletaDetalleAbonosSegmento(String value);

    @Description("Consulta Insert Boleta Detalle Abonos Tarjeta")
    String getConsultaInsertBoletaDetalleAbonosTarjeta();
    void setConsultaInsertBoletaDetalleAbonosTarjeta(String value);
    
    
    @Description("Consulta Insert Boleta Detalle Cupones")
    String getConsultaInsertBoletaDetalleCupones();
    void setConsultaInsertBoletaDetalleCupones(String value);
    
    
    @Description("Consulta Insert Boleta Detalles Monedero")
    String getConsultaInsertBoletaDetallesMonedero();
    void setConsultaInsertBoletaDetallesMonedero(String value);
    
    
    @Description("Consulta Insert Boleta Detalles Sku Descuentos")
    String getConsultaInsertBoletaDetallesSkuDescuentos();
    void setConsultaInsertBoletaDetallesSkuDescuentos(String value);
    
    
    @Description("Consulta Insert Boleta Detalle Tarjetas Prepago")
    String getConsultaInsertBoletaDetalleTarjetasPrepago();
    void setConsultaInsertBoletaDetalleTarjetasPrepago(String value);
    
    
    @Description("Consulta Insert Boleta Detalle Pagos")
    String getConsultaInsertBoletaDetallePagos();
    void setConsultaInsertBoletaDetallePagos(String value);

    
	@Description("Pub/Sub Nombre Topico Error")
    String getNombreTopicoError();
	void setNombreTopicoError(String value);

	
	@Description("Pub/Sub Nombre Suscripcion Error")
    String getNombreSuscripcionError();
	void setNombreSuscripcionError(String value);

    @Description("Pub/Sub Nombre Topico Cliente Insertado")
    String getVinculacionBoleta();
    void setVinculacionBoleta(String value);

    @Description("Instance Name SM")
    String getInstanceNameSM();
    void setInstanceNameSM(String value);

    @Description("Connection Pool Max Size")
    Integer getConnectionPoolMaxSize();
    void setConnectionPoolMaxSize(Integer value);

    @Description("Connection Pool Min Idle")
    Integer getConnectionPoolMinIdle();
    void setConnectionPoolMinIdle(Integer value);

    @Description("Connection Pool Timeout")
    Integer getConnectionPoolTimeout();
    void setConnectionPoolTimeout(Integer value);

    @Description("Connection Pool Idle Timeout")
    Integer getConnectionPoolIdleTimeout();
    void setConnectionPoolIdleTimeout(Integer value);

    @Description("Connection Pool Max Lifetime")
    Integer getConnectionPoolMaxLifetime();
    void setConnectionPoolMaxLifetime(Integer value);

    @Description("Jdbc DataBase Name")
    String getDataBaseName();
    void setDataBaseName(String value);

    @Description("Min Evictable Idle Time Millis")
    Integer getMinEvictableIdleTimeMillis();
    void setMinEvictableIdleTimeMillis(Integer value);

    @Description("Soft Min Evictable Idle Time Millis")
    Integer getSoftMinEvictableIdleTimeMillis();
    void setSoftMinEvictableIdleTimeMillis(Integer value);
}
