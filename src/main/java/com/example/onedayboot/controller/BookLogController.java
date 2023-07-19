package com.example.onedayboot.controller;

import com.example.onedayboot.dto.BookLogCreateDTO;
import com.example.onedayboot.dto.BookLogCreateResponseDTO;
import com.example.onedayboot.service.BookLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-log")
@RequiredArgsConstructor
public class BookLogController {
    private final BookLogService bookLogService;

    @PostMapping("/create")
    public ResponseEntity<BookLogCreateResponseDTO> insert(@RequestBody BookLogCreateDTO bookLogCreateDTO){
        BookLogCreateResponseDTO bookLogCreateResponseDTO = bookLogService.insert(bookLogCreateDTO);

        return ResponseEntity.ok(bookLogCreateResponseDTO);
    }
}
