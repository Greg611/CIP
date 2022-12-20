package com.cip.moviedatabase;

import com.cip.moviedatabase.Model.Movie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {

    @GetMapping
    public List<Movie> getMovie(){
        return List.of(new Movie(0,"a",9.11f,120, LocalDate.now(),null,null, null));
    }
}
