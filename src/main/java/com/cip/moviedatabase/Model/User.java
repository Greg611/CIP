package com.cip.moviedatabase.Model;

import java.time.LocalDate;

public class User {
    protected Integer id;
    protected String name;
    protected String password;
    protected LocalDate dob;
    protected String email;

    public User(Integer id, String name, String password, LocalDate dob, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.email = email;
    }

    public User(){
    }

    public User(String name, String password, LocalDate dob, String email) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}