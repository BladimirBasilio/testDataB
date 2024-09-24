# Compress files for deploying in Cloud Function
This is a quick guideline for compressing files for deploying a zip file in a cloud function.

For compressing a zip file to deploy a cloud function it must be compressed from the place where the source entry function stays, 
for example, in the next picture, it is shown the main.py file this is the place where the compression must be performed.

![folders](../images/compress-files.png)

If a folder is added as a container of the py files, the function will not be deployed appropriately within the cloud function.



# How to deploy a Cloud Function with a zip file using cloud shell

Once the python files are compressed in a zip file it can be deployed in a cloud function using the next cloud shell
statements:

```
export PROJECT_ID=crp-dev-cdp-digital
export LOCATION=us-east4
export DF_INSTANCE_NAME=corp-dev-cdp-datafusion-zkp
export DF_NAMESPACE=default
export DF_LOCATION=use4

export BUCKET_NAME_INPUT=gs://corp-dev-transient-main-v6b
export ERROR_URI=gs://corp-dev-transient-main-v6b/errores

export DB_URL_SECRET_NAME=database-name
export DB_USER_SECRET_NAME=database-user
export DB_PASSWORD_SECRET_NAME=database-password

export CF_SOURCE_URI=gs://corp-dev-raw-backups-a3a/gcs_to_data_fusion_file_trigger.zip
export DB_URL=jdbc:postgresql://google/corp_dev_cdp_clientes_360_1?cloudSqlInstance=crp-dev-cdp-digital:us-east4:corp-dev-cdp-postgres--ko5&socketFactory=com.google.cloud.sql.postgres.SocketFactory&useSSL=false

gcloud functions deploy gcs_to_data_fusion_file_trigger --region=$LOCATION \
  --entry-point=gcs_to_data_fusion_file_trigger --runtime=python37 --source=$CF_SOURCE_URI \
  --trigger-event=google.storage.object.finalize --trigger-resource=$BUCKET_NAME_INPUT \
  --set-env-vars=DF_PROJECT=$PROJECT_ID,DF_LOCATION=$DF_LOCATION,DF_INSTANCE_NAME=$DF_INSTANCE_NAME,\
DF_NAMESPACE=$DF_NAMESPACE,ERROR_URI=$ERROR_URI

```
