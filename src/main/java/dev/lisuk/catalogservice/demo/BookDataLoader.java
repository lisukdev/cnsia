package dev.lisuk.catalogservice.demo;

import dev.lisuk.catalogservice.domain.Book;
import dev.lisuk.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.save(Book.of("1234567890", "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 9.90));
        bookRepository.save(Book.of("0987654321", "The Restaurant at the End of the Universe", "Douglas Adams", 12.90));
        bookRepository.save(Book.of("1234567891", "Life, the Universe and Everything", "Douglas Adams", 7.80));
        bookRepository.save(Book.of("0987654322", "So Long, and Thanks for All the Fish", "Douglas Adams", 8.80));
        bookRepository.save(Book.of("1234567892", "Mostly Harmless", "Douglas Adams", 5.30));
    }
}
