FROM bde2020/spark-java-template:2.4.0-hadoop2.8

MAINTAINER Gezim Sejdiu <g.sejdiu@gmail.com>

ENV SPARK_APPLICATION_JAR_NAME spark-starter-0.0.1-SNAPSHOT
ENV SPARK_APPLICATION_MAIN_CLASS tech.sda.starter.spark.WordCount
ENV SPARK_APPLICATION_ARGS ""
