package com.medkaapp.security.repository;

import com.medkaapp.security.entity.Covid;
import com.medkaapp.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICovidDao extends JpaRepository<Covid, Long> {

    @Query(value = "select * from covid co where co.id_paciente = :id", nativeQuery = true)
    List<Covid> findByUser(Usuario id);
}
