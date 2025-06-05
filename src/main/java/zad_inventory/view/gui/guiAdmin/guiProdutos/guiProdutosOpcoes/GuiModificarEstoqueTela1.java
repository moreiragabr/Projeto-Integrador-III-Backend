package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.controller.ProdutoController;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiProdutos.GuiProdutos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiModificarEstoqueTela1 extends JFrame{
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
    private JPanel panelAdicionarEstoque;
    private JTextField textFieldIdProduto;
    private JButton selecionarButton;

    public GuiModificarEstoqueTela1(UsuarioEntity usuarioLogado){

        setContentPane(panelAdicionarEstoque);
        setTitle("Lista de produtos");
        setSize(900, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        ProdutoController controller = new ProdutoController(usuarioLogado);

        selecionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Long id = Long.parseLong(textFieldIdProduto.getText());
                ProdutoEntity produtoSelecionado = new ProdutoEntity();
                produtoSelecionado = controller.buscarProdutoPorId(id);
                if(produtoSelecionado!=null){
                    dispose();
                    GuiModificarEstoqueTela2 telaModificarEstoque = new GuiModificarEstoqueTela2(produtoSelecionado, usuarioLogado);
                }else{
                    JOptionPane.showMessageDialog(null, "Item não encontrado!");
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
