package dev.lisuk.catalogservice.web;

import dev.lisuk.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book(
                "1234567890",
                "The Hitchhiker's Guide to the Galaxy",
                "Douglas Adams",
                9.90
        );
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialization() throws Exception {
        var content = """
            {
                "isbn": "1234567890",
                "title": "The Hitchhiker's Guide to the Galaxy",
                "author": "Douglas Adams",
                "price": 9.90
            }
            """;
        assertThat(json.parse(content)).usingRecursiveComparison().isEqualTo(new Book(
                "1234567890",
                "The Hitchhiker's Guide to the Galaxy",
                "Douglas Adams",
                9.90
        ));
    }
}
