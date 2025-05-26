package zad_inventory;

import zad_inventory.auth.LoginService;
import zad_inventory.config.DBConnection;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.menu.MenuPrincipal;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        EntityManager em = DBConnection.getEntityManager();
        UsuarioService usuarioService = new UsuarioService(new UsuarioRepository(em));
        LoginService loginService = new LoginService(usuarioService);

        System.out.println("✅ Sistema iniciado com sucesso!");

        while (true) {
            UsuarioEntity usuarioLogado = loginService.realizarLogin();
            if (usuarioLogado != null) {
                MenuPrincipal.exibir(usuarioLogado);
            }
        }
    }
}
