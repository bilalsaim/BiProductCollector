package com.biorganization.biproject.service.loader;

import com.biorganization.biproject.model.Album;
import com.biorganization.biproject.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Timed
public class AlbumLoader implements ProductLoader{

    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    private final String albumAPI = "https://itunes.apple.com/search?term={search}&limit={limit}&entity=album";

    @Timed
    public List<Product> get(String search, int limit) throws Exception {
        String result = restTemplate.getForObject(albumAPI, String.class, search, limit);
        JsonNode rootNode = mapper.readTree(result).get("results");
        return mapper.readValue(mapper.treeAsTokens(rootNode), new TypeReference<List<Album>>(){});
    }

    @Autowired
    public void restTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
}
