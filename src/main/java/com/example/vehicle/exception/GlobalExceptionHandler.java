package com.example.vehicle.exception;

import com.example.vehicle.dto.ResDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResDTO<?>> handleEntityNotFound(EntityNotFoundException ex) {
        ResDTO<?> response = new ResDTO<>(HttpServletResponse.SC_NOT_FOUND, ex.getMessage(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResDTO<?>> handleGenericException(Exception ex) {
        ResDTO<?> response = new ResDTO<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

