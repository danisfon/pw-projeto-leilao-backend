package com.danisfon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.danisfon.backend.model.Perfil;
import com.danisfon.backend.service.PerfilService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ResponseEntity<Page<Perfil>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(perfilService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<Perfil> inserir(@Valid @RequestBody Perfil pessoa) {
        return ResponseEntity.ok(perfilService.inserir(pessoa));
    }

    @PutMapping
    public ResponseEntity<Perfil> alterar(@Valid @RequestBody Perfil pessoa) {
        return ResponseEntity.ok(perfilService.alterar(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        perfilService.excluir(id);
        return ResponseEntity.ok("Perfil excluído com sucesso!");
    }

}
