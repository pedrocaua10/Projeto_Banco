package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;

public class SenhaFuncionario extends JFrame {
    private JPasswordField senhaField;
    private JButton btnValidar;
    private JButton btnVoltar;
    private JLabel lblMensagem;

    private static final String SENHA_CORRETA_FUNCIONARIO = "func123";

    public SenhaFuncionario() {
        setTitle("Banco Malvader - Senha do Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setBackground(new Color(240, 240, 240));

        JLabel lblTitulo = new JLabel("Digite a senha do funcionário");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        senhaField = new JPasswordField(15);
        senhaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaField.setMaximumSize(new Dimension(250, 30));
        senhaField.setFont(new Font("Arial", Font.PLAIN, 16));
        senhaField.setEchoChar('*');

        lblMensagem = new JLabel("");
        lblMensagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensagem.setForeground(Color.RED);

        btnValidar = criarBotaoEstilizado("Validar Senha");
        btnVoltar = criarBotaoEstilizado("Voltar");

        btnValidar.addActionListener(e -> validarSenha());
        btnVoltar.addActionListener(e -> voltarParaMenuPrincipal());

        panel.add(lblTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(senhaField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnValidar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(lblMensagem);

        add(panel);
    }

    private JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setPreferredSize(new Dimension(250, 40));
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(50, 150, 250));
        botao.setForeground(Color.WHITE);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return botao;
    }

    private void validarSenha() {
        String senhaDigitada = new String(senhaField.getPassword());
        if (senhaDigitada.equals(SENHA_CORRETA_FUNCIONARIO)) {
            new MenuFuncionario().setVisible(true);
            this.dispose();
        } else {
            lblMensagem.setText("Senha incorreta. Tente novamente.");
        }
    }

    private void voltarParaMenuPrincipal() {
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SenhaFuncionario().setVisible(true));
    }
}
