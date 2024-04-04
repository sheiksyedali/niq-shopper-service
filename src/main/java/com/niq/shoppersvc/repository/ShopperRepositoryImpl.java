package com.niq.shoppersvc.repository;

import com.niq.shoppersvc.dto.ShopperProductDataDTO;
import com.niq.shoppersvc.dto.ShopperProductsResponseDTO;
import com.niq.shoppersvc.exception.ShopperDataNotFoundException;
import com.niq.shoppersvc.model.ProductMetaData;
import com.niq.shoppersvc.model.ShopperPersonalizedProducts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopperRepositoryImpl implements ShopperRepository {

    private Logger logger = LoggerFactory.getLogger(ShopperRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    private ShopperPersonalizedProductsRepository shopperPersonalizedProductsRepository;

    public ShopperRepositoryImpl(ShopperPersonalizedProductsRepository shopperPersonalizedProductsRepository){
        this.shopperPersonalizedProductsRepository = shopperPersonalizedProductsRepository;
    }
    @Override
    public ShopperProductsResponseDTO getProductsByShopperId(String shopperId, String category, String brand, Integer size) {

        ShopperPersonalizedProducts perProd = shopperPersonalizedProductsRepository.findFirstByShopperId(shopperId);
        if(perProd == null){
            logger.error("Shopper list not found. shopperId: "+shopperId);
            throw new ShopperDataNotFoundException("Not found. shopperId: "+shopperId);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ShopperPersonalizedProducts> criteriaQuery = criteriaBuilder.createQuery(ShopperPersonalizedProducts.class);

        Root<ShopperPersonalizedProducts> shopperPersonalizedProductsRoot = criteriaQuery.from(ShopperPersonalizedProducts.class);
        Join<ShopperPersonalizedProducts, ProductMetaData> productMetaDataJoin = shopperPersonalizedProductsRoot.join("productMetaData", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(shopperPersonalizedProductsRoot.get("shopperId"), shopperId));

        if(category != null && !category.isEmpty()){
            predicates.add(criteriaBuilder.equal(productMetaDataJoin.get("category"), category));
        }

        if(brand != null && !brand.isEmpty()){
            predicates.add(criteriaBuilder.equal(productMetaDataJoin.get("brand"), brand));
        }

        Integer limit = 10;
        if(size != null && size > 0 && size <= 100){
            limit = size;
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<ShopperPersonalizedProducts> typedQuery = entityManager.createQuery(criteriaQuery).setMaxResults(limit);
        List<ShopperPersonalizedProducts> personalizedProducts =  typedQuery.getResultList();

        ShopperProductsResponseDTO shopperProductsResponseDTO = new ShopperProductsResponseDTO();
        shopperProductsResponseDTO.setShopperId(shopperId);

        List<ShopperProductDataDTO> products = new ArrayList<>();
        for(ShopperPersonalizedProducts sp : personalizedProducts){
            ProductMetaData productMetaData = sp.getProductMetaData();

            ShopperProductDataDTO shopperProductDataDTO = new ShopperProductDataDTO();
            shopperProductDataDTO.setProductId(productMetaData.getProductId());
            shopperProductDataDTO.setRelevancyScore(sp.getRelevancyScore());
            shopperProductDataDTO.setCategory(productMetaData.getCategory());
            shopperProductDataDTO.setBrand(productMetaData.getBrand());

            products.add(shopperProductDataDTO);
        }

        shopperProductsResponseDTO.setProducts(products);
        return shopperProductsResponseDTO;
    }
}
