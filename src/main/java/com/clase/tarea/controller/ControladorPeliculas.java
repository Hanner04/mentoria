package com.clase.tarea.controller;

import com.clase.tarea.model.ProductoPeliculas;
import com.clase.tarea.service.PeliculaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pelicula")
public class ControladorPeliculas {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @GetMapping("/obtenerPelicula")
    public List<ProductoPeliculas> obtenerTodasPeliculas(){
        return peliculaServicio.obtenerTodasPeliculas();
    }

    @PostMapping("/crearPelicula")
    public ResponseEntity<Object> crearPelicula (@RequestBody ProductoPeliculas productoPeliculas){
        return peliculaServicio.crearPelicula(productoPeliculas);
    }

    @GetMapping("/obtenerPorId/{id}")
    public ProductoPeliculas obtenerPorId(@PathVariable Long id){ return peliculaServicio.obtenerPorId(id); }

    @PutMapping("/actualizarPelicula/{id}")
    public ResponseEntity<Object> actualizarPelicula(@Valid @PathVariable Long id, @RequestBody ProductoPeliculas pelicula){
        return peliculaServicio.actualizarPelicula(id, pelicula);
    }

    @DeleteMapping("/borrarPelicula/{id}")
    public void borrarPelicula(@Valid @PathVariable Long id){
        peliculaServicio.borrarPelicula(id);
    }
}
