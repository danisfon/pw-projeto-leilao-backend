package com.danisfon.backend.controller;

import com.danisfon.backend.model.Leilao;
import com.danisfon.backend.service.LeilaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leiloes")
public class LeilaoController {

    @Autowired
    private LeilaoService leilaoService;

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LEILOEIRO')")
    public ResponseEntity<Leilao> criar(@RequestBody @Valid Leilao leilao) {
        return ResponseEntity.ok(leilaoService.criar(leilao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leilao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(leilaoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Leilao>> listarTodos() {
        return ResponseEntity.ok(leilaoService.listarTodos());
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LEILOEIRO')")
    public ResponseEntity<Leilao> atualizar(@PathVariable Long id, @RequestBody @Valid Leilao leilao) {
        return ResponseEntity.ok(leilaoService.atualizar(id, leilao));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        leilaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}