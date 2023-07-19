package com.example.onedayboot.service;

import com.example.onedayboot.dto.*;
import com.example.onedayboot.entity.Book;
import com.example.onedayboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    // create
    public Integer insert(BookCreateDTO createDTO){
        Book book = Book.builder()
                .title(createDTO.getTitle())
                .price(createDTO.getPrice())
                .build();
        bookRepository.save(book);

        return book.getBookId();
    }

    // read
    public BookReadResponseDTO read(Integer bookId) throws NoSuchElementException{
        Book book = bookRepository.findById(bookId).orElseThrow();

        BookReadResponseDTO readResponseDTO = new BookReadResponseDTO();

        readResponseDTO.fromBook(book);

        return readResponseDTO;
    }

    // update (책 수정 화면-view)
    public BookEditResponseDTO edit(Integer bookId) throws NoSuchElementException {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return BookEditResponseDTO.BookFactory(book);
    }

    public void update(BookEditDTO bookEditDTO) throws NoSuchElementException{
        Book book = bookRepository.findById(bookEditDTO.getBookId()).orElseThrow();
        book = bookEditDTO.fill(book);
        bookRepository.save(book);
    }

    public void delete(Integer bookId) throws NoSuchElementException{
        Book book = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(book);
    }

    // Book List
    public List<BookListResponseDTO> bookList(String title, Integer page){
        final int pageSize=5;

        List<Book> books;

        if(page==null){
            page=0;
        }else{
            page-=1;
        }

        if(title==null){
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by("regDate").descending());
//            Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "regDate");
            books = bookRepository.findAll(pageable).toList();
        }else{
            Pageable pageable = PageRequest.of(page, pageSize);
            Sort sort = Sort.by(Sort.Order.desc("regDate"));
            pageable.getSort().and(sort);
            books = bookRepository.findByTitleContains(title, pageable);
        }
        return books.stream().map(book->
                new BookListResponseDTO(book.getBookId(), book.getTitle())
                ).collect(Collectors.toList());
    }
}
