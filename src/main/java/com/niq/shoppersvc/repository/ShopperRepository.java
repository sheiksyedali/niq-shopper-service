package com.niq.shoppersvc.repository;

import com.niq.shoppersvc.dto.ShopperProductsResponseDTO;

public interface ShopperRepository {

    ShopperProductsResponseDTO getProductsByShopperId(String shopperId, String category, String brand, Integer size);
}
