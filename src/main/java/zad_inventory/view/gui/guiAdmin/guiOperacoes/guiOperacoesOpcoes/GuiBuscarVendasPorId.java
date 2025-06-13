package zad_inventory.view.gui.guiAdmin.guiOperacoes.guiOperacoesOpcoes;

import zad_inventory.controller.OperacaoController;
import zad_inventory.model.entity.OperacaoEntity;
import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiOperacoes.GuiOperacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class GuiBuscarVendasPorId extends JFrame{
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
    private JTextField textFieldNomeProduto;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JPanel panelBuscarVendasPorId;
    private JButton buscarButton;

    public GuiBuscarVendasPorId(UsuarioEntity usuarioLogado){
        OperacaoController controller = new OperacaoController(usuarioLogado);
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        setContentPane(panelBuscarVendasPorId);
        setTitle("Buscar operação por id");
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

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(textFieldNomeProduto.getText());
                OperacaoEntity operacao = controller.buscarOperacaoPorId(id);
                UsuarioEntity usuario = operacao.getUsuario();
                ProdutoEntity produto = operacao.getProduto();

                if (operacao != null) {
                    JOptionPane.showMessageDialog(null,
                            "ID da Operação: "+id+"\nProduto vendido: "+produto.getNomeProduto()+"\nQuantidade vendida: "+operacao.getQuantidade()+"\nUsuário responsável: "+usuario.getNome()+"\nHorário: "+formatoData.format(operacao.getData()));
                }else{
                    JOptionPane.showMessageDialog(null, "Operação não encontrada!");
                }

            }
        });
    }
}
