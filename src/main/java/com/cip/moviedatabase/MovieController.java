package com.cip.moviedatabase;

import com.cip.moviedatabase.Model.*;
import com.cip.moviedatabase.Service.Service;
import com.cip.moviedatabase.XMLHandler.MoviesXML;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {
    @PostMapping
    @RequestMapping("listAllAdmin")
    public LinkedList<Admin> getAllAdmin(@RequestBody Map<String,String> request){
        LinkedList<Admin> response = Service.listAllAdmins(request);
        return response;
    }

    @PostMapping
    @RequestMapping("getAdmin")
    public Admin postAdmin(@RequestBody Map<String,String> request){
        Admin response = Service.getAdmin(request);
        return response;
    }

    @PostMapping
    @RequestMapping("newAdmin")
    public Boolean postNewAdmin(@RequestBody Map<String,String> request){
        Boolean response = Service.newAdmin(request);
        return response;
    }

    @PostMapping
    @RequestMapping(value = "modifyAdmin")
    public Boolean postModifyAdmin(@RequestBody Map<String,String> request){
        Boolean response = Service.modifyAdmin(request);
        return response;
    }

    @PostMapping
    @RequestMapping("deleteAdmin")
    public Boolean postDeleteAdmin(@RequestBody Map<String,String> request){
        Boolean response = Service.deleteAdmin(request);
        return response;
    }

    @PostMapping
    @RequestMapping("listAllUsers")
    public List<User> getAllUser(@RequestBody Map<String,String> request){
        List<User> response = Service.listAllUsers(request);
        return response;
    }

    @PostMapping
    @RequestMapping("getUser")
    public User getUser(@RequestBody Map<String,String> request){
        User response = Service.getUser(request);
        return response;
    }

    @PostMapping
    @RequestMapping("newUser")
    public Boolean postNewUser(@RequestBody Map<String,String> request){
        Boolean response = Service.newUser(request);
        return response;
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

    @GetMapping
    @RequestMapping("listAllMovies")
    public List<Movie> getAllMovies(){
        return MoviesXML.readAllMovies();
    }

    @PostMapping
    @RequestMapping("getMovie")
    public Movie getMovie(@RequestBody Map<String,String> request){
        Movie response = Service.getMovie(request);
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
    @RequestMapping("listCollections")
    public LinkedList<Collection> getUsersCollection(@RequestBody Map<String,String> request) {
        LinkedList<Collection> response = Service.listCollections(request);
        return response;
    }

    @PostMapping
    @RequestMapping("getCollection")
    public Collection getCollection(@RequestBody Map<String, String> request){
        Collection response = Service.getCollection(request);
        return response;
    }

    @PostMapping
    @RequestMapping("newCollection")
    public Boolean postNewCollection(@RequestBody Map<String,String> request){
        Boolean response = Service.newCollection(request);
        return response;
    }

    @PostMapping
    @RequestMapping("modifyCollectionName")
    public Boolean postModifyCollectionName(@RequestBody Map<String,String> request){
        Boolean response = Service.modifyCollectionName(request);
        return response;
    }

    @PostMapping
    @RequestMapping("addMovieToCollection")
    public Boolean postAddMovieToCollection(@RequestBody Map<String,String> request){
        Boolean response = Service.addMovieToCollection(request);
        return response;
    }

    @PostMapping
    @RequestMapping("deleteMovieFromCollection")
    public Boolean postDeleteMovieFromCollection(@RequestBody Map<String,String> request){
        Boolean response = Service.removeMovieFromCollection(request);
        return response;
    }

    @PostMapping
    @RequestMapping("deleteCollection")
    public Boolean postDeleteCollection(@RequestBody Map<String,String> request){
        Boolean response = Service.deleteCollection(request);
        return response;
    }

    @PostMapping
    @RequestMapping("listTags")
    public LinkedList<Tags> getTags(@RequestBody Map<String,String> request){
        LinkedList<Tags> response = Service.listTags(request);
        return response;
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
    @RequestMapping("listCastMember")
    public LinkedList<CastMember> getCastMember(@RequestBody Map<String,String> request){
        LinkedList<CastMember> response = Service.listCastMembers(request);
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
