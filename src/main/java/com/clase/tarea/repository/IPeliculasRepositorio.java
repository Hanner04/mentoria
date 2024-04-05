package com.clase.tarea.repository;

import com.clase.tarea.model.ProductoPeliculas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeliculasRepositorio extends JpaRepository<ProductoPeliculas, Long>{
}
