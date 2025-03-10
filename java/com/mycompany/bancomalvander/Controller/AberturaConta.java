package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AberturaConta extends JFrame {

    public AberturaConta() {
        setTitle("Abertura de Conta");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Criando o painel principal com layout BoxLayout (vertical)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Margem interna
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // Título
        JLabel lblTitulo = new JLabel("Escolha o tipo de conta:");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Espaçamento abaixo do título

        // Criando os botões estilizados
        JButton btnContaPoupanca = criarBotaoEstilizado("Abrir Conta Poupança");
        JButton btnContaCorrente = criarBotaoEstilizado("Abrir Conta Corrente");
        JButton btnVoltar = criarBotaoEstilizado("Voltar");

        // Ação do botão "Abrir Conta Poupança"
        btnContaPoupanca.addActionListener((ActionEvent e) -> {
            new ContaCP().setVisible(true); // Abre a tela de Conta Poupança
            dispose(); // Fecha a tela de Abertura de Conta
        });

        // Ação do botão "Abrir Conta Corrente"
        btnContaCorrente.addActionListener((ActionEvent e) -> {
            new ContaCC().setVisible(true); // Abre a tela de Conta Corrente
            dispose(); // Fecha a tela de Abertura de Conta
        });

        // Ação do botão "Voltar"
        btnVoltar.addActionListener((ActionEvent e) -> {
            new MenuFuncionario().setVisible(true); // Volta para o menu anterior
            dispose(); // Fecha a tela de Abertura de Conta
        });

        // Adicionando os componentes ao painel
        panel.add(lblTitulo);
        panel.add(btnContaPoupanca);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        panel.add(btnContaCorrente);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        panel.add(btnVoltar);

        // Adicionando o painel à janela
        add(panel);
    }

    /**
     * Método para criar botões estilizados
     */
    private JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setPreferredSize(new Dimension(200, 40)); // Tamanho preferido
        botao.setFont(new Font("Arial", Font.PLAIN, 16)); // Fonte do texto
        botao.setFocusPainted(false); // Remove o contorno ao focar
        botao.setBackground(new Color(50, 150, 250)); // Cor de fundo
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding interno
        return botao;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AberturaConta().setVisible(true)); // Abre a tela de Abertura de Conta
    }
}
