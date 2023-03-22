package com.testePratico.simplesDental.controller;

import com.testePratico.simplesDental.model.Profissional;
import com.testePratico.simplesDental.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professionals")
public class ProfissionalController {
    
    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<Profissional> salvar(@RequestBody Profissional profissional){
        Profissional profissionalSalvo = profissionalService.salvarProfissional(profissional);
        return new ResponseEntity<>(profissional, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Profissional> alterar(@PathVariable Long id, @RequestBody Profissional profissional){
        profissional.setId(id);
        Profissional profissionalSalvo = profissionalService.alterarProfissional(profissional);
        return new ResponseEntity<>(profissional, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> buscarProfissionais(@RequestParam String texto){
        List<Profissional> profissionais = profissionalService.buscarProfissionais(texto);
        return new ResponseEntity<>(profissionais, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirContato(@PathVariable Long id){
        profissionalService.excluirProfissional(id);
        return ResponseEntity.noContent().build();
    }
}