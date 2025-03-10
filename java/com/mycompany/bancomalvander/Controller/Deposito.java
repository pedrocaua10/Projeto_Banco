package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.UsuarioDAO;
import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Dao.TransacaoDAO;
import com.mycompany.bancomalvander.Model.Usuario;
import com.mycompany.bancomalvander.Model.Conta;
import com.mycompany.bancomalvander.Model.Transacao;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Deposito extends JFrame {
    private boolean isSenhaVerificada = false;
    private JTextField numeroContaField;
    private JLabel labelResultado;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ContaDAO contaDAO = new ContaDAO();
    private TransacaoDAO transacaoDAO = new TransacaoDAO();
    private int idUsuario;
    private JButton btnVoltar; // Declaração do botão Voltar

    public Deposito() {
        setTitle("Depósito - Banco Malvander");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelTitulo = new JLabel("DEPÓSITO", SwingConstants.CENTER);
        JLabel labelDigiteSenha = new JLabel("DIGITE A SENHA DO CLIENTE:");
        JPasswordField senhaField = new JPasswordField(20);
        senhaField.setPreferredSize(new Dimension(200, 30));
        JButton btnVerificarSenha = new JButton("Verificar Senha");
        JLabel labelNumeroConta = new JLabel("DIGITE O NÚMERO DA CONTA:");
        numeroContaField = new JTextField(20);
        numeroContaField.setPreferredSize(new Dimension(200, 30));
        numeroContaField.setVisible(false); // Esconde inicialmente
        JButton btnVerificarConta = new JButton("Verificar Conta");
        btnVerificarConta.setVisible(false); // Esconde inicialmente
        JLabel labelValor = new JLabel("Digite o valor a ser depositado:");
        JTextField txtValor = new JTextField(20);
        txtValor.setVisible(false); // Esconde inicialmente
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setVisible(false); // Esconde inicialmente
        labelResultado = new JLabel("", SwingConstants.CENTER);
        btnVoltar = new JButton("Voltar"); // Inicializa o botão Voltar

        Dimension buttonSize = new Dimension(200, 40);
        btnVerificarSenha.setPreferredSize(buttonSize);
        btnVerificarConta.setPreferredSize(buttonSize);
        btnDepositar.setPreferredSize(buttonSize);
        btnVoltar.setPreferredSize(buttonSize); // Configura tamanho do botão Voltar
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
        panel.add(labelValor, gbc);

        gbc.gridy = 8;
        panel.add(txtValor, gbc);

        gbc.gridy = 9;
        panel.add(btnDepositar, gbc);

        gbc.gridy = 10;
        panel.add(labelResultado, gbc);

        gbc.gridy = 11; // Adiciona o botão Voltar na parte inferior
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
                    labelResultado.setText("CONTA VERIFICADA. DIGITE O VALOR DO DEPÓSITO.");
                    txtValor.setVisible(true);
                    btnDepositar.setVisible(true);
                } else {
                    labelResultado.setText("CONTA NÃO ENCONTRADA OU NÃO PERTENCE AO USUÁRIO.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                labelResultado.setText("ERRO AO VERIFICAR CONTA.");
            }
        });

        btnDepositar.addActionListener(e -> {
            try {
                String numeroConta = numeroContaField.getText();
                String valorTexto = txtValor.getText();

                if (numeroConta.isEmpty() || valorTexto.isEmpty()) {
                    labelResultado.setText("Número da conta e valor são obrigatórios.");
                    return;
                }

                double valorDeposito;
                try {
                    valorDeposito = Double.parseDouble(valorTexto);
                    if (valorDeposito <= 0) {
                        labelResultado.setText("O valor do depósito deve ser positivo.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    labelResultado.setText("Valor inválido.");
                    return;
                }

                Conta conta = contaDAO.getContaByNumero(numeroConta);
                if (conta != null && conta.getFkUsuarioId() == idUsuario) {
                    double saldoAtual = contaDAO.getSaldo(conta.getIdConta());
                    double novoSaldo = saldoAtual + valorDeposito;

                    contaDAO.atualizarSaldo(conta.getIdConta(), novoSaldo);

                    Transacao transacao = new Transacao("CREDITO", valorDeposito, conta.getIdConta());
                    transacaoDAO.inserirTransacao(transacao);

                    labelResultado.setText(String.format("Depósito de R$ %.2f realizado com sucesso.", valorDeposito));
                } else {
                    labelResultado.setText("Conta não encontrada ou não pertence ao usuário.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                labelResultado.setText("Erro ao processar o depósito.");
            }
        });

        btnVoltar.addActionListener(e -> voltarParaMenuCliente()); // Adiciona ação ao botão Voltar

        add(panel);
    }

    private void voltarParaMenuCliente() {
        new MenuCliente().setVisible(true); // Volta para o menu do cliente
        this.dispose(); // Fecha a tela atual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Deposito().setVisible(true));
    }
}
