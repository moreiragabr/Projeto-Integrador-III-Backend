package zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.controller.ProdutoController;
import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;
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
    private JButton venderButton;
    private JPanel panelDados;
    private JPanel panelTextField;
    private JTextField textFieldIdProduto;
    private JSpinner spinnerQuantidade;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelRealizarVendaFuncionario;

    public GuiRegistrarVendaFuncionario(UsuarioEntity usuarioLogado){

        OperacaoController controller = new OperacaoController(usuarioLogado);
        ProdutoController produtoController = new ProdutoController(usuarioLogado);

        setContentPane(panelRealizarVendaFuncionario);
        setTitle("Realizar venda");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


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

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiFuncionario telaFuncionario = new GuiFuncionario(usuarioLogado);
                dispose();
            }
        });
    }
}
