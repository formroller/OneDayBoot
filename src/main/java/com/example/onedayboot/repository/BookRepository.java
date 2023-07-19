package com.example.onedayboot.repository;

import com.example.onedayboot.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findByTitleContains(String title, Pageable pageable);

}
