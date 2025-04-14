package zad_inventory.auth;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.service.UsuarioService;

import java.util.Scanner;

public class LoginService {

    private final UsuarioService usuarioService;
    private final Scanner scanner = new Scanner(System.in);

    public LoginService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuarioEntity realizarLogin() {
        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("============= Login ==============\n");
        System.out.print("..::: Email: ");
        String email = scanner.nextLine();
        System.out.print("..::: Senha: ");
        String senha = scanner.nextLine();

        try {
            UsuarioEntity usuario = usuarioService.buscarPorEmail(email);
            if (usuario.getSenha().equals(senha)) {
                System.out.println("✅ Login bem-sucedido! Bem-vindo(a), " + usuario.getNome());
                return usuario;
            } else {
                System.out.println("❌ Senha incorreta.");
            }
        } catch (Exception e) {
            System.out.println("❌ Usuário não encontrado.");
        }
        return null;
    }
}
