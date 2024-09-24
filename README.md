# CDP Big Data

## Index

1. [About](#1-about)

2. [Structure](#2-structure)

3. [Cloud functions](#3-cloud-functions)

4. [Data fusion](#4-data-fusion)

5. [Dataflow](#5-data-flow)

### 1. About

Repositorio relacionado al desarrollo de la solución de Big Data para el proyecto CDP, subproyecto cdp.

### 2. Structure

The repository has the following main folders:

* [cloudfunction](cloudfunction): cloud functions code

* [datafusion](datafusion): data fusion code (pipelines, etc)

* [modeling](modeling): DDLs for PostgreSQL tables

* [tests](src/test): data components tests

* [dataflow](src/test): streaming pipelines


### 3. Cloud functions

#### 3.1 CDAP Batch pipeline trigger gcs finalize

Cloud function que dispara un pipeline batch de Data fusion al detectar la creación de un objeto en GCS.

### 4. Data Fusion

#### 4.1 poc-lvp-2-batch-file1

Pipeline batch, que consume archivo de GCS y sube contenido a Cloud SQL

#### 4.2 Ingestion-PubSub-Pipeline_v1

Pipeline streaming, que consume archivo de PubSub y sube contenido a Cloud SQL


### 5. Dataflow

#### 5.1 Clientes

Streaming pipeline fundacional para reistrar clientes.

#### 5.1 Pedidos

Streaming pipeline fundacional para registrar pedidos.
---
cambio rama para pruebas preproduccion.


compilacion por variable _MYM_SERVICE_TIMEOUTCONNECT

