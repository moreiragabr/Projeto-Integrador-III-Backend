package zad_inventory.view.gui.guiAdmin;

import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.GuiLogin;
import zad_inventory.view.gui.guiAdmin.guiCategorias.GuiCategorias;
import zad_inventory.view.gui.guiAdmin.guiOperacoes.GuiOperacoes;
import zad_inventory.view.gui.guiAdmin.guiProdutos.GuiProdutos;
import zad_inventory.view.gui.guiAdmin.guiUsuarios.GuiUsuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAdmin extends JFrame{
    private JLabel labelTitulo;
    private JPanel panelTelaAdmin;
    private JPanel panelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JButton gerenciarProdutosButton;
    private JButton gerenciarCategoriasButton;
    private JButton gerenciarVendasButton;
    private JButton gerenciarUsuariosButton;
    private JButton sairButton;
    private JPanel panelBotaosair;
    private JLabel admLabel;

    private final UsuarioEntity usuarioLogado;

    public GuiAdmin(UsuarioEntity usuarioLogado){
        this.usuarioLogado = usuarioLogado;
        admLabel.setText("ADM: "+usuarioLogado.getNome());

        setContentPane(panelTelaAdmin);
        setTitle("Tela Admin");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        gerenciarProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiProdutos telaProdutos = new GuiProdutos(usuarioLogado);
                dispose();
            }
        });

        gerenciarCategoriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiCategorias telaCategorias = new GuiCategorias(usuarioLogado);
                dispose();
            }
        });

        gerenciarVendasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiOperacoes telaOperacoes = new GuiOperacoes(usuarioLogado);
                dispose();
            }
        });

        gerenciarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiUsuarios telaUsuarios = new GuiUsuarios(usuarioLogado);
                dispose();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiLogin telaLogin = new GuiLogin();
                dispose();
            }
        });

    }
}
