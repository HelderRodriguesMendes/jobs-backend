package com.testePratico.simplesDental.repository;

import com.testePratico.simplesDental.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    public Optional<List<Profissional>> findByNomeLike(String nome);

    public Optional<List<Profissional>> findByCargoLike(String cargo);

    @Query(value = "select * from profissional p where p.create_data like %?1%", nativeQuery = true)
    public Optional<List<Profissional>> buscarProfissionais_create_data(String texto);

    @Query(value = "Select * from profissional p inner join contato c on c.profissional_fk = p.id_p where day(p.nascimento) = ?1 or month(p.nascimento) = ?1 or year(p.nascimento) = ?1", nativeQuery = true)
    public Optional<List<Profissional>> buscarProfissionais_nascimento(String texto);
}