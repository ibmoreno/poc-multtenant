package com.idcotton.app.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {


    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CADASTRADO", nullable = false, updatable = true)
    protected Date cadastrado;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_ATUALIZADO", nullable = false, updatable = true)
    protected Date atualizado;

    @PrePersist
    public void prePersist() {
        this.cadastrado = new Date(System.currentTimeMillis());
        this.atualizado = this.cadastrado;
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizado = new Date(System.currentTimeMillis());
    }


}
