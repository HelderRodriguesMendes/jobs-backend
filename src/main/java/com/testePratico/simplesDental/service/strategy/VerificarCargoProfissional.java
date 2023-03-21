package com.testePratico.simplesDental.service.strategy;

import com.testePratico.simplesDental.exception.NotFoundException;
import com.testePratico.simplesDental.model.Profissional;
import com.testePratico.simplesDental.repository.ListagemProfissionais;
import com.testePratico.simplesDental.repository.ProfissionalRepository;

import java.util.List;
import java.util.Optional;

public class VerificarCargoProfissional implements ListagemProfissionais {
    @Override
    public List<Profissional> buscar(String texto, ProfissionalRepository profissionalRepository) {
        Optional<List<Profissional>> optional = profissionalRepository.findByCargoLike("%" + texto + "%");
        if(optional.isEmpty()){
            throw new NotFoundException("Cargo " + texto + " não encontrado");
        }
        return optional.get();
    }
}