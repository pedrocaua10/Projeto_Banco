package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Util.EstiloUtil;
import java.awt.Dimension;

import javax.swing.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Banco Malvader - Menu Principal");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = EstiloUtil.criarPainelComBoxLayout(30, 50, 30, 50);

        JLabel label = EstiloUtil.criarTitulo("Menu Principal");

        JButton btnFuncionario = EstiloUtil.criarBotaoEstilizado("1 - Funcionário");
        JButton btnCliente = EstiloUtil.criarBotaoEstilizado("2 - Cliente");
        JButton btnSair = EstiloUtil.criarBotaoEstilizado("3 - Sair do Programa");

        btnFuncionario.addActionListener(e -> abrirMenuFuncionario());
        btnCliente.addActionListener(e -> abrirMenuCliente());
        btnSair.addActionListener(e -> System.exit(0));

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnFuncionario);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnCliente);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnSair);

        add(panel);
    }

     private void abrirMenuFuncionario() {
        // Abre a tela de Menu do Funcionário
        new SenhaFuncionario().setVisible(true);
        this.dispose();
    }

    private void abrirMenuCliente() {
        // Abre a tela de Menu do Cliente
        new SenhaCliente().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}
