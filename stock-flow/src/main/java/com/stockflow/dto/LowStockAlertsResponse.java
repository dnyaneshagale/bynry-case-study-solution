package com.stockflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LowStockAlertsResponse {
    private List<LowStockAlertDto> alerts;
    private Integer totalAlerts;
}