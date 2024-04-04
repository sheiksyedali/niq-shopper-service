package com.niq.shoppersvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class ShopperPersonalizedProducts {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @NotNull
    private String shopperId;

    @NotNull
    @Column(precision = 20, scale = 14)
    private BigDecimal relevancyScore;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private ProductMetaData productMetaData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public BigDecimal getRelevancyScore() {
        return relevancyScore;
    }

    public void setRelevancyScore(BigDecimal relevancyScore) {
        this.relevancyScore = relevancyScore;
    }

    public ProductMetaData getProductMetaData() {
        return productMetaData;
    }

    public void setProductMetaData(ProductMetaData productMetaData) {
        this.productMetaData = productMetaData;
    }
}
