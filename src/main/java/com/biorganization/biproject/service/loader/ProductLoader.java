package com.biorganization.biproject.service.loader;

import com.biorganization.biproject.model.Product;

import java.util.List;

public interface ProductLoader {
    List<Product> get(String search, int limit) throws Exception;
}
