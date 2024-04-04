package com.niq.shoppersvc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ShopperProductDataDTO {
    @NotNull()
    @Size(min = 3, message = "productId should have min 3 length")
    private String productId;

    @NotNull()
    private BigDecimal relevancyScore;

    private String category;

    private String brand;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getRelevancyScore() {
        return relevancyScore;
    }

    public void setRelevancyScore(BigDecimal relevancyScore) {
        this.relevancyScore = relevancyScore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ShopperProductDataDTO{" +
                "productId='" + productId + '\'' +
                ", relevancyScore=" + relevancyScore +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
