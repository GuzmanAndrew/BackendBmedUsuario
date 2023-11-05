package com.medkaapp.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatologiaDto {

    private Long id;
    private String nombrePatologia;
    private String tipoPatologia;
    private Boolean requiereTratamiento;
    private String descripcionTratamiento;
    private int usuarioId;
}
