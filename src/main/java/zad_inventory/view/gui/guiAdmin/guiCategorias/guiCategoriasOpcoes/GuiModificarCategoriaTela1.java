package zad_inventory.view.gui.guiAdmin.guiCategorias.guiCategoriasOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.entity.CategoriaEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiCategorias.GuiCategorias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiModificarCategoriaTela1 extends JFrame{
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
    private JPanel panelModificarCategoria;
    private JButton buscarButton;

    public GuiModificarCategoriaTela1(UsuarioEntity usuarioLogado){

        CategoriaController controller = new CategoriaController();

        setContentPane(panelModificarCategoria);
        setTitle("Modificar categorias");
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
                if(id!=null){
                    CategoriaEntity categoriaSelecionada = controller.buscarCategoriaPorId(id);
                    dispose();
                    GuiModificarCategoriaTela2 telaMoficar = new GuiModificarCategoriaTela2(categoriaSelecionada, usuarioLogado);
                }else{
                    JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
                }
            }
        });


    }
}
