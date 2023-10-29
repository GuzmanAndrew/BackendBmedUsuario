package com.medkaapp.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstructuraArchivoDto {
    private String nombreArchivo;

    public EstructuraArchivoDto() {
    }

    public EstructuraArchivoDto(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
