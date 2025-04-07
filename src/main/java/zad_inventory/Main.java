package zad_inventory;

import zad_inventory.entity.Usuario;
import zad_inventory.entity.Usuario.TipoUsuario;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioRepository repository = new UsuarioRepository();
        UsuarioService service = new UsuarioService(repository);

        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Fazer login");
            System.out.println("3 - Listar todos os usuários");
            System.out.println("4 - Remover usuário por ID");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    System.out.print("Tipo (FUNCIONARIO ou GERENTE): ");
                    String tipo = scanner.nextLine().toUpperCase();

                    try {
                        TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipo);
                        service.registrarUsuario(nome, email, senha, tipoUsuario);
                        System.out.println("Usuário cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo inválido. Use FUNCIONARIO ou GERENTE.");
                    } catch (RuntimeException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("Email: ");
                    String loginEmail = scanner.nextLine();

                    System.out.print("Senha: ");
                    String loginSenha = scanner.nextLine();

                    try {
                        Usuario usuario = service.login(loginEmail, loginSenha);
                        System.out.println("Login bem-sucedido! Bem-vindo(a), " + usuario.getNome() +
                                " (" + usuario.getTipoUsuario() + ")");
                    } catch (RuntimeException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "3":
                    List<Usuario> usuarios = service.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("\nUsuários cadastrados:");
                        for (Usuario u : usuarios) {
                            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() +
                                    ", Email: " + u.getEmail() + ", Tipo: " + u.getTipoUsuario());
                        }
                    }
                    break;

                case "4":
                    System.out.print("ID do usuário para remover: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        boolean removido = service.removerUsuario(id);
                        if (removido) {
                            System.out.println("Usuário removido com sucesso.");
                        } else {
                            System.out.println("Usuário com ID " + id + " não encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                    }
                    break;

                case "5":
                    executando = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
