from cloudfunction.cloudsql_exports.main import main
from mock import patch, call, Mock
import os
import json
import base64


class dotdict(dict):
    """Helper class to access list elements with dots, e.g. table.table_id"""
    __getattr__ = dict.get
    __setattr__ = dict.__setitem__
    __delattr__ = dict.__delitem__

    def to_dict(self):
        return {}

    def result(self):
        pass


class TestCloudSQLExports(object):

    @patch('googleapiclient.discovery')
    @patch('googleapiclient.discovery.Resource')
    def test_main(self,
                  resource_mock,
                  discovery_mock):
        json_data = {
            "instance": "sample_instance",
            "db": "sample_db",
            "schema": "sample_schema",
            "table": "sample_table",
            "project": "sample_project",
             "bucket_name": "sample_bucket"
        }
        b64data = base64.b64encode(json.dumps(json_data).encode('utf-8'))

        discovery_mock.build.return_value = resource_mock

        main({'data': b64data}, dotdict({'event_id': '123', 'timestamp': '456'}))

        resource_mock().instances().export.assert_called_once_with(
            project='sample_project',
            instance='sample_instance',
            body={
                'exportContext':
                    {'kind': 'sql#exportContext',
                     'fileType': 'CSV',
                     'uri': 'gs://sample_bucket/cloudsql/2021/03/07/sample_instance/sample_db/sample_schema-sample_table.csv',
                     'databases': ['sample_db'],
                     'csvExportOptions':
                         {'selectQuery': 'SELECT * FROM sample_schema.sample_table'}
                     }
            }
        )
