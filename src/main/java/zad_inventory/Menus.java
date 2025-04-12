package zad_inventory;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;

import javax.persistence.EntityManager;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static zad_inventory.Main.loopLogin;

public class Menus {

    private static UsuarioService usuarioService;

    private static UsuarioEntity usuarioLogado;

    //Menu inicial //passado
    public static void menuInicial(UsuarioEntity usuario) {

        usuarioLogado = usuario;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n\n========= Zad Inventory ==========\n");
            if (usuarioLogado.getTipoUsuario() == UsuarioEntity.TipoUsuario.GERENTE) {
                System.out.printf("Perfil Administrativo: %s\n\n", usuarioLogado.getNome());
            } else {
                System.out.printf("Usuário:  %s\n\n", usuarioLogado.getNome());
            }
            System.out.println("==================================\n");
            System.out.println("1 - Venda e alteração de produtos\n");
            System.out.println("2 - Editar nome de usuário\n");
            if (usuarioLogado.getTipoUsuario() == UsuarioEntity.TipoUsuario.GERENTE) {
                System.out.println("3 - Relatórios\n");
                System.out.println("4 - Administrar usuários\n");
            }
            System.out.println("9 - Logout\n");
            System.out.println("\n0 - Sair\n");
            System.out.println("==================================\n");

            System.out.print("..::: Digite sua opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {

                    case 1:
                        menuProdutos();
                        break;

                    case 2:
                        menuEditarNome(usuarioLogado);
                        break;

                    case 3:
                        if (usuarioLogado.getTipoUsuario() == UsuarioEntity.TipoUsuario.GERENTE) {
                            menuRelatorios();
                        }
                        break;

                    case 4:
                        if (usuarioLogado.getTipoUsuario() == UsuarioEntity.TipoUsuario.GERENTE) {
                            menuAdministrarUsuarios(usuarioLogado);
                        }
                        break;

                    case 9:
                        System.out.println("\n🔓 Logout efetuado.\n");

                        loopLogin(null);

                        System.out.println("👋 Encerrando sistema...");
                        System.exit(0);
                        break;

                    case 0:
                        System.out.println("👋 Encerrando sistema...");
                        System.exit(0);

                    default:
                        System.out.println("Opção inválida.");

                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        } while (true);
    }

