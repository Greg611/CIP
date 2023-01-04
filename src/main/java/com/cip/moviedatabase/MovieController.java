package com.cip.moviedatabase;

import com.cip.moviedatabase.Model.Admin;
import com.cip.moviedatabase.Model.Collection;
import com.cip.moviedatabase.Model.Movie;
import com.cip.moviedatabase.Model.User;
import com.cip.moviedatabase.XMLHandler.AdminXML;
import com.cip.moviedatabase.XMLHandler.CollectionsXML;
import com.cip.moviedatabase.XMLHandler.MoviesXML;
import com.cip.moviedatabase.XMLHandler.UsersXML;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {
    @GetMapping
    @RequestMapping("getMovie")
    public Movie getMovie(@RequestBody String movieId){
        return MoviesXML.readMovie(UUID.fromString(movieId));
    }

    @GetMapping
    @RequestMapping("listAllMovies")
    public List<Movie> getAllMovies(){
        return MoviesXML.readAllMovies();
    }



    @GetMapping
    @RequestMapping("listALlUsers")
    public List<User> getAllUser(){
        return UsersXML.readAllUsers();

    }

    @GetMapping
    @RequestMapping("listCollections")
    public LinkedList<Collection> getUsersCollection(@RequestBody String userId) {
        return CollectionsXML.readUserAllCollection(UUID.fromString(userId));
    }

    @GetMapping
    @RequestMapping("getCollection")
    public Collection getCollection(@RequestBody String userId, @RequestBody String collectionId){
        return CollectionsXML.readCollection(UUID.fromString(collectionId), UUID.fromString(userId));
    }

    @PostMapping
    @RequestMapping("newAdmin")
    public void postNewAdmin(@RequestBody UUID adminId, @RequestBody Admin newAdmin){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.createAdmin(newAdmin);
    }

    @PostMapping
    @RequestMapping("newUser")
    public void postNewUser(@RequestBody User newUser){
        UsersXML.saveUser(newUser);
    }

}
