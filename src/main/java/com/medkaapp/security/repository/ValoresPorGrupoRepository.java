package com.medkaapp.security.repository;

import com.medkaapp.security.entity.ValoresPorGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValoresPorGrupoRepository extends JpaRepository<ValoresPorGrupo, Long> {
    @Query("SELECT v FROM ValoresPorGrupo v WHERE v.grupoEdadId.rango = :rangoEdad AND " +
            "((v.valorMax IS NOT NULL AND :bpm BETWEEN v.valorMin AND v.valorMax) OR " +
            "(v.valorMax IS NULL AND :bpm >= v.valorMin))")
    ValoresPorGrupo findByRangoAndBpm(String rangoEdad, Integer bpm);
}
