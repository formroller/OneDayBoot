package com.example.onedayboot.dto;

import com.example.onedayboot.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 상세 화면 응답 DTO - (tip) 정보가 모이는 곳에 데이터 설정하는 로직 모아두기
//@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookReadResponseDTO {
    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime regDate;
    private List<BookLogReadResponseDTO> bookLogs;

    public BookReadResponseDTO fromBook(Book book){
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.regDate = book.getRegDate();
//        this.regDate = LocalDateTime.parse(book.getRegDate());

        this.bookLogs = book.getBookLogList()
                .stream()
                .map(bookLog->
                        BookLogReadResponseDTO.BookLogFactory(bookLog))
                .collect(Collectors.toList());

        return this;
    }

    public static BookReadResponseDTO BookFactory(Book book){ // 정적 메소드로 객체 생성 -> 팩토리 패턴

        BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();

        bookReadResponseDTO.fromBook(book);

        return bookReadResponseDTO;
    }

}
