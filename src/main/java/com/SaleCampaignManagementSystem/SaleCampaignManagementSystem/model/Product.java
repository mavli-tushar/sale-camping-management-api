package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;
    private String title;
    private double current_price;
    private String description;
    private double mrp;
    private double discount;
    private int inventoryCount;

}
