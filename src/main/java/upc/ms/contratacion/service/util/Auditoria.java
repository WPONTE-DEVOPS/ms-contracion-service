package upc.ms.contratacion.service.util;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter 
@NoArgsConstructor 
@MappedSuperclass 
public class Auditoria {
    private String usuarioRegistro;
    private LocalDateTime fechaRegistro;
    private String usuarioModifica;
    private LocalDateTime fechaModifica;
    private String ipRegistro;
    private Integer activo;
}
