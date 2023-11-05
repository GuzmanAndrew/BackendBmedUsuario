package com.medkaapp.security.controller;

import com.medkaapp.security.dto.PatologiaDto;
import com.medkaapp.security.entity.RegistroPatologia;
import com.medkaapp.security.entity.TipoPatologia;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.RegistroPatologiaServiceImpl;
import com.medkaapp.security.service.impl.TipoPatologiaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patologias")
@CrossOrigin
public class NuevaPatologiaController {

    @Autowired
    private TipoPatologiaServiceImpl tipoPatologiaService;

    @Autowired
    private RegistroPatologiaServiceImpl  registroPatologiaService;

    @Autowired
    private IUsuarioDao pacienteDao;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/tipos")
    public List<TipoPatologia> listarTipoEnfermedades() {
        return tipoPatologiaService.listaTipoPatologias();
    }

    @GetMapping("/listar")
    public List<PatologiaDto> listarMedicamentos() {
        return registroPatologiaService.listarPatologias();
    }

    @GetMapping("/user/{id}")
    public List<PatologiaDto> medicamentoIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        return registroPatologiaService.findByUser(usuario);
    }

    @GetMapping("/find/{id}")
    public PatologiaDto medicamentoId(@PathVariable(name = "id") Long id) {
        PatologiaDto medicamentoId = registroPatologiaService.patologiaId(id);
        return medicamentoId;
    }

    @PostMapping("/save")
    public ResponseEntity<PatologiaDto> medicamentoSave(@RequestBody PatologiaDto patologia) {
        RegistroPatologia newRegistro = new RegistroPatologia();
        newRegistro.setNombrePatologia(patologia.getNombrePatologia());
        newRegistro.setTipoPatologia(patologia.getTipoPatologia());
        newRegistro.setRequiereTratamiento(patologia.getRequiereTratamiento());
        newRegistro.setDescripcionTratamiento(patologia.getDescripcionTratamiento());
        Usuario usuario = pacienteDao.findById(patologia.getUsuarioId()).orElse(null);
        newRegistro.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registroPatologiaService.guardarPatologia(newRegistro));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PatologiaDto> editMedicamento(@PathVariable(name = "id") Long id, @RequestBody PatologiaDto patologia) {
        PatologiaDto patologiaIdId = registroPatologiaService.patologiaId(id);
        RegistroPatologia patologiaSelect = modelMapper.map(patologiaIdId, RegistroPatologia.class);
        patologiaSelect.setRequiereTratamiento(patologia.getRequiereTratamiento());
        patologiaSelect.setDescripcionTratamiento(patologia.getDescripcionTratamiento());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registroPatologiaService.actualizarPatologia(patologiaSelect));
    }
}
