package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.Transacao;
import com.mycompany.bancomalvander.Model.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    // Método para inserir uma transação de depósito (CREDITO)
    public void inserirTransacao(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacao (tipo_transacao, valor, data_hora, fk_conta_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getTipoTransacao());  // Tipo da transação: 'CREDITO' ou 'DEBITO'
            stmt.setDouble(2, transacao.getValor());          // Valor da transação
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));  // Data e hora da transação
            stmt.setInt(4, transacao.getFkContaId());         // ID da conta associada

            stmt.executeUpdate();  // Executa a inserção da transação
        }
    }

    public List<Transacao> getTransacoesByConta(int idConta) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE fk_conta_id = ?";
        List<Transacao> transacoes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idConta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao transacao = new Transacao(
                            rs.getString("tipo_transacao"),
                            rs.getDouble("valor"),
                            rs.getInt("fk_conta_id")
                    );
                    transacao.setIdTransacao(rs.getInt("id_transacao"));
                    transacao.setDataHora(rs.getTimestamp("data_hora"));
                    transacoes.add(transacao);
                }
            }
        }
        return transacoes;
    }
}
