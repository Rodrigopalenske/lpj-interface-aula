package unialfa.locadora;

import javax.swing.*;
import java.awt.*;

public class LocadoraForm extends JFrame {

    private LocadoraService service;
    private JLabel labelFilme;
    private JTextField campoNomeFilme;
    private JButton botaoSalvar;
    private JList<Filme> listaDeFilmes;

    private String[] data = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"};

    public LocadoraForm() {
        service = new LocadoraService();
        setTitle("Filme");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelFilme = new JLabel("Nome do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelFilme, constraints);

        campoNomeFilme = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoNomeFilme, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 2;
        constraints.gridy = 0;
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
        if (!campoNomeFilme.getText().isEmpty() || !campoNomeFilme.getText().isBlank()) {
            service.salvar(new Filme(campoNomeFilme.getText(), "Default"));
            campoNomeFilme.setText("");
            listaDeFilmes.setModel(getListModel());
        }
    }
}
