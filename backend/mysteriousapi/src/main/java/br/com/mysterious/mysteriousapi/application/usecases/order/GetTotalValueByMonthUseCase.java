package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

public class GetTotalValueByMonthUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetTotalValueByMonthUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public Double execute(Integer month) {
        return mysteriousOrderRepository.getTotalValueByMonth(month);
    }
}
