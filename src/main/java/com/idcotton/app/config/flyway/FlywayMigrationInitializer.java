package com.idcotton.app.config.flyway;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//@Configuration
@RequiredArgsConstructor
public class FlywayMigrationInitializer {

// TODO: CONFIGURAR DEPOIS

//    private final DataSource dataSource;
//    private final FlywayConfigProperties flywayConfigProperties;
//
//    @PostConstruct
//    public void migrate() {
//        flywayConfigProperties.getSchemas().forEach(schema -> {
//            Flyway flyway = Flyway.configure()
//                    .schemas(schema)
//                    .baselineOnMigrate(Boolean.FALSE)
//                    .dataSource(dataSource).load();
//            flyway.migrate();
//        });
//    }

}
