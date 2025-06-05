package zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes;

import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiFuncionario.GuiFuncionario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiListarProdutosFuncionario extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JPanel panelLista;
    private JTable tableListaProdutos;
    private JPanel panelListaProdutosFuncionario;

    public GuiListarProdutosFuncionario(UsuarioEntity usuarioLogado){

        setContentPane(panelListaProdutosFuncionario);
        setTitle("Lista de produtos");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiFuncionario telaFuncionario = new GuiFuncionario(usuarioLogado);
                dispose();
            }
        });
    }
}
