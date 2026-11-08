package upc.ms.contratacion.service.model.mapper;

import org.springframework.stereotype.Component;

import upc.ms.contratacion.service.model.dto.ContratacionRequest;
import upc.ms.contratacion.service.model.dto.ContratacionResponse;
import upc.ms.contratacion.service.model.entity.ContratacionEntity;

@Component
public class ContratacionMapper {

    public ContratacionEntity toEntity(ContratacionRequest request) {
        ContratacionEntity entity = new ContratacionEntity();
        updateEntity(entity, request);
        return entity;
    }

    public void updateEntity(ContratacionEntity entity, ContratacionRequest request) {
        entity.setNumeroContrato(request.numeroContrato());
        entity.setDenominacion(request.denominacion());
        entity.setFechaPublica(request.fechaPublica());
        entity.setTipoCotizacionId(request.tipoCotizacionId());
        entity.setEstadoContratacionId(request.estadoContratacionId());
        entity.setTipoInvitacionId(request.tipoInvitacionId());
    }

    public ContratacionResponse toResponse(ContratacionEntity entity) {
        return new ContratacionResponse(
                entity.getContratacionId(),
                entity.getNumeroContrato(),
                entity.getDenominacion(),
                entity.getFechaPublica(),
                entity.getTipoCotizacionId(),
                entity.getEstadoContratacionId(),
                entity.getTipoInvitacionId(),
                entity.getUsuarioRegistro(),
                entity.getFechaRegistro(),
                entity.getUsuarioModifica(),
                entity.getFechaModifica());
    }
}
