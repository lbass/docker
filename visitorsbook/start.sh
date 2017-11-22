#!/bin/sh

NODE_HOME=/usr/sbin/nodejs
export NODE_HOME

PATH=./:$PATH:$NODE_HOME/bin

export PATH

#nohup npm start &
nohup nodejs app.js & 
