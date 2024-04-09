package com.clase.tarea.controller;
import com.clase.tarea.model.Movie;
import com.clase.tarea.model.Response;
import com.clase.tarea.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class ControllerMovie {

    @Autowired
    private MovieService movieservice;

    @GetMapping("/getMovie")
    public List<Movie> getMovie(){
        return movieservice.getAllMovie();
    }

    @PostMapping("/createMovie")
    public ResponseEntity<Object> createMovie (@RequestBody Movie movie){
        return movieservice.createMovie(movie);
    }

    @GetMapping("/getId/{id}")
    public Response getId (@PathVariable Long id){ return movieservice.getMovieId(id).getBody(); }



    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<Object> updateMovie (@Valid @PathVariable Long id,
                                               @RequestBody Movie movie){
        return movieservice.updateMovie(id, movie);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie (@Valid @PathVariable Long id){
        movieservice.deleteMovie(id);
    }

    @GetMapping("/getDaysSincePublication/{id}")
    public long getDaysSincePublication(@PathVariable Long id) {
        return movieservice.calculateDaysSincePublication(id);
    }

}
