import base64
import logging
import json

from datetime import datetime
from httplib2 import Http

from google.cloud import bigquery
from googleapiclient import discovery
from googleapiclient.errors import HttpError
from oauth2client.client import GoogleCredentials


def main(event, context):
    pubsub_message = json.loads(base64.b64decode(event['data']).decode('utf-8'))

    datestamp = datetime.now().strftime("%Y/%m/%d")

    project = pubsub_message['project']
    dataset_id = pubsub_message['dataset_id']
    table_id = pubsub_message['table_id'].upper()
    bucket_name = pubsub_message['bucket_name']

    destination_uri = "gs://{}/bigquery/{}/{}/{}/{}.avro".format(bucket_name, datestamp, dataset_id, table_id, table_id)
    dataset_ref = bigquery.DatasetReference(project, dataset_id)
    table_ref = dataset_ref.table(table_id)

    client = bigquery.Client()

    job_config = bigquery.job.ExtractJobConfig()
    job_config.destination_format = bigquery.DestinationFormat.AVRO

    extract_job = client.extract_table(
        table_ref,
        destination_uri,
        job_config=job_config,
        # Location must match that of the source table.
        location="US",
    )  # API request
    extract_job.result()  # Waits for job to complete.

    print(
        "Exported {}:{}.{} to {}".format(project, dataset_id, table_id, destination_uri)
    )