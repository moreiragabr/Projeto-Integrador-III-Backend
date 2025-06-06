package zad_inventory.view.gui.guiAdmin.guiOperacoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.GuiAdmin;
import zad_inventory.view.gui.guiAdmin.guiOperacoes.guiOperacoesOpcoes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class GuiOperacoes extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JButton realizarNovaVendaButton;
    private JButton listarTodasAsVendasButton;
    private JButton buscarVendasPorIDButton;
    private JButton atualizarSituaçãoDeOperaçãoButton;
    private JPanel panelBotaosair;
    private JButton voltarButton;
    private JButton buscarOperacaoPorSituacaoButton;
    private JPanel panelOperacoes;

    private final OperacaoController controller;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public GuiOperacoes(UsuarioEntity usuarioLogado){

        this.controller = new OperacaoController(usuarioLogado);

        setContentPane(panelOperacoes);
        setTitle("Gerenciamento operações");
        setSize(700,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiAdmin telaAdmin = new GuiAdmin(usuarioLogado);
            }
        });

        realizarNovaVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiRealizarVenda novaVendaTela = new GuiRealizarVenda(usuarioLogado);
            }
        });

        listarTodasAsVendasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiListarVendas listarVendasTela = new GuiListarVendas(usuarioLogado);
            }
        });

        buscarVendasPorIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiBuscarVendasPorId buscarVendasPorId = new GuiBuscarVendasPorId(usuarioLogado);
            }
        });

        atualizarSituaçãoDeOperaçãoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiAtualizarSituacaoOperacaoTela1 atualizarSituacaoOperacaoTela1 = new GuiAtualizarSituacaoOperacaoTela1(usuarioLogado);
            }
        });

        buscarOperacaoPorSituacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiBuscarOperacoesPorSituacao buscarOperacoesPorSituacao = new GuiBuscarOperacoesPorSituacao(usuarioLogado);
            }
        });
    }
}
