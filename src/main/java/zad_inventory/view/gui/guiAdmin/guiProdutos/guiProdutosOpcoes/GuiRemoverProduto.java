package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.controller.ProdutoController;
import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiProdutos.GuiProdutos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRemoverProduto extends JFrame {
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
    private JLabel labelTexto5;
    private JPanel panelRemoverProduto;
    private JTextField textFieldIdProduto;
    private JButton removerButton;

    public GuiRemoverProduto(UsuarioEntity usuarioLogado) {

        ProdutoController controller = new ProdutoController(usuarioLogado);

        setContentPane(panelRemoverProduto);
        setTitle("Lista de produtos");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Long id = Long.parseLong(textFieldIdProduto.getText());
                ProdutoEntity produto = controller.buscarProdutoPorId(id);

                if (produto != null) {
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Produto selecionado: " + produto.getNomeProduto() + "\nDeseja realmente apagar este produto?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (resposta == JOptionPane.YES_OPTION) {
                        controller.removerProduto(produto.getId());
                        JOptionPane.showMessageDialog(null, "Produto apagado com sucesso!");
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
                dispose();
                GuiProdutos telaProdutos = new GuiProdutos(usuarioLogado);
            }
        });
    }
}
