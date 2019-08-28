package com.biorganization.biproject.service.loader;

import com.biorganization.biproject.model.ProductType;

public interface ProductLoaderService {
    ProductLoader getLoader(ProductType productType);
}
