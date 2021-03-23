package com.idcotton.app.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Page<Empresa> findByNomeContainingIgnoreCase(String nome, Pageable pageable);


}
