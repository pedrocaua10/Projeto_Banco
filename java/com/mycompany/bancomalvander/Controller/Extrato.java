package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Dao.TransacaoDAO;
import com.mycompany.bancomalvander.Dao.UsuarioDAO;
import com.mycompany.bancomalvander.Model.Conta;
import com.mycompany.bancomalvander.Model.Transacao;
import com.mycompany.bancomalvander.Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class Extrato extends JFrame {
    private TransacaoDAO transacaoDAO = new TransacaoDAO();
    private ContaDAO contaDAO = new ContaDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private JTextField numeroContaField;
    private JPasswordField senhaField;
    private JPanel panel;

    public Extrato() {
        // Configuração da janela
        setTitle("Gerar Extrato - Banco Malvader");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout centralizado
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo clara
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título estilizado
        JLabel lblTitulo = new JLabel("Extrato");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(50, 150, 250));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Botão para iniciar o processo de geração do extrato
        JButton btnStart = criarBotaoEstilizado("Iniciar Geração de Extrato");
        btnStart.addActionListener(e -> iniciarGeracaoExtrato());
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(btnStart, gbc);

        // Botão de voltar estilizado
        JButton btnVoltar = criarBotaoEstilizado("Voltar");
        btnVoltar.addActionListener(e -> voltarParaMenuCliente());
        gbc.gridy = 2;
        panel.add(btnVoltar, gbc);

        // Adicionando o painel à janela
        add(panel);
    }

    private void iniciarGeracaoExtrato() {
        panel.removeAll(); // Remove os componentes anteriores (botão de início e voltar)

        // Criando os componentes para exibir número da conta e senha
        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        numeroContaField = new JTextField(15);
        JLabel labelSenha = new JLabel("Senha:");
        senhaField = new JPasswordField(15);
        JButton btnGerar = criarBotaoEstilizado("Gerar Extrato");

        btnGerar.addActionListener(e -> gerarExtrato());

        // Layout para os novos componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelNumeroConta, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(numeroContaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelSenha, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(senhaField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnGerar, gbc);

        // Botão de voltar estilizado
        JButton btnVoltar = criarBotaoEstilizado("Voltar");
        btnVoltar.addActionListener(e -> voltarParaMenuCliente());
        gbc.gridy = 3;
        panel.add(btnVoltar, gbc);

        // Atualizando a interface
        revalidate();
        repaint();
    }

    private void gerarExtrato() {
        String numeroConta = numeroContaField.getText().trim();
        String senha = new String(senhaField.getPassword()).trim();

        if (numeroConta.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            Conta conta = contaDAO.getContaByNumero(numeroConta);
            if (conta == null) {
                JOptionPane.showMessageDialog(this, "Conta não encontrada.");
                return;
            }

            Usuario usuario = usuarioDAO.getUsuarioBySenha(senha);
            if (usuario == null || usuario.getIdUsuario() != conta.getFkUsuarioId()) {
                JOptionPane.showMessageDialog(this, "Senha incorreta.");
                return;
            }

            List<Transacao> transacoes = transacaoDAO.getTransacoesByConta(conta.getIdConta());
            if (transacoes == null || transacoes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma transação encontrada para esta conta.");
                return;
            }

            String diretorio = System.getProperty("user.home") + "/extratos";
            File pasta = new File(diretorio);
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            String caminhoArquivo = diretorio + "/extrato_" + numeroConta + ".xlsx";
            GeradorExcel geradorExcel = new GeradorExcel();
            geradorExcel.gerarExtratoExcel(transacoes, caminhoArquivo);

            JOptionPane.showMessageDialog(this, "Extrato gerado com sucesso em: " + caminhoArquivo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void voltarParaMenuCliente() {
        new MenuCliente().setVisible(true); // Abre a tela de menu do cliente
        this.dispose(); // Fecha a tela de extrato
    }

    /**
     * Método para criar botões estilizados.
     */
    private JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setPreferredSize(new Dimension(300, 40)); // Tamanho preferido
        botao.setFont(new Font("Arial", Font.PLAIN, 16)); // Fonte do texto
        botao.setFocusPainted(false); // Remove o contorno ao focar
        botao.setBackground(new Color(50, 150, 250)); // Cor de fundo
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding interno
        return botao;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Extrato().setVisible(true));
    }
}
