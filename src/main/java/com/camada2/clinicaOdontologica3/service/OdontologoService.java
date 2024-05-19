package com.camada2.clinicaOdontologica3.service;

import com.camada2.clinicaOdontologica3.entity.Odontologo;
import com.camada2.clinicaOdontologica3.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardarO(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOPorId(Long id){
        return odontologoRepository.findById(id);
    }

    public void actualizarO(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }

    public void eliminarO(Long id){
        odontologoRepository.deleteById(id);
    }

    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarOPorMatricula(String matricula){
        return odontologoRepository.findByMatricula(matricula);
    }
}
