package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow;


import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process.*;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PCollection;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.option.RegistraPedidoOptions;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.CargaConfiguracionesPipeline;


public class RegistraPedidoPipeline {
	private static final Logger LOG = LoggerFactory.getLogger(RegistraPedidoPipeline.class);
	private static final String DEFAULT_CONFIG_FILE = "application.properties";
	private RegistraPedidoOptions options;
	private static ConnectionPoolProvider instance = null;

	public static void main(String[] args) {

		RegistraPedidoPipeline sp = new RegistraPedidoPipeline();

		sp.init();

		sp.run();

	}

	public void init() {

		options = new CargaConfiguracionesPipeline(DEFAULT_CONFIG_FILE).inicia();

		//dataSource = ConnectionPoolProvider.getInstance().getDataSource();

		//new ConnectionPoolProvider(options);

	}

	public void run()
	{

		Pipeline pipeline = Pipeline.create(options);

		doDataProcessing(pipeline);

		pipeline.run();
	}

	public void doDataProcessing (Pipeline pipeline)
	{

			JdbcIO.DataSourceConfiguration configuracionBaseDatos
	        = JdbcIO.DataSourceConfiguration.create(
		    		   options.getNombreJdbcDriver(), options.getJdbcUrl())
			           .withUsername(options.getUsuarioJdbc())
			           .withPassword(options.getPasswordJdbc());

		Window<String> processingWindow =
				Window.into(FixedWindows.of(Duration.standardSeconds(5)));

		PCollection<String> mensajePubSub = pipeline
					  .apply("Escucha Mensajes PubSub Pedido",
							  PubsubIO.readStrings().fromSubscription(options.getNombreSuscripcion()));


			PCollection<RegistraPedido> boletaPojo = mensajePubSub.apply(processingWindow).apply("Convierte Mensaje PubSub a Pedido",
													ParDo.of(new ConvierteJsonAPojoPedidoProcess(
															options.getJdbcUrl(),
															options.getUsuarioJdbc(),
															options.getPasswordJdbc(),
															options.getInstanceNameSM(),
															options.getAppName(),
															options.getConnectionPoolMaxSize(),
															options.getConnectionPoolMinIdle(),
															options.getConnectionPoolTimeout(),
															options.getConnectionPoolIdleTimeout(),
															options.getConnectionPoolMaxLifetime())));


		    PCollection<RegistraPedido> procesaTablasCliente = boletaPojo.apply("Crea Cliente Remitente",
		    										ParDo.of(new AlmacenaDatosClienteRemitenteJDBCProcess(
		    												options.getJdbcUrl(),
		    												options.getUsuarioJdbc(),
		    												options.getPasswordJdbc(),
		    												options.getConsultaInsertClientes(),
															options.getInstanceNameSM(),
															options.getAppName(),
															options.getConnectionPoolMaxSize(),
															options.getConnectionPoolMinIdle(),
															options.getConnectionPoolTimeout(),
															options.getConnectionPoolIdleTimeout(),
															options.getConnectionPoolMaxLifetime())));

		    PCollection<RegistraPedido> procesaTablasClienteHijas = procesaTablasCliente.apply("Crea Cliente Destinatario",
					ParDo.of(new AlmacenaDatosClienteDestinatarioJDBCProcess(
							options.getJdbcUrl(),
							options.getUsuarioJdbc(),
							options.getPasswordJdbc(),
							options.getConsultaInsertClientes(),
							options.getInstanceNameSM(),
							options.getAppName(),
							options.getConnectionPoolMaxSize(),
							options.getConnectionPoolMinIdle(),
							options.getConnectionPoolTimeout(),
							options.getConnectionPoolIdleTimeout(),
							options.getConnectionPoolMaxLifetime())));

		    escribeDatosEmail(procesaTablasClienteHijas);

		    escribeDatosTelefono(procesaTablasClienteHijas);

		    escribeDatosClienteId(procesaTablasClienteHijas);

		    PCollection<RegistraPedido> procesaTablasDireccion = procesaTablasClienteHijas.apply("Crea Direccion Destinatario",
					ParDo.of(new AlmacenaDatosClienteDestinatarioDireccionJDBCProcess(
							options.getJdbcUrl(),
							options.getUsuarioJdbc(),
							options.getPasswordJdbc(),
							options.getConsultaInsertClientesDireccion(),
							options.getInstanceNameSM(),
							options.getAppName(),
							options.getConnectionPoolMaxSize(),
							options.getConnectionPoolMinIdle(),
							options.getConnectionPoolTimeout(),
							options.getConnectionPoolIdleTimeout(),
							options.getConnectionPoolMaxLifetime())));

		PCollection<RegistraPedido> procesaTablasEmail = procesaTablasDireccion.apply("Crea Emails Destinatario",
				ParDo.of(new AlmacenaDatosClienteDestinatarioEmailJDBCProcess(
						options.getJdbcUrl(),
						options.getUsuarioJdbc(),
						options.getPasswordJdbc(),
						options.getConsultaInsertClientesEmail(),
						options.getInstanceNameSM(),
						options.getAppName(),
						options.getConnectionPoolMaxSize(),
						options.getConnectionPoolMinIdle(),
						options.getConnectionPoolTimeout(),
						options.getConnectionPoolIdleTimeout(),
						options.getConnectionPoolMaxLifetime()
						)));

		PCollection<RegistraPedido> procesaTablasTelefonos = procesaTablasEmail.apply("Crea Telefonos Destinatario",
				ParDo.of(new AlmacenaDatosClienteDestinatarioTelefonoJDBCProcess(
						options.getJdbcUrl(),
						options.getUsuarioJdbc(),
						options.getPasswordJdbc(),
						options.getConsultaInsertClientesTelefono(),
						options.getInstanceNameSM(),
						options.getAppName(),
						options.getConnectionPoolMaxSize(),
						options.getConnectionPoolMinIdle(),
						options.getConnectionPoolTimeout(),
						options.getConnectionPoolIdleTimeout(),
						options.getConnectionPoolMaxLifetime()
						)));

		PCollection<RegistraPedido> procesaTablasPedidosHijas = procesaTablasTelefonos.apply("Crea Pedido",
				ParDo.of(new AlmacenaDatosPedidoJDBCProcess(
						options.getJdbcUrl(),
						options.getUsuarioJdbc(),
						options.getPasswordJdbc(),
						options.getConsultaInsertPedidos(),
						options.getInstanceNameSM(),
						options.getAppName(),
						options.getConnectionPoolMaxSize(),
						options.getConnectionPoolMinIdle(),
						options.getConnectionPoolTimeout(),
						options.getConnectionPoolIdleTimeout(),
						options.getConnectionPoolMaxLifetime(),
						options.getRedisHost(),
						options.getRedisPort(),
						options.getRedisTimeout(),
						options.getRedisPassword(),
						options.getJedisPoolMaxActive(),
						options.getJedisPoolMaxIdle(),
						options.getJedisPoolMinIdle(),
						options.getVinculacionTopic(),
						options.getVinculacionProjectId()
				)));
		//.apply("Conectar con MDM",ParDo.of(new ConnectMDMProcess(options.getMdmUrl(), options.getMdmServicesEndpoint(), options.getMdmServicesTimeOut())));

		escribeDatosPedidoDetalleSku(procesaTablasPedidosHijas);

		PCollection<RegistraPedido> procesaMDM = procesaTablasClienteHijas
				.apply("Conectar con MDM",
						ParDo.of(new ConnectMDMProcess(options.getMdmUrl(), options.getMdmServicesEndpoint(), options.getMdmServicesTimeOut())));


		//procesaTablasPedidosHijas.apply("Conectar con MDM",
			//		ParDo.of(new ConnectMDMProcess(options.getMdmUrl(), options.getMdmServicesEndpoint(), options.getMdmServicesTimeOut())));


	}


