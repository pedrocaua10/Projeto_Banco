package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.FuncionarioDAO;
import com.mycompany.bancomalvander.Model.Funcionario;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultarFuncionario extends JFrame {
    private JTextField txtCodigoFuncionario;
    private JButton btnConsultar, btnVoltar;

    public ConsultarFuncionario() {
        setTitle("Consultar Funcionário");
        setSize(400, 300); // Reduz tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Código do Funcionário
        JLabel lblCodigoFuncionario = new JLabel("Código do Funcionário:");
        lblCodigoFuncionario.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCodigoFuncionario.setFont(new Font("Arial", Font.BOLD, 14));

        txtCodigoFuncionario = new JTextField();
        txtCodigoFuncionario.setPreferredSize(new Dimension(200, 25));
        txtCodigoFuncionario.setMaximumSize(new Dimension(200, 25));
        txtCodigoFuncionario.setFont(new Font("Arial", Font.PLAIN, 12));
        txtCodigoFuncionario.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botões
        btnConsultar = new JButton("Consultar");
        btnConsultar.setPreferredSize(new Dimension(100, 30));
        btnConsultar.setMaximumSize(new Dimension(100, 30));
        btnConsultar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setPreferredSize(new Dimension(100, 30));
        btnVoltar.setMaximumSize(new Dimension(100, 30));
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnConsultar.addActionListener(e -> {
            try {
                consultarFuncionario();
            } catch (SQLException ex) {
                Logger.getLogger(ConsultarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnVoltar.addActionListener(e -> voltar());

        // Adiciona os componentes
        panel.add(lblCodigoFuncionario);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtCodigoFuncionario);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnConsultar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);

        add(panel);
    }

     // Método para consultar funcionário e abrir a tela de resultado
   private void consultarFuncionario() throws SQLException {
    String codigoFuncionario = txtCodigoFuncionario.getText();

    if (codigoFuncionario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, insira o código do funcionário.");
        return;
    }

    FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    try {
        Funcionario funcionario = funcionarioDao.buscarPorCodigo(codigoFuncionario);
        if (funcionario != null) {
            JOptionPane.showMessageDialog(this, 
                "Funcionário encontrado:\n" +
                "Código: " + funcionario.getCodigoFuncionario() + "\n" +
                "Cargo: " + funcionario.getCargo());
        } else {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao buscar funcionário: " + ex.getMessage());
        ex.printStackTrace();
    }
}

    private void voltar() {
        new ConsultarDados().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new ConsultarFuncionario().setVisible(true);
    }
}
