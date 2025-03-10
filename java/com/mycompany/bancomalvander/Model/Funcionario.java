/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancomalvander.Model;

/**
 *
 * @author Marcos
 */
public class Funcionario {
    private int idFuncionario;
    private String codigoFuncionario;
    private String cargo;
    private int fkUsuarioId;
    private int fkRelatorioId;

    // Construtor
    public Funcionario(int idFuncionario, String codigoFuncionario, String cargo, int idUsuario, int par1) {
        this.idFuncionario = idFuncionario;
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
    }

    // Getters e Setters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public int getFkUsuarioId() {
        return fkUsuarioId;
    }

    public void setFkUsuarioId(int fkUsuarioId) {
        this.fkUsuarioId = fkUsuarioId;
    }

    public int getFkRelatorioId() {
        return fkRelatorioId;
    }

    public void setFkRelatorioId(int fkRelatorioId) {
        this.fkRelatorioId = fkRelatorioId;
    }
}
