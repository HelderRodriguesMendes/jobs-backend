package com.testePratico.simplesDental.enums;


import com.testePratico.simplesDental.exception.RegraNegocioException;
import lombok.Getter;

public enum Cargo {
    Desenvolvedor ("Desenvolvedor"),
    Designer ("Designer"),
    Suporte ("Suporte"),
    Tester ("Tester");

    @Getter
    private String descricao;

    private Cargo(String desc) {
        this.descricao = desc;
    }

    public static Cargo valor(String desc){
        for(Cargo cargo : Cargo.values()){
            if(desc.equals(cargo.getDescricao())){
                return cargo;
            }
        }
        throw new RegraNegocioException("Cargo invalido");
    }
}