	public void escribeDatosEmail(PCollection<RegistraPedido> procesaTablasHija) {

		procesaTablasHija
				.apply("Separa Datos Email",
					ParDo.of(new SeparaDatosClienteEmailContactoProcess()))
		    .apply("Crea Datos Email", ParDo.of(
		    		new AlmacenaDatosClienteEmailContactoJDBCProcess(
							options.getJdbcUrl(),
							options.getUsuarioJdbc(),
							options.getPasswordJdbc(),
							options.getConsultaInsertClientesEmail(),
							options.getInstanceNameSM(),
							options.getAppName(),
							options.getConnectionPoolMaxSize(),
							options.getConnectionPoolMinIdle(),
							options.getConnectionPoolTimeout(),
							options.getConnectionPoolIdleTimeout(),
							options.getConnectionPoolMaxLifetime())));
	}


	public void escribeDatosTelefono(PCollection<RegistraPedido>  procesaTablasHija) {

		procesaTablasHija.apply("Separa Datos Telefono",
				ParDo.of(new SeparaDatosClienteTelefonoContactoProcess()))
	    .apply("Crea Datos Telefono",ParDo.of(
	    		new AlmacenaDatosClienteTelefonoContactoJDBCProcess(
						options.getJdbcUrl(),
						options.getUsuarioJdbc(),
						options.getPasswordJdbc(),
						options.getConsultaInsertClientesTelefono(),
						options.getInstanceNameSM(),
						options.getAppName(),
						options.getConnectionPoolMaxSize(),
						options.getConnectionPoolMinIdle(),
						options.getConnectionPoolTimeout(),
						options.getConnectionPoolIdleTimeout(),
						options.getConnectionPoolMaxLifetime()
						)));
	}

	public void escribeDatosPedidoDetalleSku(PCollection<RegistraPedido>  procesaTablasHija) {

			procesaTablasHija.apply("Separa Pedido Detalle Sku",
					ParDo.of(new SeparaDatosPedidoDetalleSkuProcess()))
		    .apply("Crea Datos Pedido Detalle Sku",ParDo.of(
		    		new AlmacenaDatosPedidoDetalleSkuJDBCProcess(
							options.getJdbcUrl(),
							options.getUsuarioJdbc(),
							options.getPasswordJdbc(),
							options.getConsultaInsertPedidosDetalleSku(),
							options.getInstanceNameSM(),
							options.getAppName(),
							options.getConnectionPoolMaxSize(),
							options.getConnectionPoolMinIdle(),
							options.getConnectionPoolTimeout(),
							options.getConnectionPoolIdleTimeout(),
							options.getConnectionPoolMaxLifetime()
		    				)));
		}


	public void escribeDatosClienteId(PCollection<RegistraPedido>  procesaTablasHija) {

			procesaTablasHija.apply("Separa Datos Cliente ID",
					ParDo.of(new SeparaDatosClienteIdProcess()))
		    .apply("Crea Datos Cliente ID",ParDo.of(
		    		new AlmacenaDatosClienteIdJDBCProcess(
							options.getJdbcUrl(),
							options.getUsuarioJdbc(),
							options.getPasswordJdbc(),
							options.getConsultaInsertClienteId(),
							options.getInstanceNameSM(),
							options.getAppName(),
							options.getConnectionPoolMaxSize(),
							options.getConnectionPoolMinIdle(),
							options.getConnectionPoolTimeout(),
							options.getConnectionPoolIdleTimeout(),
							options.getConnectionPoolMaxLifetime()
		    				)));
		}

}
