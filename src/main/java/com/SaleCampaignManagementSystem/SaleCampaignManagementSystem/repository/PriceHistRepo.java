package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.PriceHistory;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistRepo extends JpaRepository<PriceHistory,Integer> {
    PriceHistory findByProduct(Product product);
}
