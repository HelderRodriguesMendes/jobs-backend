package com.testePratico.simplesDental.controller;

import com.testePratico.simplesDental.model.Contato;
import com.testePratico.simplesDental.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> salvar(@Valid @RequestBody Contato contato){
        Contato contatoSalvo = contatoService.salvarContato(contato);
        return new ResponseEntity<>(contato, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contato>>buscarContatos(@RequestParam String texto){
        List<Contato> contatos = contatoService.buscarContatos(texto);
        return new ResponseEntity<>(contatos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarContatoID(@PathVariable Long id){
        Contato contato = contatoService.buscarContatoID(id);
        return new ResponseEntity<>(contato, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> editarContato(@PathVariable Long id, @Valid @RequestBody Contato contato){
        contato.setId(id);
        Contato contatoAlterado = contatoService.editarContato(contato);
        return new ResponseEntity<>(contatoAlterado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirContato(@PathVariable Long id){
        contatoService.excluirContato(id);
        return ResponseEntity.noContent().build();
    }
}