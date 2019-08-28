package com.biorganization.biproject.model;

public enum ProductType {
    ALBUM("Album"),
    BOOK("Book");

    private String value;

    ProductType(String value) {
        this.value = value;
    }

    public static ProductType fromValue(String value) {
        for (ProductType product : values()) {
            if (product.value.equalsIgnoreCase(value)) {
                return product;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown enum type %s", value));
    }
}
