package com.testePratico.simplesDental.service;

import com.testePratico.simplesDental.exception.NotFoundException;
import com.testePratico.simplesDental.model.Profissional;
import com.testePratico.simplesDental.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional salvarProfissional(Profissional profissional){
        profissional.setCreate_data(LocalDate.now());
        Profissional profissionalSalvo = profissionalRepository.save(profissional);
        return profissionalSalvo;
    }



    public Profissional buscarProfissionalID(Long id){
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);
        if (profissionalOptional.isEmpty()){
            throw new NotFoundException("Profissional " + id + " não encontrado");
        }
        return profissionalOptional.get();
    }
}