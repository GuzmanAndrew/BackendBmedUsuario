package com.medkaapp.security.service.impl;

import com.medkaapp.security.dto.PatologiaDto;
import com.medkaapp.security.entity.RegistroPatologia;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.RegistroPatologiaRepository;
import com.medkaapp.security.service.RegistroPatologiaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroPatologiaServiceImpl implements RegistroPatologiaService {

    @Autowired
    private RegistroPatologiaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatologiaDto> listarPatologias() {
        List<RegistroPatologia> registros = repository.findAll();
        List<PatologiaDto> dtos = registros.stream()
                .map(registro -> modelMapper.map(registro, PatologiaDto.class))
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PatologiaDto guardarPatologia(RegistroPatologia patologia) {
        RegistroPatologia registroGuardado = repository.save(patologia);
        PatologiaDto dto = modelMapper.map(registroGuardado, PatologiaDto.class);
        return dto;
    }

    @Override
    public PatologiaDto patologiaId(Long id) {
        RegistroPatologia registroPatologia = repository.findById(id).get();
        PatologiaDto dtos = modelMapper.map(registroPatologia, PatologiaDto.class);
        return dtos;
    }

    @Override
    public PatologiaDto actualizarPatologia(RegistroPatologia patologia) {
        RegistroPatologia registroPatologia = repository.save(patologia);
        PatologiaDto dto = modelMapper.map(registroPatologia, PatologiaDto.class);
        return dto;
    }

    @Override
    public List<PatologiaDto> findByUser(Usuario id) {
        List<RegistroPatologia> registros = repository.findByUser(id);
        List<PatologiaDto> dtos = registros.stream()
                .map(registro -> modelMapper.map(registro, PatologiaDto.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
