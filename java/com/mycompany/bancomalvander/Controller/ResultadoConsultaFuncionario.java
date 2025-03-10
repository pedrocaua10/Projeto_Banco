package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;

public class ResultadoConsultaFuncionario extends JFrame {
    public ResultadoConsultaFuncionario(
            String cargo, String nome, String cpf, String dataNascimento, 
            String telefone, String endereco, String numeroCasa, 
            String cep, String bairro, String cidade, String estado) {

        // Configuração da janela
        setTitle("Resultado da Consulta - Funcionário");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Criando painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens

        // Labels para exibir as informações
        JLabel lblCargo = new JLabel("Cargo: " + cargo);
        JLabel lblNome = new JLabel("Nome: " + nome);
        JLabel lblCpf = new JLabel("CPF: " + cpf);
        JLabel lblDataNascimento = new JLabel("Data de Nascimento: " + dataNascimento);
        JLabel lblTelefone = new JLabel("Telefone: " + telefone);
        JLabel lblEndereco = new JLabel("Endereço: " + endereco + ", Nº " + numeroCasa);
        JLabel lblCep = new JLabel("CEP: " + cep);
        JLabel lblBairro = new JLabel("Bairro: " + bairro);
        JLabel lblCidade = new JLabel("Cidade: " + cidade);
        JLabel lblEstado = new JLabel("Estado: " + estado);

        // Configuração de alinhamento
        lblCargo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCpf.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDataNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTelefone.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEndereco.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCep.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBairro.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCidade.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> voltar());

        // Adicionando componentes ao painel
        panel.add(lblCargo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblNome);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblCpf);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblDataNascimento);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblTelefone);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblEndereco);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblCep);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblBairro);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblCidade);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblEstado);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnVoltar);

        // Adicionando painel à janela
        add(panel);
    }

    // Método para voltar à tela anterior
    private void voltar() {
        new ConsultarFuncionario().setVisible(true);
        this.dispose();
    }
}
