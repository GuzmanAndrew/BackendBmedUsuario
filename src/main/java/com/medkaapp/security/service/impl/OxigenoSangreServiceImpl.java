package com.medkaapp.security.service.impl;

import com.medkaapp.security.repository.IOxigenoSangreDao;
import com.medkaapp.security.entity.OgixenoSangre;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.service.IOxigenoSangreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OxigenoSangreServiceImpl implements IOxigenoSangreService {

    @Autowired
    private IOxigenoSangreDao iOxigenoSangreDao;

    @Override
    public List<OgixenoSangre> listarOxigenoSangre() {
        return iOxigenoSangreDao.findAll();
    }

    @Override
    public OgixenoSangre guardarOxigenoSangre(OgixenoSangre presion) {
        return iOxigenoSangreDao.save(presion);
    }

    @Override
    public OgixenoSangre oxigenoSangreId(Long id) {
        return iOxigenoSangreDao.findById(id).get();
    }

    @Override
    public OgixenoSangre actualizarOxigenoSangre(OgixenoSangre presion) {
        return iOxigenoSangreDao.save(presion);
    }

    @Override
    public void oxigenoSangreDelete(Long id) {
        iOxigenoSangreDao.deleteById(id);
    }

    @Override
    public List<OgixenoSangre> findByUser(Usuario id) {
        return iOxigenoSangreDao.findByUser(id);
    }
}
