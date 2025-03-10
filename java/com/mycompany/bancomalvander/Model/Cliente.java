package com.mycompany.bancomalvander.Model;

import java.sql.Date;

public class Cliente {
    private int idUsuario;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String telefone;
    private String senha;
    private String tipoUsuario; // Adicione este campo
    private Endereco endereco;

    // Construtor
    public Cliente(int idUsuario, String nome, String cpf, Date dataNascimento, String telefone, String senha, String tipoUsuario, Endereco endereco) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario; // Inicialize este campo
        this.endereco = endereco;
    }

    // Getters e Setters para todos os campos
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getEnderecoId() {
        return endereco != null ? endereco.getId() : -1;
    }
}
