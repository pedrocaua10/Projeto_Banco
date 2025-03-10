package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.ConnectionFactory;
import com.mycompany.bancomalvander.Model.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    public void salvarRelatorio(Relatorio relatorio) throws SQLException {
        String sql = "INSERT INTO relatorio (tipo_relatorio, data_geracao, conteudo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, relatorio.getTipoRelatorio());
            stmt.setTimestamp(2, new java.sql.Timestamp(relatorio.getDataGeracao().getTime()));
            stmt.setString(3, relatorio.getConteudo());

            stmt.executeUpdate();
        }
    }

    public List<Relatorio> getTodosRelatorios() throws SQLException {
        String sql = "SELECT * FROM relatorio";
        List<Relatorio> relatorios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getString("tipo_relatorio"),
                        rs.getTimestamp("data_geracao"),
                        rs.getString("conteudo")
                );
                relatorio.setIdRelatorio(rs.getInt("id_relatorio"));
                relatorios.add(relatorio);
            }
        }
        return relatorios;
    }
}
