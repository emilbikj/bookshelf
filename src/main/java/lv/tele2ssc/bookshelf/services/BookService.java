package lv.tele2ssc.bookshelf.services;

import lv.tele2ssc.bookshelf.repositories.BookRepository;
import lv.tele2ssc.bookshelf.model.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book findById(long bookId) {
        return bookRepository.findOne(bookId);
    }
    
    public List<Book> findByTerm(String term) {
        return bookRepository.findByTerm(term.toLowerCase());
    }
    
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
