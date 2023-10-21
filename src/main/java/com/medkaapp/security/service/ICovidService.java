package com.medkaapp.security.service;

import com.medkaapp.security.entity.Covid;
import com.medkaapp.security.entity.Usuario;

import java.util.List;

public interface ICovidService {

    public List<Covid> listarCovid();

    public Covid guardarCovid(Covid covid);

    public Covid covidId(Long id);

    public List<Covid> findByUser(Usuario id);
}
