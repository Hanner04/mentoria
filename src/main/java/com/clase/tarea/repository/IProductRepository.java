package com.clase.tarea.repository;

import com.clase.tarea.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Products, Long> {
}
