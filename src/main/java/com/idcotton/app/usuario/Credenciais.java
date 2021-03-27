package com.idcotton.app.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Embeddable
public @Data class Credenciais implements Serializable {

	private static final long serialVersionUID = -1240812161236546784L;

	@Column(name = "LOGIN", length = 80, nullable = false, columnDefinition = "CHAR(80)")
	public String login;

	@Column(name = "SENHA", length = 70, nullable = false, columnDefinition = "VARCHAR(70)")
	public String senha;

}
