package com.camada2.clinicaOdontologica3;

import com.camada2.clinicaOdontologica3.entity.Domicilio;
import com.camada2.clinicaOdontologica3.entity.Odontologo;
import com.camada2.clinicaOdontologica3.entity.Pacientes;
import com.camada2.clinicaOdontologica3.service.OdontologoService;
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
public class IntegracionOdontologoTest {
    @Autowired
    private OdontologoService OdontologoService;
    @Autowired
    private MockMvc mockMvc;


    public void agregarDatosIniciales(){
        Odontologo odontologo= new Odontologo("123","Camilo","Patiño");
        Odontologo dontologo2= new Odontologo("4","Camilo2","Patiño2");

        OdontologoService.guardarO(odontologo);
        OdontologoService.guardarO(dontologo2);
    }
    @Order(1)
    @Test
    public void getOdontologoPorIdTest() throws Exception{
        agregarDatosIniciales();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/odontologo/2").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertTrue(!respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Order(2)
    @Test
    public void listarOdontologoTest() throws Exception{

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/odontologo/listar").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


    @Order(3)
    @Test
    public void eliminarOdontologoTest() throws Exception{

        Odontologo odontologo3= new Odontologo("44","Ignacio","Freilij");

        OdontologoService.guardarO(odontologo3);
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/odontologo/eliminar/1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}
