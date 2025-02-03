package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.persistence.repositories.GenreRepository;

import java.util.List;

public class GetGenresSoldByEpochUseCase {

    private final GenreRepository genreRepository;

    public GetGenresSoldByEpochUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Object[]> execute(Integer month, String year) {
        return genreRepository.getTotalSoldByEpoch(month, year);
    }
}
