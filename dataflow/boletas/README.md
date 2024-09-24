# Registra Boletas V1 - Retry


## Indexhttps://github.com/sistemasOmnicanalLiverpool/CDP_cdp_bigdata/tree/develop/dataflow/boletas/source

1. [How to set up Registro Boletas V1 with apache beam pipeline in local environment](#1-local)

2. [How to set up Registro Boletas V1 dataflow pipeline in GCP environment](#2-gcp)

<a name="1-local"></a>
## 1. How to set up Registro Boletas V1 with apache beam pipeline in local environment

### Prerequisites:

1. To have a postgresql database with CDP data model v1.9+ installed.
2. To have a valid database user and password with insert permissions.
3. To have maven 3.0.4+ installed.
4. To have java jdk 1.8.0_231+ installed.

### Set up java project
Download code from: https://github.com/sistemasOmnicanalLiverpool/CDP_cdp_bigdata/tree/develop/dataflow/boletas


Once the project is downloaded change to directory boletas/source using command line interface. Here your directory should look like the one in the screenshot:

![folders](images/directory-project.png)

Modify pom.xml project file for running the project in local environment following the next steps:

1. Go to build -> resources section of POM file and change the name of the file needed for local environment. The file name for running the pipeline in local environment is "application1.properties", the change should look like the next screenshot:

![folders](images/conf-file-prop-loc-project.png)

2. Go to build -> plugins -> plugin -> configuration -> excludes section of POM file and change the name of the files that need to be excluded for local environment. The excluded files should be named as "RegistroBoletaPipeline.java" and RegistroBoletaOptions.java respectively. The name of the files should look like the next screenshot:

![folders](images/conf-file-prop-exc-project.png)


Within boletas/source directory look for path src/main/resources and modify the file application1.properties in the next properties, in concordance with your local environment.

```

jdbc.url=jdbc:postgresql://[host]:[port]/[cdp-database]
jdbc.user=[dbuser]
jdbc.password=[password]

```

### Execute apache beam pipeline with Maven
Everything is set up for executing our apache beam Registro Boletas V1 pipeline in local environment, next step is just to execute the pipeline with the next command line(the command line should be executed within boletas/source directory):

```

mvn -P direct-runner  compile -e exec:java -Dexec.mainClass=com.liverpool.stream.ingestion.fundacional.dataflow.RegistroBoletaLPipeline

```

The output of the execution should look like the next screenshot:

![folders](images/exec-localpipeline-project.png)


Now we can take a look at the cdp database and query some of the tables involved in the process. The result should look like the next screenshot:

![folders](images/exec-localpipeline-db-project.png)


The input data that the pipeline processed was the next json example(the example needs to be updated with a valid catalog data):

```

{
	"boleta_cabeceras": {
		"id_terminal_pos": "001",
		"numero_boleta": "1000",
		"fecha_fin_transaccion": "2020-03-26",
		"hora_fin_transaccion": "11:04:01",
		"id_tienda_origen": "INSUR",
		"id_tienda_reconocimiento": "INSUR",
		"id_tipo_transaccion": 1,
		"cuenta_empleado": 12356,
		"codigo_facturacion": "SHCP01",
		"referencia_folio_agencia_de_viajes": "AGFTYU",
		"numero_evento": "ABCD",
		"numero_boleta_cancelacion": "TYOUG7",
		"monto_boleta": 0.0,
		"total_cancelacion": 0.0,
		"numero_paqueteria": "1223365",
		"leyenda_facturacion": "SIN",
		"codigo_de_barras": "UVXYZ",
		"fecha_nacimiento_garantia_extendida": "2020-01-01",
		"fecha_garantia_extemporanea": "2020-01-01",
		"numero_indicador_marketplace": "123999",
		"leyenda_tentativa": "NOLEY",
		"clave_homologada": "UVWPQR",
		"id_atg": "100",
		"id_mdm": "100"
	},
	"boleta_detalles_sku": [
		{
			"id_linea_detalle": 1,
			"id_terminal_pos_boleta_cabeceras": "001",
			"numero_boleta_boleta_cabeceras": "1000",
			"fecha_fin_transaccion_boleta_cabeceras": "2020-03-26",
			"hora_fin_transaccion_boleta_cabeceras": "11:04:01",
			"id_tienda_origen_boleta_cabeceras": "INSUR",
			"id_tienda_reconocimiento_boleta_cabeceras": "INSUR",
			"id_sku": 1,
			"id_pep_r3": "ABCD",
			"telefono_cliente_garantia_extendida": "5556668899",
			"numero_orden_optica": "ABCD",
			"orden_de_reparacion": "EFGH",
			"numero_telefono_o_folio": "56897412",
			"numero_comanda_restaurant": "REST",
			"numero_poliza": "POL001",
			"cantidad_articulos": 1,
			"precio_subtotal_neto": 0.0,
			"id_departamento": 1,
			"precio_unitario_del_sku": 0.0,
			"fisico_o_virtual": "F",
			"es_marca_liverpool": "S",
			"boleta_detalles_sku_descuentos": [
				{
					"id_linea_detalle_boleta_detalles_sku": 1,
					"id_terminal_pos_boleta_detalles_sku": "001",
					"numero_boleta_boleta_detalles_sku": "1000",
					"fecha_fin_transaccion_boleta_detalles_sku": "2020-03-26",
					"hora_fin_transaccion_boleta_detalles_sku": "11:04:01",
					"id_tienda_origen_boleta_detalles_sku": "INSUR",
					"id_tienda_reconocimiento_boleta_detalles_sku": "INSUR",
					"id_descuento": 1,
					"total_descuento_sku": 0.0,
					"porcentaje_descuento_sku": 0.0
				}
			]
		}
	],
	"boleta_detalle_pagos": [
		{
			"id_linea_detalle_pagos": 1,
			"id_terminal_pos_boleta_cabeceras": "001",
			"numero_boleta_boleta_cabeceras": "1000",
			"fecha_fin_transaccion_boleta_cabeceras": "2020-03-26",
			"hora_fin_transaccion_boleta_cabeceras": "11:04:01",
			"id_tienda_origen_boleta_cabeceras": "INSUR",
			"id_tienda_reconocimiento_boleta_cabeceras": "INSUR",
			"id_medios_de_pago": 10,
			"monto_monedero": 0.0,
			"numero_autorizacion": "AGDFT",
			"numero_orden_soms": "TRDFHK",
			"total_pagado_otromedio_o_cambio_en_efectivo": 0.0,
			"plan_de_credito": "24MESES",
			"total_pagado_efectivo": 0.0,
			"hash_tarjeta_externa": "4587-1020",
			"numero_monedero": "LIV-001",
			"monto_redimido_monedero": 0.0,
			"saldo_anterior_monedero": 0.0,
			"monto_obtenido_monedero": 0.0,
			"saldo_actual_monedero": 0.0,
			"numero_de_cupon": "1-A"
		}
	],
	"boleta_detalle_abonos_segmento": [
		{
			"id_linea_detalle_abonos_segmento": 1,
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"id_segmento": 1,
			"importe_abonado_al_segmento": 0.0
		}
	],
	"boleta_detalles_monedero": [
		{
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"numero_monedero": "MON-01",
			"saldo_anterior_monedero": 0.0,
			"monto_monedero": 0.0,
			"monto_obtenido_monedero": 0.0,
			"saldo_actual_monedero": 0.0
		}
	],
	"boleta_detalle_cupones": [
		{
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"codigo_cupon": "CUP-01",
			"es_redimido": "R"
		}
	],
	"boleta_detalle_tarjetas_prepago": [
		{
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"codigo_upc": "UPC-01",
			"importe_tarjeta_prepago": 0.0
		}
	]
}

```
This json example contains the needed data to create a record within cdp Boleta tables.


<a name="2-gcp"></a>
## 2. How to set up Registro Boletas V1 dataflow pipeline in GCP environment

### Prerequisites:

1. To have a GCP cloud account.
2. To have a CloudSQL postgresql database with CDP data model v1.9+ installed.
3. To have a valid database user and password with insert permissions.
4. To have maven 3.0.4+ installed(If executed from GCP cloud shell it is not needed to install it).
5. To have java jdk 1.8.0_231+ installed(If executed from GCP cloud shell it is not needed to install it).
6. To create a service account with the right permissions for the resources involved in the dataflow pipeline, such as: cloudsql, pubsub, gcs and dataflow itself.
7. To have Google Cloud SDK shell installed.
8. GCS directory for temp and staging files of dataflow pipeline

Download code from: https://github.com/sistemasOmnicanalLiverpool/CDP_cdp_bigdata/tree/develop/dataflow/boletas


### Set up GCP dependencies

#### Service Account for pipeline execution
An account service is needed in order to execute Registro Boleta V1 dataflow pipeline. For executing successfully the dataflow pipeline the account service must have at least the next roles:

![folders](images/stream-pipeline-boletasv1-account-service.png)

For development purposes, rather than adding the previous roles, it is just needed to add the role "owner" to the service account.


#### CloudSQL postgreSQL
The CloudSQL postgreSQL database must have a database with the data structures of CDP V1.9+ and a user with write permissions. In addition, and just for development purposes, the CloudSQL configuration needs to be opened to network 0.0.0.0/0 (this project has not been tested in private ips yet).

![folders](images/stream-pipeline-boletasv1-cloudsql-network.png)

#### Pub/Sub topic

The dataflow pipeline listen to messages published in a Pub/Sub topic. The successful execution of this pipeline relies in a creation of a pub sub topic.

#### GCS buckets for pipeline deploy

The creation and deploy of the pipeline within GCP needs one bucket and two folders. One bucket will be used for temp files and other for staging files.

#### Secrets manager

The creation and deploy of the pipeline within GCP needs the creation of some secrets within secrets manager. In application.properties file within the project contains the secrets to be created.


### Set up java project
Modify pom.xml project file for running in GCP environment following the next steps:

1. Go to build -> resources section of POM file and change the name of the file needed for GCP environment. The file name for running the pipeline in GCP environment is "application.properties", the change should look like as follows :

```

  <build>
      <!-- Include file depending if execution is:
    	 GCP -> application.properties
    	 Local -> application1.properties							
    L Local , without L GCP. -->
    <resources>
      <resource>
        <directory>src/main/resources</directory>
        <includes>
          <include>application.properties</include>
        </includes>
      </resource>
	</resources>

```

2. Go to build -> plugins -> plugin -> configuration -> excludes  section of POM file and change the name of the files that need to be excluded for GCP environment. The excluded files should be named as "RegistroBoletaLPipeline.java" and RegistroBoletaLOptions.java respectively. The name of the files should look like as follows:

```

   <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>${maven-compiler-plugin.version}</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
           <!-- Exclude files depending if execution is:
    	 		GCP -> exclude RegistraPedidoLPipeline.java, RegistraPedidoLOptions.java
    	 		Local -> exclude RegistraPedidoPipeline.java, RegistraPedidoOptions.java							
    	  		L Local , without L GCP. -->
          <excludes>
               <exclude>**/com/liverpool/stream/ingestion/fundacional/boletas/dataflow/RegistraBoletaLPipeline.java</exclude>
               <exclude>**/com/liverpool/stream/ingestion/fundacional/boletas/dataflow/option/RegistraBoletaLOptions.java</exclude>
               <exclude>**/com/liverpool/stream/ingestion/fundacional/boletas/dataflow/utils/CargaConfiguracionesLPipeline.java</exclude>
          </excludes>
        </configuration>
      </plugin>
    </plugins>

```


Within fundacional directory look for path src/main/resources and modify the file application.properties (or properties.template) in the next properties, in concordance with your GCP environment.

```

#GCP properties
gcp.projectId=[projectid]
.........

gcp.df.service.account=[serviceaccountname@project.iam.gserviceaccount.com]
gcp.df.region=[region]
...............

# Pub/Sub properties
gcp.pubsub.topic.registro.boletas=[path-topic-name]
..................

# GCS properties
gcs.urlBase=gs://
gcs.bucketName=[bucket-name]
gcs.tempLocation=[temp-directory-name-within-bucket-name]
gcs.stagingLocation=[staging-directory-name-within-bucket-name]
......


#secret manager
gcp.sm.jdbc.user=database-user
gcp.sm.jdbc.password=database-password
gcp.sm.jdbc.database=database-name
gcp.sm.jdbc.instance.name=instance-name
gcp.sm.jdbc.user.version=latest
gcp.sm.jdbc.password.version=latest
gcp.sm.jdbc.database.version=latest
gcp.sm.jdbc.instance.name.version=latest
.....

```


### Execute GCP dataflow pipeline with Maven


Initialize Google Cloud SDK shell with the next command:

```

> gcloud init

```


Within Google Cloud SDK shell set the environment variable GOOGLE_APPLICATION_CREDENTIALS which is needed for authenticating and executing the dataflow pipeline pointing to the file downloaded from the creation of account service:

```

> set GOOGLE_APPLICATION_CREDENTIALS=D:\location\of\account\service\file\json\fileb53d3d594.json

```


Next step is just to execute the pipeline, change to directory boletas/source within Google Cloud SDK shell and execute the next command line:

```

mvn -P dataflow-runner  compile -e exec:java -Dexec.mainClass=com.liverpool.stream.ingestion.fundacional.boletas.dataflow.RegistraBoletaPipeline -Dexec.cleanupDaemonThreads=false

```

The output of the execution should look like the next screenshot:

![folders](images/stream-pipeline-boletasv1-gcp-pipeline-output.png)

If everything was set up accordingly our dataflow pipeline should be up and running and should look like the next screenshot

![folders](images/stream-pipeline-boletas-v1.png)


### Ingest json data with pubsub

The input data needed for executing and testing the dataflow pipeline is as follows:
```

{
	"boleta_cabeceras": {
		"id_terminal_pos": "001",
		"numero_boleta": "1000",
		"fecha_fin_transaccion": "2020-03-26",
		"hora_fin_transaccion": "11:04:01",
		"id_tienda_origen": "INSUR",
		"id_tienda_reconocimiento": "INSUR",
		"id_tipo_transaccion": 1,
		"id_canal_de_venta": 1,
		"id_venta_catalogo_extendido_y_otro": 1,
		"id_vendedor": 1,
		"id_tienda_original": 1,
		"id_tipo_de_evento": 1,
		"id_cliente": 1,
		"id_terminal_pos_cancelacion": 1,
		"cuenta_empleado": 12356,
		"codigo_facturacion": "SHCP01",
		"referencia_folio_agencia_de_viajes": "AGFTYU",
		"numero_evento": "ABCD",
		"numero_boleta_cancelacion": "TYOUG7",
		"monto_boleta": 0.0,
		"total_cancelacion": 0.0,
		"id_numero_centro_de_servicio": 1,
		"numero_paqueteria": "1223365",
		"leyenda_facturacion": "SIN",
		"codigo_de_barras": "UVXYZ",
		"fecha_nacimiento_garantia_extendida": "2020-01-01",
		"fecha_garantia_extemporanea": "2020-01-01",
		"numero_indicador_marketplace": "123999",
		"leyenda_tentativa": "NOLEY",
		"id_tipo_de_descuento_al_total": 1,
		"clave_homologada": "UVWPQR",
		"id_direccion_fiscal": 1,
		"id_atg": "100",
		"id_mdm": "100",
		"id_tienda_origen_gcp": 100,
		"id_terminal_pos_gcp": 100,
		"id_vendedor_original": 1
	},
	"boleta_detalles_sku": [
		{
			"id_linea_detalle": 1,
			"id_terminal_pos_boleta_cabeceras": "001",
			"numero_boleta_boleta_cabeceras": "1000",
			"fecha_fin_transaccion_boleta_cabeceras": "2020-03-26",
			"hora_fin_transaccion_boleta_cabeceras": "11:04:01",
			"id_tienda_origen_boleta_cabeceras": "INSUR",
			"id_tienda_reconocimiento_boleta_cabeceras": "INSUR",
			"id_sku": 1,
			"id_pep_r3": "ABCD",
			"telefono_cliente_garantia_extendida": "5556668899",
			"numero_orden_optica": "ABCD",
			"orden_de_reparacion": "EFGH",
			"numero_telefono_o_folio": "56897412",
			"numero_comanda_restaurant": "REST",
			"numero_poliza": "POL001",
			"cantidad_articulos": 1,
			"precio_subtotal_neto": 0.0,
			"id_motivo_devolucion": 1,
			"id_departamento": 1,
			"precio_unitario_del_sku": 0.0,
			"fisico_o_virtual": "F",
			"es_marca_liverpool": "S",
			"boleta_detalles_sku_descuentos": [
				{
					"id_linea_detalle_boleta_detalles_sku": 1,
					"id_terminal_pos_boleta_detalles_sku": "001",
					"numero_boleta_boleta_detalles_sku": "1000",
					"fecha_fin_transaccion_boleta_detalles_sku": "2020-03-26",
					"hora_fin_transaccion_boleta_detalles_sku": "11:04:01",
					"id_tienda_origen_boleta_detalles_sku": "INSUR",
					"id_tienda_reconocimiento_boleta_detalles_sku": "INSUR",
					"id_descuento": 1,
					"total_descuento_sku": 0.0,
					"porcentaje_descuento_sku": 0.0
				}
			]
		}
	],
	"boleta_detalle_pagos": [
		{
			"id_linea_detalle_pagos": 1,
			"id_terminal_pos_boleta_cabeceras": "001",
			"numero_boleta_boleta_cabeceras": "1000",
			"fecha_fin_transaccion_boleta_cabeceras": "2020-03-26",
			"hora_fin_transaccion_boleta_cabeceras": "11:04:01",
			"id_tienda_origen_boleta_cabeceras": "INSUR",
			"id_tienda_reconocimiento_boleta_cabeceras": "INSUR",
			"id_medios_de_pago": 1,
			"monto_monedero": 0.0,
			"id_cliente_tarjeta": 1,
			"numero_autorizacion": "AGDFT",
			"numero_orden_soms": "TRDFHK",
			"total_pagado_otromedio_o_cambio_en_efectivo": 0.0,
			"plan_de_credito": "24MESES",
			"total_pagado_efectivo": 0.0,
			"hash_tarjeta_externa": "4587-1020",
			"numero_monedero": "LIV-001",
			"monto_redimido_monedero": 0.0,
			"saldo_anterior_monedero": 0.0,
			"monto_obtenido_monedero": 0.0,
			"saldo_actual_monedero": 0.0,
			"numero_de_cupon": "1-A",
			"id_modo_ingreso": 1
		}
	],
	"boleta_detalle_abonos_segmento": [
		{
			"id_linea_detalle_abonos_segmento": 1,
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"id_segmento": 1,
			"importe_abonado_al_segmento": 0.0
		}
	],
	"boleta_detalles_monedero": [
		{
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"numero_monedero": "MON-01",
			"saldo_anterior_monedero": 0.0,
			"monto_monedero": 0.0,
			"monto_obtenido_monedero": 0.0,
			"saldo_actual_monedero": 0.0
		}
	],
	"boleta_detalle_cupones": [
		{
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"codigo_cupon": "CUP-01",
			"es_redimido": "R"
		}
	],
	"boleta_detalle_tarjetas_prepago": [
		{
			"id_terminal_pos": "001",
			"numero_boleta": "1000",
			"fecha_fin_transaccion": "2020-03-26",
			"hora_fin_transaccion": "11:04:01",
			"id_tienda_origen": "INSUR",
			"id_tienda_reconocimiento": "INSUR",
			"codigo_upc": "UPC-01",
			"importe_tarjeta_prepago": 0.0
		}
	]
}

```

This json example contains the full data that target cdp tables need to create a record within cdp Boleta tables.

Go to the previously created topic for the project and within topics click on +PUBLISH MESSAGE so that we can ingest the previous input json and publish the message to our topic. The next screenshot illustrates how to do it.

![folders](images/stream-pipeline-boletasv1-pubsub.png)
Finally click on publish button to publish our message within pub sub.

Now we can take a look at the cdp database and query some of the tables involved in the process. The result should look like the next screenshot:

![folders](images/exec-localpipeline-db-project.png)

apuntando a rama production