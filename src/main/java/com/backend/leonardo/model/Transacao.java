package com.backend.leonardo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Table("TRANSACAO")
public class Transacao {
    @Id
    int id;
    @Column("id_cliente")
    int clienteFk;
    @Column("VALOR")
    int valor;
    @Column("TIPO")
    char tipo;
    @Column("DESCRICAO")
    String descricao;
    @Column("REALIZADA_EM")
    LocalDateTime realizadaEm;

    public Transacao() {
    }

    public int getValor() {
        return valor;
    }

    public char getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public Transacao(int clienteFk, int valor, char tipo, String descricao, LocalDateTime realizadaEm) {
        this.clienteFk = clienteFk;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }

    public Transacao(int id, int clienteFk, int valor, char tipo, String descricao, LocalDateTime realizadaEm) {
        this.id = id;
        this.clienteFk = clienteFk;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }

}