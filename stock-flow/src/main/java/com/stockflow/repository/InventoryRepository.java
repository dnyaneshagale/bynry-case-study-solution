package com.stockflow.repository;

import com.stockflow.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("""
            SELECT i FROM Inventory i
            JOIN i.product p
            JOIN p.productType pt
            JOIN i.warehouse w
            WHERE w.company.id = :companyId
            AND i.hasRecentSales = true
            AND i.currentStock <= pt.lowStockThreshold
            """)
    List<Inventory> findLowStockItemsByCompanyId(@Param("companyId") Long companyId);
}