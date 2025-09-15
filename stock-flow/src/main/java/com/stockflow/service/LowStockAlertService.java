package com.stockflow.service;

import com.stockflow.dto.LowStockAlertDto;
import com.stockflow.dto.LowStockAlertsResponse;
import com.stockflow.dto.SupplierDto;
import com.stockflow.exception.ResourceNotFoundException;
import com.stockflow.model.Inventory;
import com.stockflow.repository.CompanyRepository;
import com.stockflow.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LowStockAlertService {

    private final CompanyRepository companyRepository;
    private final InventoryRepository inventoryRepository;

    public LowStockAlertsResponse getLowStockAlerts(Long companyId) {
        // Verify company exists
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company not found with id: " + companyId);
        }

        // Get all low stock inventory items for the company
        List<Inventory> lowStockItems = inventoryRepository.findLowStockItemsByCompanyId(companyId);
        
        // Convert inventory items to DTOs
        List<LowStockAlertDto> alerts = lowStockItems.stream()
                .map(this::convertToAlertDto)
                .collect(Collectors.toList());
        
        // Create the response
        LowStockAlertsResponse response = new LowStockAlertsResponse();
        response.setAlerts(alerts);
        response.setTotalAlerts(alerts.size());
        
        return response;
    }
    
    private LowStockAlertDto convertToAlertDto(Inventory inventory) {
        // Calculate days until stockout based on average daily usage
        Integer daysUntilStockout = null;
        if (inventory.getProduct().getAverageDailyUsage() != null && 
            inventory.getProduct().getAverageDailyUsage() > 0) {
            daysUntilStockout = (int) Math.floor(
                inventory.getCurrentStock() / inventory.getProduct().getAverageDailyUsage()
            );
        }
        
        // Create the supplier DTO
        SupplierDto supplierDto = new SupplierDto(
            inventory.getProduct().getSupplier().getId(),
            inventory.getProduct().getSupplier().getName(),
            inventory.getProduct().getSupplier().getContactEmail()
        );
        
        // Create and return the alert DTO
        return new LowStockAlertDto(
            inventory.getProduct().getId(),
            inventory.getProduct().getName(),
            inventory.getProduct().getSku(),
            inventory.getWarehouse().getId(),
            inventory.getWarehouse().getName(),
            inventory.getCurrentStock(),
            inventory.getProduct().getProductType().getLowStockThreshold(),
            daysUntilStockout,
            supplierDto
        );
    }
}