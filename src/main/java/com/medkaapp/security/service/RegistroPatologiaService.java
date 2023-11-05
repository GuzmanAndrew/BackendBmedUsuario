package com.medkaapp.security.service;

import com.medkaapp.security.dto.PatologiaDto;
import com.medkaapp.security.entity.RegistroPatologia;
import com.medkaapp.security.entity.Usuario;

import java.util.List;

public interface RegistroPatologiaService {

    List<PatologiaDto> listarPatologias();

    PatologiaDto guardarPatologia(RegistroPatologia patologia);

    PatologiaDto patologiaId(Long id);

    PatologiaDto actualizarPatologia(RegistroPatologia patologia);

    List<PatologiaDto> findByUser(Usuario id);
}
