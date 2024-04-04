package com.niq.shoppersvc.service;

import com.niq.shoppersvc.model.ProductMetaData;
import com.niq.shoppersvc.model.ShopperPersonalizedProducts;
import com.niq.shoppersvc.dto.ShopperPersonalizedProductsRequestDTO;
import com.niq.shoppersvc.dto.ShopperProductDataDTO;
import com.niq.shoppersvc.repository.ProductMetaDataRepository;
import com.niq.shoppersvc.repository.ShopperPersonalizedProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataTeamService {

    private Logger logger = LoggerFactory.getLogger(DataTeamService.class);
    private ProductMetaDataRepository productMetaDataRepository;
    private ShopperPersonalizedProductsRepository shopperPersonalizedProductsRepository;

    public DataTeamService(ProductMetaDataRepository productMetaDataRepository, ShopperPersonalizedProductsRepository shopperPersonalizedProductsRepository){
        this.productMetaDataRepository = productMetaDataRepository;
        this.shopperPersonalizedProductsRepository = shopperPersonalizedProductsRepository;
    }

    public ResponseEntity<Object> saveProductMetaData(ProductMetaData productMetaData){
        productMetaDataRepository.save(productMetaData);
        logger.info("Product meta data added. Product id: "+productMetaData.getProductId());
        return ResponseEntity.created(null).build();
    }


    @Transactional
    public ResponseEntity<Object> saveShopperPersonalizedProducts(ShopperPersonalizedProductsRequestDTO shopperPersonalizedProductsRequestDTO){
        String shopperId = shopperPersonalizedProductsRequestDTO.getShopperId();
        for(ShopperProductDataDTO shopperProductDataDTO : shopperPersonalizedProductsRequestDTO.getShelf()){

            ProductMetaData productMetaData = productMetaDataRepository.findByProductId(shopperProductDataDTO.getProductId());

            ShopperPersonalizedProducts shopperPersonalizedProducts = new ShopperPersonalizedProducts();
            shopperPersonalizedProducts.setShopperId(shopperId);
            shopperPersonalizedProducts.setRelevancyScore(shopperProductDataDTO.getRelevancyScore());
            shopperPersonalizedProducts.setProductMetaData(productMetaData);

            shopperPersonalizedProductsRepository.save(shopperPersonalizedProducts);
        }
        logger.info("Shopper personalized product list added: shopperId: "+shopperId);
        return ResponseEntity.created(null).build();
    }

}
