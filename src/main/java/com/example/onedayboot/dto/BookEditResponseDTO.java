package com.example.onedayboot.dto;

import com.example.onedayboot.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 상세 화면 응답 DTO - (tip) 정보가 모이는 곳에 데이터 설정하는 로직 모아두기
//@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookEditResponseDTO {
    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime regDate;

    public BookEditResponseDTO fromBook(Book book){
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.regDate = book.getRegDate();
//        this.regDate = LocalDateTime.parse(book.getRegDate());

        return this;
    }

    public static BookEditResponseDTO BookFactory(Book book){ // 정적 메소드로 객체 생성 -> 팩토리 패턴

        BookEditResponseDTO bookEditResponseDTO = new BookEditResponseDTO();

        bookEditResponseDTO.fromBook(book);

        return bookEditResponseDTO;
    }

}
