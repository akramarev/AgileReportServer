#!/bin/bash

mongod --dbpath /Users/akramarev/Projects/AgileReportServer/data/db &

cd /Users/akramarev/Projects/AgileReportServer/server/
/Users/akramarev/Projects/AgileReportServer/server/activator run
