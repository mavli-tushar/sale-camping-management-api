package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private CampaignDisDto[] campaignDisDtos;

}
