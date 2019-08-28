package com.biorganization.biproject.service;

import com.biorganization.biproject.model.Product;
import com.biorganization.biproject.model.ProductType;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(String search, int limit);
    List<Product> getProduct(ProductType productType, String search, int limit);
}
