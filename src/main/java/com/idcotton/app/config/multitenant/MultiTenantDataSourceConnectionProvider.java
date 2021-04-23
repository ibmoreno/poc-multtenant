package com.idcotton.app.config.multitenant;

import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Component
@RequiredArgsConstructor
public class MultiTenantDataSourceConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final long serialVersionUID = 8098234728145424794L;

    private final MultiTenantDataSource tenantDataSource;
    private final DataSource masterDataSource;

    private Map<String, DataSource> mapDataSources;

    @Override
    protected DataSource selectAnyDataSource() {
        return this.masterDataSource;
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {

        if (this.mapDataSources == null) {
            this.mapDataSources = new HashMap<>();
            this.mapDataSources.putAll(tenantDataSource.getTodosDataSource());
        }

        return this.mapDataSources.get(tenantIdentifier) != null ? this.mapDataSources.get(tenantIdentifier) : this.masterDataSource;

    }
}
