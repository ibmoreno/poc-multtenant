package com.idcotton.app.config.multitenant;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface MultiTenantDataSourceConfigRepository extends JpaRepository<MultiTenantDataSourceConfig, Long> {

    MultiTenantDataSourceConfig findByNome(String nome);

}
