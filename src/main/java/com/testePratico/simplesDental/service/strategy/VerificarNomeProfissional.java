package com.testePratico.simplesDental.service.strategy;

import com.testePratico.simplesDental.model.Profissional;
import com.testePratico.simplesDental.repository.ListagemProfissionais;
import com.testePratico.simplesDental.repository.ProfissionalRepository;

import java.util.ArrayList;
import java.util.List;

public class VerificarNomeProfissional implements ListagemProfissionais {
    @Override
    public List<Profissional> buscar(String texto, ProfissionalRepository profissionalRepository) {
        List<Profissional> profissionais = new ArrayList<>();
        profissionais = profissionalRepository.findByNomeLike("%" + texto + "%").get();
        return profissionais;
    }
}