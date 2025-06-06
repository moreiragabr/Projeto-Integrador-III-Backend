package zad_inventory.view.gui.guiAdmin.guiOperacoes.guiOperacoesOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.enums.Situacao;
import zad_inventory.model.OperacaoEntity;
import zad_inventory.model.ProdutoEntity;
import zad_inventory.model.UsuarioEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GuiBuscarOperacoesPorSituacaoTela2 extends JFrame{
    private JPanel panelListaVendas;
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JTable tableListaProdutos;
    private JLabel situacaoEscolhitaJLabel;

    public DefaultTableModel criarModelo(List<OperacaoEntity> operacoes) {
        String[] colunas = {"ID", "Itens","Quantidade", "Situação","Usuário", "Data",};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (OperacaoEntity o : operacoes) {
            ProdutoEntity produto = o.getProduto();
            UsuarioEntity usuario = o.getUsuario();
            model.addRow(new Object[]{
                    o.getId(),
                    produto.getNomeProduto(),
                    o.getQuantidade(),
                    o.getSituacao(),
                    usuario.getNome(),
                    formatoData.format(o.getData())
            });
        }
        return model;
    }

    public GuiBuscarOperacoesPorSituacaoTela2(UsuarioEntity usuarioLogado, String situacao){

        setContentPane(panelListaVendas);
        setTitle("Modificar situação de operação");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        OperacaoController controller = new OperacaoController(usuarioLogado);

        Situacao novaSituacao = Situacao.valueOf(situacao);
        situacaoEscolhitaJLabel.setText("Situação escolhida: "+novaSituacao);

        tableListaProdutos.setModel(criarModelo(controller.filtrarPorSituacao(novaSituacao)));


        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiBuscarOperacoesPorSituacao telaBuscar = new GuiBuscarOperacoesPorSituacao(usuarioLogado);
            }
        });
    }
}
