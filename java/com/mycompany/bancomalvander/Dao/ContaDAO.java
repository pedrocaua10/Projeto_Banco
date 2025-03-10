/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.ConnectionFactory;
import com.mycompany.bancomalvander.Model.Conta;
import com.mycompany.bancomalvander.Model.ContaCorrente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marcos
 */


public class ContaDAO {

    public void save(Conta conta) throws SQLException {
        String sql = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, conta.getNumeroConta());
            stmt.setString(2, conta.getAgencia());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setString(4, conta.getTipoConta());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    conta.setIdConta(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID da conta.");
                }
            }
        }
    }
    
     public Conta getContaByNumero(String numeroConta) throws SQLException {
        String sql = "SELECT * FROM conta WHERE numero_conta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conta(
                        rs.getInt("id_conta"),
                        rs.getString("numero_conta"),
                        rs.getString("agencia"),
                        rs.getDouble("saldo"),
                        rs.getString("tipo_conta"),
                        rs.getInt("fk_usuario_id") // Certifique-se de ajustar isso conforme necessário
                    );
                }
            }
        }
        return null;
    }

    public double getSaldo(int contaId) throws SQLException {
        String sql = "SELECT saldo FROM conta WHERE id_conta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("saldo");
            }
        }
        return 0.0;
    }

   public boolean depositar(int id, double valor) {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE id_cliente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sacar(int id, double valor) {
        String sql = "UPDATE conta SET saldo = saldo - ? WHERE id_conta = ? AND saldo >= ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, id);
            stmt.setDouble(3, valor);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public void deleteByNumeroConta(String numeroConta) throws SQLException {
            String sqlDeleteTransacoes = "DELETE FROM transacao WHERE fk_conta_id = (SELECT id_conta FROM conta WHERE numero_conta = ?)";
            String sqlDeleteClientes = "DELETE FROM cliente WHERE fk_conta_id_conta = (SELECT id_conta FROM conta WHERE numero_conta = ?)";
            String sqlDeleteConta = "DELETE FROM conta WHERE numero_conta = ?";

            try (Connection conn = ConnectionFactory.getConnection()) {
                conn.setAutoCommit(false); // Inicia a transação

                try (PreparedStatement stmtDeleteTransacoes = conn.prepareStatement(sqlDeleteTransacoes);
                     PreparedStatement stmtDeleteClientes = conn.prepareStatement(sqlDeleteClientes);
                     PreparedStatement stmtDeleteConta = conn.prepareStatement(sqlDeleteConta)) {

                    // Exclui transações associadas à conta
                    stmtDeleteTransacoes.setString(1, numeroConta);
                    stmtDeleteTransacoes.executeUpdate();

                    // Exclui clientes associados à conta
                    stmtDeleteClientes.setString(1, numeroConta);
                    stmtDeleteClientes.executeUpdate();

                    // Exclui a conta
                    stmtDeleteConta.setString(1, numeroConta);
                    stmtDeleteConta.executeUpdate();

                    conn.commit(); // Confirma a transação
                } catch (SQLException ex) {
                    conn.rollback(); // Reverte mudanças em caso de erro
                    throw ex;
            }
        }
    }
    
        public Conta buscarContaPorNumeroETipo(String numeroConta, String tipoConta) throws SQLException {
        String sql = "SELECT * FROM conta WHERE numero_conta = ? AND tipo_conta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            stmt.setString(2, tipoConta.toUpperCase());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conta(
                        rs.getInt("id_conta"),
                        rs.getString("numero_conta"),
                        rs.getString("agencia"),
                        rs.getDouble("saldo"),
                        rs.getString("tipo_conta"),
                        rs.getInt("fk_usuario_id") // Adapte isso conforme a sua coluna real
                    );
                }
            }
        }
        return null; // Retorna null se não encontrar a conta
    }

    public int buscarIdContaPorNumero(String numeroConta) throws SQLException {
        String sql = "SELECT id_conta FROM conta WHERE numero_conta = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroConta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_conta");
            } else {
                return -1; // Retorna -1 se a conta não for encontrada
            }
        }
    }
    
    public void atualizarSaldo(int idConta, double novoSaldo) throws SQLException {
    String sql = "UPDATE conta SET saldo = ? WHERE id_conta = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Preenchendo os parâmetros do PreparedStatement
        stmt.setDouble(1, novoSaldo);  // Define o novo saldo diretamente
        stmt.setInt(2, idConta);

        // Executa o comando de atualização
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Saldo da conta atualizado com sucesso!");
        } else {
            System.out.println("Nenhuma conta encontrada para atualização.");
        }
    }
}
    
    public double getLimiteByContaId(int contaId) throws SQLException {
        String sql = "SELECT limite FROM conta_corrente WHERE fk_conta_id_conta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("limite");
                }
            }
        }
        return 0.0;
    }

       
}
