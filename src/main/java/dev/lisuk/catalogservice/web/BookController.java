package dev.lisuk.catalogservice.web;

import dev.lisuk.catalogservice.domain.Book;
import dev.lisuk.catalogservice.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Book> viewBookList() {
        return service.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book viewBookDetails(@PathVariable String isbn) {
        return service.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBookToCatalog(@Valid Book book) {
        return service.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookFromCatalog(@PathVariable String isbn) {
        service.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book editBookDetails(@PathVariable String isbn, @Valid Book book) {
        return service.editBookDetails(isbn, book);
    }
}
