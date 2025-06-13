package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.controller.ProdutoController;
import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiModificarEstoqueTela2 extends JFrame{
    private JPanel panelAdicionarEstoque;
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
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JLabel labelProdutoSelecionado;
    private JSpinner spinnerQuantidade;
    private JButton atualizarButton;
    private JLabel labelEstoqueAtual;

    public GuiModificarEstoqueTela2(ProdutoEntity produtoSelecionado, UsuarioEntity usuarioLogado){

        ProdutoController controller = new ProdutoController(usuarioLogado);

        setContentPane(panelAdicionarEstoque);
        setTitle("Modificar estoque");
        setSize(900, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        labelProdutoSelecionado.setText("Produto selecionado: "+produtoSelecionado.getNomeProduto());
        labelEstoqueAtual.setText("Estoque atual: "+produtoSelecionado.getQuantidade());

        setVisible(true);

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = produtoSelecionado.getNomeProduto();
                String cor = produtoSelecionado.getCor();
                String tamanho = produtoSelecionado.getTamanho();
                int quantidade = (Integer) spinnerQuantidade.getValue();
                Long categoriaId = produtoSelecionado.getCategoriaId();

                controller.atualizarProduto(produtoSelecionado.getId(), nome, cor, tamanho, quantidade, categoriaId);

                ProdutoEntity produtoAtualizado = controller.buscarProdutoPorId(produtoSelecionado.getId());
                JOptionPane.showMessageDialog(null,
                        "Estoque atualizado com sucesso!" +
                                "\nNome: "+produtoAtualizado.getNomeProduto()+ "\nNovo Estoque: "+produtoAtualizado.getQuantidade()+"\nCategoria: "+produtoAtualizado.getNomeCategoria());
                dispose();
                GuiModificarEstoqueTela1 tela1 = new GuiModificarEstoqueTela1(usuarioLogado);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiModificarEstoqueTela1 tela1 = new GuiModificarEstoqueTela1(usuarioLogado);
            }
        });
    }

}
