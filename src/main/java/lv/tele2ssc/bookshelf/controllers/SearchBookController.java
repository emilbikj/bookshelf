/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.bookshelf.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import lv.tele2ssc.bookshelf.model.Book;
import lv.tele2ssc.bookshelf.services.BookService;
import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchBookController {
    
    private static final Logger logger = LoggerFactory.getLogger(SearchBookController.class);
    
    @Autowired
    private BookService bookService;
    
    @RequestMapping(method = RequestMethod.GET, path="/")
    public String page(Model model) {
        
        List<Book> list = bookService.findAllBooks();
        
        model.addAttribute("books", list);
        return "index";
    }
    
    @RequestMapping(method = RequestMethod.POST, path="/")
    public String search(@RequestParam String term, Model model) {
        logger.debug("User searches for {}", term);

        List<Book> list = bookService.findByTerm(term);
        
        model.addAttribute("books", list);
        model.addAttribute("term", term);
        return "index";
    }
    
}
