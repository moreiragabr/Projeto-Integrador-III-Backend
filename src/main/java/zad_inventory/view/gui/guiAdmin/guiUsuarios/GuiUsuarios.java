package zad_inventory.view.gui.guiAdmin.guiUsuarios;

import zad_inventory.config.DBConnection;
import zad_inventory.controller.UsuarioController;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;
import zad_inventory.view.gui.guiAdmin.GuiAdmin;
import zad_inventory.view.gui.guiAdmin.guiUsuarios.guiUsuariosOpcoes.GuiCadastroUsuarios;
import zad_inventory.view.gui.guiAdmin.guiUsuarios.guiUsuariosOpcoes.GuiRankingUsuarios;
import zad_inventory.view.gui.guiAdmin.guiUsuarios.guiUsuariosOpcoes.GuiUsuariosLista;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiUsuarios extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JButton cadastrarNovoUsuarioButton;
    private JButton listarTodosOsUsuariosButton;
    private JPanel panelBotaosair;
    private JButton voltarButton;
    private JButton rankingUsuarioButton;
    private JPanel panelUsuarios;

    public GuiUsuarios(UsuarioEntity usuarioLogado){

        UsuarioController controller = new UsuarioController();

        setContentPane(panelUsuarios);
        setTitle("Gerenciamento de usuários");
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

        cadastrarNovoUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiCadastroUsuarios telaCadastro = new GuiCadastroUsuarios(usuarioLogado);
            }
        });

        listarTodosOsUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiUsuariosLista telaLista = new GuiUsuariosLista(usuarioLogado);
            }
        });

        rankingUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiRankingUsuarios telaRanking = new GuiRankingUsuarios(usuarioLogado);
            }
        });
    }
}
