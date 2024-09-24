import base64
import logging
import json
import os
from datetime import datetime
from httplib2 import Http

from google.cloud import bigquery
from googleapiclient import discovery
from googleapiclient.errors import HttpError
from oauth2client.client import GoogleCredentials

project = os.environ.get('PROJECT_ID')
write_disposition = os.environ.get('WRITE_DISPOSITION')


def get_table_schema(client_bq, project_id, dataset_name, table_name):
    print(f"Getting table {dataset_name}.{table_name} schema")
    dataset_ref = client_bq.dataset(dataset_name, project=project_id)
    table_ref = dataset_ref.table(table_name)
    table = client_bq.get_table(table_ref)  # API Request
    return table.schema


def get_bq_dataset_from_cloudsql_schema(cloudsql_schema):
    result_dataset = os.getenv(cloudsql_schema)
    print(f"Cloud SQL detected schema: {cloudsql_schema}. BigQuery calculated dataset: {result_dataset}")
    return result_dataset


def main(event, context):
    trigger_bucket = event['bucket']
    filename = event['name']

    print(f"CF triggered for filename {filename}")

    root_dir = filename.split("/")[0]
    if root_dir == "cloudsql":
        # Construct a BigQuery client object.
        client = bigquery.Client()

        head, tail = os.path.split(filename)
        simplefilename = os.path.splitext(tail)[0]
        schema = simplefilename.split("-")[0]
        table = simplefilename.split("-")[1].upper()

        dataset = get_bq_dataset_from_cloudsql_schema(schema)

        if not dataset:
            print(f"No BigQuery dataset specified for cloud sql schema {schema}. Finishing execution")
            return

        full_table_id = "{}.{}.{}".format(project, dataset, table)

        target_schema = get_table_schema(client, project, dataset, table)

        job_config = bigquery.LoadJobConfig(
            schema=target_schema,
            skip_leading_rows=0,
            # The source format defaults to CSV, so the line below is optional.
            source_format=bigquery.SourceFormat.CSV,
            write_disposition=write_disposition
        )
        uri = "gs://{}/{}".format(trigger_bucket, filename)

        load_job = client.load_table_from_uri(
            uri, full_table_id, job_config=job_config
        )  # Make an API request.

        load_job.result()  # Waits for the job to complete.

        destination_table = client.get_table(full_table_id)  # Make an API request.
        print("Loaded {} rows.".format(destination_table.num_rows))
    else:
        print("Not a Cloud SQL backup, skipping")
