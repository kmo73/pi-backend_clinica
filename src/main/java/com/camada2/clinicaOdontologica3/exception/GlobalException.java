package com.camada2.clinicaOdontologica3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler({NotFoundException.class})
        public ResponseEntity<String> datoNoEncontrado(NotFoundException error){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
}
