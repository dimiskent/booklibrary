package be.skenteridis.booklibrary.service;

import be.skenteridis.booklibrary.model.Book;
import be.skenteridis.booklibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    public BookService(BookRepository bookRepository) {
        repository = bookRepository;
    }
    public List<Book> getBooks() {
        return repository.findAll();
    }
    public Book getBookByID(Long id) {
        return repository.findById(id).orElse(null);
    }
    public Book addBook(Book book) {
        repository.save(book);
        return book;
    }
    public Book updateBook(Long id, Book book) {
        Book current = getBookByID(id);
        if(book == null) return null;
        current.setId(id);
        current.setAuthor(book.getAuthor());
        current.setGenre(book.getGenre());
        current.setTitle(book.getTitle());
        current.setYear(book.getYear());
        return repository.save(current);
    }
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
    public List<Book> getByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCase(author);
    }
    public List<Book> getByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
    public List<Book> getByGenre(String genre) {
        return repository.findByGenreContainingIgnoreCase(genre);
    }
    public List<Book> getSortedByYear(String filter) {
        return filter.equalsIgnoreCase("desc")
                ? repository.findAllByOrderByYearDesc()
                : repository.findAllByOrderByYearAsc();
    }
}
