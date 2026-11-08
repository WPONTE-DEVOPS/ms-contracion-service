package upc.ms.contratacion.service.util;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RespuestaGenerica<T> {
    private Integer estadoCodigo;
    @Builder.Default
    private boolean error = false;
    private String mensaje;
    private String mensajeExcepcion;
    @Builder.Default
    private List<T> lista = new ArrayList<>();
    @Builder.Default
    private Integer tamanioLista = 0;
    private T objeto;
}
