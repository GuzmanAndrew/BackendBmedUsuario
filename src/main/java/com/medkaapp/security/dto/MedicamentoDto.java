package com.medkaapp.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicamentoDto {
    private Long id;
    private String nombreMedicamento;
    private String nombreLaboratorio;
    private String presentacionMedicamento;
    private String viaAdministracion;
    private String cantidadDia;
    private String cantidadMes;
    private int usuarioId;
}
