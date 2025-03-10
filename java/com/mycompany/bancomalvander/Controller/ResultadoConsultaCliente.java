package com.mycompany.bancomalvander.Controller;

import javax.swing.*;
import java.awt.*;
import com.mycompany.bancomalvander.Model.Usuario;

public class ResultadoConsultaCliente extends JFrame {
    public ResultadoConsultaCliente(Usuario usuario) {
        setTitle("Resultado da Consulta");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nome: " + usuario.getNome()));
        panel.add(new JLabel("CPF: " + usuario.getCpf()));
        panel.add(new JLabel("Data de Nascimento: " + usuario.getDataNascimento().toString()));
        // Adicione mais campos conforme necessário

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> {
            new ConsultarCliente().setVisible(true);
            this.dispose();
        });

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnVoltar);

        add(panel);
    }
}
