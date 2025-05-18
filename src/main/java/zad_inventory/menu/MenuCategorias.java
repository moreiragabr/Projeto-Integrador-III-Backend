package zad_inventory.menu;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.controller.CategoriaController;
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
                case "1" -> controller.cadastrarCategoria();
                case "2" -> controller.listarCategorias();
                case "3" -> controller.buscarCategoriaPorId();
                case "4" -> controller.atualizarCategoria();
                case "5" -> controller.removerCategoria();
                case "0" -> {
                    System.out.println("Voltando ao menu principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}