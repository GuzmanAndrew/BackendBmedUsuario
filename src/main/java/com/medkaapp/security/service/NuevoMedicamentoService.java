package com.medkaapp.security.service;

import com.medkaapp.security.entity.PresentacionMedicamento;
import com.medkaapp.security.entity.ViaMedicamento;

import java.util.List;

public interface NuevoMedicamentoService {

    List<ViaMedicamento> listarViaMedicamentos();
    List<PresentacionMedicamento> listarPresentacionMedicamentos();

}
