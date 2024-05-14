package com.medkaapp.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoporteTecnico {

    private String preguntaid;
    private String nombre;
    private String correo;
    private String asunto;
    private String mensaje;
}
