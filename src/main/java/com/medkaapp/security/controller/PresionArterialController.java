package com.medkaapp.security.controller;

import com.medkaapp.security.entity.PresionArterial;
import com.medkaapp.security.dto.CreatePresionArterialDTO;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.PresionArterialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PresionArterialController {

    @Autowired
    PresionArterialServiceImpl presionService;

    @Autowired
    IUsuarioDao pacienteDao;

    @GetMapping("/presion/user/{id}")
    public List<PresionArterial> presionIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        return presionService.findByUser(usuario);
    }

    @PostMapping("/presion/save")
    public ResponseEntity<?> presionArterialSave(@RequestBody CreatePresionArterialDTO presion) {
        PresionArterial newPresionArterial = new PresionArterial();
        newPresionArterial.setFecha(presion.getFecha());
        newPresionArterial.setHora(presion.getHora());
        newPresionArterial.setSistolica(presion.getSistolica());
        newPresionArterial.setDiastolica(presion.getDiastolica());
        Usuario paciente = pacienteDao.findById(presion.getPacienteId()).orElse(null);
        newPresionArterial.setPaciente(paciente);

        return ResponseEntity.status(HttpStatus.CREATED).body(presionService.guardarPresion(newPresionArterial));
    }

}
