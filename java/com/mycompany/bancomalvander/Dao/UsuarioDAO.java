/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.ConnectionFactory;
import com.mycompany.bancomalvander.Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marcos
 */
public class UsuarioDAO {
    public int inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, senha, tipo_usuario, data_nascimento, cpf, fk_endereco_id_endereco) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTipoUsuario());
            stmt.setDate(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getCpf());
            stmt.setInt(6, usuario.getFkEnderecoId());

            stmt.executeUpdate();

            // Recupera o ID gerado
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Falha ao inserir usuário, ID não gerado.");
            }
        }
    }
    
    public Usuario getUsuarioBySenha(String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE senha = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("senha"),
                            rs.getString("tipo_usuario"),
                            rs.getDate("data_nascimento"),
                            rs.getString("cpf"),
                            rs.getInt("fk_endereco_id_endereco")
                    );
                }
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }
    
     public Usuario validarSenha(String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE senha = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("tipo_usuario"),
                        rs.getDate("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getInt("fk_endereco_id_endereco")
                    );
                }
            }
        }
        return null;
    }
     
     public Usuario getUsuarioByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("tipo_usuario"),
                        rs.getDate("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getInt("fk_endereco_id_endereco")
                    );
                }
            }
        }
        return null;
    }
}
