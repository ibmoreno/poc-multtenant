package com.idcotton.app.config.security;

import com.idcotton.app.usuario.PerfilUsuario;
import com.idcotton.app.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Usuario usuario) {
        return new JwtUser(usuario, mapToGrantedAuthorities(usuario.getPerfilUsuario()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilUsuario perfilEnum) {
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(perfilEnum.toString());
        return authorities;
    }

}
