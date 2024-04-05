package com.clase.tarea.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductoPeliculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @NotNull
    private String autor;
    @NotNull
    private String genero;
    @NotNull
    private double precio;
    @NotNull
    private String fechaLanzamiento;
}