package com.medkaapp.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="presentacion_medicamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresentacionMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_medicamento")
    private String nombreMedicamento;
}
