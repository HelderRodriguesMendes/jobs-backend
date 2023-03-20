package com.testePratico.simplesDental.service;

import com.testePratico.simplesDental.model.Contato;
import com.testePratico.simplesDental.repository.ContatoRepository;
import com.testePratico.simplesDental.repository.ListagemContatos;

import java.util.ArrayList;
import java.util.List;

public class VerificarNomeContato implements ListagemContatos {
    @Override
    public List<Contato> buscar(String texto, ContatoRepository contatoRepository) {
        List<Contato> contatos = new ArrayList<>();
        contatos = contatoRepository.buscarContatos_nome(texto);
        return contatos;
    }
}