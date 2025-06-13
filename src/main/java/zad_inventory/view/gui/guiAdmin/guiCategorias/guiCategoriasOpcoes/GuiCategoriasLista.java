package zad_inventory.view.gui.guiAdmin.guiCategorias.guiCategoriasOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.entity.CategoriaEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiCategorias.GuiCategorias;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiCategoriasLista extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JTable tableListaCategorias;
    private JPanel panelListaCategorias;

    public DefaultTableModel criarModelo(List<CategoriaEntity> categorias) {
        String[] colunas = {"ID", "Nome","Descrição"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (CategoriaEntity c : categorias) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getDescricao()
            });
        }

        return model;
    }

    public GuiCategoriasLista(UsuarioEntity usuarioLogado){
        CategoriaController controller = new CategoriaController();

        setContentPane(panelListaCategorias);
        setTitle("Lista de categorias");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<CategoriaEntity> categorias = controller.listarCategorias();
        tableListaCategorias.setModel(criarModelo(categorias));

        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiCategorias telaCategorias = new GuiCategorias(usuarioLogado);
            }
        });
    }
}
