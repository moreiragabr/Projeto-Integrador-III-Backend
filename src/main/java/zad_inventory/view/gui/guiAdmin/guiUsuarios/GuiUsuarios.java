package zad_inventory.view.gui.guiAdmin.guiUsuarios;

import zad_inventory.config.DBConnection;
import zad_inventory.controller.UsuarioController;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;
import zad_inventory.view.gui.guiAdmin.GuiAdmin;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GuiUsuarios extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JButton cadastrarNovoUsuárioButton;
    private JButton listarTodosOsUsuáriosButton;
    private JPanel panelBotaosair;
    private JButton voltarButton;
    private JButton removerEstoqueButton;
    private JPanel panelUsuarios;

    private static UsuarioController controller;
    private static UsuarioEntity usuarioLogado;

    public GuiUsuarios(UsuarioEntity logado){
        usuarioLogado = logado;
        EntityManager em = DBConnection.getEntityManager();
        controller = new UsuarioController(new UsuarioService(new UsuarioRepository(em)));

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
    }
}
