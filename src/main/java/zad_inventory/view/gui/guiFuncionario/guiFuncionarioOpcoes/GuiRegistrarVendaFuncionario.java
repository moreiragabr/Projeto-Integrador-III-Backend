package zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes;

import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiFuncionario.GuiFuncionario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRegistrarVendaFuncionario extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelInserirDados;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JButton buscarButton;
    private JPanel panelDados;
    private JPanel panelTextField;
    private JComboBox comboBoxProdutoSelecionado;
    private JSpinner spinnerQuantidade;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelRealizarVendaFuncionario;

    public GuiRegistrarVendaFuncionario(UsuarioEntity usuarioLogado){

        setContentPane(panelRealizarVendaFuncionario);
        setTitle("Realizar venda");
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
