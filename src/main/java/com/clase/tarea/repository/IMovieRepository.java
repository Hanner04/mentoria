package com.clase.tarea.repository;

import com.clase.tarea.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository<Movie, Long>{
}
