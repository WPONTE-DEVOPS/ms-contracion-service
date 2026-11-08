package upc.ms.contratacion.service.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upc.ms.contratacion.service.util.Auditoria;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_contratacion", schema = "contratacion")
public class ContratacionEntity extends Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contratacionId;
    private Integer numeroContrato;
    private String denominacion;
    private LocalDateTime fechaPublica;

    private Long tipoCotizacionId;
    private Long estadoContratacionId;
    private Long tipoInvitacionId;

}
