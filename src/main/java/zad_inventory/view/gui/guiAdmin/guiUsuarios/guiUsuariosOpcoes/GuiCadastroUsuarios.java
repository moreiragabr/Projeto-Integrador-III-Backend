package zad_inventory.view.gui.guiAdmin.guiUsuarios.guiUsuariosOpcoes;

import zad_inventory.controller.UsuarioController;
import zad_inventory.model.enums.TipoUsuario;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.view.gui.guiAdmin.guiUsuarios.GuiUsuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCadastroUsuarios extends JFrame {
    private JPanel panelProdutosCadastro;
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
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JPanel panelLabel;
    private JLabel labelTexto1;
    private JLabel labelTexto2;
    private JLabel labelTexto3;
    private JLabel labelTexto4;
    private JPasswordField passwordField;
    private JComboBox comboBoxTipoUsuario;
    private JButton cadastrarButton;

    public GuiCadastroUsuarios(UsuarioEntity usuarioLogado) {

        UsuarioController controller = new UsuarioController();

        setContentPane(panelProdutosCadastro);
        setTitle("Cadastro de usuários");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        comboBoxTipoUsuario.addItem("GERENTE");
        comboBoxTipoUsuario.addItem("FUNCIONARIO");

        setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GuiUsuarios telaUsuarios = new GuiUsuarios(usuarioLogado);
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoUsuarioStr = (String) comboBoxTipoUsuario.getSelectedItem();
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipoUsuarioStr);
                String nome = textFieldNome.getText();
                String email = textFieldEmail.getText();
                String senha = new String(passwordField.getPassword());
                UsuarioEntity novoUsuario = new UsuarioEntity(nome, email, senha, tipoUsuario);

                if (novoUsuario != null) {
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Nome de usuário: " + novoUsuario.getNome() + "\nEmail: " + novoUsuario.getEmail() + "\nSenha: " + novoUsuario.getSenha() + "\nTipo Usuário: "+novoUsuario.getTipoUsuario()+ "\nDeseja realmente cadastrar esse usuário?",
                            "Confirmar cadastro de usuário",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (resposta == JOptionPane.YES_OPTION) {
                        controller.cadastrarNovoUsuario(novoUsuario, usuarioLogado);
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar usuário!");
                }
                dispose();
                GuiUsuarios telaUsuarios = new GuiUsuarios(usuarioLogado);
            }
        });
    }
}
