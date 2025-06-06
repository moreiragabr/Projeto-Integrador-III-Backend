package zad_inventory.view.gui.guiAdmin.guiOperacoes.guiOperacoesOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.enums.Situacao;
import zad_inventory.model.OperacaoEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiOperacoes.GuiOperacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAtualizarSituacaoOperacaoTela2 extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JLabel labelProdutoSelecionado;
    private JPanel panelOpcoes;
    private JPanel panelInserirDados;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JPanel panelDados;
    private JPanel panelTextField;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelModificarSituacaodeVenda;
    private JComboBox comboBoxSituacoes;
    private JButton atualizarButton;
    private JLabel situacaoAtualJLabel;

    public GuiAtualizarSituacaoOperacaoTela2(UsuarioEntity usuarioLogado, OperacaoEntity operacao){

        OperacaoController controller = new OperacaoController(usuarioLogado);

        setContentPane(panelModificarSituacaodeVenda);
        setTitle("Modificar situação de operação");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        labelProdutoSelecionado.setText("ID da operação selecionada:  " + operacao.getId());
        situacaoAtualJLabel.setText("Situação atual: "+operacao.getSituacao());
        comboBoxSituacoes.addItem("REALIZADA");
        comboBoxSituacoes.addItem("CANCELADA");
        comboBoxSituacoes.addItem("SEPARADA");

        setVisible(true);

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String situacaoStr = (String) comboBoxSituacoes.getSelectedItem();
                Situacao novaSituacao = Situacao.valueOf(situacaoStr);

                if (novaSituacao != null) {
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Situação atual: " + operacao.getSituacao() +"\nSituação nova: "+novaSituacao+ "\nDeseja realmente mudar a situação desta operação?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (resposta == JOptionPane.YES_OPTION) {
                        controller.atualizarSituacao(operacao.getId(), novaSituacao);
                        JOptionPane.showMessageDialog(null, "Situação atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Modificação cancelada!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Venda não encontrada!");
                }
                dispose();
                GuiAtualizarSituacaoOperacaoTela1 telaModificacao = new GuiAtualizarSituacaoOperacaoTela1(usuarioLogado);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiAtualizarSituacaoOperacaoTela1 telaModificar = new GuiAtualizarSituacaoOperacaoTela1(usuarioLogado);
            }
        });

    }
}
