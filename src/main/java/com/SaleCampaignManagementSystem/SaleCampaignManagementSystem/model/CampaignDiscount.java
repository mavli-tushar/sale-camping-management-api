package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="CampaignDiscount")
public class CampaignDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cdid;
    @ManyToOne
    @JoinColumn(name = "campaignId")
    private Campaign campaign;
    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;
    private double discount;
}
