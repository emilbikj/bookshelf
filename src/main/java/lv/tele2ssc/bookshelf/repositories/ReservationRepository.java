package lv.tele2ssc.bookshelf.repositories;

import java.util.List;
import lv.tele2ssc.bookshelf.model.Book;
import lv.tele2ssc.bookshelf.model.Reservation;
import lv.tele2ssc.bookshelf.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
    @Query("SELECT r FROM Reservation r WHERE r.book = ?1 "
            + " AND r.status <> 'CLOSED' ORDER BY r.created")
    List<Reservation> findNotClosedByBook(Book book);
    
    List<Reservation> findAllByUser(User user);
    
    @Query("SELECT r FROM Reservation r WHERE r.book = ?1 "
            + " AND r.status = 'AVAILABLE' AND r.user = ?2 ORDER BY r.created")
    Reservation findAvailable(Book book, User user);
    
    @Query("SELECT r FROM Reservation r WHERE r.book = ?1 "
            + " AND r.status = 'TAKEN' AND r.user = ?2 ORDER BY r.created")
    Reservation findTaken(Book book, User user);
    
}