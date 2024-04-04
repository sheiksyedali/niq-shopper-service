package com.niq.shoppersvc.repository;

import com.niq.shoppersvc.model.ShopperPersonalizedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopperPersonalizedProductsRepository extends JpaRepository<ShopperPersonalizedProducts, Integer> {
    ShopperPersonalizedProducts findFirstByShopperId(String shopperId);
}
