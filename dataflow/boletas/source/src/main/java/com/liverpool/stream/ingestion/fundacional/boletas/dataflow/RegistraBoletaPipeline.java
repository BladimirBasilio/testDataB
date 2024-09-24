package com.liverpool.stream.ingestion.fundacional.boletas.dataflow;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetallesSku;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.option.RegistraBoletaOptions;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process.*;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.CargaConfiguracionesPipeline;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.windowing.*;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegistraBoletaPipeline {
	private static final Logger LOG = LoggerFactory.getLogger(RegistraBoletaPipeline.class);
	private static final String DEFAULT_CONFIG_FILE = "application.properties";
	private RegistraBoletaOptions options;
	private static ConnectionPoolProvider instance = null;

	public static void main(String[] args) {

		RegistraBoletaPipeline sp = new RegistraBoletaPipeline();

		sp.init();

		sp.run();

	}

	public void init() {

		options = new CargaConfiguracionesPipeline(DEFAULT_CONFIG_FILE).inicia();

	}

	public void run()
	{

		Pipeline pipeline = Pipeline.create(options);

		doDataProcessing(pipeline);

		pipeline.run();
	}

	public void doDataProcessing(Pipeline pipeline)
	{
		JdbcIO.DataSourceConfiguration configuracionBaseDatos
				= JdbcIO.DataSourceConfiguration.create(
						options.getNombreJdbcDriver(), options.getJdbcUrl())
				.withUsername(options.getUsuarioJdbc())
				.withPassword(options.getPasswordJdbc());

		Window<String> processingWindow =
				Window.into(FixedWindows.of(Duration.standardSeconds(5)));


		PCollection<String> mensajePubSub = pipeline
				.apply("Escucha Mensajes PubSub Boletas",
						PubsubIO.readStrings().fromSubscription(options.getNombreSuscripcion()));


		PCollection<RegistraBoleta> boletaPojo = mensajePubSub
				.apply(processingWindow)
				.apply("Convierte Mensaje PubSub a Boleta",
						ParDo.of(new ConvierteJsonAPojoBoletaProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)));

		PCollectionTuple procesaBoleta = boletaPojo.apply("Procesa Boleta Cabecera",
				new SeparaDatosBoletaCabeceraMultioutputProcess(
						options.getJdbcUrl(),
						options.getUsuarioJdbc(),
						options.getPasswordJdbc(),
						options.getConsultaInsertBoletaCabeceras(),
						options.getInstanceNameSM(),
						options.getConnectionPoolMaxSize(),
						options.getConnectionPoolMinIdle(),
						options.getConnectionPoolTimeout(),
						options.getConnectionPoolIdleTimeout(),
						options.getConnectionPoolMaxLifetime(),
						"RegistraBoletaPipeline"

				));


		PCollection<RegistraBoleta> procesaBoletaCabecera =
				procesaBoleta.get("boletaCabeceraSuccessTag");


		PCollection<String> procesaBoletaCabeceraError =
				procesaBoleta.get("boletaCabeceraErrorTag");


		escribeDatosBoletaCabeceraErrorReintento(procesaBoletaCabeceraError);

		escribeDatosBoletaDetallePagos(procesaBoletaCabecera);

		escribeDatosBoletaDetalleAbonosSegmento(procesaBoletaCabecera);

		escribeDatosBoletaDetalleAbonosTarjeta(procesaBoletaCabecera);

		escribeDatosBoletaDetalleCupones(procesaBoletaCabecera);

		escribeDatosBoletaDetallesMonedero(procesaBoletaCabecera);

		escribeDatosBoletaDetalleTarjetasPrepago(procesaBoletaCabecera);

		vinculacionBoletasPorHash(procesaBoletaCabecera);


		PCollectionTuple procesaBoletaDetallesSku = procesaBoletaCabecera
				.apply("Separa Datos Boleta Detalles Sku",
						ParDo.of(new SeparaDatosBoletaDetallesSkuProcess()))
				.apply("Procesa Boleta Detalles Sku",
						new SeparaDatosBoletaDetallesSkuMultioutputProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetallesSku(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						));



		PCollection<BoletaDetallesSku> procesaBoletaDetallesSkuSuccess =
				procesaBoletaDetallesSku.get("boletaDetallesSkuSuccessTag");


		PCollection<String> procesaBoletaDetallesSkuError =
				procesaBoletaDetallesSku.get("boletaDetallesSkuErrorTag");


		escribeDatosBoletaDetallesSkuErrorReintento(procesaBoletaDetallesSkuError);


		escribeDatosBoletaDetallesSkuDescuentos(procesaBoletaDetallesSkuSuccess);

	}





	public void escribeDatosBoletaDetallePagos(PCollection<RegistraBoleta> procesaTablasHija) {

		procesaTablasHija
				.apply("Separa Datos Boleta Detalle Pagos",
						ParDo.of(new SeparaDatosBoletaDetallePagosProcess()))
				.apply("Crea Datos Boleta Detalle Pagos",
						ParDo.of(new AlmacenaDatosBoletaDetallePagosJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetallePagos(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)))
				.apply("Envia PubSub Boleta Detalle Pagos",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);


	}

	public void escribeDatosBoletaDetalleAbonosSegmento(PCollection<RegistraBoleta> procesaTablasHija) {

		procesaTablasHija
				.apply("Separa Datos Boleta Detalle Abonos Segmento",
						ParDo.of(new SeparaDatosBoletaDetalleAbonosSegmentoProcess()))
				.apply("Crea Datos Boleta Detalle Abonos Segmento",
						ParDo.of(new AlmacenaDatosBoletaDetalleAbonosSegmentoJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetalleAbonosSegmento(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)))
				.apply("Envia PubSub Boleta Detalle Abonos Segmento",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);



	}


	public void escribeDatosBoletaDetalleCupones(PCollection<RegistraBoleta> procesaTablasHija) {

		procesaTablasHija
				.apply("Separa Datos Boleta Detalle Cupones",
						ParDo.of(new SeparaDatosBoletaDetalleCuponesProcess()))
				.apply("Crea Datos Boleta Detalle Cupones",
						ParDo.of(new AlmacenaDatosBoletaDetalleCuponesJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetalleCupones(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)))
				.apply("Envia PubSub Boleta Detalle Cupones",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);


	}


	public void escribeDatosBoletaDetallesMonedero(PCollection<RegistraBoleta> procesaTablasHija) {


		procesaTablasHija
				.apply("Separa Datos Boleta Detalles Monedero",
						ParDo.of(new SeparaDatosBoletaDetallesMonederoProcess()))
				.apply("Crea Datos Boleta Detalles Monedero",
						ParDo.of(new AlmacenaDatosBoletaDetallesMonederoJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetallesMonedero(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)))
				.apply("Envia PubSub Boleta Detalles Monedero",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);

	}

	public void escribeDatosBoletaDetalleTarjetasPrepago(PCollection<RegistraBoleta> procesaTablasHija) {


		procesaTablasHija
				.apply("Separa Datos Boleta Detalle Tarjetas Prepago",
						ParDo.of(new SeparaDatosBoletaDetalleTarjetasPrepagoProcess()))
				.apply("Crea Datos Boleta Detalle Tarjetas Prepago",
						ParDo.of(new AlmacenaDatosBoletaDetalleTarjetasPrepagoJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetalleTarjetasPrepago(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime(),
								"AlmacenaDatosBoletaDetalleTarjetasPrepago"
						)))
				.apply("Envia PubSub Boleta Detalle Tarjetas Prepago",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);


	}


	public void escribeDatosBoletaDetallesSkuDescuentos(PCollection<BoletaDetallesSku> procesaTablasHija) {


		procesaTablasHija
				.apply("Separa Datos Boleta Detalles Sku Descuentos",
						ParDo.of(new SeparaDatosBoletaDetallesSkuDescuentosProcess()))
				.apply("Crea Datos Boleta Detalles Sku Descuentos",
						ParDo.of(new AlmacenaDatosBoletaDetallesSkuDescuentosJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetallesSkuDescuentos(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)))
				.apply("Envia PubSub Boleta Detalles Sku Descuentos",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);

	}

	public void escribeDatosBoletaDetalleAbonosTarjeta(PCollection<RegistraBoleta> procesaTablasHija) {

		procesaTablasHija
				.apply("Separa Datos Boleta Detalle Abonos Tarjeta",
						ParDo.of(new SeparaDatosBoletaDetalleAbonosTarjetaProcess()))
				.apply("Crea Datos Boleta Detalle Abonos Tarjeta",
						ParDo.of(new AlmacenaDatosBoletaDetalleAbonosTarjetaJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertBoletaDetalleAbonosTarjeta(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime(),
								options.getInstanceNameSM()
						)))
				.apply("Envia PubSub Boleta Detalle Abonos Tarjeta",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);



	}

	public void vinculacionBoletasPorHash(PCollection<RegistraBoleta> procesaTablasHija) {
		procesaTablasHija
				.apply("Convierte datos JSON Vinculación",
						ParDo.of(new JSONResultanteBoleta(
						)))
				.apply("Envia datos a Pubsub Vinculación",
						PubsubIO.writeStrings().to(options.getVinculacionBoleta()));
	}


	public void escribeDatosBoletaCabeceraErrorReintento(PCollection<String> procesaTablasHija) {

		procesaTablasHija
				.apply("Envia PubSub Boleta Cabecera",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);

	}

	public void escribeDatosBoletaDetallesSkuErrorReintento(PCollection<String> procesaTablasHija) {

		procesaTablasHija
				.apply("Envia PubSub Boleta Detalles Sku",
						PubsubIO.writeStrings().to(options.getNombreTopicoError())
				);

	}

}