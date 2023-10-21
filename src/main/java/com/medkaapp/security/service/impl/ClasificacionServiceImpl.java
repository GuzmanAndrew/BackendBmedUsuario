package com.medkaapp.security.service;

import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.entity.ValoresPorGrupo;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.repository.ValoresPorGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClasificacionServiceImpl implements IClasificacionService{

    @Autowired
    private ValoresPorGrupoRepository valoresRepo;
    @Autowired
    private IUsuarioDao usuarioRepository;

    @Override
    public String obtenerClasificacion(String bpmStr, String username) {
        Integer bpm = Integer.parseInt(bpmStr);
        Usuario usuario = usuarioRepository.findByUserName(username);
        String rangoEdad = convertirEdadARango(usuario.getEdad());
        ValoresPorGrupo valor = valoresRepo.findByRangoAndBpm(rangoEdad, bpm);
        return valor != null ? valor.getClasificacionId().getNombre() : "Sin clasificación";
    }

    @Override
    public String convertirEdadARango(Integer edad) {
        if (edad >= 20 && edad <= 29) {
            return "20-29";
        } else if (edad >= 30 && edad <= 39) {
            return "30-39";
        } else if (edad >= 40 && edad <= 49) {
            return "40-49";
        } else if (edad >= 50) {
            return "50+";
        } else {
            return "Fuera de rango";
        }
    }
}
