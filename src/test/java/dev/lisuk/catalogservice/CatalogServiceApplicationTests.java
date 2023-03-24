package dev.lisuk.catalogservice;

import dev.lisuk.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = Book.of(
				"1234567890",
				"The Hitchhiker's Guide to the Galaxy",
				"Douglas Adams",
				9.90
		);

		webTestClient.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.value(actualBook -> assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn()));
	}
}
