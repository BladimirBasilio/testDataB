import base64
import logging
import json

from datetime import datetime
from httplib2 import Http

from googleapiclient import discovery
from googleapiclient.errors import HttpError
from oauth2client.client import GoogleCredentials


def main(event, context):
    pubsub_message = json.loads(base64.b64decode(event['data']).decode('utf-8'))
    credentials = GoogleCredentials.get_application_default()

    service = discovery.build('sqladmin', 'v1beta4', http=credentials.authorize(Http()), cache_discovery=False)

    bucket_name = pubsub_message['bucket_name']
    cloudsql_instance = pubsub_message['instance']
    cloudsql_db = pubsub_message['db']
    cloudsql_schema = pubsub_message['schema']
    cloudsql_table = pubsub_message['table']
    project_id = pubsub_message['project']

    datestamp = datetime.now().strftime("%Y/%m/%d")

    destination_uri = "gs://{}/cloudsql/{}/{}/{}/{}-{}.csv".format(bucket_name, datestamp, cloudsql_instance, cloudsql_db, cloudsql_schema, cloudsql_table)
    select_query = "SELECT * FROM {}.{}".format(cloudsql_schema, cloudsql_table)

    instances_export_request_body = {
      "exportContext": {
        "kind": "sql#exportContext",
        "fileType": "CSV",
        "uri": destination_uri,
        "databases": [
          pubsub_message['db']
        ],
        "csvExportOptions": {
            "selectQuery": select_query
        },
      }
    }

    try:
      request = service.instances().export(
            project=project_id,
            instance=cloudsql_instance,
            body=instances_export_request_body
        )
      response = request.execute()
    except HttpError as err:
        logging.error("Could NOT run backup. Reason: {}".format(err))
    else:
      logging.info("Backup task status: {}".format(response))