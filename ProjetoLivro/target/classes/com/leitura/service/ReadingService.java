package com.example.leitura.service;

import com.example.leitura.model.Reading; // Corrigido para Reading
import com.example.leitura.repository.ReadingRepository; // Corrigido para ReadingRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    public List<Reading> findAllReadings() {
        return readingRepository.findAll();
    }

    public void saveReading(Reading reading) {
        readingRepository.save(reading);
    }
}
