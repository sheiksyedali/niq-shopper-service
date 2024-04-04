package com.niq.shoppersvc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ShopperPersonalizedProductsRequestDTO {

    @NotNull(message = "shopperId can not be null")
    @Size(min = 3, message = "shopperId should have min 3 length")
    private String shopperId;
    @NotNull(message = "shelf can not be null")
    @Size(min = 1, message = "shelf should have min 1 data")
    List<ShopperProductDataDTO> shelf;

    public List<ShopperProductDataDTO> getShelf() {
        return shelf;
    }

    public void setShelf(List<ShopperProductDataDTO> shelf) {
        this.shelf = shelf;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    @Override
    public String toString() {
        return "ShopperPersonalizedProductsRequestDTO{" +
                "shopperId='" + shopperId + '\'' +
                ", shelf=" + shelf +
                '}';
    }
}
