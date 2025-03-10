package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Model.Conta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class ConsultarConta extends JFrame {
    private JTextField txtNumeroConta;
    private JComboBox<String> comboTipoConta;
    private JButton btnConsultar, btnVoltar;

    public ConsultarConta() {
        setTitle("Consultar Conta");
        setSize(400, 300); // Reduz tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Número da Conta
        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNumeroConta.setFont(new Font("Arial", Font.BOLD, 14));

        txtNumeroConta = new JTextField();
        txtNumeroConta.setPreferredSize(new Dimension(200, 25));
        txtNumeroConta.setMaximumSize(new Dimension(200, 25));
        txtNumeroConta.setFont(new Font("Arial", Font.PLAIN, 12));
        txtNumeroConta.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tipo de Conta
        JLabel lblTipoConta = new JLabel("Tipo da Conta:");
        lblTipoConta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTipoConta.setFont(new Font("Arial", Font.BOLD, 14));

        String[] tiposConta = {"Corrente", "Poupança"};
        comboTipoConta = new JComboBox<>(tiposConta);
        comboTipoConta.setPreferredSize(new Dimension(200, 25));
        comboTipoConta.setMaximumSize(new Dimension(200, 25));
        comboTipoConta.setFont(new Font("Arial", Font.PLAIN, 12));
        comboTipoConta.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botões
        btnConsultar = new JButton("Consultar");
        btnConsultar.setPreferredSize(new Dimension(100, 30));
        btnConsultar.setMaximumSize(new Dimension(100, 30));
        btnConsultar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setPreferredSize(new Dimension(100, 30));
        btnVoltar.setMaximumSize(new Dimension(100, 30));
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnConsultar.addActionListener(e -> consultarConta());
        btnVoltar.addActionListener(e -> voltar());

        // Adiciona os componentes
        panel.add(lblNumeroConta);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtNumeroConta);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblTipoConta);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(comboTipoConta);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnConsultar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);

        add(panel);
    }

    private void consultarConta() {
    String numeroConta = txtNumeroConta.getText();
    String tipoConta = (String) comboTipoConta.getSelectedItem();

    if (numeroConta.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, insira o número da conta.");
        return;
    }

    ContaDAO contaDao = new ContaDAO();
    try {
        Conta conta = contaDao.buscarContaPorNumeroETipo(numeroConta, tipoConta);
        if (conta != null) {
            JOptionPane.showMessageDialog(this, 
                "Conta encontrada:\n" +
                "Número: " + conta.getNumeroConta() + "\n" +
                "Agência: " + conta.getAgencia() + "\n" +
                "Saldo: R$ " + conta.getSaldo());
        } else {
            JOptionPane.showMessageDialog(this, "Conta não encontrada.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao buscar conta: " + ex.getMessage());
        ex.printStackTrace();
    }
}

    private void voltar() {
        new ConsultarDados().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new ConsultarConta().setVisible(true);
    }
}
