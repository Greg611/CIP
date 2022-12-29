package com.cip.moviedatabase.Model;

import java.util.LinkedList;
import java.util.UUID;

public class Collection {
    private UUID id;

    private String name;

    private LinkedList<Movie> movies;

    public Collection(UUID id, String name, LinkedList<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public Collection(String name, LinkedList<Movie> movies) {
        this.name = name;
        this.movies = movies;
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

    public LinkedList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(LinkedList<Movie> movies) {
        this.movies = movies;
    }
}
