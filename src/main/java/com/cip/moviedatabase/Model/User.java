package com.cip.moviedatabase.Model;

import com.cip.moviedatabase.XMLHandler.CollectionsXML;
import com.cip.moviedatabase.XMLHandler.MoviesXML;
import com.cip.moviedatabase.XMLHandler.UsersXML;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.UUID;

public class User {
    protected UUID id;
    protected String name;
    protected String password;
    protected LocalDate dob;
    protected String email;
    protected LinkedList<Collection> collections = new LinkedList<>();

    public User(UUID id, String name, String password, LocalDate dob, String email) {
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

    public UUID getId() {
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

    public LinkedList<Collection> getCollections() {
        return collections;
    }

    public Movie readMovie(UUID id){
        return MoviesXML.readMovie(id);
    }

    public LinkedList<Movie> readAllMovies(){
        return MoviesXML.readAllMovies();
    }

    public User getMyAttributes(){
        return UsersXML.readUser(this.id);
    }

    public Boolean userCreateCollection(String name){
        Collection newCollection = new Collection(name, this.id);
        CollectionsXML.saveCollection(newCollection);
        return true;
    }

    public Boolean userDeleteCollection(UUID collectionId){
        CollectionsXML.deleteCollection(CollectionsXML.readCollection(collectionId, this.id));
        return true;
    }

    public Boolean userAddMovieToCollection(UUID collectionId, UUID movieId){
        int i=0;
        Collection collection = CollectionsXML.readCollection(collectionId, this.id);
        while(i<collection.getMovies().size()){
            if (collection.getMovies().get(i).getId().equals(movieId)){
                return false;
            }
            i++;
        }
        collection.getMovies().add(MoviesXML.readMovie(movieId));
        CollectionsXML.modifyCollection(collection);
        return true;
    }

    public Boolean userDeleteMovieFromCollection(UUID collectionId, UUID movieId){
        int i=0;
        Collection collection = CollectionsXML.readCollection(collectionId, this.id);
        while(i<collection.getMovies().size()){
            if (collection.getMovies().get(i).getId().equals(movieId)){
                return false;
            }
            i++;
        }
        collection.getMovies().remove(MoviesXML.readMovie(movieId));
        CollectionsXML.modifyCollection(collection);
        return true;
    }

    public Boolean userChangeCollectionName(UUID collectionId, String newName){
        Collection collection = CollectionsXML.readCollection(collectionId, this.id);
        collection.setName(newName);
        CollectionsXML.modifyCollection(collection);
        return true;
    }

    public Collection userGetCollection(UUID collectionId){
        return CollectionsXML.readCollection(collectionId, this.id);
    }
}
