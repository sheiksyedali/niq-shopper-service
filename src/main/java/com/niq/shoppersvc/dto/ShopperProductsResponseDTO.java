package com.niq.shoppersvc.dto;

import java.util.List;

public class ShopperProductsResponseDTO {
    private String shopperId;

    private List<ShopperProductDataDTO> products;

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public List<ShopperProductDataDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ShopperProductDataDTO> products) {
        this.products = products;
    }
}
