package com.cip.moviedatabase.Model;

import java.util.UUID;

public class Collection {
    private UUID id;
    private String name;

    public Collection(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Collection(String name) {
        this.name = name;
    }

    public Collection() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
