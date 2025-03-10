package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Dao.ContaPoupancaDao;
import com.mycompany.bancomalvander.Model.Conta;
import com.mycompany.bancomalvander.Model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ContaCP extends JFrame {

    public ContaCP() {
        setTitle("Conta Poupança");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(16, 1, 5, 5)); // Configura layout com espaçamento entre os campos

        // Campos comuns
        panel.add(new JLabel("Número da Conta:"));
        JTextField numeroContaField = new JTextField();
        panel.add(numeroContaField);

        panel.add(new JLabel("Nome do Cliente:"));
        JTextField nomeClienteField = new JTextField();
        panel.add(nomeClienteField);

        panel.add(new JLabel("CPF do Cliente:"));
        JTextField cpfField = new JTextField();
        panel.add(cpfField);

        // Campos para Conta Poupança
        panel.add(new JLabel("Taxa de Rendimento:"));
        JTextField taxaRendimentoField = new JTextField();
        panel.add(taxaRendimentoField);

        // Botões
        JButton enviarButton = new JButton("Enviar");
        panel.add(enviarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new AberturaConta().setVisible(true); // Retorna à tela de abertura
            dispose(); // Fecha a tela atual
        });
        panel.add(voltarButton);

        // Ação do botão "Enviar"
        enviarButton.addActionListener(e -> {
            try {
                // Captura dados comuns
                String numeroConta = numeroContaField.getText();
                String nomeCliente = nomeClienteField.getText();
                String cpfCliente = cpfField.getText();

                // Captura e cria Conta
                String agencia = "001";  // Exemplo de agência
                double saldo = 0.0;      // Saldo inicial
                String tipoConta = "POUPANCA";  // Tipo de conta
                Integer fkUsuarioId = 1;

                // Insere na tabela conta
                ContaDAO contaDao = new ContaDAO();
                Conta conta = new Conta(0, numeroConta, agencia, saldo, tipoConta, fkUsuarioId);
                contaDao.save(conta);

                // Captura e cria Conta Poupança
                double taxaRendimento = Double.parseDouble(taxaRendimentoField.getText());

                // Recupera o ID da conta salva
                int fkContaId = contaDao.getContaByNumero(numeroConta).getIdConta();

                ContaPoupanca contaPoupanca = new ContaPoupanca(taxaRendimento, fkContaId);

                // Chama DAO para salvar no banco de dados
                ContaPoupancaDao contaPoupancaDAO = new ContaPoupancaDao();
                contaPoupancaDAO.save(contaPoupanca);

                JOptionPane.showMessageDialog(this, "Conta poupança cadastrada com sucesso!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar a conta poupança: " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar a conta poupança: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar a conta poupança: " + ex.getMessage());
            }
        });

        // Adiciona o painel à janela
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ContaCP();
    }
}
