#!/bin/bash

set -ex

echo "Setting values"

RUNTIME="python37"
TRIGGEREVENT="google.storage.object.finalize"

echo "deploying the function..."

gcloud functions deploy $FUNCTION_NAME --region=$LOCATION \
	--trigger-resource=gs://$BUCKET_NAME_INPUT --source=$1 \
	--trigger-event=$TRIGGEREVENT \
  --runtime=$RUNTIME --entry-point=$ENTRYPOINTNAME  \
  --service-account=$SERVICE_ACCOUNT --project=$PROJECT_ID\
  --set-env-vars PROJECT_ID=$PROJECT_ID,\
boletas=$BOLETAS,\
clientes=$CLIENTES,\
pedidos=$PEDIDOS,\
shared=$SHARED,\
WRITE_DISPOSITION=$WRITE_DISPOSITION

echo "Function deployed..."