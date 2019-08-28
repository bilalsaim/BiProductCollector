package com.biorganization.biproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album extends Product{

    @JsonProperty("artistName")
    private String artists;

    @JsonProperty("collectionName")
    public void setAlbumName(String albumName) {
        setTitle(albumName);
    }

    public Album() {
        setProductType(ProductType.ALBUM);
    }


    public Album(String albumName, String artists) {
        setTitle(albumName);
        this.artists = artists;
        setProductType(ProductType.ALBUM);
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", artists=" + artists +
                '}';
    }
}
