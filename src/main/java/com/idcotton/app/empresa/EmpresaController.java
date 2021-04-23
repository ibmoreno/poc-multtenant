package com.idcotton.app.empresa;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/empresa", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaRepository empresaRepository;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<List<Empresa>> buscarTodos() {
        return ResponseEntity.ok(empresaRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Empresa>> buscarTodosPaginado(Pageable pageable) {
        return ResponseEntity.ok(empresaRepository.findAll(pageable));
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<Page<Empresa>> buscarPorDescricaoPaginado(
            @RequestParam(value = "nome", required = true) String nome, Pageable pageable) {
        return ResponseEntity.ok(empresaRepository.findByNomeContainingIgnoreCase(nome, pageable));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Empresa> buscarPorCodigo(@PathVariable Integer codigo) {
        return empresaRepository.findById(codigo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> salvar(@Valid @RequestBody Empresa empresa) {
        Empresa empresaCriada = empresaRepository.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Integer codigo,
                                             @RequestBody @Valid Empresa empresa) {

        if (!empresa.getCodigo().equals(codigo))
            return ResponseEntity.badRequest().build();

        Empresa empresaAtualizada = empresaRepository.save(empresa);
        return ResponseEntity.ok(empresa);

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codigo) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(codigo);
        optionalEmpresa.orElseThrow(IllegalArgumentException::new);
        empresaRepository.delete(optionalEmpresa.get());
        return ResponseEntity.noContent().build();
    }


}
