package com.medkaapp.security.repository;

import com.medkaapp.security.entity.RegistroMedicamento;
import com.medkaapp.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroMedicamentoRepository extends JpaRepository<RegistroMedicamento, Long> {

    @Query(value = "select * from registro_medicamento m where m.id_paciente = :id", nativeQuery = true)
    List<RegistroMedicamento> findByUser(Usuario id);
}
