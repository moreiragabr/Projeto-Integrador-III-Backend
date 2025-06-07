package zad_inventory.view;

import zad_inventory.model.UsuarioEntity;
import zad_inventory.enums.TipoUsuario;
import zad_inventory.controller.UsuarioController;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;
import zad_inventory.config.DBConnection;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {
    private static UsuarioController controller;
    private static Scanner scanner;
    private static UsuarioEntity usuarioLogado;

    public static void exibir(UsuarioEntity logado) {
        usuarioLogado = logado;
        scanner = new Scanner(System.in);
        EntityManager em = DBConnection.getEntityManager();
        //controller = new UsuarioController(new UsuarioService(new UsuarioRepository(em)));

        new MenuUsuario().exibirInstancia();
    }

    public void exibirInstancia() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n========= MENU USUÁRIOS =========");
            System.out.println("1 - Listar Usuários");
            System.out.println("2 - Ranking de Usuários por Produtos Criados");
            System.out.println("3 - Cadastrar Novo Usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    listarUsuarios();
                    break;
                case "2":
                    exibirRanking();
                    break;
                case "3":
                    cadastrarNovoUsuario();
                    break;
                case "0":
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarNovoUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Formato de email inválido!");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Tipo (GERENTE ou FUNCIONARIO): ");
        String tipoStr = scanner.nextLine().toUpperCase();

        TipoUsuario tipoUsuario;
        try {
            tipoUsuario = TipoUsuario.valueOf(tipoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de usuário inválido. Use GERENTE ou FUNCIONARIO.");
            return;
        }

        UsuarioEntity novoUsuario = new UsuarioEntity(nome, email, senha, tipoUsuario);
        try {
            controller.cadastrarNovoUsuario(novoUsuario, usuarioLogado);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SecurityException | IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        try {
            List<UsuarioEntity> usuarios = controller.listarUsuarios();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
            } else {
                System.out.println("\n--- Lista de Usuários ---");
                for (UsuarioEntity u : usuarios) {
                    System.out.printf("ID: %d | Nome: %s | Email: %s | Tipo: %s%n",
                            u.getId(),
                            u.getNome(),
                            u.getEmail(),
                            u.getTipoUsuario());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    private void exibirRanking() {
        try {
            List<UsuarioEntity> ranking = controller.exibirRanking();
            System.out.println("\n--- Ranking de Usuários por Produtos Cadastrados ---");
            int rank = 1;
            for (UsuarioEntity u : ranking) {
                System.out.printf("#%d | ID: %d | Nome: %s | Tipo: %s | Total de Produtos: %d%n",
                        rank++,
                        u.getId(),
                        u.getNome(),
                        u.getTipoUsuario(),
                        u.getTotalProdutos() != null ? u.getTotalProdutos() : 0);
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir ranking: " + e.getMessage());
        }
    }
}
