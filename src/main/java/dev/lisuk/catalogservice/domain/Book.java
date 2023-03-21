package dev.lisuk.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record Book (
        @NotBlank(message="The book ISBN must be defined.")
        @Pattern(regexp = "^(97(8|9))?[0-9]{9}(X|[0-9])$", message = "The book ISBN must be a valid ISBN-10 or ISBN-13.")
        String isbn,
        @NotBlank(message="The title must be defined.")
        String title,
        @NotBlank(message="The author must be defined.")
        String author,
        @NotNull(message="The price must be defined.")
        @Positive(message="The book price must be greater than zero.")
        Double price
){}
