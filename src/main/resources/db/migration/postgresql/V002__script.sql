--
-- SCRIPT CADASTRO DE UÁRIO
-- DATA: 23/03/2021
-- IVAN B. MORENO
--

-- CRIA TABELA USUARIO
CREATE TABLE USUARIO (
                         CODIGO numeric NOT NULL,
                         NOME varchar(15) NOT NULL,
                         SOBRENOME varchar(60) NOT NULL,
                         EMAIL varchar(80) NOT NULL,
                         LOGIN varchar(80) NOT NULL,
                         SENHA varchar(70) NOT NULL,
                         PERFIL_USUARIO varchar(20) NOT NULL,
                         BLOQUEADO char(1) NOT NULL,
                         OBSERVACAO varchar(150) NULL,
                         DATA_ATUALIZADO timestamp without time zone NOT NULL,
                         DATA_CADASTRADO timestamp without time zone NOT NULL
);

-- CRIA CHAVE PRIMARIA PARA USUARIO
ALTER TABLE ONLY USUARIO
    ADD CONSTRAINT PK_USUARIO PRIMARY KEY (CODIGO);

-- CRIA INDEX PARA O LOGIN DO USUÁRIO
CREATE UNIQUE INDEX IDX_USUARIO_001 ON USUARIO(LOGIN);

-- CRIA SEQUENCIA DE USUARIO
CREATE SEQUENCE SEQ_USUARIO
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


-- adiciona usuairo adminstrador
INSERT INTO usuario( codigo, nome, sobrenome, email, login, senha, perfil_usuario, bloqueado, observacao, data_atualizado, data_cadastrado)
VALUES (nextval('seq_usuario'),
        'ADMIN',
        'ADMIN',
        'admin@idcotton.com.br',
        'admin',
        '$2a$10$vAoHZw.sGJx3vL3G4jfBFOqfJ/0cavPBQlVw7lZy259WxRQqxcEGy',
        'ROLE_ADMINISTRADOR',
        'F',
        NULL,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

commit;