package com.medkaapp.security.controller;

import com.medkaapp.security.entity.Temperatura;
import com.medkaapp.security.dto.CreateTemperaturaDTO;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.TemperaturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TemperaturaController {
    @Autowired
    TemperaturaServiceImpl temperaturaService;

    @Autowired
    IUsuarioDao pacienteDao;

    @GetMapping("/temp/user/{id}")
    public List<Temperatura> presionIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        return temperaturaService.findByUser(usuario);
    }

    @PostMapping("/temp/save")
    public ResponseEntity<?> temperaturaSave(@RequestBody CreateTemperaturaDTO temperatura) {
        Temperatura newTemperatura = new Temperatura();
        newTemperatura.setFecha(temperatura.getFecha());
        newTemperatura.setHora(temperatura.getHora());
        newTemperatura.setTemperatura(temperatura.getTemperatura());
        Usuario temp = pacienteDao.findById(temperatura.getPacienteId()).orElse(null);
        newTemperatura.setPaciente(temp);

        return ResponseEntity.status(HttpStatus.CREATED).body(temperaturaService.guardarTemperatura(newTemperatura));
    }

}
