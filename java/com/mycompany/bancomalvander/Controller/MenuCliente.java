package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Util.EstiloUtil;
import java.awt.Dimension;

import javax.swing.*;

public class MenuCliente extends JFrame {

    public MenuCliente() {
        setTitle("Menu Cliente - Banco Malvader");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = EstiloUtil.criarPainelComBoxLayout(30, 50, 30, 50);

        JLabel label = EstiloUtil.criarTitulo("Menu do Cliente");

        JButton btnSaldo = EstiloUtil.criarBotaoEstilizado("1 - Saldo");
        JButton btnDeposito = EstiloUtil.criarBotaoEstilizado("2 - Depósito");
        JButton btnSaque = EstiloUtil.criarBotaoEstilizado("3 - Saque");
        JButton btnExtrato = EstiloUtil.criarBotaoEstilizado("4 - Extrato");
        JButton btnConsultarLimite = EstiloUtil.criarBotaoEstilizado("5 - Consultar Limite");
        JButton btnVoltar = EstiloUtil.criarBotaoEstilizado("Voltar");

        btnSaldo.addActionListener(e -> verificarSaldo());
        btnDeposito.addActionListener(e -> realizarDeposito());
        btnSaque.addActionListener(e -> realizarSaque());
        btnExtrato.addActionListener(e -> visualizarExtrato());
        btnConsultarLimite.addActionListener(e -> consultarLimite());
        btnVoltar.addActionListener(e -> voltarParaMenuPrincipal());

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnSaldo);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnDeposito);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnSaque);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnExtrato);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnConsultarLimite);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);

        add(panel);
    }

    private void verificarSaldo() {
        new SaldoCliente().setVisible(true);
        dispose();
    }

    private void realizarDeposito() {
        new Deposito().setVisible(true);
        dispose();
    }

    private void realizarSaque() {
        new Saque().setVisible(true);
        dispose();
    }

    private void visualizarExtrato() {
        new Extrato().setVisible(true);
        dispose();
    }

    private void consultarLimite() {
        new ConsultaLimite().setVisible(true);
        dispose();
    }

    private void voltarParaMenuPrincipal() {
        new MenuPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuCliente().setVisible(true));
    }
}
