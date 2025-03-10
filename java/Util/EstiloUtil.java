package com.mycompany.bancomalvander.Util;

import javax.swing.*;
import java.awt.*;

public class EstiloUtil {

    public static JPanel criarPainelComBoxLayout(int top, int left, int bottom, int right) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        panel.setBackground(new Color(240, 240, 240)); // Fundo claro padrão
        return panel;
    }

    public static JLabel criarTitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(50, 50, 50));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        return label;
    }

    public static JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setPreferredSize(new Dimension(250, 40));
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(50, 150, 250));
        botao.setForeground(Color.WHITE);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return botao;
    }
}
