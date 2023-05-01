package com.medkaapp.security.controller;

import com.medkaapp.security.dto.CreateCovidDTO;
import com.medkaapp.security.entity.Covid;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.CovidServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CovidController {

    @Autowired
    private CovidServiceImpl covidService;

    @Autowired
    private IUsuarioDao pacienteDao;

    @GetMapping("/covid/list")
    public List<Covid> listarCovid(){
        return covidService.listarCovid();
    }

    @GetMapping("/covid/user/{id}")
    public List<Covid> covidIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        return covidService.findByUser(usuario);
    }

    @GetMapping("/covid/{id}")
    public Covid presionId(@PathVariable(name = "id") Long id) {
        Covid covidId = new Covid();
        covidId = covidService.covidId(id);
        return covidId;
    }

    @PostMapping("/covid/save")
    public ResponseEntity<?> covidArterialSave(@RequestBody CreateCovidDTO covid) {
        Covid newCovid = new Covid();
        newCovid.setFecha(covid.getFecha());
        newCovid.setHora(covid.getHora());
        newCovid.setPrediccion(covid.getPrediccion());
        newCovid.setPuntaje(covid.getPuntaje());
        Usuario paciente = pacienteDao.findById(covid.getPacienteId()).orElse(null);
        newCovid.setPaciente(paciente);

        return ResponseEntity.status(HttpStatus.CREATED).body(covidService.guardarCovid(newCovid));
    }
}
