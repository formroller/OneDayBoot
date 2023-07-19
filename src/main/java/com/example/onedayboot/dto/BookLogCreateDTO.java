package com.example.onedayboot.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookLogCreateDTO {
    @NonNull
    @Positive
    private Integer bookId; // 책 고유 번호

    @NonNull
    private String comment;

    private Integer page;
}
