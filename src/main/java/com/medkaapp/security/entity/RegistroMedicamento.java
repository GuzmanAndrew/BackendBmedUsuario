package com.medkaapp.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "registro_medicamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_medicamento")
    private String nombreMedicamento;

    @Column(name = "nombre_laboratorio")
    private String nombreLaboratorio;

    @Column(name = "presentacion_medicamento")
    private String presentacionMedicamento;

    @Column(name = "via_administracion")
    private String viaAdministracion;

    @Column(name = "cantidad_dia")
    private String cantidadDia;

    @Column(name = "cantidad_mes")
    private String cantidadMes;

    @ManyToOne()
    @JoinColumn(name = "id_paciente")
    private Usuario usuario;
}
