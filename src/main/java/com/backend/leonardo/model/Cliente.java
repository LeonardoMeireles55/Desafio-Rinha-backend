package com.backend.leonardo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("cliente")
public class Cliente {
    @Id
    @Column("ID")
    private int id;
    @Column("SALDOS")
    private int valor;
    @Column("LIMITES")
    private int limite;

    public void setValor(int valor) {
        this.valor = valor;
    }
    public int getValor() {
        return valor;
    }

    public int getLimite() {
        return limite;
    }

    public Cliente(int id, int valor, int limite) {
        this.id = id;
        this.valor = valor;
        this.limite = limite;
    }
}
