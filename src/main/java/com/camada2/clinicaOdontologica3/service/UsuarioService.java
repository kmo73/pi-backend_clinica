package com.camada2.clinicaOdontologica3.service;

import com.camada2.clinicaOdontologica3.entity.Usuario;
import com.camada2.clinicaOdontologica3.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userBuscado = usuarioRepository.findByEmail(username);
        if (userBuscado.isPresent()){
            return userBuscado.get();
        } else{
            throw new UsernameNotFoundException("No existe el usuario");
        }



    }
}
