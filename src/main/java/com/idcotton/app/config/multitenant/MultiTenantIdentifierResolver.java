package com.idcotton.app.config.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MultiTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    public static String DEFAULT_TENANT_ID = "dataSource";

    @Override
    public String resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(MultiTenantContext.getTenantId())
                .orElse(DEFAULT_TENANT_ID);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }


}
