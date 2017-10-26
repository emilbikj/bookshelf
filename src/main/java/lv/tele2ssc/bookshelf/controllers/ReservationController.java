package lv.tele2ssc.bookshelf.controllers;

import java.util.List;
import lv.tele2ssc.bookshelf.model.Book;
import lv.tele2ssc.bookshelf.model.User;
import lv.tele2ssc.bookshelf.services.BookService;
import lv.tele2ssc.bookshelf.services.ReservationService;
import lv.tele2ssc.bookshelf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
            
    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public String order(@RequestParam long bookId, Model model) {
        Book book = bookService.findById(bookId);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        
        reservationService.doReservation(user, book);
        
        return "redirect:/mybooks";        
    }
    
}
