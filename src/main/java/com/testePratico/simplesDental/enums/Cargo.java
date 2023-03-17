package com.testePratico.simplesDental.enums;

public enum Cargo {
    DESENVOLVEDOR ("Desenvolvedor"),
    DESIGNER ("Designer"),
    SUPORTE ("Suporte"),
    TESTER ("Tester");

    private String descricao;

    Cargo(String descricao) {
        this.descricao = descricao;
    }
}