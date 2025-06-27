package com.api.middleware.model;

public class Supplier {
    private Long id;
    private String name;
    private String contactInfo;
    private String products;

    public Supplier() {
    }

    public Supplier(Long id, String name, String contactInfo, String products) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getProducts() {
        return products;
    }
}
