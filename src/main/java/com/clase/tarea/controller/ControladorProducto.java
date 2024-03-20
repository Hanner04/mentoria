package com.clase.tarea.controller;

import com.clase.tarea.model.Producto;
import com.clase.tarea.service.ProductoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ControladorProducto {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/obtenerTodos")
    public List<Producto> obtenerTodosProductos() {
        return productoServicio.obtenerTodosProductos();
    }
    @PostMapping("/crearProducto")
    public ResponseEntity<Object> crearProducto(@RequestBody Producto producto){
        return productoServicio.crearProducto(producto);
    }

    @GetMapping("/obtenerPorId/{id}")
    public Producto obtenerPorId(@PathVariable Long id){
        return productoServicio.obtenerPorId(id);
    }

    @PutMapping("/actualizarProducto/{id}")
    public ResponseEntity<Object> actualizarProducto(@Valid @PathVariable Long id,
                                                     @RequestBody Producto producto){
        return productoServicio.actualizarProducto(id, producto);
    }

    @DeleteMapping("/borrarProducto/{id}")
    public void borrarProducto(@Valid @PathVariable Long id){
         productoServicio.borrarProducto(id);
    }
}
