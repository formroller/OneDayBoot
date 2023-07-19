package com.example.onedayboot.dto;

import lombok.Getter;

@Getter
public class BookListResponseDTO {
    private Integer bookId;
    private String title;

    public BookListResponseDTO(Integer bookId, String title){
        // 필수가 아닌 항목이 추가될 수 있으므로 @AllArgs- 사용 안함 (코드가 의도 나타냄)
        this.bookId = bookId;
        this.title = title;
    }

}
