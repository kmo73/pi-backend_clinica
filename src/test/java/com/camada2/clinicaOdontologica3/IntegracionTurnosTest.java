package com.camada2.clinicaOdontologica3;


import com.camada2.clinicaOdontologica3.entity.Domicilio;
import com.camada2.clinicaOdontologica3.entity.Odontologo;
import com.camada2.clinicaOdontologica3.entity.Pacientes;
import com.camada2.clinicaOdontologica3.entity.Turno;
import com.camada2.clinicaOdontologica3.repository.IOdontologoRepository;
import com.camada2.clinicaOdontologica3.repository.IPacienteRepository;
import com.camada2.clinicaOdontologica3.repository.ITurnoRepository;
import com.camada2.clinicaOdontologica3.service.OdontologoService;
import com.camada2.clinicaOdontologica3.service.PacienteService;
import com.camada2.clinicaOdontologica3.service.TurnoService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;


   @BeforeEach
    public void agregarDatosIniciales(){
        Pacientes paciente= new Pacientes("Ignacio","Freilij","111", LocalDate.of(2022,10,15),"ignacio.freilij@gmail.com",new Domicilio("Rojas  ",3,"Almagro","Caba"));
        Odontologo odontologo= new Odontologo("123","Camilo","Patiño");
        Turno turno= new Turno(paciente,odontologo,LocalDate.of(2023,12,01));
        Turno turno2= new Turno(paciente,odontologo,LocalDate.of(2023,12,02));
        Turno turno3= new Turno(paciente,odontologo,LocalDate.of(2023,12,03));
        pacienteService.guardarP(paciente);
        odontologoService.guardarO(odontologo);
        turnoService.guardarTurno(turno);
        turnoService.guardarTurno(turno2);
        turnoService.guardarTurno(turno3);
    }
    @AfterEach
    public void borrarDatos(){
      turnoRepository.deleteAll();
      pacienteRepository.deleteAll();
      odontologoRepository.deleteAll();
    }

    @Order(1)
    @Test
    public void listarTurnosTest() throws Exception{

             MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos/listar").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Order(2)
    @Test
    public void getTurnosPorIdTest() throws Exception{

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos/3").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertTrue(!respuesta.getResponse().getContentAsString().isEmpty());
    }


    @Order(3)
    @Test
    public void eliminarTurnosTest() throws Exception{

        Pacientes paciente= new Pacientes("Ignacio2","Freilij2","1112", LocalDate.of(2022,10,15),"ignacio2.freilij@gmail.com",new Domicilio("Rojas2  ",32,"Almagro2","Caba"));
        Odontologo odontologo= new Odontologo("12322","Camilo2","Patiño2");
        Turno turno= new Turno(paciente,odontologo,LocalDate.of(2023,12,01));
        pacienteService.guardarP(paciente);
        odontologoService.guardarO(odontologo);
        turnoService.guardarTurno(turno);
                MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/4").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}