package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;

public class ResultadoConsultaConta extends JFrame {
    public ResultadoConsultaConta(String tipoConta, String cpfCliente, double saldo, double limiteDisponivel, String dataVencimento) {
        // Configuração da janela
        setTitle("Resultado da Consulta");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Criando o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens

        // Labels para exibir as informações
        JLabel lblTipoConta = new JLabel("Tipo da Conta: " + tipoConta);
        JLabel lblCpfCliente = new JLabel("CPF do Cliente: " + cpfCliente);
        JLabel lblSaldo = new JLabel("Saldo da Conta: R$ " + String.format("%.2f", saldo));
        JLabel lblLimite = new JLabel("Limite Disponível: R$ " + String.format("%.2f", limiteDisponivel));
        JLabel lblVencimento = new JLabel("Data de Vencimento: " + dataVencimento);

        // Configuração de alinhamento
        lblTipoConta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCpfCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLimite.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblVencimento.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> voltar());

        // Adicionando os componentes ao painel
        panel.add(lblTipoConta);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço
        panel.add(lblCpfCliente);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblSaldo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblLimite);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblVencimento);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnVoltar);

        // Adicionando o painel à janela
        add(panel);
    }

    // Método para voltar à tela anterior
    private void voltar() {
        new ConsultarConta().setVisible(true);
        this.dispose();
    }
}
