package com.medkaapp.security.repository;

import com.medkaapp.security.entity.FrecuenciaCardiaca;
import com.medkaapp.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFrecuenciaCardiacaDao extends JpaRepository<FrecuenciaCardiaca, Long> {
    @Query(value = "select * from frecuencia_cardiaca fc where fc.id_paciente = :id", nativeQuery = true)
    List<FrecuenciaCardiaca> findByUser(Usuario id);
}
