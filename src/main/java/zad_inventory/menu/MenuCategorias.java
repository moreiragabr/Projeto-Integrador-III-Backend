package zad_inventory.menu;

import zad_inventory.entity.CategoriaEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.controller.CategoriaController;
import java.util.List;
import java.util.Scanner;

public class MenuCategorias {

    public static void Categorias(UsuarioEntity usuarioLogado) {
        CategoriaController controller = new CategoriaController();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU CATEGORIAS ===");
            System.out.println("1. Cadastrar Categoria");
            System.out.println("2. Listar Categorias");
            System.out.println("3. Buscar Categoria por ID");
            System.out.println("4. Atualizar Categoria");
            System.out.println("5. Remover Categoria");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.print("Digite o nome da categoria: ");
                    String nome = scanner.nextLine();

                    System.out.print("Descrição da categoria: ");
                    String descricao = scanner.nextLine();
                    CategoriaEntity novaCategoria = new CategoriaEntity(nome, descricao);
                    controller.salvar(novaCategoria);
                }
                case "2" -> {
                    List<CategoriaEntity> categorias = controller.buscarTodos();
                    if (categorias.isEmpty()) {
                        System.out.println("Nenhuma categoria cadastrada.");
                    } else {
                        System.out.println("\n=== LISTA DE CATEGORIAS ===");
                        for (CategoriaEntity c : categorias) {
                            System.out.printf("ID: %d | Nome: %s%n", c.getId(), c.getNome());
                        }
                    }
                }
                case "3" -> {
                    System.out.print("Digite o ID da categoria: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    CategoriaEntity categoria = controller.buscarPorId(id);
                    if (categoria != null) {
                        System.out.printf("Categoria encontrada: ID %d | Nome: %s%n", categoria.getId(), categoria.getNome());
                    }
                }
                case "4" -> {
                    System.out.print("Digite o ID da categoria a ser atualizada: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    CategoriaEntity categoria = controller.buscarPorId(id);
                    if (categoria == null) {
                        System.out.println("Categoria não encontrada.");
                        break;
                    }
                    System.out.print("Digite o novo nome da categoria: ");
                    String novoNome = scanner.nextLine();
                    categoria.setNome(novoNome);
                    controller.atualizar(categoria);
                }
                case "5" -> {
                    System.out.print("Digite o ID da categoria a ser removida: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    CategoriaEntity categoria = controller.buscarPorId(id);
                    if (categoria == null) {
                        System.out.println("Categoria não encontrada.");
                        break;
                    }
                    controller.remover(categoria);
                }
                case "0" -> {
                    System.out.println("Voltando ao menu principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}