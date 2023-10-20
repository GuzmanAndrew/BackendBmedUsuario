package com.medkaapp.security.service;

import com.medkaapp.security.dto.CreateFrecuenciaCardiacaDTO;
import com.medkaapp.security.entity.FrecuenciaCardiaca;
import com.medkaapp.security.entity.Usuario;

import javax.persistence.Tuple;
import java.util.List;

public interface IFrecuenciaCardiacaService {
    public List<FrecuenciaCardiaca> listarFrecuenciaCardiaca();

    public FrecuenciaCardiaca guardarFrecuenciaCardiaca(FrecuenciaCardiaca presion);

    public FrecuenciaCardiaca frecuenciaCardiacaId(Long id);

    public FrecuenciaCardiaca actualizarFrecuenciaCardiaca(FrecuenciaCardiaca presion);

    public void eliminarFrecuenciaCardiaca(Long id);

    public List<Tuple> findDataFrecuencia(Usuario id);
}
