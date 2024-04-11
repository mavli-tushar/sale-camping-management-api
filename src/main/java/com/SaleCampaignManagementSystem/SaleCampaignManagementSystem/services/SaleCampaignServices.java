package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.services;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.model.*;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository.CampaignDisRepo;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository.CampaignRepo;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository.PriceHistRepo;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.repository.ProductRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleCampaignServices {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private PriceHistRepo priceHistRepo;
    @Autowired
    private CampaignRepo campaignRepo;
    @Autowired
    private CampaignDisRepo campaignDisRepo;

        public List<Product> insertProduct(List<Product> products){
            for(Product product: products){
                double mrp=product.getMrp();
                double discount=product.getDiscount();
                double currentPrice=mrp-(mrp*discount/100);
                product.setCurrent_price(currentPrice);
                productRepo.save(product);

                PriceHistory priceHistory=new PriceHistory();
                priceHistory.setCurrentPrice(currentPrice);
                priceHistory.setOldPrice(mrp);
                priceHistory.setProduct(product);
                priceHistRepo.save(priceHistory);
            }
            return products;
        }
        public PaginationDto getAllProducts(int page , int pageSize){
            Page<Product> productPage=productRepo.findAll(PageRequest.of(page-1,pageSize));
            int totalPage=productPage.getTotalPages();
            ProductDto[] productDtos=new ProductDto[pageSize];
            int i=0;
            for(Product product: productPage){
                int id=product.getPid();
                double mrp=product.getMrp();
                double currentPrice=product.getCurrent_price();
                double discount=product.getDiscount();
                int inventory=product.getInventoryCount();
                productDtos[i++]=new ProductDto(id,mrp,currentPrice,discount,inventory);

            }
            return  new PaginationDto(productDtos,page,pageSize,totalPage);
        }



        public CampaignDto addCampagn( CampaignDto campaignDto){
            LocalDate startDate=campaignDto.getStartDate();
            LocalDate endDate=campaignDto.getEndDate();
            String title=campaignDto.getTitle();
            CampaignDisDto[] campaignDisDto=campaignDto.getCampaignDisDtos();

            Campaign campaign=new Campaign();
            campaign.setStartDate(startDate);
            campaign.setEndDate(endDate);
            campaign.setTitle(title);

            campaignRepo.save(campaign);

            for(CampaignDisDto campaignDisDto1: campaignDisDto){
                Product product=productRepo.findById(campaignDisDto1.getProductId()).get();
                double discount=campaignDisDto1.getDiscount();
                CampaignDiscount campaignDiscount= new CampaignDiscount();
                campaignDiscount.setCampaign(campaign);
                campaignDiscount.setDiscount(discount);
                campaignDiscount.setProduct(product);

                campaignDisRepo.save(campaignDiscount);
            }
            if(campaign.getStartDate().equals(LocalDate.now())){
                startCampaign(campaign);
            }
            return campaignDto;
        }
        public void startCampaign(Campaign campaign){
            CampaignDiscount[] campaignDiscounts=campaignDisRepo.findByCampaign(campaign);

            for (CampaignDiscount campaignDiscount: campaignDiscounts){
                    Product product=campaignDiscount.getProduct();
                    double discount=campaignDiscount.getDiscount();
                    double oldCurrentPrice=product.getCurrent_price();
                    double newCurrentPrice=oldCurrentPrice-(oldCurrentPrice*discount/100);
                    product.setCurrent_price(newCurrentPrice);

                    double mrp=product.getMrp();
                    double newdiscount=100-(newCurrentPrice*100)/mrp;
                    product.setDiscount(newdiscount);
                    productRepo.save(product);

                    PriceHistory priceHistory=priceHistRepo.findByProduct(product);
                    priceHistory.setProduct(product);
                    priceHistory.setCurrentPrice(newCurrentPrice);
                    priceHistory.setOldPrice(oldCurrentPrice);

                    priceHistRepo.save(priceHistory);


            }
            campaignRepo.save(campaign);
        }
        public void endCampaign(Campaign campaign) {
            CampaignDiscount[] campaignDiscounts=campaignDisRepo.findByCampaign(campaign);
            for(CampaignDiscount campaignDiscount: campaignDiscounts){
                Product product=campaignDiscount.getProduct();

                double discount=campaignDiscount.getDiscount();
                double newCurrentPrice=product.getCurrent_price();
                double oldCurrentPrice=(newCurrentPrice*1000)/(100-discount);
                product.setCurrent_price(oldCurrentPrice);

                double mrp=product.getMrp();
                double oldDiscount=100-(oldCurrentPrice*100)/mrp;
                product.setDiscount(oldDiscount);
                productRepo.save(product);

                PriceHistory priceHistory =priceHistRepo.findByProduct(product);
                priceHistory.setProduct(product);
                priceHistory.setCurrentPrice(newCurrentPrice);
                priceHistory.setOldPrice(oldCurrentPrice);
                priceHistRepo.save(priceHistory);

            }
                campaignRepo.save(campaign);
        }
        @Scheduled(cron="0 0 0 * * ?")
        public void scheduleMathod(){
            LocalDate currentDate=LocalDate.now();
            Campaign[] campaigns=campaignRepo.findByStartDate(currentDate);
            for(Campaign campaign: campaigns){
                LocalDate endDate=campaign.getEndDate();
                if(currentDate.isAfter(endDate)){
                    endCampaign(campaign);
                }
            }
            campaigns=campaignRepo.findByStartDate(currentDate);
            for (Campaign campaign: campaigns){
                startCampaign(campaign);
            }
        }


}
