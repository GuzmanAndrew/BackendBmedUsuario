package com.medkaapp.security.service.impl;

import com.medkaapp.security.repository.ITemperaturaDao;
import com.medkaapp.security.entity.Temperatura;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.service.ITemperaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperaturaServiceImpl implements ITemperaturaService {

    @Autowired
    private ITemperaturaDao iTemperaturaDao;

    @Override
    public List<Temperatura> listarTemperatura() {
        return iTemperaturaDao.findAll();
    }

    @Override
    public Temperatura guardarTemperatura(Temperatura presion) {
        return iTemperaturaDao.save(presion);
    }

    @Override
    public Temperatura temperaturaId(Long id) {
        return iTemperaturaDao.findById(id).get();
    }

    @Override
    public Temperatura actualizarTemperatura(Temperatura presion) {
        return iTemperaturaDao.save(presion);
    }

    @Override
    public void eliminarTemperatura(Long id) {
        iTemperaturaDao.deleteById(id);
    }

    @Override
    public List<Temperatura> findByUser(Usuario id) {
        return iTemperaturaDao.findByUser(id);
    }
}
