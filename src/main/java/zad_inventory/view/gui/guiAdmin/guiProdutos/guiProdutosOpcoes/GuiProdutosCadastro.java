package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.repository.CategoriaRepository;
import zad_inventory.view.gui.guiAdmin.guiProdutos.GuiProdutos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiProdutosCadastro extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelInserirDados;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JPanel panelProdutosCadastro;
    private JPanel panelDados;
    private JPanel panelTextField;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JLabel labelTexto2;
    private JLabel labelTexto3;
    private JLabel labelTexto4;
    private JLabel labelTexto5;
    private JTextField textFieldNomeProduto;
    private JTextField textFieldCorProduto;
    private JTextField textFieldTamanhoProduto;
    private JComboBox comboBoxCategoriaProduto;
    private JSpinner spinnerQuantidadeProduto;
    private JButton cadastrarButton;

    private final ProdutoController controller;
    private final CategoriaController categoriaController;

    public GuiProdutosCadastro(UsuarioEntity usuarioLogado){

        this.controller = new ProdutoController(usuarioLogado);
        this.categoriaController = new CategoriaController();

        setContentPane(panelProdutosCadastro);
        setTitle("Cadastro de produtos");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<String> categorias = categoriaController.buscarTodosNomes();

        for (String categoria : categorias) {
            comboBoxCategoriaProduto.addItem(categoria);
        }

        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiProdutos telaProdutos = new GuiProdutos(usuarioLogado);
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNomeProduto.getText();
                String cor = textFieldCorProduto.getText();
                String tamanho = textFieldTamanhoProduto.getText();
                int quantidade = (Integer) spinnerQuantidadeProduto.getValue();
                Long categoriaId = categoriaController.buscarIdPorNome((String) comboBoxCategoriaProduto.getSelectedItem());

                ProdutoEntity produtoSalvo = controller.cadastrarProduto(nome, cor, tamanho, quantidade, categoriaId);
                if(produtoSalvo==null){
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar item!");
                    dispose();
                }
                JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");
                dispose();
                GuiProdutos telaProduto = new GuiProdutos(usuarioLogado);
            }
        });
    }
}
