package com.medkaapp.security.service;

public interface IClasificacionService {
    String obtenerClasificacion(String bpmStr, String username);
    String convertirEdadARango(Integer edad);
}
