package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.ConnectionFactory;
import com.mycompany.bancomalvander.Model.ContaPoupanca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaPoupancaDao {
    public void save(ContaPoupanca contaPoupanca) throws SQLException {
        String sql = "INSERT INTO conta_poupanca (taxa_rendimento, fk_conta_id_conta) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, contaPoupanca.getTaxaRendimento());
            stmt.setInt(2, contaPoupanca.getFkContaId());
            stmt.executeUpdate();
        }
    }
}
