package com.medkaapp.security.repository;

import com.medkaapp.security.dto.CreateFrecuenciaCardiacaDTO;
import com.medkaapp.security.entity.FrecuenciaCardiaca;
import com.medkaapp.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface IFrecuenciaCardiacaDao extends JpaRepository<FrecuenciaCardiaca, Long> {
    @Query(value = "select fc.fecha as fecha, fc.hora as hora, fc.frecuencia " +
            "as frecuencia, fc.id_paciente as pacienteId from " +
            "frecuencia_cardiaca fc where fc.id_paciente = :id", nativeQuery = true)
    List<Tuple> findByUserNative(Usuario id);
}
