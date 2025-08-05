// package com.danisfon.backend.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.danisfon.backend.model.Perfil;
// import com.danisfon.backend.service.CategoriaService;
// import com.danisfon.backend.service.PerfilService;

// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/categoria")
// public class CategoriaController {
//     @Autowired
//     private CategoriaService categoriaService;

//     @GetMapping
//     public ResponseEntity<Page<Perfil>> buscarTodos(Pageable pageable) {
//         return ResponseEntity.ok(categoriaService.buscarTodos(pageable));
//     }

//     @PostMapping
//     public ResponseEntity<Perfil> inserir(@Valid @RequestBody Perfil pessoa) {
//         return ResponseEntity.ok(categoriaService.inserir(pessoa));
//     }

//     @PutMapping
//     public ResponseEntity<Perfil> alterar(@Valid @RequestBody Perfil pessoa) {
//         return ResponseEntity.ok(categoriaService.alterar(pessoa));
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
//         categoriaService.excluir(id);
//         return ResponseEntity.ok("Excluindo");
//     }
// }
