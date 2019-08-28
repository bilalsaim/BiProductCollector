package com.biorganization.biproject.service.loader;

import com.biorganization.biproject.model.Book;
import com.biorganization.biproject.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookLoader implements ProductLoader {

    private ObjectMapper mapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(BookLoader.class);
    private final String bookAPI = "https://www.googleapis.com/books/v1/volumes?q={search}&?maxResults={limit}";

    private RestTemplate restTemplate;

    public List<Product> get(String search, int limit) throws Exception {

        String result = restTemplate.getForObject(bookAPI, String.class, search, limit);
        List<Product> books = new ArrayList<>();

        JsonNode rootNode = mapper.readTree(result).get("items");
        rootNode.forEach(e->
        {
            try {
                books.add(mapper.readValue(mapper.treeAsTokens(e.get("volumeInfo")), Book.class));
            } catch (IOException err) {
                log.error("Failed to get volumeInfo : {}", err.getMessage());
            }
        });

        return books;
    }

    @Autowired
    public void restTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
}
