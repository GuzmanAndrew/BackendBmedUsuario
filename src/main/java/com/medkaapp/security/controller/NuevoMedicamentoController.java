package com.medkaapp.security.controller;

import com.medkaapp.error.MedicamentoNotFoundException;
import com.medkaapp.security.dto.MedicamentoDto;
import com.medkaapp.security.entity.*;
import com.medkaapp.security.repository.IUsuarioDao;
import com.medkaapp.security.service.impl.TipoMedicamentoServiceImpl;
import com.medkaapp.security.service.impl.RegistroMedicamentoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medicamentos")
@CrossOrigin
public class NuevoMedicamentoController {

    @Autowired
    private TipoMedicamentoServiceImpl service;

    @Autowired
    private RegistroMedicamentoServiceImpl registroMedicamentoService;

    @Autowired
    private IUsuarioDao pacienteDao;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/presentacion")
    public List<PresentacionMedicamento> listarMedicamentosPresentacion() {
        return service.listarPresentacionMedicamentos();
    }

    @GetMapping("/via")
    public List<ViaMedicamento> listarViaMedicamento() {
        return service.listarViaMedicamentos();
    }

    @GetMapping("/listar")
    public List<MedicamentoDto> listarMedicamentos() {
        return registroMedicamentoService.listarMedicamentos();
    }

    @GetMapping("/user/{id}")
    public List<MedicamentoDto> medicamentoIdUser(@PathVariable(name = "id") Integer id) {
        Usuario usuario = pacienteDao.findById(id).get();
        return registroMedicamentoService.findByUser(usuario);
    }

    @GetMapping("/find/{id}")
    public MedicamentoDto medicamentoId(@PathVariable(name = "id") Long id) {
        MedicamentoDto medicamentoId = registroMedicamentoService.medicamentoId(id);
        return medicamentoId;
    }

    @PostMapping("/save")
    public ResponseEntity<MedicamentoDto> medicamentoSave(@RequestBody MedicamentoDto medicamento) {
        RegistroMedicamento newMedicamento = new RegistroMedicamento();
        newMedicamento.setNombreMedicamento(medicamento.getNombreMedicamento());
        newMedicamento.setNombreLaboratorio(medicamento.getNombreLaboratorio());
        newMedicamento.setPresentacionMedicamento(medicamento.getPresentacionMedicamento());
        newMedicamento.setViaAdministracion(medicamento.getViaAdministracion());
        newMedicamento.setCantidadDia(medicamento.getCantidadDia());
        newMedicamento.setCantidadMes(medicamento.getCantidadMes());
        Usuario usuario = pacienteDao.findById(medicamento.getUsuarioId()).orElse(null);
        newMedicamento.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registroMedicamentoService.guardarMedicamento(newMedicamento));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<MedicamentoDto> editMedicamento(@PathVariable(name = "id") Long id, @RequestBody MedicamentoDto medicamento) {
        MedicamentoDto medicamentoId = registroMedicamentoService.medicamentoId(id);
        RegistroMedicamento medicamentoSelect = modelMapper.map(medicamentoId, RegistroMedicamento.class);
        medicamentoSelect.setNombreMedicamento(medicamento.getNombreMedicamento());
        medicamentoSelect.setNombreLaboratorio(medicamento.getNombreLaboratorio());
        medicamentoSelect.setPresentacionMedicamento(medicamento.getPresentacionMedicamento());
        medicamentoSelect.setViaAdministracion(medicamento.getViaAdministracion());
        medicamentoSelect.setCantidadDia(medicamento.getCantidadDia());
        medicamentoSelect.setCantidadMes(medicamento.getCantidadMes());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registroMedicamentoService.actualizarMedicamento(medicamentoSelect));
    }

    @DeleteMapping("deleted/{id}")
    public ResponseEntity<Map<String, String>> deleteMedicamento(@PathVariable(name = "id") Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            registroMedicamentoService.eliminarMedicamento(id);
            response.put("status", "success");
            response.put("message", "Medicamento eliminado con éxito.");
            return ResponseEntity.ok(response);
        } catch (MedicamentoNotFoundException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
