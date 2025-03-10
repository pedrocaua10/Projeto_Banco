package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaCorrenteDao;
import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Model.Conta;
import com.mycompany.bancomalvander.Model.ContaCorrente;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ContaCC extends JFrame {

    public ContaCC() {
        setTitle("Conta Corrente e Poupança");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(24, 1, 5, 5)); // Ajusta layout para mais campos

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

        // Campos para Conta Corrente
        panel.add(new JLabel("Limite da Conta Corrente:"));
        JTextField limiteField = new JTextField();
        panel.add(limiteField);

        panel.add(new JLabel("Data de Vencimento:"));
        JTextField vencimentoField = new JTextField();
        panel.add(vencimentoField);

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
        String tipoConta = "CORRENTE";  // Tipo de conta
        Integer fkUsuarioId = 1;

        // Insere na tabela conta
        ContaDAO contaDao = new ContaDAO();
        Conta conta = new Conta(0, numeroConta, agencia, saldo, tipoConta, fkUsuarioId);
        contaDao.save(conta);

        // Captura e cria Conta Corrente
        double limite = Double.parseDouble(limiteField.getText());
        String dataVencimentoStr = vencimentoField.getText();

        // Converte a data para o formato aceito pelo banco
        SimpleDateFormat formatoBR = new SimpleDateFormat("dd/MM/yyyy"); // Formato Brasileiro
        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd"); // Formato SQL
        java.util.Date dataUtil = formatoBR.parse(dataVencimentoStr); // Converte de BR para Date
        String dataVencimentoSQLStr = formatoSQL.format(dataUtil); // Formata para SQL
        java.sql.Date dataVencimentoSQL = java.sql.Date.valueOf(dataVencimentoSQLStr); // Cria java.sql.Date

        // Recupera o ID da conta salva
        int fkContaId = contaDao.getContaByNumero(numeroConta).getIdConta();

        ContaCorrente contaCorrente = new ContaCorrente(0, limite, dataVencimentoSQL, fkContaId);

        // Chama DAO para salvar no banco de dados
        ContaCorrenteDao contaCorrenteDAO = new ContaCorrenteDao();
        contaCorrenteDAO.save(contaCorrente);

        JOptionPane.showMessageDialog(this, "Contas cadastradas com sucesso!");
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar as contas: Data inválida - " + ex.getMessage());
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar as contas: " + ex.getMessage());
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar as contas: " + ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar as contas: " + ex.getMessage());
    }
});

        // Adiciona o painel à janela
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ContaCC();
    }
}
