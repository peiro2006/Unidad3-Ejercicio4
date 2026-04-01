package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej4.config.NotFoundException;
import com.programacion4.unidad3ej4.config.exceptions.ConflictException;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej4.config.exceptions.BadRequestException;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.commons.IProductoExistByNameService;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoCreateService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductoCreateService implements IProductoCreateService {

    private final ICategoriaRepository categoriaRepository;
    private final IProductoExistByNameService productoExistByNameService;

    private final IProductoRepository productoRepository;

    private String capitaliza(String text) {
        if (text==null || text.isEmpty()) return text;

        text = text.toLowerCase();
        return text.substring(0,1).toUpperCase() + text.substring(1);
    }

    @Override
    public ProductoResponseDto create(ProductoCreateRequestDto dto) {

        if (productoExistByNameService.existByName(dto.getNombre())) {
            throw new ConflictException("El nombre del producto ya existe");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("No se encontró la categoría."));

        dto.setNombre(capitaliza(dto.getNombre()));
        dto.setDescripcion(capitaliza(dto.getDescripcion()));

        Producto productoAGuardar = ProductoMapper.toEntity(dto, categoria);
        
        Producto productoGuardado = productoRepository.save(productoAGuardar);

        return ProductoMapper.toResponseDto(productoGuardado);
    }
}


