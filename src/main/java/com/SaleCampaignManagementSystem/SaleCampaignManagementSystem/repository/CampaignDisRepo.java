package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.Campaign;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.CampaignDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignDisRepo extends JpaRepository<CampaignDiscount,Integer> {
    CampaignDiscount[] findByCampaign(Campaign campaign);
}
