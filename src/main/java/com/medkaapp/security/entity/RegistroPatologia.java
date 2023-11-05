package com.medkaapp.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "registro_patologia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPatologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_patologia")
    private String nombrePatologia;

    @Column(name = "tipo_patologia")
    private String tipoPatologia;

    @Column(name = "requiere_tratamiento")
    private Boolean requiereTratamiento;

    @Column(name = "descripcion_tratamiento")
    private String descripcionTratamiento;

    @ManyToOne()
    @JoinColumn(name = "id_paciente")
    private Usuario usuario;
}
