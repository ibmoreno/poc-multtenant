package com.idcotton.app.config.multitenant;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
@RequiredArgsConstructor
public class MultiTenantDataSource implements Serializable {

    private static final long serialVersionUID = -3036429191123644272L;

    private final MultiTenantDataSourceConfigRepository repository;

    public Map<String, DataSource> getTodosDataSource() {
        Map<String, DataSource> result = new HashMap<>();
        List<MultiTenantDataSourceConfig> configList = repository.findAll();
        for (MultiTenantDataSourceConfig config : configList) {
            DataSource dataSource = criaDataSource(config);
            result.put(config.getNome(), dataSource);
        }

        return result;

    }

    private DataSource criaDataSource(MultiTenantDataSourceConfig config) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDataSourceJNDI(config.getNome());
        dataSource.setJdbcUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setDriverClassName(config.getDriverClassName());
        dataSource.setConnectionTimeout(20000);
        dataSource.setIdleTimeout(300000);
        dataSource.setMinimumIdle(5);
        dataSource.setMaximumPoolSize(20);
        dataSource.setMaxLifetime(1200000);
        dataSource.setAutoCommit(true);
        dataSource.setConnectionTestQuery("SELECT 1");

        return dataSource;

    }

}
