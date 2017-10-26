package lv.tele2ssc.bookshelf.repositories;

import lv.tele2ssc.bookshelf.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    
    List<Book> findAll();
    
    @Query("SELECT b FROM Book b WHERE lower(b.title) like %?1% OR lower(b.description) like %?1% OR lower(b.author) like %?1% OR b.year like %?1%")
    List<Book> findByTerm(String term);
        
}
