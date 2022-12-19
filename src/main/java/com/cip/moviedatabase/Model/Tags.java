package com.cip.moviedatabase.Model;

public class Tags {
    private Integer id;
    private String name;

    public Tags(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tags() {
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
