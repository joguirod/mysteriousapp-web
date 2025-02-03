package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.persistence.repositories.ProductCategoryRepository;

import java.util.List;

public class GetCategoriesSoldByMonthUseCase {

    private final ProductCategoryRepository productCategoryRepository;

    public GetCategoriesSoldByMonthUseCase(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<Object[]> execute(Integer month) {
        return productCategoryRepository.getTotalSoldByMonth(month);
    }
}
