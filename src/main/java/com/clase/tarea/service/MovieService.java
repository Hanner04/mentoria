package com.clase.tarea.service;

import com.clase.tarea.model.Movie;
import com.clase.tarea.model.Response;
import com.clase.tarea.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private IMovieRepository iMovieRepository;

    public List<Movie> getAllMovie(){
        return iMovieRepository.findAll();
    }

    public ResponseEntity<Object> createMovie (Movie movie){
        iMovieRepository.save(movie);

        return ResponseEntity.status(200)
                .body(
                        Response.builder()
                                .code(200)
                                .message("Pelicula agregada")
                                .build()
                );
    }

    public ResponseEntity<Response> getMovieId(Long id) {
        Movie movie = iMovieRepository.findById(id).orElse(null);
        if (movie != null) {
            return ResponseEntity.ok()
                    .body(
                            Response.builder()
                                    .code(200)
                                    .message("found movie")
                                    .build()
                    );
        } else {
            return ResponseEntity.status(404)
                    .body(
                            Response.builder()
                                    .code(404)
                                    .message("movie not found")
                                    .build()
                    );
        }
    }



    public ResponseEntity<Object> updateMovie(Long id, Movie movie){
        Movie existMovie = iMovieRepository.findById(id).orElse(null);
        if(existMovie != null){
            existMovie.setName(movie.getName());
            existMovie.setAuthor(movie.getAuthor());
            existMovie.setGender(movie.getGender());
            existMovie.setPrice(movie.getPrice());
            existMovie.setDate(movie.getDate());

            iMovieRepository.save(existMovie);
            return ResponseEntity.status(200)
                    .body(
                            Response.builder()
                                    .code(200)
                                    .message("Update movie")
                                    .build()
                    );
        } else {
            return ResponseEntity.status(400)
                    .body(
                            Response.builder()
                                    .code(200)
                                    .message("Sorry,I can't update the movie. It seems that the movie is not found in our database.")
                                    .build()
                    );
        }

    }



    public ResponseEntity<Response> deleteMovie(Long id) {
        iMovieRepository.deleteById(id);
        return ResponseEntity.status(200)
                .body(
                        Response.builder()
                                .code(200)
                                .message("La película ha sido eliminada correctamente")
                                .build()
                );
    }

}