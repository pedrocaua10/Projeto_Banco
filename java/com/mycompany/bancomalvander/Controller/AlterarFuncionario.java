package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;

public class AlterarFuncionario extends JFrame {
    private JTextField txtCodigo, txtCargo, txtTelefone, txtEndereco, txtNumero, txtCEP, txtBairro, txtCidade, txtEstado;
    private JButton btnSalvar, btnVoltar;

    public AlterarFuncionario() {
        setTitle("Alterar Funcionário");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblCodigo = new JLabel("Código do Funcionário:");
        txtCodigo = new JTextField(15);
        JLabel lblCargo = new JLabel("Cargo:");
        txtCargo = new JTextField(15);
        JLabel lblTelefone = new JLabel("Telefone:");
        txtTelefone = new JTextField(15);
        JLabel lblEndereco = new JLabel("Endereço:");
        txtEndereco = new JTextField(15);
        JLabel lblNumero = new JLabel("Número da Casa:");
        txtNumero = new JTextField(15);
        JLabel lblCEP = new JLabel("CEP:");
        txtCEP = new JTextField(15);
        JLabel lblBairro = new JLabel("Bairro:");
        txtBairro = new JTextField(15);
        JLabel lblCidade = new JLabel("Cidade:");
        txtCidade = new JTextField(15);
        JLabel lblEstado = new JLabel("Estado:");
        txtEstado = new JTextField(15);

        btnSalvar = new JButton("Salvar");
        btnVoltar = new JButton("Voltar");

        btnSalvar.addActionListener(e -> salvarAlteracoes());
        btnVoltar.addActionListener(e -> voltar());

        panel.add(lblCodigo);
        panel.add(txtCodigo);
        panel.add(lblCargo);
        panel.add(txtCargo);
        panel.add(lblTelefone);
        panel.add(txtTelefone);
        panel.add(lblEndereco);
        panel.add(txtEndereco);
        panel.add(lblNumero);
        panel.add(txtNumero);
        panel.add(lblCEP);
        panel.add(txtCEP);
        panel.add(lblBairro);
        panel.add(txtBairro);
        panel.add(lblCidade);
        panel.add(txtCidade);
        panel.add(lblEstado);
        panel.add(txtEstado);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnSalvar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);

        add(panel);
    }

    private void salvarAlteracoes() {
        JOptionPane.showMessageDialog(this, "Alterações do funcionário salvas com sucesso!");
        // Lógica para salvar alterações
    }

    private void voltar() {
        new AlterarDados().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new AlterarFuncionario().setVisible(true);
    }
}




