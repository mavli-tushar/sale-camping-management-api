package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="pricehistory")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int priceHid;
    @ManyToOne
    @JoinColumn(name ="pid")
    private Product product;
    private double oldPrice;
    private double currentPrice;

}
