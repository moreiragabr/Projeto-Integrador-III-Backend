package zad_inventory.view.gui.guiAdmin.guiProdutos.guiProdutosOpcoes;

import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiBuscarProdutosPorCategoriaTela2 extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JTable tableListaProdutos;
    private JPanel panelListaDeProdutos;

    public DefaultTableModel criarModelo(List<ProdutoEntity> produtos) {
        String[] colunas = {"ID", "Nome","Cor","Tamanho", "Estoque",};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (ProdutoEntity p : produtos) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNomeProduto(),
                    p.getCor(),
                    p.getTamanho(),
                    p.getQuantidade()
            });
        }

        return model;
    }

    public GuiBuscarProdutosPorCategoriaTela2(List<ProdutoEntity> produtos, UsuarioEntity usuarioLogado){

        setContentPane(panelListaDeProdutos);
        setTitle("Lista de produtos");
        setSize(760, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tableListaProdutos.setModel(criarModelo(produtos));

        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiBuscarProdutoPorCategoria telaBuscar = new GuiBuscarProdutoPorCategoria(usuarioLogado);
            }
        });
    }

}
