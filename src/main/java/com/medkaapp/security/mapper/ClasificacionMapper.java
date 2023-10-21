package com.medkaapp.security.mapper;

import com.medkaapp.security.dto.CreateFrecuenciaCardiacaDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import javax.persistence.Tuple;

@Component
public class DataMapper implements Converter<Tuple, CreateFrecuenciaCardiacaDTO> {
    @Override
    public CreateFrecuenciaCardiacaDTO convert(Tuple tuple) {
        CreateFrecuenciaCardiacaDTO dto = new CreateFrecuenciaCardiacaDTO();
        dto.setFecha(tuple.get("fecha", String.class));
        dto.setHora(tuple.get("hora", String.class));
        dto.setFrecuencia(tuple.get("frecuencia", String.class));
        dto.setPacienteId(tuple.get("pacienteId", Integer.class));
        return dto;
    }
}
