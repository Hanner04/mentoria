package com.clase.tarea.service;

import com.clase.tarea.model.Products;
import com.clase.tarea.model.Response;
import com.clase.tarea.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductRepository iProductRepository;

    public List<Products> getAllProducts() {
        return iProductRepository.findAll();
    }

    public ResponseEntity<Object> createProduct(Products products){

        iProductRepository.save(products);

        return ResponseEntity.status(200)
                .body(
                        Response.builder()
                                .code(200)
                                .message("Producto agregado")
                                .build()
                );
    }

    public Products getId(Long id){
        return iProductRepository.findById(id)
                .orElse(null);
    }

    public ResponseEntity<Object> updateProduct(Long id, Products products){
        Products existProduct = getId(id);
        if(iProductRepository.existsById(id)){
            existProduct.setName(products.getName());
            existProduct.setBrand(products.getBrand());
            existProduct.setPrice(products.getPrice());
            existProduct.setState(products.getState());

            iProductRepository.save(existProduct);
            return ResponseEntity.status(200)
                    .body(
                            Response.builder()
                                    .code(200)
                                    .message("Producto actualizado")
                                    .build()
                    );
        }else{
            return ResponseEntity.status(404)
                    .body(
                            Response.builder()
                                    .code(404)
                                    .message("Producto no actualizado")
                                    .build()
                    );
        }

    }

    public void deleteProduct(Long id){
        iProductRepository.deleteById(id);
    }

}
