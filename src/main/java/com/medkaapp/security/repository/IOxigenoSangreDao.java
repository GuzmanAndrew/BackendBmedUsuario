package com.medkaapp.security.repository;

import com.medkaapp.security.entity.OgixenoSangre;
import com.medkaapp.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOxigenoSangreDao extends JpaRepository<OgixenoSangre, Long> {
    @Query(value = "select * from oxigeno_sangre os where os.id_paciente = :id", nativeQuery = true)
    List<OgixenoSangre> findByUser(Usuario id);
}
