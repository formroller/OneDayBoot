package com.example.onedayboot.dto;

import com.example.onedayboot.entity.BookLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class BookLogReadResponseDTO { // 책 기록 응답 DTO 객체 - BookReadResponseDTO의 하위 정보로 사용
    private Integer bookLogId;
    private String comment;
    private Integer page;
    private LocalDateTime regDate;

    private String displayComment;

    private BookLogReadResponseDTO fromBookLog(BookLog bookLog){
        this.bookLogId = bookLog.getBookLogId();
        this.comment = bookLog.getComment();;
        this.page = bookLog.getPage();
        this.regDate = bookLog.getRegDate();

        this.displayComment=(this.page == null ? "" : "(p."+String.valueOf(this.page)+".)")+this.comment; // 책 기록 보여주기 위한 가상 필드

        return this;
    }

    public static BookLogReadResponseDTO BookLogFactory(BookLog bookLog){
        BookLogReadResponseDTO bookLogReadResponseDTO = new BookLogReadResponseDTO();

        bookLogReadResponseDTO.fromBookLog(bookLog);

        return bookLogReadResponseDTO;
    }


}
