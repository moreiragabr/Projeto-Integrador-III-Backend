package zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes;

import zad_inventory.controller.ProdutoController;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.gui.guiFuncionario.GuiFuncionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class GuiListarProdutosFuncionario extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JTable tableListaProdutos;
    private JPanel panelListaProdutosFuncionario;

    public DefaultTableModel criarModelo(List<ProdutoEntity> produtos) {
        String[] colunas = {"ID", "Nome","Cor", "Categoria","Tamanho", "Estoque",};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (ProdutoEntity p : produtos) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNomeProduto(),
                    p.getCor(),
                    p.getNomeCategoria(),
                    p.getTamanho(),
                    p.getQuantidade()
            });
        }

        return model;
    }


    public GuiListarProdutosFuncionario(UsuarioEntity usuarioLogado){

        ProdutoController controller = new ProdutoController(usuarioLogado);

        setContentPane(panelListaProdutosFuncionario);
        setTitle("Lista de produtos");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<ProdutoEntity> produtos = controller.listarTodosProdutos();
        tableListaProdutos.setModel(criarModelo(produtos));

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
