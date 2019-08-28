package com.biorganization.biproject.service;

import com.biorganization.biproject.model.Album;
import com.biorganization.biproject.model.Book;
import com.biorganization.biproject.model.Product;
import com.biorganization.biproject.model.ProductType;
import com.biorganization.biproject.service.loader.AlbumLoader;
import com.biorganization.biproject.service.loader.ProductLoaderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    ProductLoaderService productLoaderService;

    @InjectMocks
    ProductServiceImpl classUnderTest;

    @Mock
    AlbumLoader albumLoader;

    @Mock
    AlbumLoader bookLoader;

    private List<Product> albums;
    private List<Product> books;
    private int limit;

    @Before
    public void setUp(){
        limit = 5;
        albums = Arrays.asList(
                new Album("Wilder Mind", "Mumford & Sons"),
                new Album("O", "Teoman"),
                new Album("Ney", "Bilal")
        );

        books = Arrays.asList(
                new Book("Korkma Ben Varim", Arrays.asList("Murat Mentes")),
                new Book("Tutunamayanlar", Arrays.asList("Oguz Atay"))
        );

        when(productLoaderService.getLoader(ProductType.ALBUM)).thenReturn(albumLoader);
        when(productLoaderService.getLoader(ProductType.BOOK)).thenReturn(bookLoader);
    }

    @Test
    public void getProductTest() throws Exception {


        when(albumLoader.get("search", limit)).thenReturn(albums);

        List<Product> actual = classUnderTest.getProduct(ProductType.ALBUM, "search", limit);

        verify(productLoaderService, times(1)).getLoader(ProductType.ALBUM);
        assertEquals(albums.stream().sorted(Comparator.comparing(Product::getTitle)).collect(Collectors.toList()),
                actual);
    }

    @Test
    public void getProductLimitTest() throws Exception {
        int maxProduct = 2;
        when(albumLoader.get("search", maxProduct)).thenReturn(albums);

        List<Product> actual = classUnderTest.getProduct(ProductType.ALBUM, "search", maxProduct);

        verify(productLoaderService, times(1)).getLoader(ProductType.ALBUM);
        assertEquals(actual.size(), maxProduct);
    }

    @Test
    public void getProductErrorTest() throws Exception {

        when(albumLoader.get("search", limit)).thenThrow(new IOException("Couldn't parse"));

        List<Product> actual = classUnderTest.getProduct(ProductType.ALBUM, "search", limit);

        verify(productLoaderService, times(1)).getLoader(ProductType.ALBUM);
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void getProductsTest() throws Exception {

        List<Product> products = new ArrayList<>(albums);
        products.addAll(books);

        when(albumLoader.get("search", limit)).thenReturn(albums);
        when(bookLoader.get("search", limit)).thenReturn(books);

        List<Product> actual = classUnderTest.getProducts( "search", limit);

        verify(productLoaderService, times(1)).getLoader(ProductType.ALBUM);
        assertEquals(products.stream().sorted(Comparator.comparing(Product::getTitle)).collect(Collectors.toList()),
                actual);
    }

    @Test
    public void getProductsErrorTest() throws Exception {

        when(albumLoader.get("search", limit)).thenReturn(albums);
        when(bookLoader.get("search", limit)).thenThrow(new IOException("Couldn't parse"));

        List<Product> actual = classUnderTest.getProducts("search", limit);

        verify(productLoaderService, times(1)).getLoader(ProductType.ALBUM);
        assertEquals(albums.stream().sorted(Comparator.comparing(Product::getTitle)).collect(Collectors.toList()), actual);
    }

}
