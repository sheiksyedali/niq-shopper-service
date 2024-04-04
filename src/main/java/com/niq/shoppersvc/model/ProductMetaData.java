package com.niq.shoppersvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class ProductMetaData {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @NotNull
    @Size(min = 3)
    @Column(unique = true)
    private String productId;

    @NotNull()
    @Size(min = 3, message = "category should have min 3 length")
    private String category;

    @NotNull()
    @Size(min = 3, message = "brand should have min 3 length")
    private String brand;

    @OneToMany(mappedBy = "productMetaData", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ShopperPersonalizedProducts> shopperPersonalizedProducts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public List<ShopperPersonalizedProducts> getShopperPersonalizedProducts() {
        return shopperPersonalizedProducts;
    }

    public void setShopperPersonalizedProducts(List<ShopperPersonalizedProducts> shopperPersonalizedProducts) {
        this.shopperPersonalizedProducts = shopperPersonalizedProducts;
    }

    @Override
    public String toString() {
        return "ProductMetaData{" +
                "productId='" + productId + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
