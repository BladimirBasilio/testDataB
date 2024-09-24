#utils
import requests
import google.auth
import google.auth.transport.requests
import json

def get_access_token():
    """Get the token for calling CDAP API and datafusion google APIs
    """
    # getting the credentials and project details for gcp project
    credentials, your_project_id = google.auth.default(scopes=["https://www.googleapis.com/auth/cloud-platform"])

    #getting request object
    auth_req = google.auth.transport.requests.Request()

    #print(credentials.valid) # prints False
    credentials.refresh(auth_req) #refresh token
    #cehck for valid credentials
    #print(credentials.valid)  # prints True
    #print(credentials.token) # prints token

    return credentials.token


def get_folder(dir_file):
    """Get the folder name
    """
    dir_file_split = dir_file.split('/')
    directory = ''
    if(len(dir_file_split) > 0):
        directory = dir_file_split[-2:][0]
    return directory
