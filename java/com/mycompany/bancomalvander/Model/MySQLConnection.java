/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancomalvander.Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author Marcos
 */

public class MySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/BancoMalvander"; // URL do banco de dados
        String user = "root"; // Usuário do MySQL
        String password = "Jogo0098%"; // Senha do MySQL
        try {
            
            
            // Carregar explicitamente o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão
            Connection conn = DriverManager.getConnection(url, user, password);

            // Crie uma declaração para executar consultas
            Statement stmt = conn.createStatement();

            // Executa uma consulta de exemplo
            String sql = "SELECT * FROM usuario";
            ResultSet rs = stmt.executeQuery(sql);

            // Processa os resultados
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                System.out.println("ID: " + id + ", Nome: " + nome);
            }

            // Fecha a conexão
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public class MySQLConnection2 {
        private static final String URL = "jdbc:mysql://localhost:3306/BancoMalvander";
        private static final String USER = "root";
        private static final String PASSWORD = "Jogo0098%";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    } 
    
    
}




