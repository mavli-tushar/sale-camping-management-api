package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDisDto {
    private int productId;
    private  int discount;
}
