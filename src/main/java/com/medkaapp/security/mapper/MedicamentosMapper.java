package com.medkaapp.security.mapper;

import com.medkaapp.security.dto.CreateFrecuenciaCardiacaDTO;
import com.medkaapp.security.dto.MedicamentoDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;

@Component
public class MedicamentosMapper implements Converter<Tuple, MedicamentoDto> {

    @Override
    public MedicamentoDto convert(Tuple tuple) {
        MedicamentoDto dto = new MedicamentoDto();
        dto.setId(tuple.get("id", Long.class));
        dto.setNombreMedicamento(tuple.get("nombreMedicamento", String.class));
        dto.setNombreLaboratorio(tuple.get("nombreLaboratorio", String.class));
        dto.setPresentacionMedicamento(tuple.get("presentacionMedicamento", String.class));
        dto.setViaAdministracion(tuple.get("viaAdministracion", String.class));
        dto.setCantidadDia(tuple.get("cantidadDia", String.class));
        dto.setCantidadMes(tuple.get("cantidadMes", String.class));
        return dto;
    }
}
