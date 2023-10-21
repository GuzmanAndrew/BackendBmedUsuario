package com.medkaapp.security.controller;

import com.medkaapp.security.entity.OgixenoSangre;
import com.medkaapp.security.dto.CreateOxigenoSangreDTO;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.OxigenoSangreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OxigenoCardiacaController {

    @Autowired
    OxigenoSangreServiceImpl oxigenoSangreService;

    @Autowired
    IUsuarioDao pacienteDao;

    @GetMapping("/oxi/user/{id}")
    public List<OgixenoSangre> presionIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        return oxigenoSangreService.findByUser(usuario);
    }

    @PostMapping("/oxi/save")
    public ResponseEntity<?> oxigenoSangreSave(@RequestBody CreateOxigenoSangreDTO oxigeno) {
        OgixenoSangre newOxigeno = new OgixenoSangre();
        newOxigeno.setFecha(oxigeno.getFecha());
        newOxigeno.setHora(oxigeno.getHora());
        newOxigeno.setOxigeno(oxigeno.getOxigeno());
        Usuario temp = pacienteDao.findById(oxigeno.getPacienteId()).orElse(null);
        newOxigeno.setPaciente(temp);

        return ResponseEntity.status(HttpStatus.CREATED).body(oxigenoSangreService.guardarOxigenoSangre(newOxigeno));
    }

}
