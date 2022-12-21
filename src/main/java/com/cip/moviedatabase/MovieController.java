package com.cip.moviedatabase;

import com.cip.moviedatabase.Model.Collection;
import com.cip.moviedatabase.Model.Movie;
import com.cip.moviedatabase.Model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {

    @GetMapping
    @RequestMapping("list")
    public List<Movie> getMovie(){
        return List.of(new Movie(0,"a",9.11f,120, LocalDate.now(),null,null, null));
    }

    @GetMapping
    @RequestMapping("listCollection")
    public ArrayList<Collection> getCollection() {
        User user = new User(1,"asd","asd",LocalDate.now(),"asd");
        user.createCollection("asd");
        return user.getCollections();
    }

}
