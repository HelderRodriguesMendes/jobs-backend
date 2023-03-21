package com.testePratico.simplesDental.service.strategy;

import com.testePratico.simplesDental.model.Contato;
import com.testePratico.simplesDental.repository.ContatoRepository;
import com.testePratico.simplesDental.repository.ListagemContatos;

import java.util.ArrayList;
import java.util.List;

public class VerificarContato implements ListagemContatos {
    @Override
    public List<Contato> buscar(String texto, ContatoRepository contatoRepository) {
        List<Contato> contatos = new ArrayList<>();
        contatos = contatoRepository.buscarContatos_contato(texto);
        return contatos;
    }
}