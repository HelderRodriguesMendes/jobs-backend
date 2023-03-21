package com.testePratico.simplesDental.repository;

import com.testePratico.simplesDental.model.Profissional;

import java.util.List;

public interface ListagemProfissionais {
    public List<Profissional>buscar(String texto, ProfissionalRepository profissionalRepository);
}