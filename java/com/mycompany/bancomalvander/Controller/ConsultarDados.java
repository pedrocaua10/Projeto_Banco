package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;

public class ConsultarDados extends JFrame {

    public ConsultarDados() {
        // Configuração da janela
        setTitle("Consulta de Dados");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout BoxLayout (vertical)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Margem interna
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // Título
        JLabel lblTitulo = new JLabel("Consulta de Dados");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Espaçamento abaixo do título

        // Botões estilizados
        JButton btnConsultarConta = criarBotaoEstilizado("1 - Consultar Conta");
        JButton btnConsultarFuncionario = criarBotaoEstilizado("2 - Consultar Funcionário");
        JButton btnConsultarCliente = criarBotaoEstilizado("3 - Consultar Cliente");
        JButton btnVoltar = criarBotaoEstilizado("Voltar ao Menu Funcionário");

        // Ações dos botões
        btnConsultarConta.addActionListener(e -> abrirTela(new ConsultarConta()));
        btnConsultarFuncionario.addActionListener(e -> abrirTela(new ConsultarFuncionario()));
        btnConsultarCliente.addActionListener(e -> abrirTela(new ConsultarCliente()));
        btnVoltar.addActionListener(e -> abrirTela(new MenuFuncionario()));

        // Adicionando componentes ao painel
        panel.add(lblTitulo);
        panel.add(btnConsultarConta);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        panel.add(btnConsultarFuncionario);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnConsultarCliente);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento maior antes do botão "Voltar"
        panel.add(btnVoltar);

        // Adicionando o painel à janela
        add(panel);
    }

    /**
     * Método para criar botões estilizados.
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

    /**
     * Método para abrir uma nova tela e fechar a atual.
     */
    private void abrirTela(JFrame tela) {
        tela.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarDados().setVisible(true));
    }
}
