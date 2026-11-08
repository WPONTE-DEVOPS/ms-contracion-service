package upc.ms.contratacion.service.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ContratacionRequest(

        @NotNull(message = "El número de contrato es obligatorio")
        @Positive(message = "El número de contrato debe ser positivo")
        Integer numeroContrato,

        @NotBlank(message = "La denominación es obligatoria")
        @Size(max = 255, message = "La denominación no debe exceder 255 caracteres")
        String denominacion,

        LocalDateTime fechaPublica,

        @NotNull(message = "El tipo de cotización es obligatorio")
        Long tipoCotizacionId,

        @NotNull(message = "El estado de contratación es obligatorio")
        Long estadoContratacionId,

        @NotNull(message = "El tipo de invitación es obligatorio")
        Long tipoInvitacionId
) {
}
