package com.example.onedayboot.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDTO { // DTO는 클라이언트의 HTTP 파라미터 담는 컨테이너 객체
    @NonNull
    private String title;

    @NonNull
    private Integer price;
}
