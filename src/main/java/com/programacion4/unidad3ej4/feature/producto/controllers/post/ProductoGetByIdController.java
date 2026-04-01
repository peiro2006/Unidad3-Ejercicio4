package com.programacion4.unidad3ej4.feature.producto.controllers.post;

import com.programacion4.unidad3ej4.config.BaseResponse;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetByIdService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoGetByIdController {

    private final IProductoGetByIdService productoGetByIdService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductoResponseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.ok(
                        productoGetByIdService.getById(id),
                        "El producto se obtuvo correctamente"
                )
        );
    }

}
