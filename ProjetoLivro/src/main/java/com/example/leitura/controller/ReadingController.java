package com.example.leitura.controller;

import com.example.leitura.model.Reading;
import com.example.leitura.model.User;
import com.example.leitura.model.Book;
import com.example.leitura.service.ReadingService;
import com.example.leitura.service.UserService;
import com.example.leitura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/readings")
    public String viewReadings(Model model) {
        List<Reading> readings = readingService.findAllReadings();
        List<Book> books = bookService.getAllBooks();  
        model.addAttribute("readings", readings);
        model.addAttribute("books", books);  
        return "readings";
    }

    @GetMapping("/book-readings/{bookId}")
    public String getBookReadings(@PathVariable Long bookId, Model model) {
        Book book = bookService.getBookById(bookId); 
        List<Reading> readings = readingService.getReadingsByBookId(bookId); 

        if (book == null) {
            model.addAttribute("error", "Book not found");
            return "error"; 
        }

        model.addAttribute("book", book);
        model.addAttribute("readings", readings);
        return "book-readings"; 
    }

    
    @GetMapping("/start-reading")
    public String showReadingForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Reading reading = new Reading();
        List<Book> books = bookService.getAllBooks();
        
        if (userDetails != null) {
            User loggedInUser = userService.getUserByEmail(userDetails.getUsername());
            model.addAttribute("reading", reading);
            model.addAttribute("books", books);
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            return "redirect:/login";
        }
        
        return "start-reading";
    }

    @PostMapping("/start-reading")
    public String startReading(@ModelAttribute("reading") Reading reading) {
        Book book = bookService.getBookById(reading.getBook().getId());
        User user = userService.getUserById(reading.getUser().getId()); 
        reading.setBook(book);
        reading.setUser(user); 
        readingService.saveReading(reading);
        return "redirect:/readings";
    }
    
    @GetMapping("/readings/{bookId}")
    public String viewReadingsByBook(@PathVariable Long bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        List<Reading> readings = readingService.findReadingsByBook(book);
        model.addAttribute("book", book);
        model.addAttribute("readings", readings);
        return "book-readings"; 
    }
    
    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "add-book"; 
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/readings"; 
    }
}
