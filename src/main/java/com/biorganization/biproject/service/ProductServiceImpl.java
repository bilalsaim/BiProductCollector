package com.biorganization.biproject.service;

import com.biorganization.biproject.model.Product;
import com.biorganization.biproject.model.ProductType;
import com.biorganization.biproject.service.loader.ProductLoader;
import com.biorganization.biproject.service.loader.ProductLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductLoaderService productLoaderService;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public List<Product> getProducts(String search, int limit){
        List<Product> products = new ArrayList<>();

        for(ProductType productType: ProductType.values()){
            products.addAll(getProduct(productType, search, limit));
        }

        return products.stream()
                .sorted(Comparator.comparing(Product::getTitle)).collect(Collectors.toList());
    }

    public List<Product> getProduct(ProductType productType, String search, int limit) {
        ProductLoader productLoader = productLoaderService.getLoader(productType);
        List<Product> product = new ArrayList<>();

        try {
            product = productLoader.get(search, limit).stream()
                    .sorted(Comparator.comparing(Product::getTitle)).limit(limit).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Failed to get product {} with error : {}", productType.name(), e.getMessage());
        }

        return product;
    }

    @Autowired
    public void productLoaderService(ProductLoaderService productLoaderService){
        this.productLoaderService = productLoaderService;
    }

}
