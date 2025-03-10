package com.mycompany.bancomalvander.Model;

import java.util.Date;

public class Relatorio {
    private int idRelatorio;
    private String tipoRelatorio;
    private Date dataGeracao;
    private String conteudo;

    public Relatorio(String tipoRelatorio, Date dataGeracao, String conteudo) {
        this.tipoRelatorio = tipoRelatorio;
        this.dataGeracao = dataGeracao;
        this.conteudo = conteudo;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
