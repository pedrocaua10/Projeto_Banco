package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncerramentoConta extends JFrame {

    public EncerramentoConta() {
        // Configuração da janela
        setTitle("Encerramento de Conta - Banco Malvader");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título da tela
        JLabel lblTitulo = new JLabel("Encerramento de Conta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        // Campos e botões
        JLabel lblSenha = new JLabel("Senha do Administrador:");
        JPasswordField senhaField = new JPasswordField(15);
        JButton btnVerificarSenha = new JButton("Verificar Senha");

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        JTextField txtNumeroConta = new JTextField(15);
        JButton btnEncerrarConta = new JButton("Encerrar Conta");
        JButton btnVoltar = new JButton("Voltar");

        // Configuração inicial dos campos
        lblNumeroConta.setEnabled(false);
        txtNumeroConta.setEnabled(false);
        btnEncerrarConta.setEnabled(false);

        // Ação do botão Verificar Senha
        btnVerificarSenha.addActionListener(e -> {
            String senha = new String(senhaField.getPassword());
            if (SenhaAdministrador.verificarSenha(senha)) {
                JOptionPane.showMessageDialog(this, "Senha correta!");
                lblNumeroConta.setEnabled(true);
                txtNumeroConta.setEnabled(true);
                btnEncerrarConta.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!");
                voltarParaMenuFuncionario();
            }
        });

        // Ação do botão Encerrar Conta
        btnEncerrarConta.addActionListener(e -> {
            String numeroConta = txtNumeroConta.getText();
            if (!numeroConta.isEmpty()) {
                try {
                    new ContaDAO().deleteByNumeroConta(numeroConta);
                    JOptionPane.showMessageDialog(this, "Conta " + numeroConta + " encerrada com sucesso!");
                    voltarParaMenuFuncionario();
                } catch (SQLException ex) {
                    Logger.getLogger(EncerramentoConta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Erro ao encerrar a conta: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, digite um número de conta.");
            }
        });

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> voltarParaMenuFuncionario());

        // Adicionando os componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridy++;
        panel.add(lblSenha, gbc);

        gbc.gridy++;
        panel.add(senhaField, gbc);

        gbc.gridy++;
        panel.add(btnVerificarSenha, gbc);

        gbc.gridy++;
        panel.add(lblNumeroConta, gbc);

        gbc.gridy++;
        panel.add(txtNumeroConta, gbc);

        gbc.gridy++;
        panel.add(btnEncerrarConta, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(btnVoltar, gbc);

        add(panel);
    }

    private void voltarParaMenuFuncionario() {
        new MenuFuncionario().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EncerramentoConta().setVisible(true));
    }
}
