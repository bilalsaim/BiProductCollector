package com.biorganization.biproject.controller;

import com.biorganization.biproject.model.Product;
import com.biorganization.biproject.model.ProductType;
import com.biorganization.biproject.service.ProductService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/product")
@Timed
public class ProductController {

    private RestTemplate restTemplate;
    private ProductService productService;

    @Value("${search.limit}")
    private int searchLimit;

    @Timed(extraTags = { "performance", "products" })
    @RequestMapping(value = "/fetchAll/{search}", method = RequestMethod.GET)
    public List<Product> getProduct(@PathVariable String search) {
        return productService.getProducts(search, searchLimit);
    }

    @Timed(extraTags = { "performance", "product" })
    @RequestMapping(value = "/fetch/{productType}/{search}", method = RequestMethod.GET)
    public List<Product> getAlbum(@PathVariable String search, @PathVariable String productType) {
        ProductType productType1 = ProductType.fromValue(productType);
        return productService.getProduct(productType1, search, searchLimit);
    }

    @Autowired
    public void restTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void productService(ProductService productService){
        this.productService = productService;
    }

}
