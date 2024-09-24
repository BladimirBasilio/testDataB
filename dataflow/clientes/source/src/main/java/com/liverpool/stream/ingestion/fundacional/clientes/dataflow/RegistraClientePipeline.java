package com.liverpool.stream.ingestion.fundacional.clientes.dataflow;


import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process.*;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.transforms.join.CoGroupByKey;
import org.apache.beam.sdk.transforms.join.KeyedPCollectionTuple;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TupleTag;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.option.RegistraClienteOptions;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.CargaConfiguracionesPipeline;


public class RegistraClientePipeline {
	private static final Logger LOG = LoggerFactory.getLogger(RegistraClientePipeline.class);
	private static final String DEFAULT_CONFIG_FILE = "application.properties";
	private RegistraClienteOptions options;
	final TupleTag<String> TupleClienteATg = new TupleTag<String>();
	final TupleTag<String> TupleClienteTelefono = new TupleTag<String>();
	final TupleTag<String> TupleClienteEmail = new TupleTag<String>();
	final TupleTag<String> TupleClienteFormaDePago = new TupleTag<String>();
	final TupleTag<String> TupleClienteMerge = new TupleTag<String>();
	final TupleTag<String> TupleClienteDestinatario = new TupleTag<String>();
	final TupleTag<String> TupleClienteIds = new TupleTag<String>();

