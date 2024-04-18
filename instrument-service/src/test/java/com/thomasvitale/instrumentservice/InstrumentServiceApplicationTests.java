package com.thomasvitale.instrumentservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestInstrumentServiceApplication.class)
class InstrumentServiceApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void createInstrument() {
		var instrument = new Instrument(null, "hurdy gurdy");

		webTestClient
			.post()
			.uri("/instruments")
			.bodyValue(instrument)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Instrument.class).value(actualInstrument -> {
				assertThat(actualInstrument.id()).isNotNull();
				assertThat(actualInstrument.name()).isEqualTo(instrument.name());
			});
	}

}
