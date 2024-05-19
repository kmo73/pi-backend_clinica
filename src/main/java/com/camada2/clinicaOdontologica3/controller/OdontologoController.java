package com.camada2.clinicaOdontologica3.controller;

import com.camada2.clinicaOdontologica3.entity.Odontologo;
import com.camada2.clinicaOdontologica3.exception.NotFoundException;
import com.camada2.clinicaOdontologica3.service.OdontologoService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Odontologo> crearOdontologo(@RequestBody Odontologo odontologo){

        return ResponseEntity.ok(odontologoService.guardarO(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorID(@PathVariable Long id) throws NotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOPorId(id);
        if(odontologoBuscado.isPresent()){
                return ResponseEntity.ok(odontologoService.buscarOPorId(id).get());
    }else {
            throw new NotFoundException("El odontologo no ha sido encontrado. Verifique el ID ingresado.");
        }
      }
      @GetMapping("/listar")
      public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
      }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id ) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOPorId(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarO(id);
            return ResponseEntity.ok("Odontologo eliminado correctamente");
        }else {
            return ResponseEntity.badRequest().body("No se encontro el odontologo a eliminar");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws NotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarO(odontologo);
            return ResponseEntity.ok("Odontologo actualizado correctamente");
        }else {
            throw new NotFoundException("El odontologo no ha sido actualizado correctamente. Verifique los datos ingresados.");
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Odontologo> buscarOPorMatricula(@RequestParam("matricula") String matricula) throws NotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOPorMatricula(matricula);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            throw new NotFoundException("El odontologo no ha sido encontrado. Verifique la matricula ingresada.");
        }
    }

}
