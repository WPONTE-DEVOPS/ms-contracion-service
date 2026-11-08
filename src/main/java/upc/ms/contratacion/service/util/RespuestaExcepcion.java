package upc.ms.contratacion.service.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespuestaExcepcion {
    public static <T> ResponseEntity<RespuestaGenerica<T>> handleControllerException(Exception ex) {
        // Error 404
        if (ex instanceof java.util.NoSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RespuestaGenerica.<T>builder()
                            .estadoCodigo(HttpStatus.NOT_FOUND.value())
                            .mensajeExcepcion(ex.getMessage())
                            .error(true)
                            .build());
        }
        // Error 400
        if (ex instanceof IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(RespuestaGenerica.<T>builder()
                            .estadoCodigo(HttpStatus.BAD_REQUEST.value())
                            .mensajeExcepcion(ex.getMessage())
                            .error(true)
                            .build());
        }

        // Error 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RespuestaGenerica.<T>builder()
                        .estadoCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .mensaje(ApiConstantes.STATUS_CODE_500)
                        .mensajeExcepcion(ex.getMessage())
                        .error(true)
                        .build());
    }
}
