from cloudfunction.bigquery_exports.main import main
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


class TestBigQueryExports(object):

    @patch('google.cloud.bigquery.Client')
    @patch('google.cloud.bigquery.dataset.Dataset')
    @patch('google.cloud.bigquery.table.TableReference')
    def test_main(self,
                  table_ref_mock,
                  dataset_ref_mock,
                  bq_client_mock):
        json_data = {
            "project": "sample_project",
            "dataset_id": "sample_dataset",
            "table_id": "sample_table",
             "bucket_name": "sample_bucket"
        }
        b64data = base64.b64encode(json.dumps(json_data).encode('utf-8'))

        dataset_ref_mock.table.return_value = table_ref_mock
        delta_table_mock = dotdict({"schema": "schema"})
        bq_client_mock.get_table.return_value = delta_table_mock
        queryjob_mock = dotdict({"state": 'DONE'})
        bq_client_mock.extract_table.return_value = queryjob_mock

        main({'data': b64data}, dotdict({'event_id': '123', 'timestamp': '456'}))
