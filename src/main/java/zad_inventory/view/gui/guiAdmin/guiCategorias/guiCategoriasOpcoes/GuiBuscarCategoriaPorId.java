package zad_inventory.view.gui.guiAdmin.guiCategorias.guiCategoriasOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.CategoriaEntity;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiCategorias.GuiCategorias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiBuscarCategoriaPorId extends JFrame{
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
    private JTextField textFieldIdCategoria;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelBuscarCategoriaPorId;
    private JButton buscarButton;

    public GuiBuscarCategoriaPorId(UsuarioEntity usuarioLogado){

        CategoriaController controller = new CategoriaController();

        setContentPane(panelBuscarCategoriaPorId);
        setTitle("Buscar categorias");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiCategorias telaCategorias = new GuiCategorias(usuarioLogado);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(textFieldIdCategoria.getText());
                CategoriaEntity categoria = controller.buscarCategoriaPorId(id);

                if (categoria != null) {
                    JOptionPane.showMessageDialog(null,
                            "Categoria: "+categoria.getNome()+"\nDescrição: "+categoria.getDescricao());
                }else{
                    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                }

            }
        });
    }
}
