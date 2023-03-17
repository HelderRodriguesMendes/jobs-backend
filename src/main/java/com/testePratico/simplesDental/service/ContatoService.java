package com.testePratico.simplesDental.service;

import com.testePratico.simplesDental.model.Contato;
import com.testePratico.simplesDental.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvar(Contato contato){
        Contato contatoSalvo = contatoRepository.save(contato);
        return contatoSalvo;
    }
}