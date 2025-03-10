package com.mycompany.bancomalvander.Model;

import java.sql.Timestamp;

public class Transacao {
    private int idTransacao;
    private String tipoTransacao;
    private double valor;
    private Timestamp dataHora;
    private int fkContaId;

    // Construtor
    public Transacao(String tipoTransacao, double valor, int fkContaId) {
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.fkContaId = fkContaId;
    }

    // Getters e Setters
    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public int getFkContaId() {
        return fkContaId;
    }

    public void setFkContaId(int fkContaId) {
        this.fkContaId = fkContaId;
    }
}
