package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CampaignRepo extends JpaRepository<Campaign,Integer> {
    Campaign[] findByStartDate(LocalDate currentDate);
}
