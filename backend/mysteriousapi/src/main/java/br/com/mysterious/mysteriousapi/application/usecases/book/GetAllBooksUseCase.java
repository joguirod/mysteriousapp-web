package br.com.mysterious.mysteriousapi.application.usecases.book;

import br.com.mysterious.mysteriousapi.domain.entities.book.Book;
import br.com.mysterious.mysteriousapi.persistence.repositories.BookRepository;

import java.util.List;

public class GetAllBooksUseCase {

    private final BookRepository bookRepository;

    public GetAllBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> execute() {
        return bookRepository.findAll();
    }
}
