package com.testePratico.simplesDental.repository;

import com.testePratico.simplesDental.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query(value = "select * from contato c where c.nome like %?1%", nativeQuery = true)
    public List<Contato> buscarContatos_nome(String texto);

    @Query(value = "select * from contato c where c.contato like %?1%", nativeQuery = true)
    public List<Contato> buscarContatos_contato(String texto);

    public Optional<Contato> findByContato(String contato);

}