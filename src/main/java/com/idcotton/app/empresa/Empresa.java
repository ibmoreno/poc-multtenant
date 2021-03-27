package com.idcotton.app.empresa;

import com.idcotton.app.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "EMPRESA")
@SequenceGenerator(name = "GENERETION_EMPRESA", sequenceName = "SEQ_EMPRESA", initialValue = 1, allocationSize = 1)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class Empresa extends BaseEntity {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GENERETION_EMPRESA")
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(name = "NOME", length = 75, nullable = false, columnDefinition = "VARCHAR(75)")
    private String nome;

    @Column(name = "FANTASIA", length = 75, nullable = false, columnDefinition = "VARCHAR(75)")
    private String fantasia;

    @Column(name = "CNPJ", length = 75, nullable = false, columnDefinition = "VARCHAR(75)")
    private String cnpj;


}
