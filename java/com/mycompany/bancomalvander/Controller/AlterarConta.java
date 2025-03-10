package com.mycompany.bancomalvander.Controller;

import com.mycompany.bancomalvander.Dao.ContaCorrenteDao;
import com.mycompany.bancomalvander.Dao.ContaDAO;
import com.mycompany.bancomalvander.Model.ContaCorrente;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlterarConta extends JFrame {
    private JTextField txtTipoConta, txtLimiteDisponivel, txtDataVencimento;
    private JButton btnSalvar, btnVoltar;

    public AlterarConta() {
        // Configuração da janela
        setTitle("Alterar Conta");
        setSize(400, 350); // Reduz tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens internas

        // Tipo de Conta
        JLabel lblTipoConta = new JLabel("Tipo de Conta:");
        lblTipoConta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTipoConta.setFont(new Font("Arial", Font.BOLD, 14));

        txtTipoConta = new JTextField();
        txtTipoConta.setPreferredSize(new Dimension(200, 25));
        txtTipoConta.setMaximumSize(new Dimension(200, 25));
        txtTipoConta.setFont(new Font("Arial", Font.PLAIN, 12));
        txtTipoConta.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Limite Disponível
        JLabel lblLimiteDisponivel = new JLabel("Limite Disponível:");
        lblLimiteDisponivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLimiteDisponivel.setFont(new Font("Arial", Font.BOLD, 14));

        txtLimiteDisponivel = new JTextField();
        txtLimiteDisponivel.setPreferredSize(new Dimension(200, 25));
        txtLimiteDisponivel.setMaximumSize(new Dimension(200, 25));
        txtLimiteDisponivel.setFont(new Font("Arial", Font.PLAIN, 12));
        txtLimiteDisponivel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Data de Vencimento
        JLabel lblDataVencimento = new JLabel("Data de Vencimento:");
        lblDataVencimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDataVencimento.setFont(new Font("Arial", Font.BOLD, 14));

        txtDataVencimento = new JTextField();
        txtDataVencimento.setPreferredSize(new Dimension(200, 25));
        txtDataVencimento.setMaximumSize(new Dimension(200, 25));
        txtDataVencimento.setFont(new Font("Arial", Font.PLAIN, 12));
        txtDataVencimento.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.setPreferredSize(new Dimension(100, 30));
        btnSalvar.setMaximumSize(new Dimension(100, 30));
        btnSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setPreferredSize(new Dimension(100, 30));
        btnVoltar.setMaximumSize(new Dimension(100, 30));
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Eventos
        btnSalvar.addActionListener(e -> {
            try {
                salvarAlteracoes();
            } catch (SQLException ex) {
                Logger.getLogger(AlterarConta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AlterarConta.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnVoltar.addActionListener(e -> voltar());

        // Adicionando componentes ao painel
        panel.add(lblTipoConta);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtTipoConta);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblLimiteDisponivel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtLimiteDisponivel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblDataVencimento);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtDataVencimento);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnSalvar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnVoltar);

        // Adicionando painel à janela
        add(panel);
    }

   private void salvarAlteracoes() throws SQLException, ParseException {
    // Lê os valores dos campos
    String limiteTexto = txtLimiteDisponivel.getText();
    String dataVencimentoTexto = txtDataVencimento.getText();
    String numeroConta = JOptionPane.showInputDialog(this, "Informe o número da conta:");

    double limite;
    java.sql.Date dataVencimento = null;

    // Validação dos dados
    try {
        limite = Double.parseDouble(limiteTexto); // Converte limite para double

        // Formatação e conversão da data usando SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Impede datas inválidas (como 31/02/2024)
        java.util.Date utilDate = sdf.parse(dataVencimentoTexto); // Converte String para Date
        dataVencimento = new java.sql.Date(utilDate.getTime()); // Converte para java.sql.Date

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, "Limite inválido. Por favor, insira um número válido.");
        return;
    }

    // Busca o ID da conta correspondente ao número da conta
    int fkContaId = obterIdContaPorNumero(numeroConta);
    if (fkContaId == -1) {
        JOptionPane.showMessageDialog(this, "Conta não encontrada. Verifique o número da conta.");
        return;
    }

    // Cria o objeto ContaCorrente
    ContaCorrente contaCorrente = new ContaCorrente(0, limite, dataVencimento, fkContaId);
    ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();

    try {
        boolean sucesso = contaCorrenteDao.atualizarContaCorrente(contaCorrente);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Conta corrente atualizada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a conta corrente. Verifique os dados.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro no banco de dados: " + ex.getMessage());
        ex.printStackTrace();
    }
}


    private int obterIdContaPorNumero(String numeroConta) throws SQLException {
        ContaDAO contaDao = new ContaDAO();
        return contaDao.buscarIdContaPorNumero(numeroConta); // Retorna -1 em caso de erro
    }

    private void voltar() {
        new AlterarDados().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new AlterarConta().setVisible(true);
    }
}
