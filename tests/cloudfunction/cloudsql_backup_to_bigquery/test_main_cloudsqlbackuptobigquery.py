from cloudfunction.cloudsql_backup_to_bigquery.main import main, get_table_schema, get_bq_dataset_from_cloudsql_schema
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


class TestCloudSQLBackupToBigQuery(object):

    project_id = "project-123"
    dataset = "dataset1"
    table1 = "table1"

    @patch('google.cloud.bigquery.Client')
    @patch('google.cloud.bigquery.dataset.Dataset')
    def test_get_table_schema(self,
                              dataset_mock,
                              bigquery_client_mock):
        mock_schema = "schema"
        mock_table = dotdict({"schema": mock_schema})

        bigquery_client_mock.dataset.return_value = dataset_mock
        bigquery_client_mock.get_table.return_value = mock_table

        assert get_table_schema(bigquery_client_mock, self.project_id, self.dataset, self.table1) == mock_schema

    def test_get_bq_dataset_from_cloudsql_schema(self):
        assert get_bq_dataset_from_cloudsql_schema("sample_schema") == "sample_dataset"

    @patch('google.cloud.bigquery.Client')
    @patch(
        'cloudfunction.cloudsql_backup_to_bigquery.main.get_bq_dataset_from_cloudsql_schema')
    @patch(
        'cloudfunction.cloudsql_backup_to_bigquery.main.get_table_schema')
    def test_main_root_dir_not_cloudsql(self,
                             get_table_schema_mock,
                             get_bq_dataset_from_cloudsql_schema_mock,
                             bq_client_mock):
        trigger_bucket = "sample_bucket"
        filename = f"{trigger_bucket}/sample.dat"

        get_bq_dataset_from_cloudsql_schema_mock.return_value = None

        main({'bucket': trigger_bucket,
              'name': filename,
              'size': "10"},
             None)
        get_bq_dataset_from_cloudsql_schema_mock.assert_not_called()
        get_table_schema_mock.assert_not_called()

    @patch('google.cloud.bigquery.Client')
    @patch(
        'cloudfunction.cloudsql_backup_to_bigquery.main.get_bq_dataset_from_cloudsql_schema')
    @patch(
        'cloudfunction.cloudsql_backup_to_bigquery.main.get_table_schema')
    def test_main_no_dataset(self,
                             get_table_schema_mock,
                             get_bq_dataset_from_cloudsql_schema_mock,
                             bq_client_mock):
        trigger_bucket = "sample_bucket"
        filename = "cloudsql/schema-table.dat"

        get_bq_dataset_from_cloudsql_schema_mock.return_value = None

        main({'bucket': trigger_bucket,
                          'name': filename,
                          'size': "10"},
                         None)
        get_table_schema_mock.assert_not_called()

    @patch('google.cloud.bigquery.Client')
    @patch(
        'cloudfunction.cloudsql_backup_to_bigquery.main.get_bq_dataset_from_cloudsql_schema')
    @patch(
        'cloudfunction.cloudsql_backup_to_bigquery.main.get_table_schema')
    @patch('google.cloud.bigquery.schema.SchemaField')
    def test_main(self,
                  schema_field_mock,
                  get_table_schema_mock,
                  get_bq_dataset_from_cloudsql_schema_mock,
                  bq_client_mock):
        trigger_bucket = "sample_bucket"
        filename = "cloudsql/schema-table.dat"
        dataset = "sample_dataset"

        get_bq_dataset_from_cloudsql_schema_mock.return_value = dataset

        queryjob_mock = dotdict({"state": 'DONE'})
        bq_client_mock.load_table_from_uri.return_value = queryjob_mock

        get_table_schema_mock.return_value = schema_field_mock

        main({'bucket': trigger_bucket,
              'name': filename,
              'size': "10"},
             None)
