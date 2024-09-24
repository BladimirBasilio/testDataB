import logging
import os
import utils.utils as utils
import utils.cdap_utils as cdap_utils
from google.cloud import secretmanager
from google.api_core.exceptions import NotFound, PermissionDenied


def gcs_to_data_fusion_file_trigger(event, context):
    """Triggered from a file on a GCS bucket.
    Args:
         event (dict): Event payload.
         context (google.cloud.functions.Context): Metadata for the event.
    """
    filename = event['name']
    print(f"CF triggered for filename {filename}")

    input_uri = 'gs://' + event['bucket'] + '/' + event['name']
    folder = utils.get_folder(event['name'])

    size = event['size']
    if int(size) == 0 and filename.endswith("/"):
        print(f"{filename} does not correspond to a file, but to a folder. Skipping")
        pass

    # First, check if it's an error file, and discard it
    if folder != '' and folder == 'errores':
        print('Ignoring error file: ' + input_uri)
        return

    #get environment variables
    error_message = 'Specified environment variable is not set.'

    # Values specific to the project, location and name of the Data Fusion instance
    df_project = os.environ.get('DF_PROJECT', error_message)
    df_location = os.environ.get('DF_LOCATION', error_message)
    location = os.environ.get('LOCATION', error_message) 

    df_instance_name = os.environ.get('DF_INSTANCE_NAME', error_message)

    # Namespace on the Data Fusion instance where the pipeline is deployed
    df_namespace = os.environ.get('DF_NAMESPACE', error_message)
    
    # URI of a bucket where the errors will be saved, in CSV format
    error_uri = event['bucket'] + '/' + folder + '/errores'

    # Names of the secrets that hold CloudSQL URL and credentials
    db_url_secret_name = os.environ.get('DB_URL_SECRET_NAME', error_message)
    db_user_secret_name = os.environ.get('DB_USER_SECRET_NAME', error_message)
    db_password_secret_name = os.environ.get('DB_PASSWORD_SECRET_NAME', error_message)

    # URL of the Cloud SQL Database connection
    project_id = os.environ.get('PROJECT_ID', error_message)
    db_url = access_secret_version_from_secret_manager(db_url_secret_name, project_id)
    db_username = access_secret_version_from_secret_manager(db_user_secret_name, project_id)
    db_password = access_secret_version_from_secret_manager(db_password_secret_name, project_id)
    
    # The pipeline name is formed by prefixing the folder name with the target_df_prefix prefix

    target_df_prefix = os.environ.get('DF_PREFIX', error_message)

    df_pipeline_name = target_df_prefix+folder
    print('Pipeline: ' + df_pipeline_name)

    if(folder != ''):
        # This variables are passed to the pipeline upon execution
        macro_variables = {'input_uri': input_uri, 'error_uri': error_uri, 'db_url': db_url,
            'db_username': db_username, 'db_password': db_password}
        authtoken = utils.get_access_token()

        if(cdap_utils.check_instance(authtoken, df_project, location, df_instance_name)):
            cdap_utils.start_pipeline(authtoken, df_project, df_location, df_instance_name, df_namespace, df_pipeline_name, macro_variables)


def access_secret_version_from_secret_manager(secret_id, secrets_project_id, version_id="latest"):
    # Create the Secret Manager client.
    client = secretmanager.SecretManagerServiceClient()

    # Build the resource name of the secret version.
    name = f"projects/{secrets_project_id}/secrets/{secret_id}/versions/{version_id}"

    print(f"Attempting to read {secret_id} from secret manager")

    try:
        # Access the secret version (needs Secret Manager Secret Accessor role)
        response = client.access_secret_version(name=name)

        print(f"{secret_id} successfully obtained from secret manager")

        # Return the decoded payload.
        return response.payload.data.decode('UTF-8')
    except NotFound as nf:
        print(f"Secret {secret_id} not found or destroyed. Review and update your secrets.")
        return None
    except PermissionDenied as pd:
        print(f"403 Forbidden: no required permissions to access secret {secret_id}. Check IAM roles assigned to this SA.")
        return None
    except Exception as e:
        print(
            f"Exception when accessing secret {secret_id}")
        return None