package com.testePratico.simplesDental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Entity
public class Contato implements Serializable {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Nome")
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    @NotBlank(message = "Contato")
    @Column(unique = true)
    private String contato;

    @JsonIgnore
    @ManyToOne
    private Profissional profissional;

    public Contato(Long id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }
}