package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

public class GetTotalValueByMonthYearUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetTotalValueByMonthYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public Double execute(Integer month, String year) {
        return mysteriousOrderRepository.getTotalValueByMonthAndYear(month, year);
    }
}
