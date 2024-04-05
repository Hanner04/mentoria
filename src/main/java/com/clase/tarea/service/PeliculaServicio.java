package com.clase.tarea.service;

import com.clase.tarea.model.ProductoPeliculas;
import com.clase.tarea.model.Response;
import com.clase.tarea.repository.IPeliculasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServicio {
    @Autowired
    private IPeliculasRepositorio iPeliculasRepositorio;

    public List<ProductoPeliculas> obtenerTodasPeliculas(){
        return iPeliculasRepositorio.findAll();
    }

    public ResponseEntity<Object> crearPelicula(ProductoPeliculas productoPeliculas){
        iPeliculasRepositorio.save(productoPeliculas);

        return ResponseEntity.status(200)
                .body(
                        Response.builder()
                                .code(200)
                                .message("Pelicula agregada")
                                .build()
                );
    }

    public ProductoPeliculas obtenerPorId(Long id){
        return iPeliculasRepositorio.findById(id)
                .orElse(null);
    }

    public ResponseEntity <Object> actualizarPelicula(Long id,ProductoPeliculas pelicula){
        ProductoPeliculas existePelicula = obtenerPorId(id);
        if(existePelicula != null){
            existePelicula.setNombre(pelicula.getNombre());
            existePelicula.setAutor(pelicula.getAutor());
            existePelicula.setGenero(pelicula.getGenero());
            existePelicula.setPrecio(pelicula.getPrecio());
            existePelicula.setFechaLanzamiento(pelicula.getFechaLanzamiento());

            iPeliculasRepositorio.save(existePelicula);
            return ResponseEntity.status(200)
                    .body(
                            Response.builder()
                                    .code(200)
                                    .message("Pelicula Actualizada")
                                    .build()
                    );

        }else{
            return ResponseEntity.status(404)
                    .body(
                            Response.builder()
                                    .code(404)
                                    .message("Lo siento, no pudimos actualizar la pelicula. Parece que la pelicula no se encontró en nuestra base de datos.")
                                    .build()
                    );
        }
    }
    public void borrarPelicula(Long id){
        iPeliculasRepositorio.deleteById(id);
    }
}
