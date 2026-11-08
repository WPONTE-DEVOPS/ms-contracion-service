package upc.ms.contratacion.service.util;

import java.util.NoSuchElementException;

/**
 * Extiende NoSuchElementException para que RespuestaExcepcion la traduzca a HTTP 404.
 */
public class ResourceNotFoundException extends NoSuchElementException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
