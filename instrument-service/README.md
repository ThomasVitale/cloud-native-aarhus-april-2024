# Instrument Service

## Stack

* Java 21 with GraalVM
* Spring Boot 3.3
* Grafana OSS

## Pre-requisites

An observability platform based on Grafana OSS is provided. You can use Docker Compose to run it from the root folder.

```bash
cd ..
docker compose up -d
cd instrument-service
```

## Usage

The Instrument Service application can be run as follows to rely on Testcontainers to spin up a PostgreSQL database:

```bash
./gradlew bootTestRun
```

You can now call the application. This example uses [httpie](https://httpie.io) to send HTTP requests.

```shell
http :8080
```

Create a new instrument:

```bash
http :8080/instruments name="hurdy-gurdy"
```

Retrieve all the instruments:

```bash
http :8080/instruments
```
