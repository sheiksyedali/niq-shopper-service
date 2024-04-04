package com.niq.shoppersvc.controller;

import com.niq.shoppersvc.dto.ShopperProductsResponseDTO;
import com.niq.shoppersvc.service.EcommerceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

    private Logger logger = LoggerFactory.getLogger(EcommerceController.class);
    private final EcommerceService ecommerceService;

    public EcommerceController(EcommerceService ecommerceService){
        this.ecommerceService = ecommerceService;
    }

    @GetMapping(path = "/shopper/{shopperId}")
    @PreAuthorize("hasRole('DATA') or hasRole('ECOMM')")
    public ShopperProductsResponseDTO getProductsByShopperId(@PathVariable("shopperId") String shopperId,
                                                             @RequestParam(name = "category", required = false) String category,
                                                             @RequestParam(name = "brand", required = false) String brand,
                                                             @RequestParam(name = "size", required = false) Integer size) {
        logger.debug("Get products by shopper: shopperId: "+shopperId+"; category: "+category +"; brand: "+brand+"; size: "+size);
        return ecommerceService.getProductsByShopperId(shopperId,category,brand,size);

    }
}
