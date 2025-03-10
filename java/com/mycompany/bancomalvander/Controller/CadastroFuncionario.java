package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.FuncionarioDAO;
import com.mycompany.bancomalvander.Dao.UsuarioDAO;
import com.mycompany.bancomalvander.Model.Funcionario;
import com.mycompany.bancomalvander.Model.MySQLConnection;
import com.mycompany.bancomalvander.Model.MySQLConnection.MySQLConnection2;
import com.mycompany.bancomalvander.Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.sql.SQLException;

public class CadastroFuncionario extends JFrame {
    private JButton btnCadastroFuncionario;
    private JButton btnSair;
    private JButton btnVoltar;
    private JTextField txtCodigo;
    private JTextField txtCargo;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtDataNascimento;
    private JTextField txtTelefone;
    private JTextField txtEndereco;
    private boolean senhaValidada = false;

    public CadastroFuncionario() {
        setTitle("Cadastro de Funcionário - Banco Malvader");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout GridBagLayout para melhor centralização
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelSenha = new JLabel("Digite a senha de administrador:");
        JPasswordField senhaField = criarCampoSenha();
        JButton btnVerificarSenha = new JButton("Verificar Senha");

        JLabel labelCadastro = new JLabel("Cadastrar Novo Funcionário");
        labelCadastro.setFont(new Font("Arial", Font.BOLD, 16));
        labelCadastro.setHorizontalAlignment(JLabel.CENTER);

        // Campos de texto com dicas
        txtCodigo = criarCampoComDica("Código do Funcionário");
        txtCargo = criarCampoComDica("Cargo");
        txtNome = criarCampoComDica("Nome do Funcionário");
        txtCpf = criarCampoComDica("CPF");
        txtDataNascimento = criarCampoComDica("Data de Nascimento");
        txtTelefone = criarCampoComDica("Telefone");
        txtEndereco = criarCampoComDica("ID do Endereço");

        // Configuração dos botões
        btnCadastroFuncionario = new JButton("Cadastrar Funcionário");
        btnSair = new JButton("Sair");
        btnVoltar = new JButton("Voltar");

        // Desabilitar os botões de cadastro até que a senha seja validada
        btnCadastroFuncionario.setEnabled(false);

        // Ação para verificar a senha
        btnVerificarSenha.addActionListener(e -> {
            String senha = new String(senhaField.getPassword());
            if (validarSenha(senha)) {
                senhaValidada = true;
                btnCadastroFuncionario.setEnabled(true); // Habilita o botão de cadastro
                JOptionPane.showMessageDialog(panel, "Senha validada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(panel, "Senha incorreta. Tente novamente.");
            }
        });

        // Evento para o botão de cadastro
        btnCadastroFuncionario.addActionListener(e -> {
    if (senhaValidada) {
        // Obtém os dados dos campos de texto
        String codigoFuncionario = txtCodigo.getText();
        String cargo = txtCargo.getText();
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String dataNascimento = txtDataNascimento.getText();
        String telefone = txtTelefone.getText();
        String enderecoIdStr = txtEndereco.getText();

        // Validação de campos
        if (!validarCpf(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido. Digite apenas números (11 dígitos).");
            return;
        }

        try {
            Date.valueOf(dataNascimento); // Validação do formato de data
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use yyyy-MM-dd.");
            return;
        }

        int enderecoId;
        try {
            enderecoId = Integer.parseInt(enderecoIdStr); // Validação do ID de endereço
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de endereço inválido. Digite um número.");
            return;
        }

        try {
            // Constrói os objetos
            Usuario usuario = new Usuario(
                0, nome, "senha123", "FUNCIONARIO",
                Date.valueOf(dataNascimento), cpf, enderecoId
            );

            Funcionario funcionario = new Funcionario(
                0, codigoFuncionario, cargo, 0, 1 // O ID do usuário será gerado
            );

            // Insere no banco
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            if (funcionarioDAO.cadastrarFuncionario(usuario, funcionario)) {
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar funcionário.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Senha não validada. Acesso negado.");
    }
});



        btnSair.addActionListener(e -> System.exit(0));
        btnVoltar.addActionListener(e -> voltarParaMenuFuncionario());

        // Adiciona componentes ao painel com alinhamento centralizado
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(labelSenha, gbc);

        gbc.gridy = 1;
        panel.add(senhaField, gbc);

        gbc.gridy = 2;
        panel.add(btnVerificarSenha, gbc);

        gbc.gridy = 3;
        panel.add(labelCadastro, gbc);

        gbc.gridwidth = 1; // Define gridwidth de volta para 1
        gbc.gridy = 4;
        panel.add(txtCodigo, gbc);

        gbc.gridy = 5;
        panel.add(txtCargo, gbc);

        gbc.gridy = 6;
        panel.add(txtNome, gbc);

        gbc.gridy = 7;
        panel.add(txtCpf, gbc);

        gbc.gridy = 8;
        panel.add(txtDataNascimento, gbc);
        
        gbc.gridy = 9;
        panel.add(txtTelefone, gbc);

        gbc.gridy = 10;
        panel.add(txtEndereco, gbc);

        gbc.gridy = 11;
        panel.add(btnCadastroFuncionario, gbc);

        gbc.gridy = 12;
        panel.add(btnSair, gbc);

        gbc.gridy = 13;
        panel.add(btnVoltar, gbc);

        add(panel);
    }

    private boolean validarSenha(String senha) {
        // Validação simples da senha; altere conforme necessário
        return "1234".equals(senha);
    }

    private JPasswordField criarCampoSenha() {
        JPasswordField senhaField = new JPasswordField(15);
        senhaField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        return senhaField;
    }

    private JTextField criarCampoComDica(String dica) {
        JTextField textField = new JTextField(15);
        textField.setText(dica);
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(dica)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(dica);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }


    private void voltarParaMenuFuncionario() {
        new MenuFuncionario().setVisible(true);
        this.dispose();
    }
    
    private boolean validarCpf(String cpf) {
        return cpf.matches("\\d{11}"); // Verifica se tem 11 dígitos numéricos
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroFuncionario().setVisible(true));
    }
}
