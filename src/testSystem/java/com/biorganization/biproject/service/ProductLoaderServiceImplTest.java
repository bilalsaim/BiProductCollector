package com.biorganization.biproject.service;

import com.biorganization.biproject.model.ProductType;
import com.biorganization.biproject.service.loader.AlbumLoader;
import com.biorganization.biproject.service.loader.BookLoader;
import com.biorganization.biproject.service.loader.ProductLoader;
import com.biorganization.biproject.service.loader.ProductLoaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductLoaderServiceImplTest {

    @Autowired
    ProductLoaderService classUnderTest;

    @Test
    public void getLoaderAlbumTest(){
        ProductLoader productLoader = classUnderTest.getLoader(ProductType.ALBUM);
        assertEquals(productLoader.getClass(), AlbumLoader.class);
    }

    @Test
    public void getLoaderBookTest(){
        ProductLoader productLoader = classUnderTest.getLoader(ProductType.BOOK);
        assertEquals(productLoader.getClass(), BookLoader.class);
    }
}
