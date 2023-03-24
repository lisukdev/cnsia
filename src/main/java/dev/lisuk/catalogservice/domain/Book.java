package dev.lisuk.catalogservice.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.Instant;

public record Book (
        @Id
        Long id,
        @NotBlank(message="The book ISBN must be defined.")
        @Pattern(regexp = "^(97(8|9))?[0-9]{9}(X|[0-9])$", message = "The book ISBN must be a valid ISBN-10 or ISBN-13.")
        String isbn,
        @NotBlank(message="The title must be defined.")
        String title,
        @NotBlank(message="The author must be defined.")
        String author,
        String publisher,
        @NotNull(message="The price must be defined.")
        @Positive(message="The book price must be greater than zero.")
        Double price,

        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version

){
        public static Book of(
                String isbn, String title, String author, String publisher, Double price
        ) {
                return new Book(null, isbn, title, author, publisher, price, null, null, 0);
        }
}
