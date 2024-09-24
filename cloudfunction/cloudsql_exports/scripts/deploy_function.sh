#!/bin/bash

set -ex

echo "Setting values"

RUNTIME="python37"

echo "deploying the function..."

gcloud functions deploy $FUNCTION_NAME --region=$LOCATION\
    --trigger-topic $TRIGGER_TOPIC_NAME --source=$1 \
    --runtime=$RUNTIME --entry-point=$ENTRYPOINTNAME \
    --service-account=$SERVICE_ACCOUNT --project=$PROJECT_ID

echo "Function deployed..."