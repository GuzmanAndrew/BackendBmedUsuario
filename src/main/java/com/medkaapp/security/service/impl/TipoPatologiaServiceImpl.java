package com.medkaapp.security.service.impl;

import com.medkaapp.security.entity.TipoPatologia;
import com.medkaapp.security.repository.TipoPatologiaRepository;
import com.medkaapp.security.service.TipoPatologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPatologiaServiceImpl implements TipoPatologiaService {

    @Autowired
    private TipoPatologiaRepository tipoPatologiaRepository;


    @Override
    public List<TipoPatologia> listaTipoPatologias() {
        return tipoPatologiaRepository.findAll();
    }

    @Override
    public TipoPatologia patologiaTypeId(Long id) {
        return tipoPatologiaRepository.findById(id).get();
    }
}
