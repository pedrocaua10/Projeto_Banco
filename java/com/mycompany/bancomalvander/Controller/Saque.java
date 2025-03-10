package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Dao.UsuarioDAO;
import com.mycompany.bancomalvander.Model.Conta;
import com.mycompany.bancomalvander.Model.Usuario;

import javax.swing.*;
import java.awt.*;

public class Saque extends JFrame {
    private ContaDAO contaDAO = new ContaDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private JButton btnVoltar; // Declaração do botão Voltar

    public Saque() {
        setTitle("Saque - Banco Malvader");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelNumeroConta = new JLabel("Digite o número da conta:");
        JTextField numeroContaField = new JTextField(15);
        JLabel labelSenha = new JLabel("Digite a senha do cliente:");
        JPasswordField senhaField = new JPasswordField(15);
        JLabel labelValor = new JLabel("Informe o valor do saque:");
        JTextField valorField = new JTextField(15);
        JButton btnSacar = new JButton("Sacar");
        btnVoltar = new JButton("Voltar"); // Inicializa o botão Voltar

        // Ação do botão sacar
        btnSacar.addActionListener(e -> {
            String numeroConta = numeroContaField.getText().trim();
            String senha = new String(senhaField.getPassword()).trim();
            String valorTexto = valorField.getText().trim();

            if (numeroConta.isEmpty() || senha.isEmpty() || valorTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
                return;
            }

            try {
                double valorSaque = Double.parseDouble(valorTexto);

                // Verifica se a conta existe
                Conta conta = contaDAO.getContaByNumero(numeroConta);
                if (conta == null) {
                    JOptionPane.showMessageDialog(this, "Conta não encontrada.");
                    return;
                }

                // Valida a senha do usuário no banco de dados
                Usuario usuario = usuarioDAO.getUsuarioBySenha(senha);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(this, "Senha incorreta.");
                    return;
                }

                // Verifica se há saldo suficiente
                if (conta.getSaldo() < valorSaque) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente.");
                    return;
                }

                // Realiza o saque
                boolean sucesso = contaDAO.sacar(conta.getIdConta(), valorSaque);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao realizar o saque. Tente novamente.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido para o saque.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ocorreu um erro: " + ex.getMessage());
            }
        });
        
        btnVoltar.addActionListener(e -> voltarParaMenuCliente()); // Adiciona ação ao botão Voltar

        // Configuração do layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelNumeroConta, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(numeroContaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelSenha, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(senhaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelValor, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(valorField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(btnSacar, gbc);
        
        Dimension buttonSize = new Dimension(150, 20);
        btnSacar.setPreferredSize(buttonSize);
        btnVoltar.setPreferredSize(buttonSize); // Configura tamanho do botão Voltar
        
        
        
        gbc.gridy = 11; // Adiciona o botão Voltar na parte inferior
        panel.add(btnVoltar, gbc);

        add(panel);
    }
    
     private void voltarParaMenuCliente() {
        new MenuCliente().setVisible(true); // Volta para o menu do cliente
        this.dispose(); // Fecha a tela atual
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Saque().setVisible(true));
    }
    
}
