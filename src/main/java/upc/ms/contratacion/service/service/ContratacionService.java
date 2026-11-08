package upc.ms.contratacion.service.service;

import java.util.List;

import upc.ms.contratacion.service.model.dto.ContratacionRequest;
import upc.ms.contratacion.service.model.dto.ContratacionResponse;

public interface ContratacionService {

    List<ContratacionResponse> listar();

    ContratacionResponse obtenerPorId(Long id);

    ContratacionResponse registrar(ContratacionRequest request, String usuario, String ip);

    ContratacionResponse actualizar(Long id, ContratacionRequest request, String usuario);

    void eliminar(Long id, String usuario);
}
