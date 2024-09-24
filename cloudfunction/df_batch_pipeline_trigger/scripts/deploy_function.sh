#!/bin/bash

set -ex

echo "Setting values"
RUNTIME="python37"
TRIGGEREVENT="google.storage.object.finalize"

echo "deploying the function..."
#
gcloud functions deploy $FUNCTION_NAME --region=$LOCATION \
  --entry-point=$ENTRYPOINTNAME --runtime=$RUNTIME --source=$1 \
  --trigger-event=$TRIGGEREVENT --trigger-resource=gs://$BUCKET_NAME_INPUT \
  --service-account=$SERVICE_ACCOUNT --project=$PROJECT_ID\
  --set-env-vars=DF_PROJECT=$DF_PROJECT_ID,\
DF_PREFIX=$DF_PREFIX,\
PROJECT_ID=$PROJECT_ID,\
DF_LOCATION=$DF_LOCATION,\
DF_INSTANCE_NAME=$DF_INSTANCE_NAME,\
DF_NAMESPACE=$DF_NAMESPACE,\
DB_URL_SECRET_NAME=$DB_URL_SECRET_NAME,\
DB_USER_SECRET_NAME=$DB_USER_SECRET_NAME,\
DB_PASSWORD_SECRET_NAME=$DB_PASSWORD_SECRET_NAME,\
LOCATION=$LOCATION

echo "Function deployed.."
