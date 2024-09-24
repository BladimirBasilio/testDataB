Stream pipelines
## How to set up Registra Clientes V1 dataflow pipeline in GCP environment

### Prerequisites:

1. To have a GCP cloud account.
2. To have a CloudSQL postgresql database with CDP data model v2.4.A Fix123+ installed.
3. To have a valid database user and password with insert permissions.
4. To have maven 3.0.4+ installed(If executed from GCP cloud shell it is not needed to install it).
5. To have java jdk 1.8.0_231+ installed(If executed from GCP cloud shell it is not needed to install it).
6. To create a service account with the right permissions for the resources involved in the dataflow pipeline, such as: cloudsql, pubsub, gcs, secrets manager and dataflow itself.
7. To have Google Cloud SDK shell installed.
8. Existing GCS directories for temp and staging files of dataflow pipeline

Download code from: https://github.com/sistemasOmnicanalLiverpool/CDP_cdp_bigdata/tree/develop/dataflow/clientes

### Set up GCP dependencies

#### Service Account for pipeline execution
An account service is needed in order to execute Registra Pedidos V1 dataflow pipeline. For executing successfully the dataflow pipeline the account service must have at least the next roles:
- Cloud SQL Client
- Dataflow Admin
- Cloud Dataflow Service Agent
- Dataflow Worker
- Pub/Sub Subscriber
- Storage Object Admin
- roles/secretmanager.viewer


For development purposes, rather than adding the previous roles, it is just needed to add the role "owner" to the service account.


#### CloudSQL postgreSQL
The CloudSQL postgreSQL database must have a database with the data structures of CDP V2.4A Fix123+ and a user with write permissions.
There is a dependency with functions that need to be applied located in directory ddls located within this project.


#### Pub/Sub topic

The dataflow pipeline listen to messages published in a Pub/Sub topic. The successful execution of this pipeline relies in a creation of a pub sub topic and subscription.

#### GCS buckets for pipeline deploy

The creation and deploy of the pipeline within GCP needs one bucket and two folders. One bucket will be used for temp files and other for staging files.

#### Secrets manager

At runtime execution some options are retrieved from Secrets Manager such as:

- database-user
- database-password
- database-name
- instance-name


### Set up java project
Modify pom.xml project file for running in GCP environment following the next steps:

1. Go to build -> resources section of POM file and change the name of the file needed for GCP environment. The file name for running the pipeline in GCP environment is "application.properties" (or properties.template), the change should look like as follows :

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

2. Go to build -> plugins -> plugin -> configuration -> excludes  section of POM file and change the name of the files that need to be excluded for GCP environment. The name of the files should look like as follows:

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
    	 		GCP -> exclude RegistroBoletaLPipeline.java, RegistroBoletaLOptions.java
    	 		Local -> exclude RegistroBoletaPipeline.java, RegistroBoletaOptions.java							
    	  		L Local , without L GCP. -->
          <excludes>
               <exclude>**/com/liverpool/stream/ingestion/fundacional/clientes/dataflow/RegistraClienteLPipeline.java</exclude>
               <exclude>**/com/liverpool/stream/ingestion/fundacional/clientes/dataflow/option/RegistraClienteLOptions.java</exclude>
               <exclude>**/com/liverpool/stream/ingestion/fundacional/clientes/dataflow/utils/CargaConfiguracionesLPipeline.java</exclude>
          </excludes>
        </configuration>
      </plugin>
    </plugins>

```


Within clientes directory look for path source/src/main/resources and modify the file application.properties (or properties.template) in the properties that start with prefix "gcp."(except the section #secret manager), in concordance with your GCP environment. Note:

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


Next step is just to execute the pipeline, change to directory clientes within Google Cloud SDK shell and execute the next command line:

```

mvn -P dataflow-runner  compile -e exec:java -Dexec.mainClass=com.liverpool.stream.ingestion.fundacional.clientes.dataflow.RegistraClientePipeline -Dexec.cleanupDaemonThreads=false

```


### Ingest json data with pubsub

The input data needed for executing and testing the dataflow pipeline is as follows:

- ATG

```

