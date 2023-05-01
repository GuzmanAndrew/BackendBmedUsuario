package com.medkaapp.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="covid")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Covid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    @Column(name = "hora")
    private String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

    @Column(name = "prediccion")
    private String prediccion;

    @Column(name = "puntaje")
    private String puntaje;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Usuario paciente;

}
