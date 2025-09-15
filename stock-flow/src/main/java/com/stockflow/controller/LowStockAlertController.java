package com.stockflow.controller;

import com.stockflow.dto.LowStockAlertsResponse;
import com.stockflow.service.LowStockAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LowStockAlertController {

    private final LowStockAlertService lowStockAlertService;
    
    /**
     * Endpoint to get low-stock alerts for a company
     * 
     * @param companyId The ID of the company
     * @return A ResponseEntity containing the low-stock alerts
     */
    @GetMapping("/companies/{companyId}/alerts/low-stock")
    public ResponseEntity<LowStockAlertsResponse> getLowStockAlerts(@PathVariable Long companyId) {
        LowStockAlertsResponse response = lowStockAlertService.getLowStockAlerts(companyId);
        return ResponseEntity.ok(response);
    }
}