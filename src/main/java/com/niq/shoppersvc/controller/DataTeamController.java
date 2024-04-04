package com.niq.shoppersvc.controller;

import com.niq.shoppersvc.model.ProductMetaData;
import com.niq.shoppersvc.dto.ShopperPersonalizedProductsRequestDTO;
import com.niq.shoppersvc.service.DataTeamService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataTeamController {
    private Logger logger = LoggerFactory.getLogger(DataTeamController.class);
    private DataTeamService dataTeamService;

    public DataTeamController(DataTeamService dataTeamService){
        this.dataTeamService = dataTeamService;
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('DATA')")
    public ResponseEntity<Object> saveProductMetaData(@Valid @RequestBody ProductMetaData productMetaData){
        logger.debug("Request to add product: "+productMetaData);
        return dataTeamService.saveProductMetaData(productMetaData);
    }

    @PostMapping("/shopper/personalized-product")
    @PreAuthorize("hasRole('DATA')")
    public ResponseEntity<Object> saveShopperPersonalizedProducts(@RequestBody ShopperPersonalizedProductsRequestDTO shopperPersonalizedProductsRequestDTO){
        logger.debug("Request to add shopper personalized product: "+shopperPersonalizedProductsRequestDTO);
        return dataTeamService.saveShopperPersonalizedProducts(shopperPersonalizedProductsRequestDTO);
    }
}
