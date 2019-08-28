package com.biorganization.biproject.service.loader;

import com.biorganization.biproject.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductLoaderServiceImpl implements ProductLoaderService {

    private AlbumLoader albumLoader;
    private BookLoader bookLoader;

    private Map<ProductType, ProductLoader> productLoader = new HashMap<>();

    @PostConstruct
    public void initProductLoaderMap(){
        productLoader.put(ProductType.ALBUM, albumLoader);
        productLoader.put(ProductType.BOOK, bookLoader);
    }

    @Override
    public ProductLoader getLoader(ProductType productType){
        return productLoader.get(productType);
    }

    @Autowired
    public void bookLoader(BookLoader bookLoader){
        this.bookLoader = bookLoader;
    }

    @Autowired
    public void albumLoader(AlbumLoader albumLoader){
        this.albumLoader = albumLoader;
    }
}
