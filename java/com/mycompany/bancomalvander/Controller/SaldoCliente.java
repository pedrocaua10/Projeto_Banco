package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.UsuarioDAO;
import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Model.Usuario;
import com.mycompany.bancomalvander.Model.Conta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SaldoCliente extends JFrame {
    private boolean isSenhaVerificada = false;
    private JButton btnVoltar;
    private JTextField numeroContaField;
    private JLabel labelResultado;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ContaDAO contaDAO = new ContaDAO();
    private int idUsuario;

    public SaldoCliente() {
        setTitle("Verificar Saldo - Banco Malvander");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelTitulo = new JLabel("SALDO", SwingConstants.CENTER);
        JLabel labelDigiteSenha = new JLabel("DIGITE A SENHA DO CLIENTE:");
        JPasswordField senhaField = new JPasswordField(20);
        senhaField.setPreferredSize(new Dimension(200, 30));
        JButton btnVerificarSenha = new JButton("Verificar Senha");
        btnVoltar = new JButton("Voltar");
        labelResultado = new JLabel("", SwingConstants.CENTER);
        JLabel labelNumeroConta = new JLabel("DIGITE O NÚMERO DA CONTA:");
        numeroContaField = new JTextField(20);
        numeroContaField.setPreferredSize(new Dimension(200, 30));
        numeroContaField.setVisible(false); // Esconde inicialmente
        JButton btnVerificarConta = new JButton("Verificar Conta");
        btnVerificarConta.setVisible(false); // Esconde inicialmente

        Dimension buttonSize = new Dimension(200, 40);
        btnVerificarSenha.setPreferredSize(buttonSize);
        btnVerificarConta.setPreferredSize(buttonSize);
        btnVoltar.setPreferredSize(buttonSize);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelResultado.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(labelTitulo, gbc);

        gbc.gridy = 1;
        panel.add(labelDigiteSenha, gbc);

        gbc.gridy = 2;
        panel.add(senhaField, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(btnVerificarSenha, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(labelNumeroConta, gbc);

        gbc.gridy = 5;
        panel.add(numeroContaField, gbc);

        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(btnVerificarConta, gbc);

        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(labelResultado, gbc);

        gbc.gridy = 8;
        panel.add(btnVoltar, gbc);

        btnVerificarSenha.addActionListener(e -> {
            String senha = new String(senhaField.getPassword());
            try {
                Usuario usuario = usuarioDAO.getUsuarioBySenha(senha);
                if (usuario != null) {
                    idUsuario = usuario.getIdUsuario();
                    isSenhaVerificada = true;
                    labelResultado.setText("SENHA VERIFICADA. DIGITE O NÚMERO DA CONTA.");
                    numeroContaField.setVisible(true);
                    btnVerificarConta.setVisible(true);
                } else {
                    isSenhaVerificada = false;
                    labelResultado.setText("SENHA INCORRETA.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                labelResultado.setText("ERRO AO VERIFICAR SENHA.");
            }
        });

        btnVerificarConta.addActionListener(e -> {
            String numeroConta = numeroContaField.getText();
            try {
                Conta conta = contaDAO.getContaByNumero(numeroConta);
                if (conta != null && conta.getFkUsuarioId() == idUsuario) {
                    labelResultado.setText(String.format("Saldo disponível: R$ %.2f", conta.getSaldo()));
                } else {
                    labelResultado.setText("CONTA NÃO ENCONTRADA OU NÃO PERTENCE AO USUÁRIO.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                labelResultado.setText("ERRO AO VERIFICAR CONTA.");
            }
        });

        btnVoltar.addActionListener(e -> voltarParaMenuCliente());

        add(panel);
    }

    private void voltarParaMenuCliente() {
        new MenuCliente().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SaldoCliente().setVisible(true));
    }
}

