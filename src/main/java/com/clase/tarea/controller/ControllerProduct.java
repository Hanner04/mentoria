package com.clase.tarea.controller;

import com.clase.tarea.model.Products;
import com.clase.tarea.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ControllerProduct {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public List<Products> getAllProduct() {
        return productService.getAllProducts();
    }
    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(@RequestBody Products products){
        return productService.createProduct(products);
    }

    @GetMapping("/getId/{id}")
    public Products getId(@PathVariable Long id){
        return productService.getId(id);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Object> updateProduct(@Valid @PathVariable Long id,
                                                     @RequestBody Products products){
        return productService.updateProduct(id, products);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@Valid @PathVariable Long id){
         productService.deleteProduct(id);
    }
}
