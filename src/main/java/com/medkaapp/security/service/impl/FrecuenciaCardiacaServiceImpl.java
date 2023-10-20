package com.medkaapp.security.service.impl;

import com.medkaapp.security.dto.CreateFrecuenciaCardiacaDTO;
import com.medkaapp.security.repository.IFrecuenciaCardiacaDao;
import com.medkaapp.security.entity.FrecuenciaCardiaca;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.service.IFrecuenciaCardiacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;

@Service
public class FrecuenciaCardiacaServiceImpl implements IFrecuenciaCardiacaService {

    @Autowired
    private IFrecuenciaCardiacaDao iFrecuenciaCardiacaDao;

    @Override
    public List<FrecuenciaCardiaca> listarFrecuenciaCardiaca() {
        return iFrecuenciaCardiacaDao.findAll();
    }

    @Override
    public FrecuenciaCardiaca guardarFrecuenciaCardiaca(FrecuenciaCardiaca presion) {
        return iFrecuenciaCardiacaDao.save(presion);
    }

    @Override
    public FrecuenciaCardiaca frecuenciaCardiacaId(Long id) {
        return iFrecuenciaCardiacaDao.findById(id).get();
    }

    @Override
    public FrecuenciaCardiaca actualizarFrecuenciaCardiaca(FrecuenciaCardiaca presion) {
        return iFrecuenciaCardiacaDao.save(presion);
    }

    @Override
    public void eliminarFrecuenciaCardiaca(Long id) {
        iFrecuenciaCardiacaDao.deleteById(id);
    }

    @Override
    public List<Tuple> findDataFrecuencia(Usuario id) {
        return iFrecuenciaCardiacaDao.findByUserNative(id);
    }
}
