#utils
import requests
import google.auth
import google.auth.transport.requests
import json


def check_instance(authtoken, project, location, instancename):
    """It validates if an instance of data fusion is up and running so that pipelines can be started
    Args:
         authtoken : token for authentication, it can be retrieved through get_access_token() method
         project : name of the project where data fusion is executing
         location : name of the location where data fusion is executing
         instancename : name of instance data fusion
    """
    actionList = "instances/" + instancename  #this is for enterprise edition
    url = "https://datafusion.googleapis.com/v1beta1/projects/" + \
        project + "/locations/" + location + "/" + actionList

    response = requests.request("GET", url, headers={
                                'Content-Type': 'application/json', 'Authorization': 'Bearer ' + authtoken})
    print("Checking instances status")
    if response.status_code == 200:
        print("Active instance, rsp : " + str(response.status_code))
        instances = json.loads(response.text)
        for attribute, value in instances.items():
            if attribute == 'state':
                # print("status:" + value)
                # print (instances['name'])
                if value == "RUNNING":
                    print("Instance running Sucessfully")
                    return True
                elif value == "DELETING":
                    print('Still deleting: %s' % time.ctime())
                    time.sleep(10)
                    check_instance(authtoken)
                    return True
                elif value == "CREATING":
                    print('Still Creating: %s' % time.ctime())
                    time.sleep(10)
                    check_instance(authtoken)
                    return True
    else:
        print("Instance not found, Error" + str(response.status_code))
        print("Text, Error" + str(response.text))
        print("Content, Error" + str(response.content))
        return False



def start_pipeline(authtoken, project, location, instancename, namespace, pipeline_name, macro_variables):
    """Start a pipeline in data fusion instance
    Args:
         token : token for authentication
         project : name of the project where data fusion is executing
         location : name of the location where data fusion is executing
         instancename : name of instance data fusion
         pipeline_name : name of the pipeline to start within data fusion
         macro_variables : these are the macro variables to replace within pipeline use placeholder ${variable}
    """
    print("Starting pipeline:" + pipeline_name)
    url = "https://" + instancename + "-" + project + \
        "-dot-" + location + ".datafusion.googleusercontent.com/api" + \
        "/v3/namespaces/" + namespace + "/" + \
        "apps" + "/" + pipeline_name + "/" + \
        "workflows/DataPipelineWorkflow/start"

    print("CDAP_ENDPOINT=" + url)
    #print(type(processing_file))
    #print(str(processing_file))
    #response = requests.request("POST", url, headers={
    #                            'Content-Type': 'application/json', 'Authorization': 'Bearer ' + authtoken},\
    #                            data=processing_file)
    response = requests.request("POST", url, headers={'Authorization': 'Bearer ' + authtoken},\
                                data=str(macro_variables))

    print("Response after initiating data fusion: " + str(response))
