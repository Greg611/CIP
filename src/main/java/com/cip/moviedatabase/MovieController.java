package com.cip.moviedatabase;

import com.cip.moviedatabase.Model.*;
import com.cip.moviedatabase.Service.Service;
import com.cip.moviedatabase.XMLHandler.AdminXML;
import com.cip.moviedatabase.XMLHandler.MoviesXML;
import com.cip.moviedatabase.XMLHandler.UsersXML;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {

    @PostMapping
    @RequestMapping("getMovie")
    public Movie getMovie(@RequestBody String movieId){
        return MoviesXML.readMovie(UUID.fromString(movieId));
    }

    @GetMapping
    @RequestMapping("listAllMovies")
    public List<Movie> getAllMovies(){
        return MoviesXML.readAllMovies();
    }

    @PostMapping
    @RequestMapping("listAllUsers")
    public List<User> getAllUser(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminGetAllUsers();
    }

    @PostMapping
    @RequestMapping("listCollections")
    public LinkedList<Collection> getUsersCollection(@RequestBody String userId) {
        User user = UsersXML.readUser(UUID.fromString(userId));
        return user.getCollections();
    }

    @PostMapping
    @RequestMapping("getCollection")
    public Collection getCollection(@RequestBody Map<String, String> ids){
        User user = UsersXML.readUser(UUID.fromString(ids.get("userId")));
        return user.userGetCollection(UUID.fromString(ids.get("collectionId")));
    }

    @PostMapping
    @RequestMapping("getUser")
    public User getUser(@RequestBody String adminId, @RequestBody String userId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminGetUser(UUID.fromString(userId));
    }

    @PostMapping
    @RequestMapping("listTags")
    public LinkedList<Tags> getTags(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminReadAllTags();
    }

    @PostMapping
    @RequestMapping("listCastMember")
    public LinkedList<CastMember> getCastMember(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminReadAllCastMember();
    }

    @PostMapping
    @RequestMapping("listAllAdmin")
    public List<Admin> getAllAdmin(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminGetAllAdmins();
    }

    @PostMapping
    @RequestMapping("newAdmin")
    public Boolean postNewAdmin(@RequestBody Map<String,String> request){
        Boolean response = Service.newAdmin(request);
        return response;
    }

    @PostMapping
    @RequestMapping(value = "modifyAdmin")
    public Boolean postModifyAdmin(@RequestBody UUID adminId, @RequestBody Admin modifiedAdmin){
        Admin admin = AdminXML.readAdmin(adminId);
        return admin.adminModifyAdmin(modifiedAdmin);
    }

    @PostMapping
    @RequestMapping("deleteAdmin")
    public Boolean postDeleteAdmin(@RequestBody UUID adminId, @RequestBody Admin deletedAdmin){
        Admin admin = AdminXML.readAdmin(adminId);
        return admin.adminDeleteAdmin(deletedAdmin);
    }

    @PostMapping
    @RequestMapping("newUser")
    public Boolean postNewUser(@RequestBody User newUser){
        return UsersXML.saveUser(newUser);
    }

    @PostMapping
    @RequestMapping("modifyUser")
    public Boolean postModifyUser(@RequestBody Map<String, String> request){
        Boolean response = Service.modifyUser(request);
        return response;
    }

    @PostMapping
    @RequestMapping("deleteUser")
    public Boolean postDeleteUser(@RequestBody Map<String, String> request){
        Boolean response = Service.deleteUser(request);
        return response;
    }

    @PostMapping
    @RequestMapping("newMovie")
    public Boolean postNewMovie(@RequestBody Map<String, String> request){
        Boolean response = Service.newMovie(request);
        return response;
    }

    @PostMapping
    @RequestMapping("modifyMovie")
    public Boolean postModifyMovie(@RequestBody Map<String, String> request){
        Boolean response = Service.modifyMovie(request);
        return response;
    }

    @PostMapping
    @RequestMapping("deleteMovie")
    public Boolean postDeleteMovie(@RequestBody Map<String, String> request){
        Boolean response = Service.deleteMovie(request);
        return response;
    }


    @PostMapping
    @RequestMapping("newCollection")
    public Boolean postNewCollection(@RequestBody UUID userId, @RequestBody String newCollectionName){
        User user = UsersXML.readUser(userId);
        return user.userCreateCollection(newCollectionName);
    }

    @PostMapping
    @RequestMapping("modifyCollectionName")
    public Boolean postModifyCollectionName(@RequestBody UUID userId, @RequestBody String newCollectionName, @RequestBody UUID collectionID){
        User user = UsersXML.readUser(userId);
        return user.userChangeCollectionName(collectionID,newCollectionName);
    }

    @PostMapping
    @RequestMapping("addMovieToCollection")
    public Boolean postAddMovieToCollection(@RequestBody UUID userId, @RequestBody UUID collectionId, @RequestBody UUID movieId){
        User user = UsersXML.readUser(userId);
        return user.userAddMovieToCollection(collectionId, movieId);
    }

    @PostMapping
    @RequestMapping("deleteMovieFromCollection")
    public Boolean postDeleteMovieFromCollection(@RequestBody UUID userId, @RequestBody UUID collectionId, @RequestBody UUID movieId){
        User user = UsersXML.readUser(userId);
        return user.userDeleteMovieFromCollection(collectionId, movieId);
    }

    @PostMapping
    @RequestMapping("deleteCollection")
    public Boolean postDeleteCollection(@RequestBody UUID userId, @RequestBody UUID collectionId){
        User user = UsersXML.readUser(userId);
        return user.userDeleteCollection(collectionId);
    }

    @PostMapping
    @RequestMapping("newTag")
    public Boolean postNewTag(@RequestBody Map<String, String> request){
        Boolean response = Service.newTag(request);
        return response;
    }

    @PostMapping
    @RequestMapping("deleteTag")
    public Boolean postDeleteTag(@RequestBody Map<String, String> request){
        Boolean response = Service.deleteTag(request);
        return response;
    }

    @PostMapping
    @RequestMapping("modifyTag")
    public Boolean postModifyTag(@RequestBody Map<String, String> request){
        Boolean response = Service.modifyTag(request);
        return response;
    }

    @PostMapping
    @RequestMapping("newCastMember")
    public Boolean postNewCastMember(@RequestBody Map<String, String> request){
        Boolean response = Service.newCastMember(request);
        return response;
    }

    @PostMapping
    @RequestMapping("deleteCastMember")
    public Boolean postDeleteCastMember(@RequestBody Map<String, String> request){
        Boolean response = Service.deleteCastMember(request);
        return response;
    }

    @PostMapping
    @RequestMapping("modifyCastMember")
    public Boolean postModifyCastMember(@RequestBody Map<String, String> request){
        Boolean response = Service.modifyCastMember(request);
        return response;
    }

}
