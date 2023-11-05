package com.medkaapp.security.service;

import com.medkaapp.security.dto.MedicamentoDto;
import com.medkaapp.security.entity.RegistroMedicamento;
import com.medkaapp.security.entity.Usuario;

import java.util.List;

public interface RegistroMedicamentoService {

    List<MedicamentoDto> listarMedicamentos();

    MedicamentoDto guardarMedicamento(RegistroMedicamento medicamento);

    MedicamentoDto medicamentoId(Long id);

    MedicamentoDto actualizarMedicamento(RegistroMedicamento medicamento);

    void eliminarMedicamento(Long id);

    List<MedicamentoDto> findByUser(Usuario id);
}
