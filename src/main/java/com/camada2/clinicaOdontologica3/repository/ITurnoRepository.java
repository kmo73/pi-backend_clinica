package com.camada2.clinicaOdontologica3.repository;

import com.camada2.clinicaOdontologica3.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
