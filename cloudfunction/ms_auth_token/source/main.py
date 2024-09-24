import os
import urllib.request
import google.auth.transport.requests
import google.oauth2.id_token


def make_authorized_get_request(service_url):
    """
    make_authorized_get_request makes a GET request to the specified HTTP endpoint
    in service_url (must be a complete URL) by authenticating with the
    ID token obtained from the google-auth client library.
    """

    req = urllib.request.Request(service_url)

    auth_req = google.auth.transport.requests.Request()
    id_token = google.oauth2.id_token.fetch_id_token(auth_req, service_url)

    req.add_header("Authorization", f"Bearer {id_token}")
    response = urllib.request.urlopen(req)

    print("Response status code: %s" % response.status)

    return response.read()

def get_authorized(data,context):
    # Makes an authenticated request to URL set in environment variables
    try:
        url = os.environ.get("URL")
        response = make_authorized_get_request(url)
        return response

    except Exception as e:
        print(e)
        return str(e)