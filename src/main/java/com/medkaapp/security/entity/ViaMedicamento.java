package com.medkaapp.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="via_medicamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViaMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "via_medicamento")
    private String viaMedicamento;
}
