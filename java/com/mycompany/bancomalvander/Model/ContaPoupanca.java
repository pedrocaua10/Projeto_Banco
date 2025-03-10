package com.mycompany.bancomalvander.Model;

public class ContaPoupanca {
    private int idContaPoupanca;
    private double taxaRendimento;
    private int fkContaId;

    // Construtor
    public ContaPoupanca(double taxaRendimento, int fkContaId) {
        this.taxaRendimento = taxaRendimento;
        this.fkContaId = fkContaId;
    }

    // Getters e Setters
    public int getIdContaPoupanca() {
        return idContaPoupanca;
    }

    public void setIdContaPoupanca(int idContaPoupanca) {
        this.idContaPoupanca = idContaPoupanca;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public int getFkContaId() {
        return fkContaId;
    }

    public void setFkContaId(int fkContaId) {
        this.fkContaId = fkContaId;
    }
}
