package zad_inventory.view.gui.guiAdmin.guiCategorias.guiCategoriasOpcoes;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.CategoriaEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiCategorias.GuiCategorias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiRemoverCategoria extends JFrame{
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
    private JComboBox comboBoxCategoriaProduto;
    private JPanel panelLabel;
    private JLabel labelTexto5;
    private JPanel panelRemoverCategoria;
    private JButton removerButton;

    public GuiRemoverCategoria(UsuarioEntity usuarioLogado){
        CategoriaController controller = new CategoriaController();

        setContentPane(panelRemoverCategoria);
        setTitle("Remover categorias");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<String> categorias = controller.buscarTodosNomes();

        for (String categoria : categorias) {
            comboBoxCategoriaProduto.addItem(categoria);
        }

        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiCategorias telaCategorias = new GuiCategorias(usuarioLogado);
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = controller.buscarIdPorNome((String) comboBoxCategoriaProduto.getSelectedItem());
                CategoriaEntity categoriaSelecionada = controller.buscarCategoriaPorId(id);

                if (id != null) {
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Categoria selecionada: " + categoriaSelecionada.getNome() + "\nDeseja realmente apagar esta categoria?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (resposta == JOptionPane.YES_OPTION) {
                        controller.removerCategoria(id);
                        JOptionPane.showMessageDialog(null, "Categoria apagada com sucesso!");

                        List<String> categorias = controller.buscarTodosNomes();
                        comboBoxCategoriaProduto.removeAllItems();
                        for (String categoria : categorias) {
                            comboBoxCategoriaProduto.addItem(categoria);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
                }

            }
        });
    }
}
