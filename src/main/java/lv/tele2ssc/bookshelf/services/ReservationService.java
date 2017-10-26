package lv.tele2ssc.bookshelf.services;

import java.sql.Timestamp;
import java.util.List;
import lv.tele2ssc.bookshelf.model.Book;
import lv.tele2ssc.bookshelf.model.Reservation;
import lv.tele2ssc.bookshelf.model.ReservationStatus;
import lv.tele2ssc.bookshelf.model.User;
import lv.tele2ssc.bookshelf.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
 
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> findAllByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }
    
    public void doReservation(User user, Book book) {
        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setCreated(new Timestamp(System.currentTimeMillis()));
        
        List<Reservation> existing = reservationRepository.findNotClosedByBook(book);
        
        if (existing.isEmpty()) {
            reservation.setStatus(ReservationStatus.AVAILABLE);
        } else {
            reservation.setStatus(ReservationStatus.IN_QUEUE);
        }
        
        reservationRepository.save(reservation);
    }
    
    public void giveBook(User user, Book book) {
        Reservation reservation = reservationRepository.findAvailable(book, user);
        reservation.setStatus(ReservationStatus.TAKEN);
        reservationRepository.save(reservation);
    }
    
    public void takeBook(User user, Book book) {
        Reservation reservation = reservationRepository.findTaken(book, user);
        reservation.setStatus(ReservationStatus.CLOSED);
        reservationRepository.save(reservation);
    }
}