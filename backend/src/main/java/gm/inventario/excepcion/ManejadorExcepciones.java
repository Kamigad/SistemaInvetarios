package gm.inventario.excepcion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(ExistenciasInvalidasExcepcion.class)
    public ResponseEntity<ErrorRespuesta> manejarExistenciasInvalidas(ExistenciasInvalidasExcepcion ex){
        ErrorRespuesta error = new ErrorRespuesta(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }
}