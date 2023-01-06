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

    Service service = new Service();

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
    public List<User> getAllUser(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminGetAllUsers();
    }

    @GetMapping
    @RequestMapping("listCollections")
    public LinkedList<Collection> getUsersCollection(@RequestBody String userId) {
        User user = UsersXML.readUser(UUID.fromString(userId));
        return user.getCollections();
    }

    @PostMapping
    @RequestMapping("getCollection")
    public Collection getCollection(@RequestBody ObjectNode ids){
        User user = UsersXML.readUser(UUID.fromString(ids.get("userId").asText()));
        return user.userGetCollection(UUID.fromString(ids.get("collectionId").asText()));
    }

    @GetMapping
    @RequestMapping("getUser")
    public User getUser(@RequestBody String adminId, @RequestBody String userId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminGetUser(UUID.fromString(userId));
    }

    @GetMapping
    @RequestMapping("listTags")
    public LinkedList<Tags> getTags(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminReadAllTags();
    }

    @GetMapping
    @RequestMapping("listCastMember")
    public LinkedList<CastMember> getCastMember(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminReadAllCastMember();
    }

    @GetMapping
    @RequestMapping("listAllAdmin")
    public List<Admin> getAllAdmin(@RequestBody String adminId){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminGetAllAdmins();
    }

    @PostMapping
    @RequestMapping("newAdmin")
    public Boolean postNewAdmin(@RequestBody Map<String,String> o){
        Boolean result = service.newAdmin(o);
        return result;
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
    public Boolean postModifyUser(@RequestBody UUID adminId, @RequestBody User modifiedUser){
        Admin admin = AdminXML.readAdmin(adminId);
        return admin.adminModifyUser(modifiedUser);
    }

    @PostMapping
    @RequestMapping("deleteUser")
    public Boolean postDeleteUser(@RequestBody UUID adminId, @RequestBody User deletedUser){
        Admin admin = AdminXML.readAdmin(adminId);
        return admin.adminDeleteUser(deletedUser);
    }

    @PostMapping
    @RequestMapping("newMovie")
    public Boolean postNewMovie(@RequestBody UUID adminId, @RequestBody Movie newMovie){
        Admin admin = AdminXML.readAdmin(adminId);
        return admin.adminCreateMovie(newMovie);
    }

    @PostMapping
    @RequestMapping("modifyMovie")
    public Boolean postModifyMovie(@RequestBody UUID adminId, @RequestBody Movie modifiedMovie){
        Admin admin = AdminXML.readAdmin(adminId);
        return admin.adminModifyMovie(modifiedMovie);
    }

    @PostMapping
    @RequestMapping("deleteMovie")
    public Boolean postDeleteMovie(@RequestBody UUID adminId, @RequestBody Movie deletedMovie){
        Admin admin = AdminXML.readAdmin(adminId);
        return admin.adminDeleteMovie(deletedMovie);
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
    public Boolean postNewTag(@RequestBody String adminId, @RequestBody Tags newTag){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminCreateTag(newTag);
    }

    @PostMapping
    @RequestMapping("deleteTag")
    public Boolean postDeleteTag(@RequestBody String adminId, @RequestBody Tags deletedTag){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminDeleteTag(deletedTag);
    }

    @PostMapping
    @RequestMapping("modifyTag")
    public Boolean postModifyTag(@RequestBody String adminId, @RequestBody Tags modifiedTag){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminModifyTag(modifiedTag);
    }

    @PostMapping
    @RequestMapping("newCastMember")
    public Boolean postNewCastMember(@RequestBody String adminId, @RequestBody CastMember newCastMember){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminCreateCastMember(newCastMember);
    }

    @PostMapping
    @RequestMapping("deleteCastMember")
    public Boolean postDeleteCastMember(@RequestBody String adminId, @RequestBody CastMember deletedCastMember){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminDeleteCastMember(deletedCastMember);
    }

    @PostMapping
    @RequestMapping("modifyCastMember")
    public Boolean postModifyCastMember(@RequestBody String adminId, @RequestBody CastMember modifiedCastMember){
        Admin admin = AdminXML.readAdmin(UUID.fromString(adminId));
        return admin.adminModifyCastMember(modifiedCastMember);
    }

}
