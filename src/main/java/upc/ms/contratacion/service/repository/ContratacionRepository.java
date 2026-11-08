package upc.ms.contratacion.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.ms.contratacion.service.model.entity.ContratacionEntity;

@Repository
public interface ContratacionRepository extends JpaRepository<ContratacionEntity, Long> {

    List<ContratacionEntity> findByActivoOrderByContratacionIdDesc(Integer activo);

    Optional<ContratacionEntity> findByContratacionIdAndActivo(Long contratacionId, Integer activo);
}
