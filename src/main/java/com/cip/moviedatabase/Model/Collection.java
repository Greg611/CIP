package com.cip.moviedatabase.Model;

public class Collection {
    private Integer id;
    private String name;

    public Collection(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Collection(String name) {
        this.name = name;
    }

    public Collection() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
