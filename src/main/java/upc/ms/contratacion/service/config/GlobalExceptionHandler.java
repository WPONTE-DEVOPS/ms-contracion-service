package upc.ms.contratacion.service.config;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import upc.ms.contratacion.service.util.ApiConstantes;
import upc.ms.contratacion.service.util.RespuestaGenerica;

/**
 * Maneja las excepciones que ocurren ANTES de entrar al método del controller
 * (validación @Valid, JSON mal formado, tipos de parámetros inválidos), por lo que
 * no pueden ser capturadas por el try/catch del controller.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaGenerica<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String detalle = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return badRequest(ApiConstantes.MSG_VALIDACION, detalle);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<RespuestaGenerica<Object>> handleBadRequest(Exception ex) {
        return badRequest(ApiConstantes.MSG_VALIDACION, ex.getMessage());
    }

    private ResponseEntity<RespuestaGenerica<Object>> badRequest(String mensaje, String detalle) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(RespuestaGenerica.builder()
                        .estadoCodigo(HttpStatus.BAD_REQUEST.value())
                        .error(true)
                        .mensaje(mensaje)
                        .mensajeExcepcion(detalle)
                        .build());
    }
}
