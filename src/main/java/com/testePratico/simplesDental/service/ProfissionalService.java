package com.testePratico.simplesDental.service;

import com.testePratico.simplesDental.exception.NotFoundException;
import com.testePratico.simplesDental.model.Profissional;
import com.testePratico.simplesDental.repository.ListagemProfissionais;
import com.testePratico.simplesDental.repository.ProfissionalRepository;
import com.testePratico.simplesDental.service.strategy.VerificarCargoProfissional;
import com.testePratico.simplesDental.service.strategy.VerificarNascimentoProfissional;
import com.testePratico.simplesDental.service.strategy.VerificarNomeProfissional;
import com.testePratico.simplesDental.service.strategy.Verificarcreate_dataProfissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional salvarProfissional(Profissional profissional){
        profissional.setCreate_data(LocalDate.now());
        Profissional profissionalSalvo = profissionalRepository.save(profissional);
        profissionalSalvo.getContatos().stream().forEach(contato -> {
            contato.setProfissional(profissionalSalvo);
        });
        return profissionalRepository.save(profissionalSalvo);
    }

    public Profissional buscarProfissionalID(Long id){
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);
        if (profissionalOptional.isEmpty()){
            throw new NotFoundException("Profissional " + id + " não encontrado");
        }
        return profissionalOptional.get();
    }

    public List<Profissional> buscarProfissionais(String texto){
        List<Profissional> profissionais =  verificarBuscaProfissionais(texto);
        return profissionais;
    }

    public Profissional alterarProfissional(Profissional profissional){
        buscarProfissionalID(profissional.getId());
        Profissional profissionalAlterado = profissionalRepository.save(profissional);
        return profissionalAlterado;
    }

    public void excluirProfissional(Long id){
        buscarProfissionalID(id);
        profissionalRepository.deleteById(id);
    }

    //--------------------------------PADRAO DE PROJETO STRATEGY-----------------------------------------
    private List<Profissional> verificarBuscaProfissionais(String texto){

        List<Profissional> prof = null;
        List<Profissional> profissionais = new ArrayList<>();
        ListagemProfissionais buscarPorNome = new VerificarNomeProfissional();
        ListagemProfissionais buscarPorCargo = new VerificarCargoProfissional();
        ListagemProfissionais buscarPorNascimento = new VerificarNascimentoProfissional();
        ListagemProfissionais buscarPorCreateData = new Verificarcreate_dataProfissional();

        prof = realizarBusca(texto, buscarPorNome);
        if(!prof.isEmpty()){
            prof.stream().forEach(profissional -> {
                profissionais.add(profissional);
            });
        }

        prof = realizarBusca(texto, buscarPorCargo);
        if(!prof.isEmpty()){
            prof.stream().forEach(profissional -> {
                profissionais.add(profissional);
            });
        }

        prof = realizarBusca(texto, buscarPorNascimento);
        if(!prof.isEmpty()){
            prof.stream().forEach(profissional -> {
                profissionais.add(profissional);
            });
        }

        prof = realizarBusca(texto, buscarPorCreateData);
        if(!prof.isEmpty()){
            prof.stream().forEach(profissional -> {
                profissionais.add(profissional);
            });
        }

        if(profissionais.isEmpty()){
            throw new NotFoundException("Profissional " + texto + " não encontrado");
        }

        return profissionais;
    }

    private List<Profissional> realizarBusca(String texto, ListagemProfissionais buscaQualquer){
        List<Profissional> profissionais = buscaQualquer.buscar(texto, profissionalRepository);
        return profissionais;
    }
}