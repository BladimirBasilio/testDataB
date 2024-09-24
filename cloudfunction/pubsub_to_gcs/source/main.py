from google.cloud import storage
import os

error_message = 'Specified environment variable is not set.'


def hello_pubsub(event, context):
    """Background Cloud Function to be triggered by Pub/Sub.
    Args:
         event (dict):  The dictionary with data specific to this type of
         event. The `data` field contains the PubsubMessage message. The
         `attributes` field will contain custom attributes if there are any.
         context (google.cloud.functions.Context): The Cloud Functions event
         metadata. The `event_id` field contains the Pub/Sub message ID. The
         `timestamp` field contains the publish time.
    """
    import base64

    print("""This Function was triggered by messageId {} published at {}""".format(context.event_id, context.timestamp))

    print(f"Incoming msg: {event}")

    if 'data' in event:
        content = base64.b64decode(event['data']).decode('utf-8')

        storage_client = storage.Client()

        conciliation_bucket_name = os.environ.get('CONCILIATION_BUCKET_NAME', error_message)
        bucket = storage_client.get_bucket(conciliation_bucket_name)

        destination_blob_name = f"conciliation_msg_{content.timestamp}"
        blob = bucket.blob(destination_blob_name)
        blob.upload_from_string(content)

        print('File {} uploaded to {}.'.format(
            destination_blob_name,
            conciliation_bucket_name))

    else:
        print('Input json does not have a "data" attribute, therefore nothing is being persisted in GCS')
