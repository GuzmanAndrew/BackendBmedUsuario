package com.medkaapp.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
public class CreateCovidDTO {

    private String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    private String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm", Locale.US));
    private String prediccion;
    private String puntaje;
    private int pacienteId;
}
