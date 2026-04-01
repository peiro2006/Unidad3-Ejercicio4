package com.programacion4.unidad3ej4.config;

import com.programacion4.unidad3ej4.config.exceptions.CustomException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundException extends CustomException {
    public NotFoundException(String message) {

        super(message, HttpStatus.NOT_FOUND, List.of(message));

    }
}
