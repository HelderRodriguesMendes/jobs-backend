package com.testePratico.simplesDental.model;

import com.testePratico.simplesDental.enums.Cargo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

    @Column
    private LocalDate create_data;

    @OneToMany(mappedBy = "profissional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contato> contatos;
}