-- CRIA TABELA DE CONFIGURAÇÕES DE CONEXÕES
CREATE TABLE if not exists public.DATASOURCE_CONFIG (
    CODIGO BIGINT PRIMARY KEY,
    DRIVER_CLASS_NAME VARCHAR(255),
    URL VARCHAR(255),
    NOME VARCHAR(50),
    USERNAME VARCHAR(30),
    PASSWORD VARCHAR(70),
    DATA_ATUALIZADO timestamp without time zone NOT NULL,
    DATA_CADASTRADO timestamp without time zone NOT NULL
);

-- CRIA INDEX PARA O NOME DO DATASOURCE
CREATE UNIQUE INDEX IDX_NOME_DATASOURCE ON DATASOURCE_CONFIG(NOME);

-- INSERE DADOS DO DATASOURCE PARA CONEXÃO
INSERT INTO DATASOURCE_CONFIG VALUES (1, 'org.postgresql.Driver', 'jdbc:postgresql://localhost:5432/multitenant?currentSchema=public&ApplicationName=multitenant'    , 'public'   , 'user_multitenant', 'user_multitenant', current_timestamp, current_timestamp);
INSERT INTO DATASOURCE_CONFIG VALUES (2, 'org.postgresql.Driver', 'jdbc:postgresql://localhost:5432/multitenant?currentSchema=empresa001&ApplicationName=multitenant', 'empresa01', 'user_multitenant', 'user_multitenant', current_timestamp, current_timestamp);
INSERT INTO DATASOURCE_CONFIG VALUES (3, 'org.postgresql.Driver', 'jdbc:postgresql://localhost:5432/multitenant?currentSchema=empresa002&ApplicationName=multitenant', 'empresa02', 'user_multitenant', 'user_multitenant', current_timestamp, current_timestamp);

commit;
