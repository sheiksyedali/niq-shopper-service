package com.niq.shoppersvc.service;

import com.niq.shoppersvc.dto.ShopperProductsResponseDTO;
import com.niq.shoppersvc.repository.ShopperRepository;
import org.springframework.stereotype.Service;

@Service
public class EcommerceService {
    private ShopperRepository shopperRepository;

    public EcommerceService(ShopperRepository shopperRepository){
        this.shopperRepository = shopperRepository;
    }

    public ShopperProductsResponseDTO getProductsByShopperId(String shopperId, String category, String brand, Integer size){
        return shopperRepository.getProductsByShopperId(shopperId,category,brand,size);
    }
}
