package com.idcotton.app.config.multitenant;


import com.idcotton.app.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DATASOURCE_CONFIG")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public @Data class MultiTenantDataSourceConfig extends BaseEntity {

    @Id
    @Column(name = "CODIGO", nullable = false)
    @EqualsAndHashCode.Include
    private Long codigo;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DRIVER_CLASS_NAME", nullable = false)
    private String driverClassName;

}
