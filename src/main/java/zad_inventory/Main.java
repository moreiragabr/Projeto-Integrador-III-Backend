package zad_inventory;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {

    private static UsuarioService usuarioService;
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioEntity usuarioLogado;

    public static void main(String[] args) {

        EntityManager em = DBConnection.getEntityManager();
        usuarioService = new UsuarioService(new UsuarioRepository(em));

        System.out.println("✅ Sistema iniciado com sucesso!");

        loopLogin(null);
    }

    public static void loopLogin(UsuarioEntity usuarioLogado) {
        while (true) {
            if (usuarioLogado == null) {
                realizarLogin();
            } else {
                Menus.menuInicial(usuarioLogado);
            }
        }
    }

    private static void realizarLogin() {
        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("============= Login ==============\n");
        System.out.print("..::: Email: ");
        String email = scanner.nextLine();
        System.out.print("\n..::: Senha: ");
        String senha = scanner.nextLine();

        try {
            UsuarioEntity usuario = usuarioService.buscarPorEmail(email);
            if (usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                System.out.println("✅ Login bem-sucedido! Bem-vindo(a), " + usuario.getNome());
                Menus.menuInicial(usuarioLogado);
            } else {
                System.out.println("❌ Senha incorreta.");
            }
        } catch (Exception e) {
            System.out.println("❌ Usuário não encontrado.");
        }
    }
}