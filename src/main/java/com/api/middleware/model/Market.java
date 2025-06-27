package com.api.middleware.model;

public class Market {
    private Long id;
    private String name;
    private String location;
    private String type;

    public Market() {
    }

    public Market(Long id, String name, String location, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }
}
