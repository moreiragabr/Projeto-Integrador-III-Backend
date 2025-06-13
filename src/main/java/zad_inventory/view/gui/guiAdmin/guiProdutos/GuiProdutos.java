package zad_inventory.view.gui.guiAdmin.guiProdutos;

import zad_inventory.controller.CategoriaController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.GuiAdmin;
import zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiProdutos extends JFrame {
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JButton cadastrarNovoProdutoButton;
    private JButton listarTodosOsProdutosButton;
    private JButton buscarProdutoPorIDButton;
    private JButton modificarProdutoButton;
    private JPanel panelBotaosair;
    private JButton voltarButton;
    private JPanel panelTelaProdutos;
    private JButton removerProdutoButton;
    private JButton buscarProdutoPorNomeButton;
    private JButton buscarProdutoPorCategoriaButton;
    private JButton adicionarEstoqueButton;

    private final ProdutoController controller;
    private final CategoriaController categoriaController;

    public GuiProdutos(UsuarioEntity usuarioLogado) {
        this.controller = new ProdutoController(usuarioLogado);
        this.categoriaController = new CategoriaController();

        setContentPane(panelTelaProdutos);
        setTitle("Gerenciamento produtos");
        setSize(700, 550);
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

        cadastrarNovoProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var categorias = categoriaController.listarCategorias();
                if (categorias.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Cadastre categorias antes de cadastrar produtos.");
                } else {
                    dispose();
                    GuiProdutosCadastro telaCadastroProduto = new GuiProdutosCadastro(usuarioLogado);
                }
            }
        });

        listarTodosOsProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiProdutosLista telaListaProdutos = new GuiProdutosLista(usuarioLogado);
            }
        });

        buscarProdutoPorIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiBuscarProdutoPorId telaBuscarProdutoId = new GuiBuscarProdutoPorId(usuarioLogado);
            }
        });

        modificarProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiModificarProdutoTela1 telaModificarProduto = new GuiModificarProdutoTela1(usuarioLogado);
            }
        });

        removerProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiRemoverProduto telaRemoverProduto = new GuiRemoverProduto(usuarioLogado);
            }
        });

        buscarProdutoPorNomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiBuscarProdutoPorNome telaBuscarProdutoNome = new GuiBuscarProdutoPorNome(usuarioLogado);
            }
        });

        buscarProdutoPorCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiBuscarProdutoPorCategoria telaBuscarProdutoCategoria = new GuiBuscarProdutoPorCategoria(usuarioLogado);
            }
        });

        adicionarEstoqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiModificarEstoqueTela1 telaModificarEstoque = new GuiModificarEstoqueTela1(usuarioLogado);
            }
        });
    }
}
