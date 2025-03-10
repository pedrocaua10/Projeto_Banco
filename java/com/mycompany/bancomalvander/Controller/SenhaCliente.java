package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;

public class SenhaCliente extends JFrame {
    private JPasswordField senhaField;
    private JButton btnValidar;
    private JButton btnVoltar;
    private JLabel lblMensagem;

    public SenhaCliente() {
        // Configuração da janela
        setTitle("Banco Malvader - Senha do Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Painel principal com BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Margem interna
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // Título
        JLabel lblTitulo = new JLabel("Digite sua senha");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22)); // Tamanho maior e negrito
        lblTitulo.setForeground(new Color(50, 50, 50)); // Cor do texto
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Espaçamento abaixo do título

        // Campo de senha
        senhaField = new JPasswordField(15);
        senhaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaField.setMaximumSize(new Dimension(250, 30));
        senhaField.setFont(new Font("Arial", Font.PLAIN, 16));
        senhaField.setEchoChar('*');

        // Mensagem de erro
        lblMensagem = new JLabel("");
        lblMensagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensagem.setForeground(Color.RED);

        // Botões estilizados
        btnValidar = criarBotaoEstilizado("Validar Senha");
        btnVoltar = criarBotaoEstilizado("Voltar");

        // Eventos dos botões
        btnValidar.addActionListener(e -> validarSenha());
        btnVoltar.addActionListener(e -> voltarParaMenuPrincipal());

        // Adicionando componentes ao painel
        panel.add(lblTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panel.add(senhaField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnValidar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(lblMensagem);

        // Adicionando painel à janela
        add(panel);
    }

    private JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setPreferredSize(new Dimension(250, 40));
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(50, 150, 250)); // Cor de fundo
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return botao;
    }

    private void validarSenha() {
        if (String.valueOf(senhaField.getPassword()).equals("1234")) {
            new MenuCliente().setVisible(true);
            this.dispose();
        } else {
            lblMensagem.setText("Senha incorreta.");
        }
    }

    private void voltarParaMenuPrincipal() {
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SenhaCliente().setVisible(true));
    }
}
