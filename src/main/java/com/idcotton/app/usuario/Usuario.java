package com.idcotton.app.usuario;

import com.idcotton.app.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO", uniqueConstraints = @UniqueConstraint(columnNames = "LOGIN", name = "idx_usuario_001"))
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "G_USUARIO", sequenceName = "SEQ_USUARIO", initialValue = 1, allocationSize = 1)
public @Data class Usuario extends BaseEntity {

	private static final long serialVersionUID = -4410497076064420978L;

	@Id
	@Column(name = "CODIGO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "G_USUARIO")
	@EqualsAndHashCode.Include
	private Integer codigo;

	@Column(name = "NOME", length = 15, nullable = false, columnDefinition = "VARCHAR(15)")
	private String nome;

	@Column(name = "SOBRENOME", length = 60, nullable = false, columnDefinition = "VARCHAR(70)")
	private String sobreNome;

	@Column(name = "EMAIL", length = 70, nullable = false, columnDefinition = "VARCHAR(70)")
	private String email;

	@Embedded
	private Credenciais credenciais;

	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL_USUARIO", length = 20, nullable = false, columnDefinition = "VARCHAR(20)")
	private PerfilUsuario perfilUsuario;

	@Type(type = "true_false")
	@Column(name = "BLOQUEADO", length = 1, nullable = false, columnDefinition = "CHAR(1)")
	private Boolean bloqueado;

	@Column(name = "OBSERVACAO", length = 150, nullable = true, columnDefinition = "VARCHAR(150)")
	private String observacao;

	public String getNomeCompleto() {
		return this.nome + " " + this.sobreNome;
	}

}
