#!/bin/bash
set -ex

#cd  $FOLDER/source/
pwd
if [ -f requirements.txt ]; then
    pip3 install --upgrade pip
    python3 -m pip install -r requirements.txt
#   python3 -m pip install cmake
 fi

#do some testing
echo "Testing Python App - if test run it here"
#python3 -m unittest discover --verbose -p test_*.py
