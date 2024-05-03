package unialfa.locadora;

import javax.swing.*;
import java.awt.*;

public class LocadoraForm extends JFrame {

    private LocadoraService service;
    private JLabel labelNomeFilme;
    private JTextField campoNomeFilme;
    private JLabel labelDiretor;
    private JTextField campoDiretor;
    private JLabel labelDuracaoMinuto;
    private JTextField campoDuracaoMinuto;
    private JLabel labelElenco;
    private JTextField campoElenco;
    private JLabel labelClassificacao;
    private JTextField campoClassificacao;
    private JButton botaoSalvar;
    private JList<Filme> listaDeFilmes;
    private Boolean permitirExecao = Boolean.FALSE;
    private String[] data = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"};

    public LocadoraForm() {
        service = new LocadoraService();
        setTitle("Filme");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelNomeFilme = new JLabel("Nome do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelNomeFilme, constraints);

        campoNomeFilme = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoNomeFilme, constraints);

        labelDiretor = new JLabel("Nome do Diretor:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelDiretor, constraints);

        campoDiretor = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoDiretor, constraints);

        labelDuracaoMinuto = new JLabel("Duração do Filme em Minutos:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelDuracaoMinuto, constraints);

        campoDuracaoMinuto = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoDuracaoMinuto, constraints);

        labelElenco = new JLabel("Elenco do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelElenco, constraints);

        campoElenco = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoElenco, constraints);

        labelClassificacao = new JLabel("Classificação do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelClassificacao, constraints);

        campoClassificacao = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(campoClassificacao, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        painelEntrada.add(botaoSalvar, constraints);

        JPanel painelSaida = new JPanel(new BorderLayout());

        listaDeFilmes = new JList<>(carregarFilmes());
        listaDeFilmes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaDeFilmes);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        //pack();
        setLocationRelativeTo(null);
    }

    private DefaultListModel<Filme> getListModel() {
        DefaultListModel<Filme> model = new DefaultListModel<>();
        service.listarFilmes().forEach(model::addElement);
        return model;
    }

    private DefaultListModel<Filme> carregarFilmes() {
        return getListModel();
    }

    private void executarAcaoDoBotao() {
        permitirExecao = Boolean.TRUE;
        verificaCampos(campoNomeFilme.getText(), "Nome do Filme");
        verificaCampos(campoDiretor.getText(), "Nome do Diretor");

        verificaDuracao(campoDuracaoMinuto.getText());

        verificaCampos(campoElenco.getText(), "Elenco do Filme");
        verificaCampos(campoClassificacao.getText(), "Classificação do Filme");

        if (permitirExecao) {
            service.salvar(new Filme(campoNomeFilme.getText(), campoDiretor.getText(), campoDuracaoMinuto.getText(), campoElenco.getText(), campoClassificacao.getText()));
            campoNomeFilme.setText("");
            campoDiretor.setText("");
            campoDuracaoMinuto.setText("");
            campoElenco.setText("");
            campoClassificacao.setText("");
            listaDeFilmes.setModel(getListModel());
        }
    }

    private void verificaCampos(String valor, String campo) {
        try{
            if (valor.isEmpty()) throw new RuntimeException("Campo " + campo + " não pode ser vazio");
            if (valor.isBlank()) throw new RuntimeException("Campo " + campo + " não pode ter apenas espaços");

        } catch (RuntimeException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            permitirExecao = Boolean.FALSE;
        }
    }
    private void verificaDuracao(String valor) {
        try {
            if (valor.isEmpty()) throw new RuntimeException("Campo Duração em Minutos não pode ser vazio");
            if (valor.isBlank()) throw new RuntimeException("Campo Duração em Minutos não pode ter apenas espaços");
            Integer.parseInt(valor);
        }catch (NumberFormatException en){
            JOptionPane.showMessageDialog(this, "A duração do filme deve está em minutos");
            permitirExecao = Boolean.FALSE;
        } catch (RuntimeException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            permitirExecao = Boolean.FALSE;
        }
    }
}
