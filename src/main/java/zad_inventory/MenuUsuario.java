package zad_inventory;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.entity.UsuarioEntity.TipoUsuario;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    private static UsuarioService usuarioService;
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioEntity usuarioLogado;

    private static void exibirMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Listar usuários");
        if (usuarioLogado.getTipoUsuario() == TipoUsuario.GERENTE) {
            System.out.println("2. Registrar novo usuário");
            System.out.println("3. Excluir usuário");
        }
        System.out.println("4. Editar seu nome");
        System.out.println("5. Logout");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                listarUsuarios();
                break;
            case "2":
                if (usuarioLogado.getTipoUsuario() == TipoUsuario.GERENTE) {
                    registrarUsuario();
                } else {
                    acessoNegado();
                }
                break;
            case "3":
                if (usuarioLogado.getTipoUsuario() == TipoUsuario.GERENTE) {
                    excluirUsuario();
                } else {
                    acessoNegado();
                }
                break;
            case "4":
                editarNome();
                break;
            case "5":
                usuarioLogado = null;
                System.out.println("🔓 Logout efetuado.");
                break;
            case "0":
                System.out.println("👋 Encerrando sistema...");
                System.exit(0);
            default:
                System.out.println("❌ Opção inválida.");
        }
    }

    private static void listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.listarUsuarios();
        System.out.println("\n=== USUÁRIOS ===");
        for (UsuarioEntity u : usuarios) {
            System.out.printf("ID: %d | Nome: %s | Email: %s | Tipo: %s\n",
                    u.getId(), u.getNome(), u.getEmail(), u.getTipoUsuario());
        }
    }

    private static void registrarUsuario() {
        System.out.println("\n=== REGISTRAR NOVO USUÁRIO ===");
        UsuarioEntity novo = new UsuarioEntity();
        System.out.print("Nome: ");
        novo.setNome(scanner.nextLine());
        System.out.print("Email: ");
        novo.setEmail(scanner.nextLine());
        System.out.print("Senha: ");
        novo.setSenha(scanner.nextLine());
        System.out.print("Tipo (FUNCIONARIO/GERENTE): ");
        novo.setTipoUsuario(TipoUsuario.valueOf(scanner.nextLine().toUpperCase()));

        try {
            usuarioService.registrarUsuario(novo, usuarioLogado);
            System.out.println("✅ Usuário registrado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao registrar: " + e.getMessage());
        }
    }

    private static void excluirUsuario() {
        System.out.print("ID do usuário a ser excluído: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            usuarioService.deletarUsuario(id, usuarioLogado);
            System.out.println("✅ Usuário excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao excluir: " + e.getMessage());
        }
    }

    private static void editarNome() {
        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        usuarioLogado.setNome(novoNome);
        usuarioService.listarUsuarios(); // Reutilizando para persistir alteração
        EntityManager em = DBConnection.getEntityManager();
        em.getTransaction().begin();
        em.merge(usuarioLogado);
        em.getTransaction().commit();
        em.close();

        System.out.println("✅ Nome atualizado com sucesso!");
    }

    private static void acessoNegado() {
        System.out.println("❌ Acesso negado! Apenas gerentes podem executar esta ação.");
    }
}
