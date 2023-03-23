package dev.lisuk.catalogservice.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    @Test
    void whenAllFieldsCorrectThenValidationSuceeds() {
        var book = new Book(
                "1234567890",
                "The Hitchhiker's Guide to the Galaxy",
                "Douglas Adams",
                9.90
        );
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidtionFails(){
        var book = new Book(
                "123456789",
                "The Hitchhiker's Guide to the Galaxy",
                "Douglas Adams",
                9.90
        );
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isNotEmpty();
    }
}
