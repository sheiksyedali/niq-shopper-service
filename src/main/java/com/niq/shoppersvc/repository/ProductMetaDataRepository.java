package com.niq.shoppersvc.repository;

import com.niq.shoppersvc.model.ProductMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductMetaDataRepository extends JpaRepository<ProductMetaData, Integer> {
    @Query("SELECT p FROM ProductMetaData p WHERE p.productId = :productId")
    ProductMetaData findByProductId(String productId);
}
