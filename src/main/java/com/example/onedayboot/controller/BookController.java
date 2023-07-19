package com.example.onedayboot.controller;

import com.example.onedayboot.dto.*;
import com.example.onedayboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/create")
    public String create(){
        return "book/create";
    }

    @PostMapping("/create")
    public String insert(BookCreateDTO createDTO){
        Integer bookId = bookService.insert(createDTO);
        return String.format("redirect:/book/read/%s",bookId);
    }

    // read
//    @GetMapping("/read/{bookId}")
//    public ModelAndView read(@PathVariable Integer bookId){
//        ModelAndView mav = new ModelAndView(); // 스프링 데이터(Model) and 화면(View)을 함께 담을 수 있는 객체(Model 정보를 View에 전달)
//
//        try{
//            BookReadResponseDTO bookReadResponseDTO = bookService.read(bookId);
//            mav.addObject("bookReadResponseDTO", bookReadResponseDTO); // 책 정보 잘 읽은 것 보장
//            mav.setViewName("book/read");
//        }catch (NoSuchElementException ex){
//            mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
//            mav.addObject("message", "책 정보가 없습니다.");
//            mav.addObject("location", "/book");
//            mav.setViewName("common/error/422");
//        }
//        return mav;
//    }
    @GetMapping("/read/{bookId}")
    public ModelAndView read(@PathVariable Integer bookId) throws NoSuchElementException{
        ModelAndView mav = new ModelAndView();

        BookReadResponseDTO bookReadResponseDTO = bookService.read(bookId);

        mav.addObject("bookReadResponseDTO", bookReadResponseDTO);

        mav.setViewName("book/read");

        return mav;
    }

    // update - view
    @GetMapping("/edit/{bookId}")
    public ModelAndView edit(@PathVariable Integer bookId) throws NoSuchElementException{
        ModelAndView mav = new ModelAndView();

        BookEditResponseDTO bookEditResponseDTO = bookService.edit(bookId);

        mav.addObject("bookEditResponseDTO", bookEditResponseDTO);

        mav.setViewName("book/edit");

        return mav;
    }

    @PostMapping("/edit/{bookId}")
    public ModelAndView update(@Validated BookEditDTO bookEditDTO, Errors errors){
        // 오류 있을 경우
        if(errors.hasErrors()){
            String errorMsg = errors // 오류 객체에서
                    .getFieldErrors() // 오류 목록 가져온 후
                    .stream() // 스트림으로 변경
                    .map(x-> x.getField()+" : "+x.getDefaultMessage()) // "필드명" : "오류 메세지" 형태로 각 항목 적용
                    .collect(Collectors.joining("\n")); // 줄바꿈 문자로 합치기

            return error422(errorMsg,
                    String.format("/book/edit/%s", bookEditDTO.getBookId())
                    );
        }
        // 오류 없을 경우
        bookService.update(bookEditDTO);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(String.format("redirect:/book/read/%s", bookEditDTO.getBookId()));
        return mav;
    }



    // delete
    @PostMapping("/delete")
    public String delete(Integer bookId) throws NoSuchElementException{
        bookService.delete(bookId);
        return "redirect:/book/list";
    }

    // bookList
    @GetMapping(value = {"/","/list"})
    public ModelAndView bookList(String title, Integer page, ModelAndView mav){
        mav.setViewName("/book/list");

        List<BookListResponseDTO> books = bookService.bookList(title, page);
        mav.addObject("books", books);
        return mav;
    }

    /*ExceptionHandler*/
    // 책 정보 없음 및 유효성 검사 실패 시
    private ModelAndView error422(String message, String location){
        ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        mav.addObject("message",message);
        mav.addObject("location", location);
        mav.setViewName("common/error/422");
        return mav;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex){
        return error422("책 정보 없음", "/book");
    }

}
