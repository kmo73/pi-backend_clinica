package com.camada2.clinicaOdontologica3.security;

import com.camada2.clinicaOdontologica3.entity.*;
import com.camada2.clinicaOdontologica3.repository.IOdontologoRepository;
import com.camada2.clinicaOdontologica3.repository.IPacienteRepository;
import com.camada2.clinicaOdontologica3.repository.ITurnoRepository;
import com.camada2.clinicaOdontologica3.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private ITurnoRepository turnoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();
        String contraseñaCifrada = encriptador.encode("admin");
        Usuario user1 = new Usuario("Camilo", "camilo123","admin@mail.com", contraseñaCifrada, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(user1);
        String contraseñaCifrada2 = encriptador.encode("user");
        Usuario user2 = new Usuario("Ignacio","Nacho123", "user@mail.com", contraseñaCifrada2,UsuarioRole.ROLE_USER);
        usuarioRepository.save(user2);

    }
}
