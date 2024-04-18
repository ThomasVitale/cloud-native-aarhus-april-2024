package com.thomasvitale.instrumentservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class InstrumentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstrumentServiceApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()
				.GET("/", request -> ServerResponse.ok().body("Java and Spring Boot Rock!"))
				.build();
	}

}

@RestController
@RequestMapping("/instruments")
class InstrumentController {

	private static final Logger logger = LoggerFactory.getLogger(InstrumentController.class);
	private final InstrumentRepository instrumentRepository;

	public InstrumentController(InstrumentRepository instrumentRepository) {
		this.instrumentRepository = instrumentRepository;
	}

	@GetMapping
	List<Instrument> getAll() {
		logger.info("Returning all instruments");
		return instrumentRepository.findAll();
	}

	@PostMapping
	Instrument addInstrument(@RequestBody Instrument instrument) {
		logger.info("Adding new instrument");
		return instrumentRepository.save(instrument);
	}
}

record Instrument(@Id Long id, String name) {}

interface InstrumentRepository extends ListCrudRepository<Instrument,Long> {}
