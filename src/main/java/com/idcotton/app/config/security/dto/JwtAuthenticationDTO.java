package com.idcotton.app.config.security.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public @Data class JwtAuthenticationDTO {

	@NotEmpty(message = "Login não pode ser vazia.")
	private String login;

	@NotEmpty(message = "Senha não pode ser vazia.")
	private String senha;

}
