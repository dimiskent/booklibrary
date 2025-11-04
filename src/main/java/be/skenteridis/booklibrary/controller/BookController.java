package be.skenteridis.booklibrary.controller;

import be.skenteridis.booklibrary.model.Book;
import be.skenteridis.booklibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) {
        this.service = service;
    }
    @GetMapping
    public List<Book> getBooks() {
        return service.getBooks();
    }
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }
    @GetMapping("/{id}")
    public Book getBookByID(@PathVariable Long id) {
        return service.getBookByID(id);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }
    @GetMapping("/search")
    public List<Book> getFilteredBooks(
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "genre", required = false) String genre,
            @RequestParam(name = "order", required = false) String order
            ) {
        if(author != null) return service.getByAuthor(author);
        if(title != null) return service.getByTitle(title);
        if(genre != null) return service.getByGenre(genre);
        return service.getSortedByYear(order);
    }
}
