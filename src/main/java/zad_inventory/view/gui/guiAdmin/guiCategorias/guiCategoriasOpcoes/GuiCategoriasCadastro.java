package zad_inventory.view.gui.guiAdmin.guiCategorias.guiCategoriasOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiCategorias.GuiCategorias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCategoriasCadastro extends JFrame {
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
    private JTextField textFieldNomeCategoria;
    private JTextField textFieldDescricaoCategoria;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JLabel labelTexto2;
    private JPanel panelCategoriaCadastro;
    private JButton cadastrarButton;

    public GuiCategoriasCadastro(UsuarioEntity usuarioLogado) {

        CategoriaController controller = new CategoriaController();

        setContentPane(panelCategoriaCadastro);
        setTitle("Cadastro de categorias");
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

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNomeCategoria.getText();
                String descricao = textFieldDescricaoCategoria.getText();

                if (nome != null && descricao != null) {
                    controller.cadastrarCategoria(nome, descricao);
                    JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
                    GuiCategorias telaCategoria = new GuiCategorias(usuarioLogado);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Dados inválidos ou nulos!");
                }
            }
        });
    }
}
