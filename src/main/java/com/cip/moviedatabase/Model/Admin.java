package com.cip.moviedatabase.Model;

import java.time.LocalDate;
import java.util.LinkedList;

public class Admin extends User{
    public Admin(Integer id, String name, String password, LocalDate dob, String email) {
        super(id, name, password, dob, email);
    }

    public void createMovie(String title, Float imdb, Integer duration, LocalDate release_date, LinkedList<CastMember> directors, LinkedList<CastMember> cast, LinkedList<Tags> tags){
        Movie newMovie = new Movie(title, imdb, duration, release_date, directors, cast, tags);
    }
}
