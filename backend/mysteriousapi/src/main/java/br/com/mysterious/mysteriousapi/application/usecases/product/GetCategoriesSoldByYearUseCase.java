package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.persistence.repositories.ProductCategoryRepository;

import java.util.List;

public class GetCategoriesSoldByYearUseCase {

    private final ProductCategoryRepository productCategoryRepository;

    public GetCategoriesSoldByYearUseCase(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<Object[]> execute(String year) {
        return productCategoryRepository.getTotalSoldByYear(year);
    }
}
