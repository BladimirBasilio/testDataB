#!/bin/bash
set -ex

JOBNAME="${DATAFLOW_JOB_NAME}dfjob"
getJobsNumber=$(gcloud dataflow jobs list --project $PROJECT_ID  --region $REGION --status active --filter="name="$JOBNAME | awk '{print $2}' | tail -n+2 | wc -l)
getJobID=$(gcloud dataflow jobs list --project $PROJECT_ID  --region $REGION --status active --filter="name="$JOBNAME | awk '{print $1}' | tail -n+2)

## Functions
check_jobs () {
  echo "Checking active job $1 - Draining"
  gcloud dataflow jobs drain $getJobID --project $PROJECT_ID  --region $REGION

  sleep 10
  getJobsRunningNumber=$(gcloud dataflow jobs list --project $PROJECT_ID  --region $REGION --status active --filter="name="$1 | awk '{print $2}' | tail -n+2 | wc -l)
  if  [ $getJobsRunningNumber -eq 0 ] 2>/dev/null; then
    echo "done with Draining"
  else
    echo "still Draining job"
    check_jobs $1
  fi
}

cd ./source

if  [ $getJobsNumber -eq 0 ] 2>/dev/null; then
  echo "Spinning a new Dataflow Job"
  mvn clean
  mvn -P dataflow-runner  compile -e exec:java -Dexec.mainClass=com.liverpool.stream.ingestion.fundacional.clientes.dataflow.RegistraClientePipeline -Dexec.cleanupDaemonThreads=false

else
  #gcloud dataflow jobs drain $JOBNAME  -$-project $PROJECT_ID  --region $REGION
  echo "Job Found need to Drain"
  check_jobs $JOBNAME
  mvn clean
  echo "Spinning a new Dataflow Job"
  mvn -P dataflow-runner  compile -e exec:java -Dexec.mainClass=com.liverpool.stream.ingestion.fundacional.clientes.dataflow.RegistraClientePipeline -Dexec.cleanupDaemonThreads=false

fi
