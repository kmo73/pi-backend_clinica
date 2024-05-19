package com.camada2.clinicaOdontologica3;

import com.camada2.clinicaOdontologica3.entity.Domicilio;
import com.camada2.clinicaOdontologica3.entity.Pacientes;
import com.camada2.clinicaOdontologica3.service.PacienteService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
public class IntegracionPacienteTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;


    public void agregarDatosIniciales(){
        Pacientes paciente= new Pacientes("Ignacio","Freilij","111", LocalDate.of(2022,10,15),"ignacio.freilij@gmail.com",new Domicilio("Rojas  ",3,"Almagro","Caba"));
        Pacientes paciente2= new Pacientes("Ignacio2","Freilij2","1112", LocalDate.of(2022,10,15),"ignacio2.freilij@gmail.com",new Domicilio("Rojas2  ",32,"Almagro","Caba"));

        pacienteService.guardarP(paciente);
        pacienteService.guardarP(paciente2);
    }

    @Order(1)
    @Test
    public void getPacientePorIdTest() throws Exception{
        agregarDatosIniciales();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/paciente/2").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertTrue(!respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Order(2)
    @Test
    public void listarPacientesTest() throws Exception{

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/paciente/listar").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


    @Order(3)
    @Test
    public void eliminarPacienteTest() throws Exception{

        Pacientes paciente3= new Pacientes("Ignacio3","Freilij3","1113", LocalDate.of(2022,10,15),"ignacio3.freilij@gmail.com",new Domicilio("Rojas3  ",33,"Almagro3","Caba"));

        pacienteService.guardarP(paciente3);

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/eliminar/1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}
