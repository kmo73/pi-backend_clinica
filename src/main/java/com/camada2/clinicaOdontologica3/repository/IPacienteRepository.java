package com.camada2.clinicaOdontologica3.repository;

import com.camada2.clinicaOdontologica3.entity.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Pacientes, Long> {
    Optional<Pacientes> findByEmail(String email);
}
