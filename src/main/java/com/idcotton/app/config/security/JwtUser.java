package com.idcotton.app.config.security;

import com.idcotton.app.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 3415186267906945534L;
	
	private Usuario usuario;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		this.usuario = usuario;
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getCredenciais().senha;
	}

	@Override
	public String getUsername() {
		return usuario.getCredenciais().login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !usuario.getBloqueado();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	

}
