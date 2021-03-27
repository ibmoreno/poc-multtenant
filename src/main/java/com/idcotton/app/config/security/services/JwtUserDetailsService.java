package com.idcotton.app.config.security.services;

import com.idcotton.app.config.security.JwtUserFactory;
import com.idcotton.app.core.exception.NotFoundException;
import com.idcotton.app.usuario.Usuario;
import com.idcotton.app.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioService.buscaPorLogin(login);
        if (usuario.isPresent()) {
            return JwtUserFactory.create(usuario.get());
        }

        throw new NotFoundException("Login não encontrado.");

    }

}
