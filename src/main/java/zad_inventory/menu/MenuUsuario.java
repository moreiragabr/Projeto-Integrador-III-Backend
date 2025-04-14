package zad_inventory.menu;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.enums.TipoUsuario;
import zad_inventory.service.UsuarioService;

import java.util.Scanner;

public class MenuUsuario {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioService usuarioService =
            new UsuarioService(new UsuarioRepository(DBConnection.getEntityManager()));

    private static UsuarioEntity usuarioLogado;

    public static void exibir(UsuarioEntity logado) {
        usuarioLogado = logado;
        boolean executando = true;

        while (executando) {
            System.out.println("\n========= MENU USUÁRIOS =========");
            System.out.println("1 - Listar Usuários");
            System.out.println("2 - Cadastrar Novo Usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    usuarioService.listarUsuarios().forEach(System.out::println);
                    break;
                case "2":
                    cadastrarNovoUsuario();
                    break;
                case "0":
                    executando = false;
                    break;
                default:
                    System.out.println("❌ Opção inválida.");
            }
        }
    }

    private static void cadastrarNovoUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Tipo (GERENTE ou FUNCIONARIO): ");
        String tipo = scanner.nextLine().toUpperCase();

        try {
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipo);
            UsuarioEntity novoUsuario = new UsuarioEntity(nome, email, senha, tipoUsuario);
            usuarioService.registrarUsuario(novoUsuario, usuarioLogado);
            System.out.println("✅ Usuário cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Tipo de usuário inválido. Use GERENTE ou FUNCIONARIO.");
        }
    }
}
