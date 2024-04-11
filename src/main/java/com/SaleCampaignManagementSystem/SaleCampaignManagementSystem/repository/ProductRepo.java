package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
