package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.option.RegistraClienteOptions;

public class CargaConfiguracionesPipeline  {
	private static final Logger LOG = LoggerFactory.getLogger(CargaConfiguracionesPipeline.class);
	private String rutaArchivoConfiguraciones;
	private RegistraClienteOptions options;

	public CargaConfiguracionesPipeline(String rutaArchivoConfiguraciones) {
		this.rutaArchivoConfiguraciones = rutaArchivoConfiguraciones;
	}

	public RegistraClienteOptions inicia() {
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
				new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
						.configure(params.properties()
								.setFileName(this.rutaArchivoConfiguraciones));

		//define pipeline options
		options = PipelineOptionsFactory.create()
				.as(RegistraClienteOptions.class);

		try {
			LOG.info("Inicia Carga de Configuraciones");
			Configuration config = builder.getConfiguration();

			// Set DataFlow options
			options.setAppName(config.getString("gcp.df.registra.clientes.appName"));
			LOG.info("La AppName es:" + config.getString("gcp.df.registra.clientes.appName"));

			LOG.info("Temp location:" + config.getString("gcp.df.temp"));
			options.setTempLocation(config.getString("gcp.df.temp"));

			options.setStagingLocation(config.getString("gcp.df.staging"));
			LOG.info("Staging location:" + config.getString("gcp.df.staging"));

			LOG.info("Runner:DataflowRunner");
			options.setRunner(DataflowRunner.class);
			//options.setRunner(DirectRunner.class);
			//options.setStreaming(false);
			LOG.info("Project ID:" + config.getString("gcp.projectId"));
			options.setProject(config.getString("gcp.projectId"));
			//options.setAutoscalingAlgorithm(AutoscalingAlgorithmType.THROUGHPUT_BASED);

			options.setMaxNumWorkers(Integer.parseInt(config.getString("gcp.df.maxWorkers")));
			options.setJobName(config.getString("gcp.df.registra.clientes.appName") + "dfjob");

			String usePublicIps = config.getString("gcp.df.usePublicIps");
			String network = config.getString("gcp.df.network");
			String subnetwork = config.getString("gcp.df.subnetwork");
			LOG.info("UsePublicIps:" + usePublicIps);
			LOG.info("network:" + network);
			LOG.info("subnetwork:" + subnetwork);


			options.setUsePublicIps(Boolean.parseBoolean(usePublicIps));

			if (!(network.isEmpty() || network == null))
				options.setNetwork(network);
			if (!(subnetwork.isEmpty() || subnetwork == null))
				options.setSubnetwork(subnetwork);


			LOG.info("Service Account:" + config.getString("gcp.df.service.account"));
			options.setServiceAccount(config.getString("gcp.df.service.account"));

			options.setRegion(config.getString("gcp.df.region"));
			LOG.info(config.getString("gcp.df.region"));
			// Set custom DataFlow options
			options.setNombreTopico(config.getString("gcp.pubsub.topic.registra.clientes"));
			options.setNombreSuscripcion(config.getString("gcp.pubsub.topic.subscription.registra.clientes"));

			String projectId = config.getString("gcp.projectId");


			String databaseNameSM = getSecretValueSM(projectId,
					config.getString("gcp.sm.jdbc.database"),
					config.getString("gcp.sm.jdbc.database.version"));

			options.setDataBaseName(databaseNameSM);

			String instanceNameSM = getSecretValueSM(projectId,
					config.getString("gcp.sm.jdbc.instance.name"),
					config.getString("gcp.sm.jdbc.instance.name.version"));

			options.setInstanceNameSM(instanceNameSM);

			String jdbcUserSM = getSecretValueSM(projectId,
					config.getString("gcp.sm.jdbc.user"),
					config.getString("gcp.sm.jdbc.user.version"));

			String jdbcPwdSM = getSecretValueSM(projectId,
					config.getString("gcp.sm.jdbc.password"),
					config.getString("gcp.sm.jdbc.password.version"));

			String jdbcUrlSM;
			if (!(config.getString("jdbc.sm.url") == null ||
					config.getString("jdbc.sm.url").isEmpty())) {
				jdbcUrlSM = config.getString("jdbc.sm.url")
						.replace("{database-name}", databaseNameSM);
				//.replace("{instance-name}", instanceNameSM);

				options.setJdbcUrl(jdbcUrlSM);
			} else {
				//TODO: Generar excepcion a la falta de conexion a la BD
				jdbcUrlSM = null;
			}

			if (!(config.getString("jdbc.url") == null ||
					config.getString("jdbc.url").isEmpty())) {
				options.setJdbcUrl(config.getString("jdbc.url"));
			}

			options.setNombreJdbcDriver(config.getString("jdbc.driver"));

			options.setUpdate(Boolean.parseBoolean(config.getString("gcp.df.update")));
			//options.setEnableStreamingEngine(true);
			options.setUsuarioJdbc(jdbcUserSM);
			options.setPasswordJdbc(jdbcPwdSM);

			options.setDiskSizeGb(Integer.parseInt(config.getString("gcp.df.diskSize")));
			LOG.info("DiskSizeGb:" + config.getString("gcp.df.diskSize"));
			options.setWorkerMachineType(config.getString("gcp.df.machineType"));

			options.setConsultaInsertClientes(config.getString("jdbc.insert.query.clientes.clientes"));
			options.setConsultaInsertClientesDireccion(config.getString("jdbc.insert.query.clientes.cliente.direcciones"));
			options.setConsultaInsertClientesEmail(config.getString("jdbc.insert.query.clientes.cliente.emails.contacto"));
			options.setConsultaInsertClientesTelefono(config.getString("jdbc.insert.query.clientes.cliente.telefonos.contacto"));
			options.setConsultaInsertClientesFormaPago(config.getString("jdbc.insert.query.clientes.cliente.formas.pago"));
			options.setConsultaInsertClienteMerge(config.getString("jdbc.insert.query.clientes.cliente.merge"));
			options.setConsultaInsertClienteAtg(config.getString("jdbc.insert.query.clientes.cliente.atg"));
			options.setConsultaInsertClienteId(config.getString("jdbc.insert.query.clientes.cliente.ids"));
			options.setConsultaInsertClienteDestinatario(config.getString("jdbc.insert.query.clientes.clientes.destinatario"));
			options.setConsultaDataClienteByIdPadreDestinatario(config.getString("jdbc.insert.query.clientes.clientes.data_cliente_by_id_cliente_padre_del_destinatario"));

			options.setNombreTopicoClienteInsertado(config.getString("gcp.pubsub.topic.registra.cliente.insertado"));
			options.setNombreSuscripcionClienteInsertado(config.getString("gcp.pubsub.topic.subscription.registra.cliente.insertado"));
			options.setNombreTopicoClienteHash(config.getString("gcp.pubsub.topic.subscription.cliente.hash"));
			String networkTags = config.getString("gcp.df.networkTags");

			if (!(networkTags == null || networkTags.isEmpty())) {
				List<String> tagNetworkCloudSql = Arrays.asList(networkTags);
				options.setExperiments(tagNetworkCloudSql);

			}

			options.setConnectionPoolMaxSize(Integer.valueOf(config.getString("connection.pool.max.size")));
			options.setConnectionPoolMinIdle(Integer.valueOf(config.getString("connection.pool.min.idle")));
			options.setConnectionPoolTimeout(Integer.valueOf(config.getString("connection.pool.timeout")));
			options.setConnectionPoolIdleTimeout(Integer.valueOf(config.getString("connection.pool.idle.timeout")));
			options.setConnectionPoolMaxLifetime(Integer.valueOf(config.getString("connection.pool.max.lifetime")));
		}
		catch(ConfigurationException cex)
		{
			LOG.error("Error al cargar configuraciones iniciales:", cex);
			cex.printStackTrace();
		} catch(Exception ex) {
			LOG.error("Error al cargar configuraciones iniciales:", ex);
		}

		return options;
	}


	public String getSecretValueSM(String projectId, String secretId, String versionId) throws Exception {


		try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
			SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, versionId);

			AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);

			return response.getPayload().getData().toStringUtf8();

		}
	}
}

