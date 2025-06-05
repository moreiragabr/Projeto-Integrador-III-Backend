package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiModificarProdutoTela2 extends JFrame{
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
    private JTextField textFieldNomeProduto;
    private JTextField textFieldCorProduto;
    private JTextField textFieldTamanhoProduto;
    private JComboBox comboBoxCategoriaProduto;
    private JSpinner spinnerQuantidadeProduto;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JLabel labelTexto2;
    private JLabel labelTexto3;
    private JLabel labelTexto4;
    private JLabel labelTexto5;
    private JLabel labelProdutoSelecionado;
    private JPanel panelModificarProduto;
    private JButton modificarButton;

    public GuiModificarProdutoTela2(UsuarioEntity usuarioLogado, ProdutoEntity produto){

        ProdutoController controller = new ProdutoController(usuarioLogado);
        CategoriaController categoriaController = new CategoriaController();

        setContentPane(panelModificarProduto);
        setTitle("Modificar produto");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<String> categorias = categoriaController.buscarTodosNomes();

        for (String categoria : categorias) {
            comboBoxCategoriaProduto.addItem(categoria);
        }

        labelProdutoSelecionado.setText("Produto selecionado: "+produto.getNomeProduto());

        setVisible(true);

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = textFieldNomeProduto.getText();
                String cor = textFieldCorProduto.getText();
                String tamanho = textFieldTamanhoProduto.getText();
                int quantidade = (Integer) spinnerQuantidadeProduto.getValue();
                Long categoriaId = categoriaController.buscarIdPorNome((String) comboBoxCategoriaProduto.getSelectedItem());

                controller.atualizarProduto(produto.getId(), nome, cor, tamanho, quantidade, categoriaId);

                ProdutoEntity produtoAtualizado = controller.buscarProdutoPorId(produto.getId());
                JOptionPane.showMessageDialog(null,
                        "Produto atualizado com sucesso!" +
                                "\nNome: "+produtoAtualizado.getNomeProduto()+ "\nCor: "+produtoAtualizado.getCor()+"\nTamanho: "+produtoAtualizado.getTamanho()+"\nEstoque: "+produtoAtualizado.getQuantidade()+"\nCategoria: "+produtoAtualizado.getNomeCategoria());
                dispose();
                GuiModificarProdutoTela1 telaModificarProduto = new GuiModificarProdutoTela1(usuarioLogado);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiModificarProdutoTela1 telaModificarProduto = new GuiModificarProdutoTela1(usuarioLogado);
            }
        });
    }
}
