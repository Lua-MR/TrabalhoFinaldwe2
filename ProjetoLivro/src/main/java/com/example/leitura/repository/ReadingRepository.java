package com.example.leitura.repository;

import com.example.leitura.model.Book;
import com.example.leitura.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findByBook(Book book);
    List<Reading> findByBookId(Long bookId);
    void deleteByBookId(Long bookId);
}