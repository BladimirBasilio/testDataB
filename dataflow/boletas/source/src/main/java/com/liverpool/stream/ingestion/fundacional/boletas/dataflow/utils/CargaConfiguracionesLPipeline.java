package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.option.RegistraBoletaLOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CargaConfiguracionesLPipeline {
	private static final Logger LOG = LoggerFactory.getLogger(CargaConfiguracionesLPipeline.class);
	private String rutaArchivoConfiguraciones;
	private RegistraBoletaLOptions options;

	
	public CargaConfiguracionesLPipeline(String rutaArchivoConfiguraciones) {
		this.rutaArchivoConfiguraciones = rutaArchivoConfiguraciones;
		
	}
	
	public RegistraBoletaLOptions inicia() {
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
				new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
				.configure(params.properties()
						.setFileName(this.rutaArchivoConfiguraciones));
		
		//define pipeline options.
		 options = PipelineOptionsFactory.create()
												.as(RegistraBoletaLOptions.class);
		
		try
		{
			LOG.info("Inicia Carga de Configuraciones");
			Configuration config = builder.getConfiguration();


			// Set DataFlow options
			options.setNombreTopico(config.getString("gcp.pubsub.topic.registra.boleta"));
			options.setNombreSuscripcion(config.getString("gcp.pubsub.topic.subscription.registra.boleta"));
			options.setNombreTopicoError(config.getString("gcp.pubsub.topic.registra.boleta.error"));
			options.setNombreSuscripcionError(config.getString("gcp.pubsub.topic.subscription.registra.boleta.error"));
			
			options.setNombreJdbcDriver(config.getString("jdbc.driver"));
			options.setJdbcUrl(config.getString("jdbc.url"));
			options.setUsuarioJdbc(config.getString("jdbc.user"));
			options.setPasswordJdbc(config.getString("jdbc.password"));
			
			options.setConsultaInsertBoletaCabeceras(config.getString("jdbc.insert.query.boleta.cabeceras"));
			options.setConsultaInsertBoletaDetallesSku(config.getString("jdbc.insert.query.boleta.detalles.sku"));
			options.setConsultaInsertBoletaDetalleAbonosSegmento(config.getString("jdbc.insert.query.boleta.detalle.abonos.segmento"));
			options.setConsultaInsertBoletaDetalleCupones(config.getString("jdbc.insert.query.boleta.detalle.cupones"));
			options.setConsultaInsertBoletaDetallePagos(config.getString("jdbc.insert.query.boleta.detalle.pagos"));
			options.setConsultaInsertBoletaDetallesMonedero(config.getString("jdbc.insert.query.boleta.detalles.monedero"));
			options.setConsultaInsertBoletaDetallesSkuDescuentos(config.getString("jdbc.insert.query.boleta.detalles.sku.descuentos"));
			options.setConsultaInsertBoletaDetalleTarjetasPrepago(config.getString("jdbc.insert.query.boleta.detalle.tarjetas.prepago"));
			options.setConsultaInsertBoletaDetalleAbonosTarjeta(config.getString("jdbc.insert.query.boleta.detalle.abonos.tarjeta"));
			options.setVinculacionBoleta(config.getString("gcp.pubsub.topic.boleta.vinculacion"));

		}
		catch(ConfigurationException cex)
		{
			LOG.error("Error al cargar configuraciones iniciales:",cex);
			cex.printStackTrace();
		}
		
		return options;
	}
	
}
