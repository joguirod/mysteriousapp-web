package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.usecases.book.GetAllBooksUseCase;
import br.com.mysterious.mysteriousapi.persistence.repositories.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {

    @Bean
    GetAllBooksUseCase getAllBooksUseCase(BookRepository bookRepository) {
        return new GetAllBooksUseCase(bookRepository);
    }
}
