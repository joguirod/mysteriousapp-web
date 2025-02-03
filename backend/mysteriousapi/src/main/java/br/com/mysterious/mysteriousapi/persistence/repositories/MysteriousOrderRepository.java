package br.com.mysterious.mysteriousapi.persistence.repositories;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MysteriousOrderRepository extends JpaRepository<MysteriousOrder, UUID> {
    List<MysteriousOrder> findByMysteriousUser_MysteriousUserId(UUID mysteriousUserId);

    @Query("SELECT mo FROM MysteriousOrder mo WHERE EXTRACT(YEAR FROM mo.orderDate) = :year")
    List<MysteriousOrder> findByYear(String year);

    @Query("SELECT mo FROM MysteriousOrder mo WHERE EXTRACT(MONTH FROM mo.orderDate) = :month")
    List<MysteriousOrder> findByMonth(Integer month);

    @Query("SELECT mo FROM MysteriousOrder mo WHERE EXTRACT(MONTH FROM mo.orderDate) = :month AND EXTRACT(YEAR FROM mo.orderDate) = :year")
    List<MysteriousOrder> findByMonthAndYear(Integer month, String year);

    @Query("SELECT SUM(mo.totalValue) FROM MysteriousOrder mo WHERE EXTRACT(YEAR FROM mo.orderDate) = :year")
    Double getTotalValueByYear(String year);

    @Query("SELECT SUM(mo.totalValue) FROM MysteriousOrder mo WHERE EXTRACT(MONTH FROM mo.orderDate) = :month")
    Double getTotalValueByMonth(Integer month);

    @Query("SELECT SUM(mo.totalValue) FROM MysteriousOrder mo WHERE EXTRACT(MONTH FROM mo.orderDate) = :month AND EXTRACT(YEAR FROM mo.orderDate) = :year")
    Double getTotalValueByMonthAndYear(Integer month, String year);
}
