package br.com.mysterious.mysteriousapi.persistence.repositories;

import br.com.mysterious.mysteriousapi.domain.entities.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT oi.category.description, SUM(oi.quantity) as totalVendas " +
            "FROM OrderItem oi " +
            "WHERE EXTRACT(YEAR FROM oi.mysteriousOrder.orderDate) = :year " +
            "GROUP BY oi.category.description " +
            "ORDER BY totalVendas DESC")
    List<Object[]> getTotalSoldByYear(String year);

    @Query("SELECT oi.category.description, SUM(oi.quantity) as totalVendas " +
            "FROM OrderItem oi " +
            "WHERE EXTRACT(MONTH FROM oi.mysteriousOrder.orderDate) = :month " +
            "GROUP BY oi.category.description " +
            "ORDER BY totalVendas DESC")
    List<Object[]> getTotalSoldByMonth(Integer month);

    @Query("SELECT oi.category.description, SUM(oi.quantity) as totalVendas " +
            "FROM OrderItem oi " +
            "WHERE EXTRACT(MONTH FROM oi.mysteriousOrder.orderDate) = :month " +
            "AND EXTRACT(YEAR FROM oi.mysteriousOrder.orderDate) = :year " +
            "GROUP BY oi.category.description " +
            "ORDER BY totalVendas DESC")
    List<Object[]> getTotalSoldByEpoch(Integer month, String year);
}
