package zad_inventory.view.gui.guiAdmin.guiOperacoes.guiOperacoesOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiOperacoes.GuiOperacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiBuscarOperacoesPorSituacao extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelInserirDados;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JPanel panelDados;
    private JPanel panelTextField;
    private JComboBox comboBoxCategoria;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelBuscarOperacaoPorSituacao;
    private JButton buscarButton;

    public GuiBuscarOperacoesPorSituacao(UsuarioEntity usuarioLogado){

        OperacaoController controller = new OperacaoController(usuarioLogado);

        setContentPane(panelBuscarOperacaoPorSituacao);
        setTitle("Buscar venda por situacão");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        comboBoxCategoria.addItem("REALIZADA");
        comboBoxCategoria.addItem("CANCELADA");
        comboBoxCategoria.addItem("SEPARADA");

        setVisible(true);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String situacao = (String) comboBoxCategoria.getSelectedItem();
                dispose();
                GuiBuscarOperacoesPorSituacaoTela2 telaBuscar = new GuiBuscarOperacoesPorSituacaoTela2(usuarioLogado, situacao);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiOperacoes telaOperacoes = new GuiOperacoes(usuarioLogado);
            }
        });
    }
}
