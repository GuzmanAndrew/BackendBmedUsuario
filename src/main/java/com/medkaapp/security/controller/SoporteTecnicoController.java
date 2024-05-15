package com.medkaapp.security.controller;

import com.medkaapp.security.entity.SoporteTecnico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class SoporteTecnicoController {

    @PostMapping("/soporte-tecnico")
    public ResponseEntity<?> capturarDatos(@RequestBody SoporteTecnico datos) {
        if (datos.getPreguntaid() == null || datos.getPreguntaid().isEmpty()) {
            int valorRandom = (int) Math.floor(Math.random() * 100 + 1);
            String idPreguntaConvert = Integer.toString(valorRandom);
            datos.setPreguntaid(idPreguntaConvert);
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://uk9lkr1ld9.execute-api.us-east-1.amazonaws.com/produccion/pregunta";
        String requestJson = "{\"preguntaid\":\"" + datos.getPreguntaid() + "\",\"nombre\":\"" + datos.getNombre() + "\",\"correo\":\"" + datos.getCorreo() + "\",\"asunto\":\"" + datos.getAsunto() + "\",\"mensaje\":\"" + datos.getMensaje() + "\"}";
        String response = restTemplate.postForObject(url, requestJson, String.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
