package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import com.mycompany.bancomalvander.Model.Usuario;
import com.mycompany.bancomalvander.Dao.UsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultarCliente extends JFrame {
    private JTextField txtCpf;
    private JButton btnBuscar, btnVoltar;

    public ConsultarCliente() {
        setTitle("Consultar Cliente");
        setSize(400, 300); // Reduz tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Adiciona o rótulo do CPF
        JLabel lblCpf = new JLabel("CPF do Cliente:");
        lblCpf.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCpf.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo do texto

        // Configura o campo de CPF
        txtCpf = new JTextField();
        txtCpf.setPreferredSize(new Dimension(200, 25)); // Tamanho do campo
        txtCpf.setMaximumSize(new Dimension(200, 25)); // Limita largura máxima
        txtCpf.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte do campo
        txtCpf.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configura os botões
        btnBuscar = new JButton("Buscar");
        btnVoltar = new JButton("Voltar");
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnBuscar.setPreferredSize(new Dimension(100, 30)); // Tamanho do botão
        btnBuscar.setMaximumSize(new Dimension(100, 30));
        btnVoltar.setPreferredSize(new Dimension(100, 30));
        btnVoltar.setMaximumSize(new Dimension(100, 30));

        // Define ações para os botões
        btnBuscar.addActionListener(e -> {
            try {
                buscarCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ConsultarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnVoltar.addActionListener(e -> voltar());

        // Adiciona os componentes ao painel
        panel.add(lblCpf);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre os componentes
        panel.add(txtCpf);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnBuscar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);

        // Adiciona o painel à janela
        add(panel);
    }

    private void buscarCliente() throws SQLException {
        String cpf = txtCpf.getText();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getUsuarioByCpf(cpf);
        if (usuario != null) {
            new ResultadoConsultaCliente(usuario).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        }
    }

    private void voltar() {
        new MenuFuncionario().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new ConsultarCliente().setVisible(true);
    }
}
