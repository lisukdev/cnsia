package dev.lisuk.catalogservice.demo;

import dev.lisuk.catalogservice.domain.Book;
import dev.lisuk.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        bookRepository.saveAll(List.of(
                Book.of("1234567890", "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Random House", 9.90),
                Book.of("0987654321", "The Restaurant at the End of the Universe", "Douglas Adams", null, 12.90),
                Book.of("1234567891", "Life, the Universe and Everything", "Douglas Adams", "Spectrum", 7.80),
                Book.of("0987654322", "So Long, and Thanks for All the Fish", "Douglas Adams", "Penguin", 8.80),
                Book.of("1234567892", "Mostly Harmless", "Douglas Adams", null, 5.30)
        ));
    }
}
