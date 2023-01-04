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
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
        return MoviesXML.readAllMovies();
    }



    @GetMapping
    @RequestMapping("listAllUsers")
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
    @RequestMapping("modifyAdmin")
    public void postModifyAdmin(@RequestBody UUID adminId, @RequestBody Admin modifiedAdmin){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.modifyAdmin(modifiedAdmin);
    }

    @PostMapping
    @RequestMapping("deleteAdmin")
    public void postDeleteAdmin(@RequestBody UUID adminId, @RequestBody Admin deletedAdmin){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.deleteAdmin(deletedAdmin);
    }

    @PostMapping
    @RequestMapping("newUser")
    public void postNewUser(@RequestBody User newUser){
        UsersXML.saveUser(newUser);
    }

    @PostMapping
    @RequestMapping("modifyUser")
    public void postModifyUser(@RequestBody UUID adminId, @RequestBody Admin modifiedUser){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.modifyUser(modifiedUser);
    }

    @PostMapping
    @RequestMapping("deleteUser")
    public void postDeleteUser(@RequestBody UUID adminId, @RequestBody Admin deletedUser){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.deleteUser(deletedUser);
    }

    @PostMapping
    @RequestMapping("newMovie")
    public void postNewMovie(@RequestBody UUID adminId, @RequestBody Movie newMovie){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.createMovie(newMovie);
    }

    @PostMapping
    @RequestMapping("modifyMovie")
    public void postModifyMovie(@RequestBody UUID adminId, @RequestBody Movie modifiedMovie){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.modifyMovie(modifiedMovie);
    }

    @PostMapping
    @RequestMapping("deleteMovie")
    public void postDeleteMovie(@RequestBody UUID adminId, @RequestBody Movie deletedMovie){
        Admin admin = AdminXML.readAdmin(adminId);
        admin.deleteMovie(deletedMovie);
    }


    @PostMapping
    @RequestMapping("newCollection")
    public void postNewMovie(@RequestBody UUID userId, @RequestBody String newCollectionName){
        User user = UsersXML.readUser(userId);
        user.userCreateCollection(newCollectionName);
    }

    @PostMapping
    @RequestMapping("modifyCollectionName")
    public void postModifyCollectionName(@RequestBody UUID userId, @RequestBody String newCollectionName, @RequestBody UUID collectionID){
        User user = UsersXML.readUser(userId);
        user.userChangeCollectionName(collectionID,newCollectionName);
    }

    @PostMapping
    @RequestMapping("addMovieToCollection")
    public void postAddMovieToCollection(@RequestBody UUID userId, @RequestBody UUID collectionId, @RequestBody UUID movieId){
        User user = UsersXML.readUser(userId);
        user.userAddMovieToCollection(collectionId, movieId);
    }

    @PostMapping
    @RequestMapping("deleteMovieToCollection")
    public void postDeleteMovieToCollection(@RequestBody UUID userId, @RequestBody UUID collectionId, @RequestBody UUID movieId){
        User user = UsersXML.readUser(userId);
        user.userDeleteMovieFromCollection(collectionId, movieId);
    }

    @PostMapping
    @RequestMapping("deleteCollection")
    public void postDeleteCollection(@RequestBody UUID userId, @RequestBody UUID collectionId){
        User user = UsersXML.readUser(userId);
        user.userDeleteCollection(collectionId);
    }
}
