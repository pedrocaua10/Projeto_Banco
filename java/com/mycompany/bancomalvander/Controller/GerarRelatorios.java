package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.RelatorioDAO;
import com.mycompany.bancomalvander.Model.Relatorio;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GerarRelatorios extends JFrame {
    private boolean isSenhaVerificada = false;
    private RelatorioDAO relatorioDAO = new RelatorioDAO();

    public GerarRelatorios() {
        setTitle("Gerar Relatórios - Banco Malvader");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Criando os componentes
        JLabel labelSenha = new JLabel("Digite a senha de administrador:");
        JPasswordField senhaField = new JPasswordField(20);
        senhaField.setPreferredSize(new Dimension(200, 30));
        JButton btnVerificarSenha = new JButton("Verificar Senha");
        JButton btnRelatorioGeral = new JButton("1 - Relatório Geral");
        JButton btnVoltar = new JButton("Voltar");

        btnRelatorioGeral.setEnabled(false);

        Dimension buttonSize = new Dimension(200, 40);
        btnVerificarSenha.setPreferredSize(buttonSize);
        btnRelatorioGeral.setPreferredSize(buttonSize);
        btnVoltar.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(labelSenha, gbc);

        gbc.gridy = 1;
        panel.add(senhaField, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(btnVerificarSenha, gbc);

        gbc.gridy = 3;
        panel.add(btnRelatorioGeral, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(Box.createVerticalStrut(10), gbc);

        gbc.gridy = 5;
        panel.add(btnVoltar, gbc);

        btnVerificarSenha.addActionListener(e -> {
            String senha = new String(senhaField.getPassword());
            if (SenhaAdministrador.verificarSenha(senha)) {
                JOptionPane.showMessageDialog(this, "Senha correta.");
                isSenhaVerificada = true;
                btnRelatorioGeral.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta.");
                isSenhaVerificada = false;
            }
        });

        btnRelatorioGeral.addActionListener(e -> {
            if (isSenhaVerificada) {
                gerarRelatorioGeral();
            } else {
                JOptionPane.showMessageDialog(this, "Verifique a senha primeiro.");
            }
        });

        btnVoltar.addActionListener(e -> {
            new MenuFuncionario().setVisible(true);
            this.dispose();
        });

        add(panel);
    }

    private void gerarRelatorioGeral() {
        try {
            List<Relatorio> relatorios = relatorioDAO.getTodosRelatorios();
            if (relatorios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum relatório encontrado.");
                return;
            }

            String diretorio = System.getProperty("user.home") + "/Desktop/T2-Banco de Dados/relatorios";
            File pasta = new File(diretorio);
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            String caminhoArquivo = diretorio + "/relatorio_geral.xlsx";
            GeradorExcel geradorExcel = new GeradorExcel();
            geradorExcel.gerarRelatorioExcel(relatorios, caminhoArquivo);

            JOptionPane.showMessageDialog(this, "Relatório exportado para Excel em: " + caminhoArquivo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar arquivo Excel: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter relatórios: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GerarRelatorios().setVisible(true));
    }
}
