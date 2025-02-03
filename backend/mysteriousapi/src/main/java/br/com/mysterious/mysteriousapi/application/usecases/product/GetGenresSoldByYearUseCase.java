package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.persistence.repositories.GenreRepository;

import java.util.List;

public class GetGenresSoldByYearUseCase {

    private final GenreRepository genreRepository;

    public GetGenresSoldByYearUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Object[]> execute(String year) {
        return genreRepository.getTotalSoldByYear(year);
    }
}

