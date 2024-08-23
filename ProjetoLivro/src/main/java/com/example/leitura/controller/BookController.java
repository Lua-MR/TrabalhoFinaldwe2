package com.example.leitura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.leitura.model.Book;
import com.example.leitura.service.BookService;
import com.example.leitura.service.ReadingService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private ReadingService readingService;

    @GetMapping("/books")
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();  
        model.addAttribute("books", books);  
        return "books"; 
    }
    
    @PostMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        
        Book book = bookService.getBookById(id);

        if (book != null) {
            readingService.deleteReadingsByBookId(id);
            bookService.deleteBook(id);
        }

        return "redirect:/readings";
    }
    
    @GetMapping("/edit-book/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "error"; 
        }
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/update-book")
    public String updateBook(@ModelAttribute("book") Book book) {
        Book existingBook = bookService.getBookById(book.getId());
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPages(book.getPages());
             bookService.saveBook(existingBook);
        }
        return "redirect:/readings";
    }
}
