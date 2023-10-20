package com.medkaapp.security.controller;

import com.medkaapp.security.entity.FrecuenciaCardiaca;
import com.medkaapp.security.dto.CreateFrecuenciaCardiacaDTO;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.mapper.DataMapper;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.FrecuenciaCardiacaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FrecuenciaCardiacaController {
    @Autowired
    FrecuenciaCardiacaServiceImpl frecuenciaCardiacaService;
    @Autowired
    DataMapper mapper;
    @Autowired
    IUsuarioDao pacienteDao;

    @GetMapping("/frecuencia/user/{id}")
    public List<CreateFrecuenciaCardiacaDTO> frecuenciaCardiacaIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        List<Tuple> tuple = frecuenciaCardiacaService.findDataFrecuencia(usuario);
        return tuple.stream().map(mapper::convert).collect(Collectors.toList());
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
