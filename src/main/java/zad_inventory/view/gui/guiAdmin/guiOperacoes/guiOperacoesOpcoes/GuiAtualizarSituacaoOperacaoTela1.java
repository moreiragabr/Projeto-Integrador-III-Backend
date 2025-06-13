package zad_inventory.view.gui.guiAdmin.guiOperacoes.guiOperacoesOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.model.entity.OperacaoEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiOperacoes.GuiOperacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAtualizarSituacaoOperacaoTela1 extends JFrame{
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
    private JTextField textFieldIDdeVenda;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelModificarSituacao;
    private JButton selecionarButton;

    public GuiAtualizarSituacaoOperacaoTela1(UsuarioEntity usuarioLogado){

        OperacaoController controller = new OperacaoController(usuarioLogado);

        setContentPane(panelModificarSituacao);
        setTitle("Modificar situação de operação");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiOperacoes telaOperacoes = new GuiOperacoes(usuarioLogado);
            }
        });

        selecionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(textFieldIDdeVenda.getText());
                OperacaoEntity operacao = controller.buscarOperacaoPorId(id);

                if (operacao != null) {
                    dispose();
                    GuiAtualizarSituacaoOperacaoTela2 telaModificar = new GuiAtualizarSituacaoOperacaoTela2(usuarioLogado, operacao);
                }else{
                    JOptionPane.showMessageDialog(null, "Operação não encontrada!");
                }

            }
        });
    }
}
