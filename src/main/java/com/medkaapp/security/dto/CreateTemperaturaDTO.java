package com.medkaapp.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
public class CreateTemperaturaDTO {

    private String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    private String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm", Locale.US));

    private int temperatura;

    private int pacienteId;
}
