package com.medkaapp.security.service.impl;

import com.medkaapp.security.entity.Covid;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.ICovidDao;
import com.medkaapp.security.service.ICovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CovidServiceImpl implements ICovidService {

    @Autowired
    private ICovidDao covidDao;

    @Override
    public List<Covid> listarCovid() {
        return covidDao.findAll();
    }

    @Override
    public Covid guardarCovid(Covid covid) {
        return covidDao.save(covid);
    }

    @Override
    public Covid covidId(Long id) {
        return covidDao.findById(id).get();
    }

    @Override
    public Covid actualizarCovid(Covid covid) {
        return covidDao.save(covid);
    }

    @Override
    public void eliminarCovid(Long id) {
        covidDao.deleteById(id);
    }

    @Override
    public List<Covid> findByUser(Usuario id) {
        return covidDao.findByUser(id);
    }
}
