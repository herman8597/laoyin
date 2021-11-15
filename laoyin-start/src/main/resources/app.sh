#!/bin/bash
# Copyright 1999-2022 XXXX.
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

################ Version Info ##################
# Create Date: 2021-10-23
# Author:      liming
# Mail:        11478375@qq.com
# Version:     1.0
# Attention:   shell template
################################################
# load environment variable
. /etc/profile
. ~/.bash_profile
. /etc/bashrc

# define variables
HOME=$(cd "$(dirname "$0")" && pwd)
APP_FILE=laoyin.jar
APP_NAME=${APP_FILE%.*}
APP_SUFFIX=${APP_FILE#*.}
PID=
JVM_OPTS="-Xms512M -Xmx512M \
          -XX:+HeapDumpOnOutOfMemoryError \
          -XX:+PrintGCDateStamps  -XX:+PrintGCDetails -Xloggc:$HOME/logs/gc.log \
          -XX:+UseParallelGC"
APP_OPTS="--spring.profiles.active=test"
LOG_FILE=$HOME/logs/$APP_NAME.log

# common function
function error_exit() {
    echo -e "\033[0;31m$1\033[0m"
    exit 1
}
# check environment variable params
if [ -z "$JAVA_HOME" ]; then
    error_exit "Please set the JAVA_HOME variable in your environment, Weneed java(x64)! jdk8 or later is better!"
fi
if [ "$1" = "" ]; then
    error_exit "未输入操作名  {start|stop|restart|status}"
fi
if [ ! -e "$LOG_FILE" ]; then
    mkdir -p ${LOG_FILE%/*}
fi


# function
function query_pid () {
    PID=`ps -ef |grep java|grep $APP_FILE|grep -v grep|awk '{print $2}'`
}

function monitor() {
    query_pid
    tail_str="tail --pid=$PID -f $1"
    $tail_str | while read line
    do
        echo $line
        is_startup=`echo $line|grep "$2"|wc -l`
        if [ $is_startup -eq 1 ] ; then
            kill -9 `ps axu|grep "${tail_str}"|grep -v "grep"|awk '{printf $2}'`
            echo "Start $APP_NAME start success..."
            exit 0
        fi
    done
}

function start () {
    query_pid
    if [ x"$PID" != x"" ]; then
	    echo "$APP_NAME is running..."
	else
		nohup java -jar  $JVM_OPTS $HOME/$APP_FILE $APP_OPTS > $LOG_FILE 2>&1 &
        monitor $LOG_FILE "JVM running"
	fi
    return 0
}

function stop () {
    query_pid
    if [ x"$PID" != x"" ]; then
        kill -TERM $PID
        echo "$APP_NAME (pid:$PID) exiting..."
        while [ x"$PID" != x"" ]
		do
			sleep 1
			query_pid
		done
        echo "$APP_NAME is stoped"
    else
        echo "$APP_NAME is stoped"
    fi
    return 0
}
function restart()
{
    stop
    sleep 1
    start
    return 0
}

function status()
{
    PID=`ps -ef |grep java|grep $APP_FILE|grep -v grep|wc -l`
    if [ $PID != 0 ];then
        echo "$AppName is running..."
    else
        echo "$AppName is stoped"
    fi
    return 0
}

# exec
case $1 in
start)
    start
    ;;
stop)
    stop
    ;;
restart)
    restart
    ;;
status)
    status
    ;;
*) ;;

esac
