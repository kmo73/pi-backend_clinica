package com.camada2.clinicaOdontologica3.controller;

import com.camada2.clinicaOdontologica3.entity.Pacientes;
import com.camada2.clinicaOdontologica3.exception.NotFoundException;
import com.camada2.clinicaOdontologica3.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Pacientes> crearPaciente(@RequestBody Pacientes paciente){

        return ResponseEntity.ok(pacienteService.guardarP(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pacientes>> buscarPacientePorID(@PathVariable Long id) throws NotFoundException {
        Optional<Pacientes> pacienteBuscado = pacienteService.buscarPPorId(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteService.buscarPPorId(id));
        }else {
            throw new NotFoundException("El paciente no ha sido encontrado. Verifique el ID ingresado.");
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Pacientes>> listarPacientes(){

        return ResponseEntity.ok(pacienteService.listarTodos());
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id ) {
        Optional<Pacientes> pacienteBuscado = pacienteService.buscarPPorId(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarP(id);
            return ResponseEntity.ok("Paciente eliminado correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se encontro el paciente a eliminar");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Pacientes paciente) throws NotFoundException{
        Optional<Pacientes> pacienteBuscado = pacienteService.buscarPPorId(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarP(paciente);
            return ResponseEntity.ok("Paciente actualizado correctamente");
        }else {
            throw new NotFoundException("El paciente no ha sido actualizado correctamente. Verifique los datos ingresados.");
        }
    }

    @GetMapping("/correo")
    public ResponseEntity<Pacientes> buscarPacientePorCorreo(@RequestParam("email") String correo) throws NotFoundException  {
        Optional<Pacientes> pacienteBuscado = pacienteService.buscarPPorEmail(correo);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new NotFoundException("El paciente no ha sido encontrado. Verifique el correo ingresado.");
        }
    }

}
