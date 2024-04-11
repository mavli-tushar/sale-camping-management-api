package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private int id;
    private double mrp;
    private double current_price;
    private double discount;
    private int inventoryCount;
}
