package com.camada2.clinicaOdontologica3.service;

import com.camada2.clinicaOdontologica3.entity.Turno;
import com.camada2.clinicaOdontologica3.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class TurnoService {
    @Autowired
    private ITurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }

    public Optional<Turno> buscarTurnoPorId(Long id){
        return turnoRepository.findById(id);
    }

    public List<Turno> listarTurnos(){
        return turnoRepository.findAll();
    }

}
