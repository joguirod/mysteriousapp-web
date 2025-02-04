package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

public class GetTotalValueByYearUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetTotalValueByYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public Double execute(String year) {
        return mysteriousOrderRepository.getTotalValueByYear(year);
    }
}
