package com.programacion4.unidad3ej4.feature.producto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Se requiere un nombre")
    private String nombre;

    @NotNull(message = "Se requiere un código")
    private String codigo;

    @NotBlank(message = "Se requiere una descripción")
    private String descripcion;

    @NotNull(message = "Se requiere un precio")
    private Double precio;

    @NotNull(message = "Se requiere un numero de Stock")
    private Integer stock;

    private boolean estaEliminado = false;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
