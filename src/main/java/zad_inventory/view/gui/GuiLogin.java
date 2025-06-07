package zad_inventory.view.gui;

import zad_inventory.model.UsuarioEntity;
import zad_inventory.view.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import zad_inventory.auth.LoginService;
import zad_inventory.config.DBConnection;
import zad_inventory.service.UsuarioService;
import javax.persistence.EntityManager;


public class GuiLogin extends JFrame {
    private JPanel panelLogin;
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    private JPanel panelSubtitulo;
    private JLabel labelSubtitulo;
    private JPanel panelEntradaDeDados;
    private JPanel panelLabels;
    private JPanel panelTextFields;
    private JTextField textfieldUsuario;
    private JPasswordField passwordfieldSenha;
    private JLabel labelUsuario;
    private JLabel labelSenha;
    private JPanel panelButton;
    private JButton buttonEntrar;
    private JButton sairButton;

    EntityManager em = DBConnection.getEntityManager();
    UsuarioService usuarioService = new UsuarioService();
    LoginService loginService = new LoginService(usuarioService);


    public GuiLogin(){
        setContentPane(panelLogin);
        setTitle("Login");
        setSize(600,440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buttonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textfieldUsuario.getText();
                String senha = new String(passwordfieldSenha.getPassword());

                UsuarioEntity usuarioLogado = loginService.realizarLogin(email, senha);

                if (usuarioLogado != null) {
                    MenuPrincipal.exibir(usuarioLogado);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Senha incorreta ou usuário inexistente.");
                }
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
