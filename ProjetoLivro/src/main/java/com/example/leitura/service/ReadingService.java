package com.example.leitura.service;

import com.example.leitura.model.Book;
import com.example.leitura.model.Reading; // Corrigido para Reading
import com.example.leitura.repository.ReadingRepository; // Corrigido para ReadingRepository

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    public List<Reading> findAllReadings() {
        return readingRepository.findAll();
    }

    public void saveReading(Reading reading) {
        readingRepository.save(reading);
    }
    
    public List<Reading> findReadingsByBook(Book book) {
        return readingRepository.findByBook(book);
    }

    public List<Reading> getReadingsByBookId(Long bookId) {
        return readingRepository.findByBookId(bookId);
    }
    
    public void deleteReadingsByBookId(Long bookId) {
        readingRepository.deleteByBookId(bookId);
    }
}
