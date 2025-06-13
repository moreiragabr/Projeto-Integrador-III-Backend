package zad_inventory.view.gui.guiAdmin.guiCategorias.guiCategoriasOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.entity.CategoriaEntity;
import zad_inventory.model.entity.UsuarioEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiModificarCategoriaTela2 extends JFrame{
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
    private JPanel panelModificarCategoria;
    private JButton modificarButton;
    private JLabel categoriaSelecionadaJLabel;

    public GuiModificarCategoriaTela2(CategoriaEntity categoriaSelecionada, UsuarioEntity usuarioLogado){

        CategoriaController controller = new CategoriaController();

        setContentPane(panelModificarCategoria);
        setTitle("Modificar categorias");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        categoriaSelecionadaJLabel.setText("Categoria selecionada: "+categoriaSelecionada.getNome());

        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiModificarCategoriaTela1 telaModificar = new GuiModificarCategoriaTela1(usuarioLogado);
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNomeCategoria.getText();
                String descricao = textFieldDescricaoCategoria.getText();

                if (nome != null && descricao != null) {
                    controller.atualizarCategoria(categoriaSelecionada.getId(), nome, descricao);

                    CategoriaEntity categoriaAtualizada = controller.buscarCategoriaPorId(categoriaSelecionada.getId());

                    JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!" +
                            "\nNome: "+categoriaAtualizada.getNome()+"\nDescrição: "+categoriaAtualizada.getDescricao());

                    GuiModificarCategoriaTela1 telaModificar = new GuiModificarCategoriaTela1(usuarioLogado);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Dados inválidos ou nulos!");
                }

            }
        });
    }
}
