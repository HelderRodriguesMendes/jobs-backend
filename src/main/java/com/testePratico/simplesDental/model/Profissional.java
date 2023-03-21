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

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profissional implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_p")
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, name = "nomeProfissional")
    private String nome;

    @Getter
    @Column(nullable = false)
    private String cargo;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

    @Getter
    @Setter
    @Column
    private LocalDate create_data;

    @Getter
    @Setter
    @OneToMany(mappedBy = "profissional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contato> contatos;

    public void setCargo(Cargo cargo) {
        if(cargo != null){
            this.cargo = cargo.getDescricao();
        }
    }
}