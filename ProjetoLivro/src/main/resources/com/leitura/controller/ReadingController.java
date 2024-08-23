package com.example.leitura.controller;

import com.example.leitura.model.Reading;
import com.example.leitura.model.Book;
import com.example.leitura.service.ReadingService;
import com.example.leitura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @Autowired
    private BookService bookService;

    @GetMapping("/readings")
    public String viewReadings(Model model) {
        List<Reading> readings = readingService.findAllReadings();
        model.addAttribute("readings", readings);
        return "readings"; // Certifique-se de que a página "readings.jsp" ou "readings.html" está no diretório correto.
    }

    @GetMapping("/start-reading")
    public String showReadingForm(Model model) {
        Reading reading = new Reading();
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("reading", reading);
        model.addAttribute("books", books);
        return "start-reading"; // Certifique-se de que a página "start-reading.jsp" ou "start-reading.html" está no diretório correto.
    }

    @PostMapping("/start-reading")
    public String startReading(@ModelAttribute("reading") Reading reading) {
        readingService.saveReading(reading);
        return "redirect:/readings"; // Redireciona para a página que lista as leituras.
    }
}
