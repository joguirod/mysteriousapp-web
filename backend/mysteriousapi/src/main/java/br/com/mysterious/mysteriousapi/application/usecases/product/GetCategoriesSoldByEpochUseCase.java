package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.persistence.repositories.ProductCategoryRepository;

import java.util.List;

public class GetCategoriesSoldByEpochUseCase {

    private final ProductCategoryRepository productCategoryRepository;

    public GetCategoriesSoldByEpochUseCase(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<Object[]> execute(Integer month, String year) {
        return productCategoryRepository.getTotalSoldByEpoch(month, year);
    }
}
