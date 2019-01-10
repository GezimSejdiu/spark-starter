# Apache Spark demo example
This is a starter repo for Apache Spark docker.

## Build
```
git clone https://github.com/GezimSejdiu/spark-starter.git
cd spark-starter
mvn clean package
```

## Running the application on a Spark standalone cluster via Docker

To run the application, execute the following steps:

1. Setup a Spark cluster as described on http://github.com/big-data-europe/docker-spark.
```sh
docker run --name spark-master -h spark-master -e ENABLE_INIT_DAEMON=false -d bde2020/spark-master:2.4.0-hadoop2.8
docker run --name spark-worker-1 --link spark-master:spark-master -e ENABLE_INIT_DAEMON=false -d bde2020/spark-worker:2.4.0-hadoop2.8
```
2. Build the Docker image:
```bash
docker build --rm=true -t bde/spark-starter .
```
3. Run the Docker container:
```bash
docker run --name spark-starter-app -e ENABLE_INIT_DAEMON=false --link spark-master:spark-master  -d bde/spark-starter
```