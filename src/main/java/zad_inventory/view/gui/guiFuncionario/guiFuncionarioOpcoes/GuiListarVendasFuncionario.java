package zad_inventory.view.gui.guiFuncionario.guiFuncionarioOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.model.entity.OperacaoEntity;
import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiFuncionario.GuiFuncionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GuiListarVendasFuncionario extends JFrame{
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelTituloApp;
    private JLabel labelTituloApp;
    private JPanel panelOpcoes;
    private JPanel panelBotoes;
    private JPanel panelBotao;
    private JButton voltarButton;
    private JTable tableListaProdutos;
    private JPanel panelListarVendasFuncionario;



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


    public GuiListarVendasFuncionario(UsuarioEntity usuarioLogado){

        OperacaoController controller = new OperacaoController(usuarioLogado);

        setContentPane(panelListarVendasFuncionario);
        setTitle("Tela Admin");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<OperacaoEntity> operacoes = controller.listarOperacoes();
        tableListaProdutos.setModel(criarModelo(operacoes));

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
