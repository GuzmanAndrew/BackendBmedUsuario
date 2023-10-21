package com.medkaapp.security.service.impl;

import com.medkaapp.error.MedicamentoNotFoundException;
import com.medkaapp.security.dto.MedicamentoDto;
import com.medkaapp.security.entity.RegistroMedicamento;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.RegistroMedicamentoRepository;
import com.medkaapp.security.service.RegistroMedicamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroMedicamentoServiceImpl implements RegistroMedicamentoService {

    @Autowired
    private RegistroMedicamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MedicamentoDto> listarMedicamentos() {
        List<RegistroMedicamento> registros = repository.findAll();
        List<MedicamentoDto> dtos = registros.stream()
                .map(registro -> modelMapper.map(registro, MedicamentoDto.class))
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public MedicamentoDto guardarMedicamento(RegistroMedicamento medicamento) {
        RegistroMedicamento registroGuardado = repository.save(medicamento);
        MedicamentoDto dtoResultante = modelMapper.map(registroGuardado, MedicamentoDto.class);
        return dtoResultante;
    }

    @Override
    public MedicamentoDto actualizarMedicamento(RegistroMedicamento medicamento) {
        RegistroMedicamento registroGuardado = repository.save(medicamento);
        MedicamentoDto dtoResultante = modelMapper.map(registroGuardado, MedicamentoDto.class);
        return dtoResultante;
    }

    @Override
    public MedicamentoDto medicamentoId(Long id) {
        RegistroMedicamento registroMedicamento = repository.findById(id).get();
        MedicamentoDto dtos = modelMapper.map(registroMedicamento, MedicamentoDto.class);
        return dtos;
    }

    @Override
    public void eliminarMedicamento(Long id) {
        if (!repository.existsById(id)) {
            throw new MedicamentoNotFoundException("Medicamento no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<MedicamentoDto> findByUser(Usuario id) {
        List<RegistroMedicamento> registros = repository.findByUser(id);
        List<MedicamentoDto> dtos = registros.stream()
                .map(registro -> modelMapper.map(registro, MedicamentoDto.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
