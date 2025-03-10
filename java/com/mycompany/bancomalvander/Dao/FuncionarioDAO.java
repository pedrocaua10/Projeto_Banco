/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.ConnectionFactory;
import com.mycompany.bancomalvander.Model.Funcionario;
import com.mycompany.bancomalvander.Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marcos
 */
public class FuncionarioDAO {
    
    public Funcionario buscarPorCodigo(String codigoFuncionario) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE codigo_funcionario = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigoFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = 0;
                    return new Funcionario(
                        rs.getInt("id_funcionario"),
                        rs.getString("codigo_funcionario"),
                        rs.getString("cargo"), idUsuario, 0
                    );
                }
            }
        }
        return null; // Retorna null se não encontrar o funcionário
    }

  public boolean cadastrarFuncionario(Usuario usuario, Funcionario funcionario) {
    String sqlUsuario = "INSERT INTO usuario (nome, senha, tipo_usuario, data_nascimento, cpf, fk_endereco_id_endereco) VALUES (?, ?, ?, ?, ?, ?)";
    String sqlFuncionario = "INSERT INTO funcionario (codigo_funcionario, cargo, fk_usuario_id_usuario, fk_relatorio_id_relatorio) VALUES (?, ?, ?, ?)";

    // Abrindo a conexão com o banco e iniciando a transação
    try (Connection connection = ConnectionFactory.getConnection()) {
        connection.setAutoCommit(false); // Inicia a transação

        int usuarioId = -1;

        // Insere na tabela usuario e recupera o ID gerado
        try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmtUsuario.setString(1, usuario.getNome());
            stmtUsuario.setString(2, usuario.getSenha());
            stmtUsuario.setString(3, usuario.getTipoUsuario());
            stmtUsuario.setDate(4, usuario.getDataNascimento());
            stmtUsuario.setString(5, usuario.getCpf());
            stmtUsuario.setInt(6, usuario.getFkEnderecoId()); // chave estrangeira para endereço
            stmtUsuario.executeUpdate();

            var rs = stmtUsuario.getGeneratedKeys();
            if (rs.next()) {
                usuarioId = rs.getInt(1); // Recupera o ID gerado
            } else {
                throw new SQLException("Falha ao inserir usuário, ID não gerado.");
            }
        }

        // Insere na tabela funcionario
        try (PreparedStatement stmtFuncionario = connection.prepareStatement(sqlFuncionario)) {
            stmtFuncionario.setString(1, funcionario.getCodigoFuncionario());
            stmtFuncionario.setString(2, funcionario.getCargo());
            stmtFuncionario.setInt(3, usuarioId); // Usa o ID gerado como FK
            stmtFuncionario.setObject(4, funcionario.getFkRelatorioId()); // Pode ser null dependendo da lógica
            stmtFuncionario.executeUpdate();
        }

        connection.commit(); // Confirma a transação

        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



}

