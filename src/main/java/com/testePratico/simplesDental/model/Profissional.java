package com.testePratico.simplesDental.model;

import com.testePratico.simplesDental.enums.Cargo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profissional implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Cargo cargo;
    private LocalDate nascimento;
    private LocalDate create_data;

    @OneToMany(mappedBy = "profissional", fetch = FetchType.LAZY)
    private List<Contato> contatos;

}