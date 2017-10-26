package lv.tele2ssc.bookshelf.controllers;

import java.util.ArrayList;
import java.util.List;
import lv.tele2ssc.bookshelf.model.Book;
import lv.tele2ssc.bookshelf.model.Reservation;
import lv.tele2ssc.bookshelf.model.ReservationStatus;
import lv.tele2ssc.bookshelf.model.User;
import lv.tele2ssc.bookshelf.services.BookService;
import lv.tele2ssc.bookshelf.services.ReservationService;
import lv.tele2ssc.bookshelf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ManageController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReservationService reservationService;
    
    @RequestMapping(path = "/manage", method = RequestMethod.GET)
    public String manage(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        
        return "manage";        
    }
    
    @RequestMapping(path = "/manage/user", method = RequestMethod.GET)
    public String manageUser(@RequestParam long userId, Model model) {
        User user = userService.findUser(userId);
        List<Reservation> reservations = reservationService.findAllByUser(user);
        
        List<Book> available = new ArrayList<>();
        List<Book> owned = new ArrayList<>();
        
        for (Reservation r : reservations) {
            Book b = r.getBook();
            
            switch (r.getStatus()) {
                case AVAILABLE:
                    available.add(b);
                    break;
                case TAKEN:
                    owned.add(b);
                    break;
            }
        }

        model.addAttribute("availableBooks", available);
        model.addAttribute("ownedBooks", owned);
        model.addAttribute("user", user);
        
        return "manage-user-books";        
    }
    
    @RequestMapping(path = "/manage/give", method = RequestMethod.POST)
    public String manageGive(@RequestParam long userId, @RequestParam long bookId, Model model) {
        Book book = bookService.findById(bookId);
        User user = userService.findUser(userId);
        reservationService.giveBook(user, book);
        
        
        return "redirect:/manage/user?userId="+userId;        
    }
    
    @RequestMapping(path = "/manage/take", method = RequestMethod.POST)
    public String manageTake(@RequestParam long userId, @RequestParam long bookId, Model model) {
        Book book = bookService.findById(bookId);
        User user = userService.findUser(userId);
        reservationService.takeBook(user, book);
        
        
        return "redirect:/manage/user?userId="+userId;        
    }
    
}
