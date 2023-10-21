package com.medkaapp.security.service.impl;

import com.medkaapp.security.entity.PresentacionMedicamento;
import com.medkaapp.security.entity.ViaMedicamento;
import com.medkaapp.security.repository.PresentacionMedicamentoRepository;
import com.medkaapp.security.repository.ViaMedicamentoRepository;
import com.medkaapp.security.service.NuevoMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NuevoMedicamentoServiceImpl implements NuevoMedicamentoService {

    @Autowired
    private ViaMedicamentoRepository viaMedicamentoRepository;

    @Autowired
    private PresentacionMedicamentoRepository presentacionMedicamentoRepository;

    @Override
    public List<ViaMedicamento> listarViaMedicamentos() {
        return viaMedicamentoRepository.findAll();
    }

    @Override
    public List<PresentacionMedicamento> listarPresentacionMedicamentos() {
        return presentacionMedicamentoRepository.findAll();
    }
}
