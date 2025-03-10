package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefoneDAO {

    public void inserirTelefone(int usuarioId, String telefone) throws SQLException {
        String sql = "INSERT INTO telefone (numero, fk_usuario_id_usuario) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, telefone);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        }
    }

    public void atualizarTelefone(int usuarioId, String novoTelefone) throws SQLException {
        String sql = "UPDATE telefone SET numero = ? WHERE fk_usuario_id_usuario = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, novoTelefone);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        }
    }

    public void removerTelefones(int usuarioId) throws SQLException {
        String sql = "DELETE FROM telefone WHERE fk_usuario_id_usuario = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, usuarioId);
            stmt.executeUpdate();
        }
    }
}