{
	"cliente": {
		"id_cliente": 0,
		"primer_nombre": "NICOLASX",
		"segundo_nombre": "",
		"apellido_paterno": "CAUICHX",
		"apellido_materno": "SANTANAX",
		"fecha_de_nacimiento": "1991-02-02",
		"genero": "M",
		"rfc": "NICS19950202",
		"fecha_de_registro": "2020-02-23",
		"telefono_de_contacto": [
			{
				"id_cliente_telefonos_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"telefono": "5512345678",
				"alias_telefono": "casa"
			},
			{
				"id_cliente_telefonos_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"telefono": "5509876543",
				"alias_telefono": "celular"
			},
			{
				"id_cliente_telefonos_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"telefono": "5578904321",
				"alias_telefono": "trabajo"
			},
			{
				"id_cliente_telefonos_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"telefono": "5598763456",
				"alias_telefono": "adicional"
			}
		],
		"email_de_contacto": [
			{
				"id_cliente_emails_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"tipo": "cuenta",
				"email": "COMUNICANICOLAS@HOTMAIL.COM"
			},
			{
				"id_cliente_emails_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"tipo": "notificacion",
				"email": "COMUNICANICOLAS@HOTMAIL.COM"
			}
		],
		"direccion": {
			"id_direccion_del_cliente": 0,
			"id_cliente": 0,
			"alias_de_direccion": "casa",
			"edificio": "X5",
			"id_colonia": 1,
			"id_municipio": 1,
			"id_estado_del_pais": 1,
			"calle": "cerrada 221",
			"numero_interior": "221",
			"codigo_postal": "97130",
			"numero_exterior": "22",
			"id_tipo_direccion_cliente": 1,
			"baja_domicilio": "NO"
		},
		"cliente_formas_de_pago": {
			"id_cliente_tarjeta": 0,
			"tipo_tarjeta": "EXT",
			"esta_activa": 1,
			"hash1_tarjeta": "RRDD-ERRR-SDSA",
			"id_cliente": 0,
			"id_banco_emisor_tarjeta": 1
		}
	},
	"cliente_atg": {
		"id_cliente_atg": 0,
		"id_atg": "01ATG",
		"sistema_fuente_atg": "ATG",
		"id_cliente": 0
	},
	"cliente_merge": {},
	"clienteDestinatario": {
		"id_cliente": 0,
		"primer_nombre": "NICOLASXX",
		"segundo_nombre": "",
		"apellido_paterno": "CAUICHXX",
		"apellido_materno": "SANTANAXX"
		},
	"cliente_id": {}
}

```


- MDM P&S

```

{
	"cliente": {
		"id_cliente": 0,
		"primer_nombre": "NICOLAS",
		"segundo_nombre": "",
		"apellido_paterno": "CAUICH",
		"apellido_materno": "SANTANA",
		"fecha_de_nacimiento": "1995-02-02",
		"genero": "M",
		"rfc": "NCS950202",
		"telefono_de_contacto": [
			{
				"id_cliente_telefonos_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"telefono": "5552683000",
				"alias_telefono": "default",
				"extension_telefonica": "30180"
			}
		],
		"email_de_contacto": [
			{
				"id_cliente_emails_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"tipo": "default",
				"email": "COMUNICANICOLAS@HOTMAIL.COM"
			}
		],
		"direccion": {
			"id_direccion_del_cliente": 0,
			"id_cliente": 0,
			"entre_calle_2": "calle 3",
			"alias_de_direccion": "casa",
			"id_colonia": 1,
			"id_municipio": 0,
			"id_estado_del_pais": 0,
			"calle": "cerrada 221",
			"numero_interior": "221",
			"entre_calle_1": "calle 5",
			"codigo_postal": "97130",
			"numero_exterior": "22",
			"id_tipo_direccion_cliente": 0,
			"id_pais": 0,
			"id_catalogo_catalogos": 1
		},
		"cliente_formas_de_pago": {
			"id_cliente_tarjeta": 0,
			"numero_tarjeta_cliente": "4577-4123-4478",
			"tipo_tarjeta": "liver",
			"id_cliente": 0,
			"id_banco_emisor_tarjeta": 1
		}
	},
	"cliente_atg": {},
	"cliente_merge": {},
	"clienteDestinatario": {},
	"cliente_id": {
		"id_cliente_ids": 0,
		"id_origen": "01A",
		"id_cliente": 0,
		"id_sistema_origen": 1
	}
}

