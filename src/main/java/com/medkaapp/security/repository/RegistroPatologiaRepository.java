package com.medkaapp.security.repository;

import com.medkaapp.security.entity.RegistroPatologia;
import com.medkaapp.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroPatologiaRepository extends JpaRepository<RegistroPatologia, Long> {

    @Query(value = "select * from registro_patologia m where m.id_paciente = :id", nativeQuery = true)
    List<RegistroPatologia> findByUser(Usuario id);
}
