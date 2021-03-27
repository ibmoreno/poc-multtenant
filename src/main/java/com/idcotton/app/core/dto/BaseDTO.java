package com.idcotton.app.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public @Data
abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = 8430846498943278776L;

    protected Date cadastrado;

    protected Date atualizado;

}
