package zad_inventory.view.gui.guiAdmin.guiOperacoes.guiOperacoesOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiOperacoes.GuiOperacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRealizarVenda extends JFrame {
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
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelRealizarVenda;
    private JButton venderButton;
    private JSpinner spinnerQuantidade;
    private JTextField textFieldIdProduto;

    public GuiRealizarVenda(UsuarioEntity usuarioLogado) {

        OperacaoController controller = new OperacaoController(usuarioLogado);
        ProdutoController produtoController = new ProdutoController(usuarioLogado);

        setContentPane(panelRealizarVenda);
        setTitle("Realizar nova venda");
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

        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(textFieldIdProduto.getText());
                int quantidade = (Integer) spinnerQuantidade.getValue();
                spinnerQuantidade.setValue(0);
                ProdutoEntity produto = produtoController.buscarProdutoPorId(id);

                if (produto != null) {
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Produto selecionado: " + produto.getNomeProduto() + "\nQuantidade: " + quantidade + "\nConfirmar venda?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (resposta == JOptionPane.YES_OPTION) {
                        controller.registrarOperacao(id, quantidade);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                }
            }
        });
    }
}
