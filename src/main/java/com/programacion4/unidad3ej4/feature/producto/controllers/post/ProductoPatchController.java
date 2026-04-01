package com.programacion4.unidad3ej4.feature.producto.controllers.post;

import com.programacion4.unidad3ej4.config.BaseResponse;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoPatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoPatchController {

    private final IProductoPatchService productoPatchService;

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductoResponseDto>> patch(
            @PathVariable Long id,
            @RequestBody ProductoUpdateRequestDto dto) {

        return ResponseEntity.ok(
                BaseResponse.ok(
                        productoPatchService.patch(id, dto),
                        "Producto actualizado parcialmente"
                )
        );
    }
}
