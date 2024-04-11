package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.contoller;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.CampaignDto;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.PaginationDto;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.Product;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.services.SaleCampaignServices;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("saleCamping")
public class SaleController {
    @Autowired
    SaleCampaignServices saleCampaignServices;
    @PostMapping("add")
    public List<Product> insertData(@RequestBody List<Product> products){
        return saleCampaignServices.insertProduct(products);
    }
    @PostMapping("addCampaign")
    public CampaignDto addCampaign(@RequestBody CampaignDto campaignDto){
        return saleCampaignServices.addCampagn(campaignDto);
    }
    @GetMapping("getAll")
    public PaginationDto getAllData(@RequestParam int page,@RequestParam int pageSize){
        return saleCampaignServices.getAllProducts(page,pageSize);
    }

}
