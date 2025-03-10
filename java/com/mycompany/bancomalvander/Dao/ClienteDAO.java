package com.mycompany.bancomalvander.Dao;

import com.mycompany.bancomalvander.Model.Cliente;
import com.mycompany.bancomalvander.Model.ConnectionFactory;
import com.mycompany.bancomalvander.Model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ClienteDAO {

    public void save(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'CLIENTE', ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, cliente.getDataNascimento()); // Certifique-se que data_nascimento é do tipo java.sql.Date
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSenha());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                addCliente(userId);
            }
        }
    }

    private void addCliente(int userId) throws SQLException {
        String sql = "INSERT INTO cliente (id_usuario) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    public Cliente findByCPF(String cpf) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE cpf = ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, cpf);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int idEndereco = rs.getInt("fk_endereco_id_endereco");
                Endereco endereco = findEnderecoById(idEndereco); // Buscando o endereço corretamente.
                return new Cliente(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("telefone"),
                    rs.getString("senha"),
                    rs.getString("tipo_usuario"),
                    endereco // Associando o endereço ao cliente.
                );
            }
        }
    }
    return null;
}

    public Endereco findEnderecoById(int idEndereco) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEndereco);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("cep"),
                        rs.getString("local"),
                        rs.getInt("numero_casa"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                    );
                }
            }
        }
        return null;
    }

    // Método para depósito
    public boolean depositar(int contaId, double valor) throws SQLException {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE id_conta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, contaId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Método para saque
    public boolean sacar(int contaId, double valor) throws SQLException {
        String sql = "UPDATE conta SET saldo = saldo - ? WHERE id_conta = ? AND saldo >= ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, contaId);
            stmt.setDouble(3, valor);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public void updateCliente(Cliente cliente, Endereco endereco) throws SQLException {
        // Atualizar endereço
        String sqlEndereco = "UPDATE endereco SET cep = ?, local = ?, numero_casa = ?, bairro = ?, cidade = ?, estado = ? WHERE id_endereco = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlEndereco)) {
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLocal());
            stmt.setInt(3, endereco.getNumeroCasa());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setInt(7, endereco.getIdEndereco()); 

            stmt.executeUpdate();
        }

        // Atualizar cliente
        String sqlCliente = "UPDATE usuario SET nome = ?, senha = ?, tipo_usuario = ?, data_nascimento = ?, fk_endereco_id_endereco = ? WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlCliente)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSenha());
            stmt.setString(3, cliente.getTipoUsuario());
            stmt.setDate(4, cliente.getDataNascimento());
            stmt.setInt(5, cliente.getEnderecoId()); // Parâmetro corrigido.
            stmt.setString(6, cliente.getCpf());
            stmt.executeUpdate();
        }
    }
}
