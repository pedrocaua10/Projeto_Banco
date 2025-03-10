package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pelo menu principal do funcionário.
 * 
 * @author Marcos
 */
public class MenuFuncionario extends JFrame {

    public MenuFuncionario() {
        // Configuração da janela
        setTitle("Banco Malvader - Menu Funcionário");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Painel principal com BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Margem interna
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        // Título
        JLabel label = new JLabel("Menu do Funcionário");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Tamanho maior e negrito
        label.setForeground(new Color(50, 50, 50)); // Cor do texto
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Espaçamento abaixo do título

        // Botões estilizados
        JButton btnAberturaConta = criarBotaoEstilizado("1 - Abertura de Conta");
        JButton btnEncerramentoConta = criarBotaoEstilizado("2 - Encerramento de Conta");
        JButton btnConsultarDados = criarBotaoEstilizado("3 - Consultar Dados");
        JButton btnAlterarDados = criarBotaoEstilizado("4 - Alterar Dados");
        JButton btnCadastroFuncionario = criarBotaoEstilizado("5 - Cadastro de Funcionários");
        JButton btnRelatorios = criarBotaoEstilizado("6 - Gerar Relatórios");
        JButton btnSair = criarBotaoEstilizado("7 - Sair");
        JButton btnVoltar = criarBotaoEstilizado("Voltar");

        // Eventos dos botões
        btnAberturaConta.addActionListener(e -> abrirTelaAberturaConta());
        btnEncerramentoConta.addActionListener(e -> abrirTelaEncerramentoConta());
        btnConsultarDados.addActionListener(e -> abrirTelaConsultarDados());
        btnAlterarDados.addActionListener(e -> abrirTelaAlterarDados());
        btnCadastroFuncionario.addActionListener(e -> abrirTelaCadastroFuncionario());
        btnRelatorios.addActionListener(e -> abrirTelaGerarRelatorios());
        btnSair.addActionListener(e -> System.exit(0));
        btnVoltar.addActionListener(e -> voltarParaMenuPrincipal());

        // Adicionando componentes ao painel
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panel.add(btnAberturaConta);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espaçamento
        panel.add(btnEncerramentoConta);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnConsultarDados);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnAlterarDados);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnCadastroFuncionario);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnRelatorios);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento maior antes dos botões finais
        panel.add(btnSair);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);

        // Adicionando painel à janela
        add(panel);
    }

    /**
     * Método para criar botões estilizados.
     */
    private JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setPreferredSize(new Dimension(250, 40));
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.setFocusPainted(false); // Remove o contorno ao focar
        botao.setBackground(new Color(50, 150, 250)); // Cor de fundo
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return botao;
    }

    // Métodos para navegação entre telas
    private void abrirTelaAberturaConta() {
        new AberturaConta().setVisible(true);
        this.dispose();
    }

    private void abrirTelaEncerramentoConta() {
        new EncerramentoConta().setVisible(true);
        this.dispose();
    }

    private void abrirTelaConsultarDados() {
        new ConsultarDados().setVisible(true);
        this.dispose();
    }

    private void abrirTelaAlterarDados() {
        new AlterarDados().setVisible(true);
        this.dispose();
    }

    private void abrirTelaCadastroFuncionario() {
        new CadastroFuncionario().setVisible(true);
        this.dispose();
    }

    private void abrirTelaGerarRelatorios() {
        new GerarRelatorios().setVisible(true);
        this.dispose();
    }

    private void voltarParaMenuPrincipal() {
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFuncionario().setVisible(true));
    }
}