    //Menu de produtos //passado
    public static void menuProdutos() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Vendas\n");
        System.out.println("2 - Cadastro de produtos\n");
        System.out.println("3 - Consulta de produtos\n");
        System.out.println("4 - Alteração de produtos\n");
        if(usuarioLogado.getTipoUsuario() == UsuarioEntity.TipoUsuario.GERENTE) {
            System.out.println("5 - Remoção de produtos\n");
        }
        System.out.println("\n0 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    menuVendas();
                    break;

                case 2:
                    menuCadastroProdutos();
                    break;

                case 3:
                    menuConsultaProdutos();
                    break;

                case 4:
                    menuAlteracaoProdutos();
                    break;

                case 5:
                    if(usuarioLogado.getTipoUsuario() == UsuarioEntity.TipoUsuario.GERENTE) {
                        menuRemocaoProdutos();
                    }
                    break;

                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    //Opções do menu de produtos

    public static void menuVendas() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja cadastrar uma nova venda?\n");
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    public static void menuCadastroProdutos() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja cadastrar um novo produto?\n");
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    public static void menuConsultaProdutos() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja consultar a lista de  produtos?\n");
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    public static void menuRemocaoProdutos() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja remover um produto?\n");
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    public static void menuAlteracaoProdutos() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja alterar um produto?\n");
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    //Menu de relatórios
    public static void menuRelatorios() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Gerar relatório individual\n");
        System.out.println("2 - Gerar relatório geral\n");
        System.out.println("\n4 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    menuRelatorioIndividual();
                case 2:
                    menuRelatorioGeral();
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    //Opções do menu de relatório

    public static void menuRelatorioIndividual() {

    }

    public static void menuRelatorioGeral() {

    }


    //Menu para editar nome de usuário
    public static void menuEditarNome(UsuarioEntity usuario) {

        usuarioLogado = usuario;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja editar o nome da sua conta?\n");
        System.out.printf("Nome atual: %s\n\n", usuarioLogado.getNome());
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("\n\n========= Zad Inventory ==========\n");
                    System.out.println("\n==== Edição de nome de usuário ===\n");
                    System.out.print("..::: Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.println("0");

                    usuarioLogado.setNome(novoNome);
                    System.out.println("1");
                    //usuarioService.listarUsuarios(); // Reutilizando para persistir alteração
                    System.out.println("2");
                    EntityManager em = DBConnection.getEntityManager();
                    System.out.println("3");
                    em.getTransaction().begin();
                    System.out.println("4");
                    em.merge(usuarioLogado);
                    System.out.println("5");
                    em.getTransaction().commit();
                    System.out.println("6");
                    em.close();
                    System.out.println("7");

                    System.out.println("✅ Nome atualizado com sucesso!");
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }


    //Menu de administrar usuários
    public static void menuAdministrarUsuarios(UsuarioEntity usuarioLogado) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Excluir usuários\n");
        System.out.println("2 - Cadastrar usuários\n");
        System.out.println("3 - Lista de usuários\n");
        System.out.println("\n0 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    menuExcluirUsuarios(usuarioLogado);
                    break;

                case 2:
                    menuCadastrarUsuarios(usuarioLogado);
                    break;

                case 3:
                    menuListaUsuarios();
                    break;

                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    //Opções do menu de administrar usuarios

    public static void menuExcluirUsuarios(UsuarioEntity usuario) {

        EntityManager em = DBConnection.getEntityManager();
        usuarioService = new UsuarioService(new UsuarioRepository(em));

        usuarioLogado = usuario;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja excluir um usuário?\n");
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("\n\n========= Zad Inventory ==========\n");
                    System.out.println("\n======== Lista de usuários =======\n");
                    List<UsuarioEntity> usuarios = usuarioService.listarUsuarios();
                    for (UsuarioEntity u : usuarios) {
                        System.out.printf("ID: %d | Nome: %s | Email: %s | Tipo: %s\n",
                                u.getId(), u.getNome(), u.getEmail(), u.getTipoUsuario());
                    }
                    System.out.print("\n..::: ID do usuário a ser excluído: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    try {
                        usuarioService.deletarUsuario(id, usuarioLogado);
                        System.out.println("✅ Usuário excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("❌ Erro ao excluir: " + e.getMessage());
                    }
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    public static void menuCadastrarUsuarios(UsuarioEntity usuario) {

        EntityManager em = DBConnection.getEntityManager();
        usuarioService = new UsuarioService(new UsuarioRepository(em));

        usuarioLogado = usuario;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja adicionar um novo usuário?\n");
        System.out.println("1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("\n\n========= Zad Inventory ==========\n\n");
                    System.out.println("===== Registrar novo usuário =====");
                    UsuarioEntity novo = new UsuarioEntity();
                    System.out.print("\n..::: Nome: ");
                    novo.setNome(scanner.nextLine());
                    System.out.print("\n..::: Email: ");
                    novo.setEmail(scanner.nextLine());
                    System.out.print("\n..::: Senha: ");
                    novo.setSenha(scanner.nextLine());
                    System.out.print("\n..::: Tipo (FUNCIONARIO/GERENTE): ");
                    novo.setTipoUsuario(UsuarioEntity.TipoUsuario.valueOf(scanner.nextLine().toUpperCase()));

                    try {
                        usuarioService.registrarUsuario(novo, usuarioLogado);
                        System.out.println("✅ Usuário registrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("❌ Erro ao registrar: " + e.getMessage());
                    }
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    public static void menuListaUsuarios(){

        EntityManager em = DBConnection.getEntityManager();
        usuarioService = new UsuarioService(new UsuarioRepository(em));

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("\n======== Lista de usuários =======\n");
        List<UsuarioEntity> usuarios = usuarioService.listarUsuarios();
        for (UsuarioEntity u : usuarios) {
            System.out.printf("ID: %d | Nome: %s | Email: %s | Tipo: %s\n",
                    u.getId(), u.getNome(), u.getEmail(), u.getTipoUsuario());
        }
        System.out.println("\n..::: Pressione qualquer tecla para prosseguir");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}
