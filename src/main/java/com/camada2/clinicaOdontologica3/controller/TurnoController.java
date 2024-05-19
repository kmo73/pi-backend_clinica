package com.camada2.clinicaOdontologica3.controller;

import com.camada2.clinicaOdontologica3.entity.Odontologo;
import com.camada2.clinicaOdontologica3.entity.Pacientes;
import com.camada2.clinicaOdontologica3.entity.Turno;
import com.camada2.clinicaOdontologica3.service.OdontologoService;
import com.camada2.clinicaOdontologica3.service.PacienteService;
import com.camada2.clinicaOdontologica3.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        Optional<Pacientes> pacienteBuscado = pacienteService.buscarPPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno elminado con éxito");
        } else {
            return ResponseEntity.badRequest().body("No se encontro el turno a eliminar");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(turno.getId());
        if (turnoBuscado.isPresent()){
            Optional<Pacientes> pacienteBuscado = pacienteService.buscarPPorId(turno.getPaciente().getId());
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarOPorId(turno.getOdontologo().getId());
            if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Turno actualizado con éxito");
            } else {
                return ResponseEntity.badRequest().body("No se pudo encontrar al paciente y/o al odontologo");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar el turno");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarUnTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> mostrarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }
}
