package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.ConnectionFactory;
import com.mycompany.bancomalvander.Model.ContaCorrente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaCorrenteDao {
    public void save(ContaCorrente contaCorrente) throws SQLException {
        String sql = "INSERT INTO conta_corrente (limite, data_vencimento, fk_conta_id_conta) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, contaCorrente.getLimite());
            stmt.setDate(2, contaCorrente.getDataVencimento());
            stmt.setInt(3, contaCorrente.getFkContaId());
            stmt.executeUpdate();
        }
    }
    // Método para atualizar os dados da conta corrente
    public boolean atualizarContaCorrente(ContaCorrente contaCorrente) throws SQLException {
        String sql = "UPDATE conta_corrente SET limite = ?, data_vencimento = ? WHERE fk_conta_id_conta = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura os parâmetros da query
            stmt.setDouble(1, contaCorrente.getLimite());
            stmt.setDate(2, contaCorrente.getDataVencimento());
            stmt.setInt(3, contaCorrente.getFkContaId());

            // Executa o UPDATE e verifica se houve alterações
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se ao menos uma linha foi alterada
        }
    }
}

