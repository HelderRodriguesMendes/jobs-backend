package com.testePratico.simplesDental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_c")
    private Long id;

    @NotBlank(message = "Nome")
    @Column(nullable = false, name = "nomeContato")
    private String nome;

    @NotBlank(message = "Contato")
    @Column(unique = true)
    private String contato;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "profissional_fk")
    private Profissional profissional;

    public Contato(Long id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }
}