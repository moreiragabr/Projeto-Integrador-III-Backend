package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiProdutos.GuiProdutos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiBuscarProdutoPorCategoria extends JFrame{
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
    private JPanel panelBuscarProdutoPorCategoria;
    private JComboBox comboBoxCategoria;
    private JButton buscarButton;


    public GuiBuscarProdutoPorCategoria(UsuarioEntity usuarioLogado){

        ProdutoController controller = new ProdutoController(usuarioLogado);
        CategoriaController categoriaController = new CategoriaController();

        setContentPane(panelBuscarProdutoPorCategoria);
        setTitle("Modificar produtos");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<String> categorias = categoriaController.buscarTodosNomes();

        for (String categoria : categorias) {
            comboBoxCategoria.addItem(categoria);
        }

        setVisible(true);


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long categoriaId = categoriaController.buscarIdPorNome((String) comboBoxCategoria.getSelectedItem());
                if(categoriaId!=null){
                    List<ProdutoEntity> produtos = controller.buscarProdutosPorCategoria(categoriaId);
                    dispose();
                    GuiBuscarProdutosPorCategoriaTela2 tela2 = new GuiBuscarProdutosPorCategoriaTela2(produtos, usuarioLogado);
                }else{
                    JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
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
