package com.testePratico.simplesDental.repository;

import com.testePratico.simplesDental.model.Contato;

import java.util.List;

public interface ListagemContatos {
    public List<Contato>buscar(String texto, ContatoRepository contatoRepository);
}