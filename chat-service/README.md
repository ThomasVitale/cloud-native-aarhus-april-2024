# Instrument Service

## Stack

* Java 21
* Spring Boot 3.2
* Ollama

## Running the application

The application uses [ChromaDB](https://www.trychroma.com) as a vector database and relies on [Ollama](https://ollama.com) for providing LLMs. You can either run Ollama locally on your laptop, or rely on the Testcontainers support in Spring Boot to spin up an Ollama service automatically.

### Ollama as a native application

First, make sure you have [Ollama](https://ollama.ai) installed on your laptop.
Then, use Ollama to run the _llama2_ large language model.

```shell
ollama run llama2
```

Finally, run the Spring Boot application.

```shell
./gradlew bootTestRun
```

### Ollama as a dev service with Testcontainers

The application relies on the native Testcontainers support in Spring Boot to spin up an Ollama service with a _llama2_ model at startup time.

```shell
./gradlew bootTestRun --args='--spring.profiles.active=ollama'
```

## Calling the application

You can now call the application that will use Ollama and llama2 to answer questions about some documents the application uploads with stories from fictitious music bands.
This example uses [httpie](https://httpie.io) to send HTTP requests.

```shell
http --raw="Who is Mocha Mike?" :8282/ai/doc/chat
```
