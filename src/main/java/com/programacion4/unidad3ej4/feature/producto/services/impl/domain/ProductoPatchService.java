package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej4.config.NotFoundException;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoPatchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoPatchService implements IProductoPatchService {

    private final IProductoRepository productoRepository;
    private final ICategoriaRepository categoriaRepository;

    @Override
    public ProductoResponseDto patch(Long id, ProductoUpdateRequestDto dto) {

        // 1. Buscar producto
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        if (producto.isEstaEliminado()) {
            throw new NotFoundException("Producto no encontrado");
        }

        // 2. Actualizar SOLO lo que viene

        if (dto.getNombre() != null) {
            producto.setNombre(capitalize(dto.getNombre()));
        }

        if (dto.getCodigo() != null) {
            producto.setCodigo(dto.getCodigo());
        }

        if (dto.getDescripcion() != null) {
            producto.setDescripcion(capitalize(dto.getDescripcion()));
        }

        if (dto.getPrecio() != null) {
            producto.setPrecio(dto.getPrecio());
        }

        if (dto.getStock() != null) {
            producto.setStock(dto.getStock());
        }

        // 3. Categoría (caso especial)
        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

            producto.setCategoria(categoria);
        }

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
