## How to set up Registra Pedidos V1 dataflow pipeline in GCP environment
### Prerequisites:

1. To have a GCP cloud account.
2. To have a CloudSQL postgresql database with CDP data model v2.4.A Fix123+ installed.
3. To have a valid database user and password with insert permissions.
4. To have maven 3.0.4+ installed(If executed from GCP cloud shell it is not needed to install it).
5. To have java jdk 1.8.0_231+ installed(If executed from GCP cloud shell it is not needed to install it).
6. To create a service account with the right permissions for the resources involved in the dataflow pipeline, such as: cloudsql, pubsub, gcs, secrets manager and dataflow itself.
7. To have Google Cloud SDK shell installed.
8. Existing GCS directories for temp and staging files of dataflow pipeline

Download code from: https://github.com/sistemasOmnicanalLiverpool/CDP_cdp_bigdata/tree/develop/dataflow/pedidos

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
		                <exclude>**/com/liverpool/stream/ingestion/fundacional/pedidos/dataflow/RegistraPedidoLPipeline.java</exclude>
		               <exclude>**/com/liverpool/stream/ingestion/fundacional/pedidos/dataflow/option/RegistraPedidoLOptions.java</exclude>
		               <exclude>**/com/liverpool/stream/ingestion/fundacional/pedidos/dataflow/utils/CargaConfiguracionesLPipeline.java</exclude>
          </excludes>
        </configuration>
      </plugin>
    </plugins>

```


Within pedidos directory look for path source/src/main/resources and modify the file application.properties (or properties.template) in the properties that start with prefix "gcp."(except the section #secret manager), in concordance with your GCP environment. Note:

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


Next step is just to execute the pipeline, change to directory fundacional within Google Cloud SDK shell and execute the next command line:

```

mvn -P dataflow-runner  compile -e exec:java -Dexec.mainClass=com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.RegistraPedidoPipeline -Dexec.cleanupDaemonThreads=false

```


### Ingest json data with pubsub

The input data needed for executing and testing the dataflow pipeline is as follows:
```

{
  "pedido": {
    "numero_del_documento": "T-123",
    "estado_orden": "ES",
    "fecha_modificacion": "2021-03-02",
    "hora_modificacion": "12:00:00",
    "estado_renglon_bitacora": "",
    "id_tipo_entrega": 107,
    "id_tipo_de_venta": 109,
    "id_tipo_de_act": 119,
    "id_tipo_de_documento": 60,
    "fecha_de_compra": "2021-03-02",
    "operacion_crud": "INSERT"
  },
  "clienteRemitente": {
    "primer_nombre": "John",
    "segundo_nombre": "Doe",
    "apellido_paterno": "Nobody",
    "apellido_materno": "Noone",
    "telefono_de_contacto": [
      {
        "telefono": "441552",
        "alias_telefono": "fijo",
        "operacion_crud": "INSERT"
      },
      {
        "telefono": "663996",
        "alias_telefono": "oficina",
        "operacion_crud": "INSERT"
      },
      {
        "telefono": "998665332",
        "alias_telefono": "celular",
        "operacion_crud": "INSERT"
      }
    ],
    "email_de_contacto": {
      "email": "johndoe@gmail.com",
      "operacion_crud": "INSERT"
    },
    "operacion_crud": "INSERT"
  },
  "clienteDestinatario": {
    "primer_nombre": "John",
    "segundo_nombre": "Doe",
    "apellido_paterno": "Noone",
    "apellido_materno": "Nobody",
    "telefono_de_contacto": [
      {
        "telefono": "441552",
        "alias_telefono": "fijo",
        "operacion_crud": "INSERT"
      },
      {
        "telefono": "663996",
        "alias_telefono": "oficina",
        "operacion_crud": "INSERT"
      },
      {
        "telefono": "998665332",
        "alias_telefono": "celular",
        "operacion_crud": "INSERT"
      }
    ],
    "email_de_contacto": {
      "email": "johndoe@gmail.com",
      "operacion_crud": "INSERT"
    },
    "direccion": {
      "calle": "Wall Street Of. 302",
      "numero_exterior": "302",
      "numero_interior": "100",
      "codigo_postal": "001",
      "operacion_crud": "INSERT"
    },
    "operacion_crud": "INSERT"
  },
  "pedido_detalle_sku": [
    {
      "numero_del_documento_pedidos": "T-123",
      "estado_orden_pedidos": "ES",
      "fecha_modificacion_pedidos": "2021-03-02",
      "hora_modificacion_pedidos": "12:00:00",
      "fecha_actualizacion_estado": "2021-03-02",
      "piezas": 2,
      "id_linea_detalle": 2,
      "hora_actualizacion_estado": "12:00:00"
    },
    {
      "numero_del_documento_pedidos": "T-123",
      "estado_orden_pedidos": "ES",
      "fecha_modificacion_pedidos": "2021-03-02",
      "hora_modificacion_pedidos": "12:00:00",
      "fecha_actualizacion_estado": "2021-03-02",
      "piezas": 25,
      "id_linea_detalle": 1,
      "hora_actualizacion_estado": "12:00:00"
    }
  ]
}

```

## Activate Pipeline
the pipeline is based on cloudbuild and docker, there is one requirement for running through  this "automated way"
  1) Spinnaker installed and the app for this deployment created
  2) docker images build for the custom maven with gcloud, you might find it on the repo under the folder

The pipeline process is triggered by the commit on any file on this folder, and the main runner for the jobs is a kubernetes pod that will run the image builded based on the maven-custom-sdk this is based on openjdk8 and Maven 3.6.

the entrypoint is a bash file `run_job.sh` that will activate the service account and ensure that if any job is running it will be drain. be aware of the following lines.

This line, will contain the email for the SA that will run the jobs
- Line 6 `DATAFLOW_SA="crp-dev-cdp-dig-pipeline-df@crp-dev-cdp-digital.iam.gserviceaccount.com"`

Since we are using Kubernetes secrets, here should be the path of the volume where the secret will be mounted
- Line 9 `DATAFLOW_SA_KEY_PATH="/var/run/secret/cloud.google.com/crp-dev-cdp-digital-511c76818de9.json"`

In this line will add the name of the job that will run on Dataflow in order to be able to drain it
- Line 16 `JOBNAME="registrapedidosv1dfjob"`
.


## Jobs Clean Up
Since this is a Kubernetes job,
we need to be aware that the clean up should be manually at this time.
More info : https://github.com/spinnaker/spinnaker/issues/5201
https://github.com/spinnaker/spinnaker/issues/5201.

apuntando a rama production