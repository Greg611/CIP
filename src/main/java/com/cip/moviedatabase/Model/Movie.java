package com.cip.moviedatabase.Model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;

public class Movie {
    private UUID id;
    private String title;
    private Float imdb;
    private Integer duration;
    private LocalDate releaseDate;
    private LinkedList<CastMember> directors;
    private LinkedList<CastMember> cast;
    private LinkedList<Tags> tags;

    public Movie(UUID id, String title, Float imdb, Integer duration, LocalDate releaseDate, LinkedList<CastMember> directors, LinkedList<CastMember> cast, LinkedList<Tags> tags) {
        this.id = id;
        this.title = title;
        this.imdb = imdb;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.directors = directors;
        this.cast = cast;
        this.tags = tags;
    }

    public Movie(String title, Float imdb, Integer duration, LocalDate releaseDate, LinkedList<CastMember> directors, LinkedList<CastMember> cast, LinkedList<Tags> tags) {
        this.title = title;
        this.imdb = imdb;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.directors = directors;
        this.cast = cast;
        this.tags = tags;
    }

    public Movie() {
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getImdb() {
        return imdb;
    }

    public void setImdb(Float imdb) {
        this.imdb = imdb;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate release_date) {
        this.releaseDate = release_date;
    }

    public LinkedList<CastMember> getDirectors() {
        return directors;
    }

    public void addDirectors(CastMember director) {
        this.directors.add(director);
    }

    public LinkedList<CastMember> getCast() {
        return cast;
    }

    public void addCast(CastMember cast) {
        this.cast.add(cast);
    }

    public LinkedList<Tags> getTags() {
        return tags;
    }

    public void addTags(Tags tag) {
        this.tags.add(tag);
    }
}
