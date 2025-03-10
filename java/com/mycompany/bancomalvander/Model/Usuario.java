package com.mycompany.bancomalvander.Model;

import java.sql.Date;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String senha;
    private String tipoUsuario;
    private Date dataNascimento;
    private String cpf;
    private String telefone; // Adicionado o atributo telefone
    private int fkEnderecoId;

    // Construtor
    public Usuario(int idUsuario, String nome, String senha, String tipoUsuario, Date dataNascimento, String cpf, int fkEnderecoId) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.fkEnderecoId = fkEnderecoId;
    }

    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getFkEnderecoId() {
        return fkEnderecoId;
    }

    public void setFkEnderecoId(int fkEnderecoId) {
        this.fkEnderecoId = fkEnderecoId;
    }
}
