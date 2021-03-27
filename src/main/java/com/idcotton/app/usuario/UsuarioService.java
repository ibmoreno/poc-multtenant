package com.idcotton.app.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -3172581244723050988L;
	private final UsuarioRepository usuarioRepository;

	public Optional<Usuario> buscaPorLogin(String login) {
		return usuarioRepository.findByCredenciaisLoginEqualsIgnoreCase(login);
	}

}
