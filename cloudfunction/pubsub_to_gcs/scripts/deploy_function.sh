#!/bin/bash

set -ex

echo "Setting values"
RUNTIME="python37"
TRIGGEREVENT="google.pubsub.topic.publish"

echo "deploying the function pubsub_to_gcs..."
#
gcloud functions deploy $FUNCTION_NAME --region=$LOCATION \
  --entry-point=$ENTRYPOINTNAME --runtime=$RUNTIME --source=$1 \
  --trigger-topic=$ERROR_TOPIC \
  --service-account=$SERVICE_ACCOUNT --project=$PROJECT_ID\
  --set-env-vars=CONCILIATION_BUCKET_NAME=$CONCILIATION_BUCKET_NAME

echo "Function deployed.."


# event_type = google.pubsub.topic.publish
# $ERROR_TOPIC tiene que ser el nombre del topico de errores que crearn con mario
# La SA tiene que tener permisos de escritura sobre ese bucket
# $CONCILIATION_BUCKET_NAME tiene que ser el bucket del que ale lea luego para hacer conciliación. ojo con poner el transient pq eso dispararía CF sin sentido.
