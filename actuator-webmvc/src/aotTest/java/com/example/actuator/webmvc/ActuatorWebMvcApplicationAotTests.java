package com.example.actuator.webmvc;

import org.junit.jupiter.api.Test;

import org.springframework.aot.smoketest.support.junit.AotSmokeTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@AotSmokeTest
class ActuatorWebMvcApplicationAotTests {

	@Test
	void shouldContainLinks(WebTestClient client) {
		client.get().uri("/actuator").exchange().expectStatus().isOk().expectBody().jsonPath("$._links.self.templated")
				.isEqualTo(false).jsonPath("$._links.env-toMatch.templated").isEqualTo(true);
	}

	@Test
	void shouldHaveCustomHealthIndicator(WebTestClient client) {
		client.get().uri("/actuator/health").exchange().expectStatus().isOk().expectBody().jsonPath("$.status")
				.isEqualTo("UP").jsonPath("$.components.custom.status").isEqualTo("UP")
				.jsonPath("$.components.custom.details.hello").isEqualTo("world");
	}

	@Test
	void shouldHaveCustomEndpoint(WebTestClient client) {
		client.get().uri("/actuator/custom").exchange().expectStatus().isOk().expectBody().consumeWith(
				(result) -> assertThat(new String(result.getResponseBodyContent())).isEqualTo("custom-read"));
	}

	@Test
	void shouldHaveCustomWebEndpoint(WebTestClient client) {
		client.get().uri("/actuator/customWeb").exchange().expectStatus().isEqualTo(299).expectBody().consumeWith(
				(result) -> assertThat(new String(result.getResponseBodyContent())).isEqualTo("customWeb-read"));
	}

	@Test
	void shouldHaveReadiness(WebTestClient client) {
		client.get().uri("/actuator/health/readiness").exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.status").isEqualTo("UP");
	}

	@Test
	void shouldHaveEnvInfoProperties(WebTestClient client) {
		client.get().uri("/actuator/info").exchange().expectStatus().isOk().expectBody().jsonPath("$.app.hello")
				.isEqualTo("world");
	}

	@Test
	void shouldHaveJavaInfoProperties(WebTestClient client) {
		client.get().uri("/actuator/info").exchange().expectStatus().isOk().expectBody().jsonPath("$.java.version")
				.isNotEmpty();
	}

	@Test
	void shouldHaveOsInfoProperties(WebTestClient client) {
		client.get().uri("/actuator/info").exchange().expectStatus().isOk().expectBody().jsonPath("$.os.name")
				.isNotEmpty();
	}

	@Test
	void shouldHaveMetrics(WebTestClient client) {
		client.get().uri("/actuator/metrics/jvm.classes.loaded").exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.measurements.[0].value").isNotEmpty();
	}

	@Test
	void prometheusWorks(WebTestClient client) {
		client.get().uri("/actuator/prometheus").exchange().expectStatus().isOk().expectBody()
				.consumeWith((result) -> assertThat(new String(result.getResponseBodyContent()))
						// Check custom timer
						.contains("custom_timer_seconds_max 5.0").contains("custom_timer_seconds_count 1.0")
						.contains("custom_timer_seconds_sum 5.0")
						// Check JVM metric
						.contains("# TYPE jvm_threads_peak_threads gauge"));
	}

}
