package upc.ms.contratacion.service.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import upc.ms.contratacion.service.model.dto.ContratacionRequest;
import upc.ms.contratacion.service.model.dto.ContratacionResponse;
import upc.ms.contratacion.service.service.ContratacionService;
import upc.ms.contratacion.service.util.ApiConstantes;
import upc.ms.contratacion.service.util.RespuestaExcepcion;
import upc.ms.contratacion.service.util.RespuestaGenerica;

@RestController
@RequestMapping("/api/v1/contrataciones")
@RequiredArgsConstructor
public class ContratacionController {

    private static final String HEADER_USUARIO = "X-Usuario";
    private static final String USUARIO_DEFAULT = "SYSTEM";

    private final ContratacionService service;

    // GET - 200
    @GetMapping
    public ResponseEntity<RespuestaGenerica<ContratacionResponse>> listar() {
        try {
            List<ContratacionResponse> lista = service.listar();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(RespuestaGenerica.<ContratacionResponse>builder()
                            .estadoCodigo(HttpStatus.OK.value())
                            .mensaje(ApiConstantes.MSG_LISTADO)
                            .lista(lista)
                            .tamanioLista(lista.size())
                            .error(false)
                            .build());
        } catch (Exception ex) {
            return RespuestaExcepcion.handleControllerException(ex);
        }
    }

    // GET - 200 / 404
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaGenerica<ContratacionResponse>> obtenerPorId(@PathVariable Long id) {
        try {
            ContratacionResponse response = service.obtenerPorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(RespuestaGenerica.<ContratacionResponse>builder()
                            .estadoCodigo(HttpStatus.OK.value())
                            .mensaje(ApiConstantes.MSG_OBTENIDO)
                            .objeto(response)
                            .error(false)
                            .build());
        } catch (Exception ex) {
            return RespuestaExcepcion.handleControllerException(ex);
        }
    }

    // POST - 201 / 400
    @PostMapping
    public ResponseEntity<RespuestaGenerica<ContratacionResponse>> registrar(
            @Valid @RequestBody ContratacionRequest request,
            @RequestHeader(value = HEADER_USUARIO, defaultValue = USUARIO_DEFAULT) String usuario,
            HttpServletRequest httpRequest) {
        try {
            ContratacionResponse response = service.registrar(request, usuario, httpRequest.getRemoteAddr());
            return ResponseEntity.created(URI.create("/api/v1/contrataciones/" + response.contratacionId()))
                    .body(RespuestaGenerica.<ContratacionResponse>builder()
                            .estadoCodigo(HttpStatus.CREATED.value())
                            .mensaje(ApiConstantes.MSG_REGISTRADO)
                            .objeto(response)
                            .error(false)
                            .build());
        } catch (Exception ex) {
            return RespuestaExcepcion.handleControllerException(ex);
        }
    }

    // PUT - 200 / 400 / 404
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaGenerica<ContratacionResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ContratacionRequest request,
            @RequestHeader(value = HEADER_USUARIO, defaultValue = USUARIO_DEFAULT) String usuario) {
        try {
            ContratacionResponse response = service.actualizar(id, request, usuario);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(RespuestaGenerica.<ContratacionResponse>builder()
                            .estadoCodigo(HttpStatus.OK.value())
                            .mensaje(ApiConstantes.MSG_ACTUALIZADO)
                            .objeto(response)
                            .error(false)
                            .build());
        } catch (Exception ex) {
            return RespuestaExcepcion.handleControllerException(ex);
        }
    }

    // DELETE - 200 / 404
    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaGenerica<ContratacionResponse>> eliminar(
            @PathVariable Long id,
            @RequestHeader(value = HEADER_USUARIO, defaultValue = USUARIO_DEFAULT) String usuario) {
        try {
            service.eliminar(id, usuario);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(RespuestaGenerica.<ContratacionResponse>builder()
                            .estadoCodigo(HttpStatus.OK.value())
                            .mensaje(ApiConstantes.MSG_ELIMINADO)
                            .error(false)
                            .build());
        } catch (Exception ex) {
            return RespuestaExcepcion.handleControllerException(ex);
        }
    }
}
