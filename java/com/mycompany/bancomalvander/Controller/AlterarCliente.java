package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ClienteDAO;
import com.mycompany.bancomalvander.Dao.TelefoneDAO;
import com.mycompany.bancomalvander.Model.Cliente;
import com.mycompany.bancomalvander.Model.Endereco;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AlterarCliente extends JFrame {
    private JTextField txtCPF, txtTelefone, txtEndereco, txtNumero, txtCEP, txtBairro, txtCidade, txtEstado;
    private JButton btnSalvar, btnVoltar;
    private Cliente cliente;

    public AlterarCliente() {
        setTitle("Alterar Cliente");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblCPF = new JLabel("CPF:");
        txtCPF = new JTextField(15);
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

        panel.add(lblCPF);
        panel.add(txtCPF);
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
    String cpf = txtCPF.getText();
    ClienteDAO clienteDAO = new ClienteDAO();
    TelefoneDAO telefoneDAO = new TelefoneDAO(); // Assumindo conexão compartilhada

    try {
        cliente = clienteDAO.findByCPF(cpf);
        if (cliente != null) {
            // Atualizar endereço
            Endereco endereco = new Endereco(
                cliente.getEnderecoId(),
                txtCEP.getText(),
                txtEndereco.getText(),
                Integer.parseInt(txtNumero.getText()),
                txtBairro.getText(),
                txtCidade.getText(),
                txtEstado.getText()
            );
            clienteDAO.updateCliente(cliente, endereco);

            // Atualizar telefone na tabela 'telefone'
            String telefone = txtTelefone.getText();
            //telefoneDAO.removerTelefones(cliente.getId());
            // telefoneDAO.inserirTelefone(cliente.getId(), telefone);

            JOptionPane.showMessageDialog(this, "Alterações do cliente salvas com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado!");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar alterações: " + e.getMessage());
    }
}

    private void voltar() {
        new MenuFuncionario().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new AlterarCliente().setVisible(true);
    }
}
