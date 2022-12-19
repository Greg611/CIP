package com.cip.moviedatabase.Model;

import java.time.LocalDate;

public class CastMember {
    private Integer id;
    private String name;
    private String alias;
    private LocalDate dob;

    public CastMember() {
    }

    public CastMember(Integer id, String name, String alias, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.dob = dob;
    }

    public CastMember(String name, String alias, LocalDate dob) {
        this.name = name;
        this.alias = alias;
        this.dob = dob;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
