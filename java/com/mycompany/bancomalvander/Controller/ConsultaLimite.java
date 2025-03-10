package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Dao.UsuarioDAO;
import com.mycompany.bancomalvander.Model.Conta;
import com.mycompany.bancomalvander.Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ConsultaLimite extends JFrame {
    private Usuario usuarioAutenticado;
    private ContaDAO contaDAO = new ContaDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public ConsultaLimite() {
        
        setTitle("Consulta de Limite - Banco Malvader");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelSenha = new JLabel("Digite a senha do cliente:");
        JPasswordField senhaField = new JPasswordField(15);
        senhaField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        JButton btnVerificarSenha = new JButton("Verificar Senha");

        JLabel labelNumeroConta = new JLabel("Digite o número da conta:");
        JTextField numeroContaField = new JTextField(15);
        numeroContaField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        JButton btnVerificarConta = new JButton("Verificar Conta");

        JLabel labelResultado = new JLabel("Limite disponível:");
        JLabel resultadoField = new JLabel("");
        resultadoField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Inicialmente, desabilita os campos até a senha ser verificada
        labelNumeroConta.setEnabled(false);
        numeroContaField.setEnabled(false);
        btnVerificarConta.setEnabled(false);
        labelResultado.setEnabled(false);
        resultadoField.setEnabled(false);

        // Botão Voltar para o MenuCliente
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarParaMenuCliente());

        // Ação do botão verificar senha
        btnVerificarSenha.addActionListener(e -> {
            String senha = new String(senhaField.getPassword());
            try {
                usuarioAutenticado = usuarioDAO.validarSenha(senha);
                if (usuarioAutenticado != null) {
                    JOptionPane.showMessageDialog(this, "Senha correta.");
                    labelNumeroConta.setEnabled(true);
                    numeroContaField.setEnabled(true);
                    btnVerificarConta.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Senha incorreta.");
                    voltarParaMenuCliente();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao verificar senha.");
            }
        });

        // Ação do botão verificar conta
        btnVerificarConta.addActionListener(e -> {
            String numeroConta = numeroContaField.getText();
            try {
                Conta conta = contaDAO.getContaByNumero(numeroConta);
                if (conta != null && "CORRENTE".equals(conta.getTipoConta())) {
                    double limite = contaDAO.getLimiteByContaId(conta.getIdConta());
                    labelResultado.setEnabled(true);
                    resultadoField.setEnabled(true);
                    resultadoField.setText("Limite disponível: R$ " + limite);
                } else {
                    JOptionPane.showMessageDialog(this, "Conta não encontrada ou não é uma conta corrente.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao verificar conta.");
            }
        });

        // Adiciona os componentes ao painel usando GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelSenha, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(senhaField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(btnVerificarSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelNumeroConta, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(numeroContaField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(btnVerificarConta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelResultado, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(resultadoField, gbc);

        // Adiciona o botão de Voltar
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnVoltar, gbc);

        // Adiciona o painel à janela
        add(panel);
    }

    private void voltarParaMenuCliente() {
        new MenuCliente().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultaLimite().setVisible(true));
    }
}
