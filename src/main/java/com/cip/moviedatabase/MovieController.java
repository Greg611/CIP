package com.cip.moviedatabase;

import com.cip.moviedatabase.Model.Collection;
import com.cip.moviedatabase.Model.Movie;
import com.cip.moviedatabase.Model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {

    @GetMapping
    @RequestMapping("list")
    public List<Movie> getMovie(){
        return List.of(new Movie("a",9.11f,120, LocalDate.now(),null,null, null));
    }

    @GetMapping
    @RequestMapping("listCollections")
    public LinkedList<Collection> getCollection() {
        User user = new User("asd","asd",LocalDate.now(),"asd");
        user.createCollection("asd");
        return user.getCollections();
    }
}
