package zad_inventory.view.gui.guiAdmin.guiUsuarios.guiUsuariosOpcoes;

import zad_inventory.controller.UsuarioController;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiUsuarios.GuiUsuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiRankingUsuarios extends JFrame{
    private JPanel panelListaDeUsuarios;
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JTable tableListaProdutos;

    public DefaultTableModel criarModelo(List<UsuarioEntity> usuarios) {
        String[] colunas = {"ID", "Nome","Email", "Tipo de usuário"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (UsuarioEntity u : usuarios) {
            model.addRow(new Object[]{
                    u.getId(),
                    u.getNome(),
                    u.getEmail(),
                    u.getTipoUsuario()
            });
        }

        return model;
    }

    public GuiRankingUsuarios(UsuarioEntity usuarioLogado){

        UsuarioController controller = new UsuarioController();

        setContentPane(panelListaDeUsuarios);
        setTitle("Ranking de usuários");
        setSize(700,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<UsuarioEntity> usuarios = controller.exibirRanking();
        tableListaProdutos.setModel(criarModelo(usuarios));

        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiUsuarios telaUsuarios = new GuiUsuarios(usuarioLogado);
            }
        });

    }
}
