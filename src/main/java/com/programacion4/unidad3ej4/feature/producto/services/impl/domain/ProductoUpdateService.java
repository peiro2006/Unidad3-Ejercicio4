package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej4.config.NotFoundException;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoUpdateService implements IProductoUpdateService {

    private final IProductoRepository productoRepository;
    private final ICategoriaRepository categoriaRepository;

    @Override
    public ProductoResponseDto update(Long id, ProductoUpdateRequestDto dto) {

        // 1. Buscar producto
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        if (producto.isEstaEliminado()) {
            throw new NotFoundException("Producto no encontrado");
        }

        // 2. Buscar categoría nueva
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        // 3. Actualizar campos
        producto.setNombre(capitalize(dto.getNombre()));
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(capitalize(dto.getDescripcion()));
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);

        // 4. Guardar
        Producto actualizado = productoRepository.save(producto);

        return ProductoMapper.toResponseDto(actualizado);
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;

        text = text.toLowerCase();
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