```


- MDM Area gris

```

{
	"cliente": {
		"id_cliente": 0,
		"primer_nombre": "NICOLASX",
		"segundo_nombre": "",
		"apellido_paterno": "CAUICHX",
		"apellido_materno": "SANTANAX",
		"fecha_de_nacimiento": "1991-02-02",
		"genero": "M",
		"rfc": "NICS19950202",
		"id_transaccion": "ABC123",
		"id_operacion": "XXX",
		"salida_primer_nombre": "NICOLASY",
		"salida_segundo_nombre": "",
		"bandera_del_nombre": "XXX",
		"salida_genero": "M",
		"salida_fecha_de_nacimiento": "1991-02-03",
		"salida_rfc": "NICS19950203Y",
		"bandera_rfc": "XXX",
		"bandera_ucm": "XXX",
		"nombre_de_token": "XXX",
		"fecha_de_la_operacion": "2021-02-26",
		"salida_apellido_paterno": "CAUICHY",
		"salida_bandera_fecha": "2021-02-26",
		"salida_token_primer_nombre": "ABC",
		"salida_token_apellido_paterno": "DEF",
		"salida_token_segundo_nombre": "JKL",
		"salida_bandera_rfc": "MNO",
		"telefono_de_contacto": [
			{
				"id_cliente_telefonos_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"telefono": "5512345678",
				"alias_telefono": "casa",
				"id_transaccion": "ABC123",
				"id_operacion": "XXX",
				"lada": "+1",
				"extension_telefonica": "30180",
				"codigo_zip": "05148",
				"id_provincia": 0,
				"id_ciudad": 0,
				"salida_numero_de_telefono": "x1",
				"salida_tipo_de_telefono": "x2",
				"bandera_telefono": "Y",
				"salida_extension_telefonica": "x5",
				"fecha_de_operacion": "2020-02-26"
			}
		],
		"email_de_contacto": [
			{
				"id_cliente_emails_de_contacto": 0,
				"id_cliente": 0,
				"id_sistema_origen": 1,
				"tipo": "default",
				"email": "COMUNICANICOLAS@HOTMAIL.COM",
				"id_transaccion": "ABC123",
				"id_operacion": "XXX",
				"salida_correo_electronico": "x1",
				"salida_tipo": "x2",
				"bandera_tipo": "N",
				"token_correo_electronico": "X5",
				"fecha_operacion": "2020-02-26"
			}
		],
		"direccion": {
			"id_direccion_del_cliente": 0,
			"id_cliente": 0,
			"id_estado_del_pais": 1,
			"calle": "cerrada 221",
			"numero_exterior": "22",
			"id_sistema_origen": 1,
			"id_transaccion": "ABC123",
			"id_operacion": "XXX",
			"direccion_3": "dir 1",
			"direccion_4": "dir 4",
			"direccion_2": "dir 2",
			"direccion_5": "dir 5",
			"tipo_de_direccion": "x",
			"codigo_zip": "04589",
			"id_distrito": 1,
			"id_provincia": 1,
			"id_condado": 1,
			"salida_numero_exterior": "x5",
			"salida_calle": "calle x",
			"salida_direccion_3": "x3",
			"salida_direccion_4": "x4",
			"salida_direccion_2": "x2",
			"salida_direccion_5": "x5",
			"salida_tipo": "078",
			"salida_codigo_zip": "7454",
			"salida_id_distrito": 1,
			"salida_id_provincia": 1,
			"salida_id_estado_del_pais": 1,
			"salida_id_condado": 1,
			"bandera_calle": "xxxx",
			"bandera_emcccp": "xxxx",
			"bandera_termino": "xxxx",
			"token_calle": "ABC",
			"fecha_de_operacion": "2021-02-26"
		},
		"cliente_formas_de_pago": {}
	},
	"cliente_atg": {},
	"cliente_merge": {},
	"clienteDestinatario": {},
	"cliente_id": {
		"id_cliente_ids": 0,
		"id_origen": "01G",
		"id_cliente": 0,
		"id_sistema_origen": 1
	}
}

```
apuntando a rama production