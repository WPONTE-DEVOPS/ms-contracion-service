package upc.ms.contratacion.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import upc.ms.contratacion.service.model.dto.ContratacionRequest;
import upc.ms.contratacion.service.model.dto.ContratacionResponse;
import upc.ms.contratacion.service.model.entity.ContratacionEntity;
import upc.ms.contratacion.service.model.mapper.ContratacionMapper;
import upc.ms.contratacion.service.repository.ContratacionRepository;
import upc.ms.contratacion.service.service.ContratacionService;
import upc.ms.contratacion.service.util.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ContratacionServiceImpl implements ContratacionService {

    private static final Integer ACTIVO = 1;
    private static final Integer INACTIVO = 0;

    private final ContratacionRepository repository;
    private final ContratacionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ContratacionResponse> listar() {
        return repository.findByActivoOrderByContratacionIdDesc(ACTIVO).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContratacionResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscarActivo(id));
    }

    @Override
    @Transactional
    public ContratacionResponse registrar(ContratacionRequest request, String usuario, String ip) {
        ContratacionEntity entity = mapper.toEntity(request);
        entity.setUsuarioRegistro(usuario);
        entity.setFechaRegistro(LocalDateTime.now());
        entity.setIpRegistro(ip);
        entity.setActivo(ACTIVO);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public ContratacionResponse actualizar(Long id, ContratacionRequest request, String usuario) {
        ContratacionEntity entity = buscarActivo(id);
        mapper.updateEntity(entity, request);
        entity.setUsuarioModifica(usuario);
        entity.setFechaModifica(LocalDateTime.now());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Long id, String usuario) {
        ContratacionEntity entity = buscarActivo(id);
        entity.setActivo(INACTIVO);
        entity.setUsuarioModifica(usuario);
        entity.setFechaModifica(LocalDateTime.now());
        repository.save(entity);
    }

    private ContratacionEntity buscarActivo(Long id) {
        return repository.findByContratacionIdAndActivo(id, ACTIVO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la contratación con id " + id));
    }
}
