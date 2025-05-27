package zad_inventory.view;

import zad_inventory.controller.CategoriaController;
import zad_inventory.model.UsuarioEntity;
import java.util.Scanner;

public class MenuCategorias {
    // Método estático para compatibilidade com o MenuAdmin original
    public static void Categorias(UsuarioEntity usuarioLogado) {
        new MenuCategorias(usuarioLogado).exibirMenu();
    }

    private final Scanner scanner;
    private final CategoriaController controller;

    private MenuCategorias(UsuarioEntity usuarioLogado) {
        this.scanner = new Scanner(System.in);
        this.controller = new CategoriaController();
    }

    private void exibirMenu() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== MENU CATEGORIAS ===");
            System.out.println("1. Cadastrar Categoria");
            System.out.println("2. Listar Todas as Categorias");
            System.out.println("3. Buscar Categoria por ID");
            System.out.println("4. Atualizar Categoria");
            System.out.println("5. Remover Categoria");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> cadastrarCategoriaView();
                case "2" -> listarCategoriasView();
                case "3" -> buscarCategoriaPorIdView();
                case "4" -> atualizarCategoriaView();
                case "5" -> removerCategoriaView();
                case "0" -> executando = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // -- Métodos de View --
    private void cadastrarCategoriaView() {
        System.out.println("\n--- CADASTRAR CATEGORIA ---");
        System.out.print("Nome da categoria: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        try {
            controller.cadastrarCategoria(nome, descricao);
            System.out.println("Categoria cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarCategoriasView() {
        System.out.println("\n--- LISTA DE CATEGORIAS ---");
        var categorias = controller.listarCategorias();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }
        categorias.forEach(c ->
                System.out.printf("ID: %d | Nome: %s | Descrição: %s\n",
                        c.getId(), c.getNome(), c.getDescricao())
        );
    }

    private void buscarCategoriaPorIdView() {
        System.out.println("\n--- BUSCAR CATEGORIA POR ID ---");
        System.out.print("Digite o ID da categoria: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            var categoria = controller.buscarCategoriaPorId(id);
            System.out.printf("ID: %d | Nome: %s | Descrição: %s\n",
                    categoria.getId(), categoria.getNome(), categoria.getDescricao());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Use apenas números.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void atualizarCategoriaView() {
        System.out.println("\n--- ATUALIZAR CATEGORIA ---");
        System.out.print("Digite o ID da categoria: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nova descrição: ");
            String descricao = scanner.nextLine();

            controller.atualizarCategoria(id, nome, descricao);
            System.out.println("Categoria atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerCategoriaView() {
        System.out.println("\n--- REMOVER CATEGORIA ---");
        System.out.print("Digite o ID da categoria: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            controller.removerCategoria(id);
            System.out.println("Categoria removida com sucesso!");
        } catch (Exception e) {
            System.out.println("Há produtos cadastrados com esta categoria");
            System.out.println("Erro: " + e.getMessage());
        }
    }
}