package com.medkaapp.security.controller;

import com.medkaapp.security.entity.FrecuenciaCardiaca;
import com.medkaapp.security.dto.CreateFrecuenciaCardiacaDTO;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.FrecuenciaCardiacaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FrecuenciaCardiacaController {
    @Autowired
    FrecuenciaCardiacaServiceImpl frecuenciaCardiacaService;

    @Autowired
    IUsuarioDao pacienteDao;


    @GetMapping("/frecuencia/user/{id}")
    public List<FrecuenciaCardiaca> frecuenciaCardiacaIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        return frecuenciaCardiacaService.findByUser(usuario);
    }

    @PostMapping("/frecuencia/save")
    public ResponseEntity<?> frecuenciaCardiacaSave(@RequestBody CreateFrecuenciaCardiacaDTO frecuencia) {
        FrecuenciaCardiaca newFrecuencia = new FrecuenciaCardiaca();
        newFrecuencia.setFecha(frecuencia.getFecha());
        newFrecuencia.setHora(frecuencia.getHora());
        newFrecuencia.setFrecuencia(frecuencia.getFrecuencia());
        Usuario temp = pacienteDao.findById(frecuencia.getPacienteId()).orElse(null);
        newFrecuencia.setPaciente(temp);

        return ResponseEntity.status(HttpStatus.CREATED).body(frecuenciaCardiacaService.guardarFrecuenciaCardiaca(newFrecuencia));
    }

}
