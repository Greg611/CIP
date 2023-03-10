package com.cip.moviedatabase.Model;

import java.util.UUID;

public class Tags {
    private UUID id;
    private String name;

    public Tags(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tags(String name){
        this.name = name;
    }

    public Tags() {
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

    @Override
    public String toString(){
        String result ="{\n\tid: " + this.id + "\n\tname: " + this.name + "\n}";
        return result;
    }
}
