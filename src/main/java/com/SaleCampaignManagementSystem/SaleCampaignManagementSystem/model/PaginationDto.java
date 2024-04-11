package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto {
    private ProductDto[] productDtos;
    private int page;
    private int pageSize;
    private  int totalPage;

}
