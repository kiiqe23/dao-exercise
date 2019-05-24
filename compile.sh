#!/bin/bash

mkdir classes
javac -d classes/ -cp lib/javaee.jar:lib/primefaces-7.0.jar:lib/jar_files/javaee-api-8.0.jar  ./*java

cd classes
jar -cvf edu.war ./*
