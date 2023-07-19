package com.example.onedayboot.service;

import com.example.onedayboot.dto.BookLogCreateDTO;
import com.example.onedayboot.dto.BookLogCreateResponseDTO;
import com.example.onedayboot.entity.Book;
import com.example.onedayboot.entity.BookLog;
import com.example.onedayboot.repository.BookLogRepository;
import com.example.onedayboot.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookLogService {
    private BookRepository bookRepository;
    private BookLogRepository bookLogRepository;

    public BookLogService(BookRepository bookRepository, BookLogRepository bookLogRepository){
        this.bookRepository = bookRepository;
        this.bookLogRepository = bookLogRepository;
    }

    public BookLogCreateResponseDTO insert(BookLogCreateDTO bookLogCreateDTO){
        Book book = bookRepository.findById(bookLogCreateDTO.getBookId()).orElseThrow();

        BookLog bookLog = BookLog.builder()
                .book(book)
                .comment(bookLogCreateDTO.getComment())
                .page(bookLogCreateDTO.getPage())
                .build();

        bookLog = bookLogRepository.save(bookLog);

        return BookLogCreateResponseDTO.bookLogFactory(bookLog);
    }
}
