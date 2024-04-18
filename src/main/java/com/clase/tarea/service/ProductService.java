package com.clase.tarea.service;

import com.clase.tarea.model.Products;
import com.clase.tarea.model.Response;
import com.clase.tarea.repository.IProductRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductService {

    @Autowired
    private IProductRepository iProductRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public List<Products> getAllProducts() {
        return iProductRepository.findAll();
    }

    public ResponseEntity<Object> createProduct(Products products){

        AtomicReference<String> message = new AtomicReference<>();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Products>> violations = validator.validate(products);

        try{
                if (!violations.isEmpty()) {
                    violations.forEach(violation -> message.set(violation.getMessage()));
                    LOGGER.error("Error: Create product: {}",message);
                    return ResponseEntity.status(401)
                            .body(Response
                                    .builder()
                                    .code(401)
                                    .message(message.toString())
                                    .build()
                            );
                }else{

                    LOGGER.info("INFO: Create product: {}",products);
                    iProductRepository.save(products);

                    return ResponseEntity.status(200)
                            .body(
                                    Response.builder()
                                            .code(200)
                                            .message("Product added successfully")
                                            .build()
                            );
                }

        }catch(Exception e){
            LOGGER.error("Error: Create product: {}", "Duplicate key. this product is already registered");
            return ResponseEntity.status(401)
                    .body(
                            Response.builder()
                                    .code(401)
                                    .message("Error: Create task: {"+products+"} This task is already registered.")
                                    .build()
                    );

        }

    }

    public Products getId(Long id){
        return iProductRepository.findById(id)
                .orElse(null);
    }

    public ResponseEntity<Object> updateProduct(Long id, Products products){
        Products existProduct = getId(id);

        if(existProduct != null ){
            existProduct.setName(products.getName());
            existProduct.setBrand(products.getBrand());
            existProduct.setPrice(products.getPrice());
            existProduct.setState(products.getState());

            LOGGER.info("UPDATE product: {}", existProduct);
            iProductRepository.save(existProduct);
            return ResponseEntity.status(200)
                    .body(
                            Response.builder()
                                    .code(200)
                                    .message("Product update successfully")
                                    .build()
                    );
        }else{

            LOGGER.info("UPDATE: Product not update {}", id);
            return ResponseEntity.status(404)
                    .body(
                            Response.builder()
                                    .code(404)
                                    .message("Product not update")
                                    .build()
                    );
        }

    }

    public void deleteProduct(Long id){
        iProductRepository.deleteById(id);
    }

}
