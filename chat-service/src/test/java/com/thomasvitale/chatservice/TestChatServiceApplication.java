package com.thomasvitale.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.chromadb.ChromaDBContainer;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestChatServiceApplication {

    @Bean
	@RestartScope
	@ServiceConnection
	ChromaDBContainer chroma() {
		return new ChromaDBContainer("ghcr.io/chroma-core/chroma:0.4.24");
	}

    @Bean
	@RestartScope
	@ServiceConnection
	@Profile("ollama")
	OllamaContainer ollama() {
		return new OllamaContainer(DockerImageName.parse("ghcr.io/thomasvitale/ollama-llama3")
				.asCompatibleSubstituteFor("ollama/ollama"));
	}

    public static void main(String[] args) {
        SpringApplication.from(ChatServiceApplication::main).with(TestChatServiceApplication.class).run(args);
    }

}
