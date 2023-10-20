package com.medkaapp.security.controller;

import com.medkaapp.security.dto.ClasificacionFrecuenciaDto;
import com.medkaapp.security.service.ClasificacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clasificacion-frecuencia")
@CrossOrigin
public class ClasificacionFrecuenciaController {
    @Autowired
    private ClasificacionServiceImpl clasificacionService;

    @PostMapping("/clasificar")
    public ResponseEntity<Map<String, String>> clasificar(@RequestBody ClasificacionFrecuenciaDto request) {
        String clasificacion = clasificacionService.obtenerClasificacion(request.getValue(), request.getUserName());
        Map<String, String> response = new HashMap<>();
        response.put("clasificacion", clasificacion);
        return ResponseEntity.ok(response);
    }
}
