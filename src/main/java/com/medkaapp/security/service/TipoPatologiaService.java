package com.medkaapp.security.service;

import com.medkaapp.security.entity.TipoPatologia;

import java.util.List;

public interface TipoPatologiaService {

    List<TipoPatologia> listaTipoPatologias();

    TipoPatologia patologiaTypeId(Long id);
}
