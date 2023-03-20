package com.testePratico.simplesDental.service;

import com.testePratico.simplesDental.exception.NotFoundException;
import com.testePratico.simplesDental.exception.RegraNegocioException;
import com.testePratico.simplesDental.model.Contato;
import com.testePratico.simplesDental.repository.ContatoRepository;
import com.testePratico.simplesDental.repository.ListagemContatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvarContato(Contato contato){
        verificarExistenciaContato(contato.getContato());
        Contato contatoSalvo = contatoRepository.save(contato);
        return contatoSalvo;
    }

    public Contato buscarContatoID(Long id){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (contatoOptional.isEmpty()){
            throw new NotFoundException("Contato " + id + " não encontrado");
        }
        return contatoOptional.get();
    }

    public List<Contato> buscarContatos(String texto){
        List<Contato> contatos = verificarBuscaContatos(texto);
        return contatos;
    }
//----------------------------------------------------------------------------------------------------
    private List<Contato> verificarBuscaContatos(String texto){
        List<Contato> contatos = new ArrayList<>();
        ListagemContatos buscaPorNome = new VerificarNomeContato();
        ListagemContatos buscaPorContato = new VerificarContato();
        contatos = realizarBusca(texto, buscaPorNome);
        if(contatos.isEmpty()){
            contatos = realizarBusca(texto, buscaPorContato);
            if(contatos.isEmpty()){
                throw new NotFoundException("Contato " + texto + " não encontrado");
            }
        }
        return contatos;
    }

    private List<Contato> realizarBusca(String texto, ListagemContatos buscaQualquer){
        List<Contato> contatos = buscaQualquer.buscar(texto, contatoRepository);
        return contatos;
    }

    private void verificarExistenciaContato(String contato){
        Optional<Contato> contatoOptional = contatoRepository.findByContato(contato);
        if (contatoOptional.isPresent()){
            throw new RegraNegocioException("Contato " + contato + " já está cadastrado");
        }
    }
}