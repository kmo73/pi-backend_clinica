package com.camada2.clinicaOdontologica3.service;

import com.camada2.clinicaOdontologica3.entity.Pacientes;
import com.camada2.clinicaOdontologica3.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Pacientes guardarP(Pacientes paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Pacientes> buscarPPorId(Long id){
        return pacienteRepository.findById(id);
    }

    public void actualizarP(Pacientes paciente){
        pacienteRepository.save(paciente);
    }

    public void eliminarP(Long id){
        pacienteRepository.deleteById(id);
    }

    public List<Pacientes> listarTodos(){
        return pacienteRepository.findAll();
    }

    public Optional<Pacientes> buscarPPorEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }


}
