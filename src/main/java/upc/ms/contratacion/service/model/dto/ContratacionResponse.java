package upc.ms.contratacion.service.model.dto;

import java.time.LocalDateTime;

public record ContratacionResponse(
        Long contratacionId,
        Integer numeroContrato,
        String denominacion,
        LocalDateTime fechaPublica,
        Long tipoCotizacionId,
        Long estadoContratacionId,
        Long tipoInvitacionId,
        String usuarioRegistro,
        LocalDateTime fechaRegistro,
        String usuarioModifica,
        LocalDateTime fechaModifica
) {
}
