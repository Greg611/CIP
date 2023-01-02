package com.cip.moviedatabase;

import com.cip.moviedatabase.Model.Admin;
import com.cip.moviedatabase.Model.Collection;
import com.cip.moviedatabase.Model.Movie;
import com.cip.moviedatabase.Model.User;
import com.cip.moviedatabase.XMLHandler.AdminXML;
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
    @RequestMapping("list")
    public List<Movie> getMovie(){
        return List.of(new Movie("a",9.11f,120, LocalDate.now(),null,null, null));
    }

    @GetMapping
    @RequestMapping("user")
    public List<User> user(){
        return UsersXML.readAllUsers();

    }

    @GetMapping
    @RequestMapping("newAdmin")
    public void newAdmin(){
        Admin admin =new Admin(UUID.randomUUID(),"asd","asd",LocalDate.now(),"asd");
        AdminXML.saveAdmin(admin);
    }

    @GetMapping
    @RequestMapping("listCollections")
    public LinkedList<Collection> getCollection() {
        User user = new User(UUID.fromString("f7c0bc58-ad3e-4a84-bf38-120fa28f7f65"), "asd", "asd", LocalDate.now(), "asd");
        System.out.println(UUID.randomUUID());
        UsersXML.saveUser(user);
        user.userCreateCollection("asd");
        return user.getCollections();
    }

    @PostMapping
    @RequestMapping("newUser")
    public void postNewUser(@RequestBody User newUser){
        UsersXML.saveUser(newUser);
    }
}
