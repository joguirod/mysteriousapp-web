package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.usecases.book.GetAllBooksUseCase;
import br.com.mysterious.mysteriousapi.domain.entities.book.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final GetAllBooksUseCase getAllBooksUseCase;

    public BookController(GetAllBooksUseCase getAllBooksUseCase) {
        this.getAllBooksUseCase = getAllBooksUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(getAllBooksUseCase.execute(), HttpStatus.OK);
    }
}
