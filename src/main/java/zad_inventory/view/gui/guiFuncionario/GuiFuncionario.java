package zad_inventory.view.gui.guiFuncionario;

import zad_inventory.controller.OperacaoController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.GuiLogin;
import zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes.GuiListarProdutosFuncionario;
import zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes.GuiListarVendasFuncionario;
import zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes.GuiRegistrarVendaFuncionario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GuiFuncionario extends JFrame {
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JButton listaDeProdutosButton;
    private JButton novaVendaButton;
    private JButton listaDeVendasButton;
    private JPanel panelBotaosair;
    private JButton sairButton;
    private JLabel labelUsuarioLogado;
    private JPanel panelTelaUsuario;

    private final UsuarioEntity usuarioLogado;
    private final OperacaoController operacaoController;
    private final ProdutoController produtoController;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public GuiFuncionario(UsuarioEntity usuarioLogado){
        this.usuarioLogado = usuarioLogado;
        this.operacaoController = new OperacaoController(usuarioLogado);
        this.produtoController = new ProdutoController(usuarioLogado);

        setContentPane(panelTelaUsuario);
        setTitle("Menu usuário");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        listaDeProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiListarProdutosFuncionario telaListaProdutosFuncionario = new GuiListarProdutosFuncionario(usuarioLogado);
                dispose();
            }
        });

        novaVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiRegistrarVendaFuncionario telaRegistrarVendaFuncionario = new GuiRegistrarVendaFuncionario(usuarioLogado);
                dispose();
            }
        });

        listaDeVendasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiListarVendasFuncionario telaListarVendasFuncionario = new GuiListarVendasFuncionario(usuarioLogado);
                dispose();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiLogin telaLogin = new GuiLogin();
                dispose();
            }
        });
    }
}
