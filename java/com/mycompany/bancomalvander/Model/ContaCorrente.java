package com.mycompany.bancomalvander.Model;

import java.sql.Date;

public class ContaCorrente {
    private int idContaCorrente;
    private double limite;
    private Date dataVencimento;
    private int fkContaId;

    // Construtor
    public ContaCorrente(int idContaCorrente, double limite, Date dataVencimento, int fkContaId) {
        this.idContaCorrente = idContaCorrente;
        this.limite = limite;
        this.dataVencimento = dataVencimento;
        this.fkContaId = fkContaId;
    }

    // Getters e Setters
    public int getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(int idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getFkContaId() {
        return fkContaId;
    }

    public void setFkContaId(int fkContaId) {
        this.fkContaId = fkContaId;
    }
}
