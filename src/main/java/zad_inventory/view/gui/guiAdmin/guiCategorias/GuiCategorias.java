package zad_inventory.view.gui.guiAdmin.guiCategorias;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.GuiAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCategorias extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JButton cadastrarNovaCategoriaButton;
    private JButton listarTodasAsCategoriasButton;
    private JButton buscarCategoriaPorIDButton;
    private JButton modificarCategoriaButton;
    private JPanel panelBotaosair;
    private JButton voltarButton;
    private JButton removerCategoriaButton;
    private JPanel panelCategorias;

    private final CategoriaController controller;

    public GuiCategorias(UsuarioEntity usuarioLogado){
        this.controller = new CategoriaController();

        setContentPane(panelCategorias);
        setTitle("Gerenciamento categorias");
        setSize(700,550);
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
    }
}
