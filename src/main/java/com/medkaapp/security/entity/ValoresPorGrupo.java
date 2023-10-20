package com.medkaapp.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValoresPorGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "grupo_edad_id")
    private GruposEdad grupoEdadId;
    @ManyToOne
    @JoinColumn(name = "clasificacion_id")
    private Clasificaciones clasificacionId;
    private Integer valorMin;
    private Integer valorMax;
}
