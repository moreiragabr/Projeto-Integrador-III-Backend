package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.controller.ProdutoController;
import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiProdutos.GuiProdutos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiBuscarProdutoPorId extends JFrame{
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
    private JTextField textFieldIdProduto;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelBuscarProdutoPorId;
    private JButton buscarButton;

    public GuiBuscarProdutoPorId(UsuarioEntity usuarioLogado){

        ProdutoController controller = new ProdutoController(usuarioLogado);

        setContentPane(panelBuscarProdutoPorId);
        setTitle("Buscar produto por ID");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Long id = Long.parseLong(textFieldIdProduto.getText());
                ProdutoEntity produto = controller.buscarProdutoPorId(id);

                if (produto != null) {
                    JOptionPane.showMessageDialog(null,
                            "Produto: "+produto.getNomeProduto()+"\nEstoque: "+produto.getQuantidade()+"\nCategoria: "+produto.getNomeCategoria()+"\nID Categoria: "+produto.getCategoriaId());
                }else{
                    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiProdutos telaProdutos = new GuiProdutos(usuarioLogado);
            }
        });
    }
}
