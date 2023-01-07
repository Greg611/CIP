package com.cip.moviedatabase.Model;

import java.time.LocalDate;
import java.util.UUID;

public class CastMember {
    private UUID id;
    private String name;
    private String alias;
    private LocalDate dob;

    public CastMember() {
    }

    public CastMember(UUID id, String name, String alias, LocalDate dob) {
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

    public UUID getId() {
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

    @Override
    public String toString(){
        String result = "{\n\tid: " + this.id + "\n\tname: " + this.name + "\n\talias: " + this.alias + "\n\tdob: " +
                this.dob + "\n}";
        return result;
    }
}
