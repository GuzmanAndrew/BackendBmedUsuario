package com.medkaapp.security.repository;

import com.medkaapp.security.entity.Temperatura;
import com.medkaapp.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITemperaturaDao extends JpaRepository<Temperatura, Long> {
    @Query(value = "select * from temperatura t where t.id_paciente = :id", nativeQuery = true)
    List<Temperatura> findByUser(Usuario id);
}
