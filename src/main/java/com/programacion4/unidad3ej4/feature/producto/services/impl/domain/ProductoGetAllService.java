package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetAllService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoGetAllService implements IProductoGetAllService {

    private final IProductoRepository prodRepo;

    @Override
    public List<ProductoResponseDto> getAll(){

        List<Producto> productos = prodRepo.findByEstaEliminadoFalse();

        return productos.stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }


}
