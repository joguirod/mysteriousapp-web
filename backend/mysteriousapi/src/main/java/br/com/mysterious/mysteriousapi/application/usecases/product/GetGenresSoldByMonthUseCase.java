package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.persistence.repositories.GenreRepository;

import java.util.List;

public class GetGenresSoldByMonthUseCase {

    private final GenreRepository genreRepository;

    public GetGenresSoldByMonthUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Object[]> execute(Integer month) {
        return genreRepository.getTotalSoldByMonth(month);
    }
}