	public static void main(String[] args) {

		RegistraClientePipeline sp = new RegistraClientePipeline();

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


		PCollection<String> mensajePubSub = pipeline
				.apply("Escucha Mensajes PubSub Clientes",
						PubsubIO.readStrings().fromSubscription(options.getNombreSuscripcion()));


		Window<String> processingWindow =
				Window.into(FixedWindows.of(Duration.standardSeconds(5)));


		PCollection<RegistraCliente> boletaPojo = mensajePubSub
				.apply(processingWindow)
				.apply("Convierte Mensaje PubSub a Cliente",
						ParDo.of(new ConvierteJsonAPojoRegistraClienteProcess(
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


		PCollection<RegistraCliente> procesaTablasCliente = boletaPojo.apply("Crea Cliente",
				ParDo.of(new AlmacenaDatosClienteJDBCProcess(
						options.getJdbcUrl(),
						options.getUsuarioJdbc(),
						options.getPasswordJdbc(),
						options.getConsultaInsertClientes(),
						options.getInstanceNameSM(),
						options.getConnectionPoolMaxSize(),
						options.getConnectionPoolMinIdle(),
						options.getConnectionPoolTimeout(),
						options.getConnectionPoolIdleTimeout(),
						options.getConnectionPoolMaxLifetime())));

		PCollection<KV<String,String>> resultClienteTelefono = escribeDatosTelefono(procesaTablasCliente);


		PCollection<KV<String,String>> resultClienteEmail =  escribeDatosEmail(procesaTablasCliente);

		PCollection<Cliente> procesaDireccion =  escribeDatosDireccion(procesaTablasCliente);

		PCollection<KV<String,String>> resultFormaDePago = escribeDatosFormaPago(procesaDireccion);

		PCollection<KV<String,String>> resultClienteAtg = escribeDatosClienteAtg(procesaTablasCliente);

		PCollection<KV<String,String>>  resultClienteMerge = escribeDatosClienteMerge(procesaTablasCliente);

		PCollection<KV<String,String>> resultClienteId = escribeDatosClienteId(procesaTablasCliente);

		PCollection<KV<String,String>> resultClienteDestinatario = escribeDatosClienteDestinatario(procesaTablasCliente);

		// Join data

		// Merge collection values into a CoGbkResult collection.
		PCollection<KV<String, CoGbkResult>> joinedCollection =
				KeyedPCollectionTuple.of(TupleClienteATg, resultClienteAtg)
						.and(TupleClienteTelefono, resultClienteTelefono)
						.and(TupleClienteEmail, resultClienteEmail)
						.and(TupleClienteFormaDePago, resultFormaDePago)
						.and(TupleClienteMerge, resultClienteMerge)
						.and(TupleClienteDestinatario, resultClienteDestinatario)
						.and(TupleClienteIds, resultClienteId)
						.apply(CoGroupByKey.<String>create());

		joinedCollection
				.apply(ParDo.of(new ConstruirJsonResultante()))
				.apply("Envia PubSub Insert CLiente",
						PubsubIO.writeStrings().to(options.getNombreTopicoClienteInsertado()));

		joinedCollection
				.apply(ParDo.of(new ConstruirJsonHashPubSub()))
				.apply("Envia PubSub Insert Hash",
						PubsubIO.writeStrings().to(options.getNombreTopicoClienteHash()));

	}



	public PCollection<KV<String,String>> escribeDatosEmail(PCollection<RegistraCliente> procesaTablasHija) {

		return procesaTablasHija
				.apply("Separa Datos Email",
						ParDo.of(new SeparaDatosClienteEmailContactoProcess()))
				.apply("Crea Datos Email",ParDo.of(
						new AlmacenaDatosClienteEmailContactoJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClientesEmail(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)));

	}


	public PCollection<KV<String,String>> escribeDatosTelefono(PCollection<RegistraCliente>  procesaTablasHija) {

		return procesaTablasHija.apply("Separa Datos Telefono",
						ParDo.of(new SeparaDatosClienteTelefonoContactoProcess()))
				.apply("Crea Datos Telefono", ParDo.of(
						new AlmacenaDatosClienteTelefonoContactoJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClientesTelefono(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)));
	}


	public PCollection<Cliente> escribeDatosDireccion(PCollection<RegistraCliente>  procesaTablasHija) {

		return procesaTablasHija
				// procesaTablasHija.apply("Separa Datos Direccion",
				//(ParDo.of(new SeparaDatosClienteDireccionProcess()))
				.apply("Crea Datos Direccion", ParDo.of(
						new AlmacenaDatosClienteDireccionJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClientesDireccion(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime())));
	}


	public PCollection<KV<String,String>> escribeDatosFormaPago(PCollection<Cliente>  procesaTablasHija) {

		return procesaTablasHija
				.apply("Separa Datos Forma Pago",
						ParDo.of(new SeparaDatosClienteFormaPagoProcess()))
				.apply("Crea Datos Forma Pago",ParDo.of(
						new AlmacenaDatosClienteFormaPagoJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClientesFormaPago(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)));
	}


	public PCollection<KV<String,String>>  escribeDatosClienteAtg(PCollection<RegistraCliente>  procesaTablasHija) {

		return procesaTablasHija.apply("Separa Datos Cliente ATG",
						ParDo.of(new SeparaDatosClienteAtgProcess()))
				.apply("Crea Datos Cliente ATG",ParDo.of(
						new AlmacenaDatosClienteAtgJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClienteAtg(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime()
						)));
	}


	public PCollection<KV<String,String>> escribeDatosClienteMerge(PCollection<RegistraCliente>  procesaTablasHija) {

		return procesaTablasHija.apply("Separa Datos Cliente Merge",
						ParDo.of(new SeparaDatosClienteMergeProcess()))
				.apply("Crea Datos Cliente Merge",ParDo.of(
						new AlmacenaDatosClienteMergeJDBCProcess(options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClienteMerge(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime())));
	}


	public PCollection<KV<String,String>> escribeDatosClienteId(PCollection<RegistraCliente>  procesaTablasHija) {

		return procesaTablasHija.apply("Separa Datos Cliente ID",
						ParDo.of(new SeparaDatosClienteIdProcess()))
				.apply("Crea Datos Cliente ID",
						ParDo.of(new AlmacenaDatosClienteIdJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClienteId(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime())));
	}


	public PCollection<KV<String,String>> escribeDatosClienteDestinatario(PCollection<RegistraCliente>  procesaTablasHija) {

		return procesaTablasHija.apply("Separa Datos Cliente Destinatario",
						ParDo.of(new SeparaDatosClienteDestinatarioProcess()))
				.apply("Crea Datos Cliente Destinatario",ParDo.of(
						new AlmacenaDatosClienteDestinatarioJDBCProcess(
								options.getJdbcUrl(),
								options.getUsuarioJdbc(),
								options.getPasswordJdbc(),
								options.getConsultaInsertClienteDestinatario(),
								options.getConsultaDataClienteByIdPadreDestinatario(),
								options.getInstanceNameSM(),
								options.getConnectionPoolMaxSize(),
								options.getConnectionPoolMinIdle(),
								options.getConnectionPoolTimeout(),
								options.getConnectionPoolIdleTimeout(),
								options.getConnectionPoolMaxLifetime())));

	}


}
