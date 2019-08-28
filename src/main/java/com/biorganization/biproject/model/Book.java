package com.biorganization.biproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book extends Product{

    @JsonProperty("authors")
    private List<String> authors;

    public Book() {
        setProductType(ProductType.BOOK);
    }

    public Book(String bookTitle, List<String> authors) {
        setTitle(bookTitle);
        this.authors = authors;
        setProductType(ProductType.BOOK);
    }

    @JsonProperty("title")
    public void setBookTitle(String title) {
        setTitle(title);
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                '}';
    }
}
