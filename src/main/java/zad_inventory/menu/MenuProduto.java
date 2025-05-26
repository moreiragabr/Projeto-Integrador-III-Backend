package zad_inventory.menu;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.controller.ProdutoController;
import java.util.Scanner;

public class MenuProduto {

    public static void exibir(UsuarioEntity usuarioLogado) {
        ProdutoController controller = new ProdutoController(usuarioLogado);
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU DE PRODUTOS ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Buscar Produto por ID");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Remover Produto");
            System.out.println("6. Buscar por Nome");
            System.out.println("7. Buscar por Categoria");
            System.out.println("8. Adicionar Estoque");
            System.out.println("9. Remover Estoque");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> controller.cadastrarProduto();
                case "2" -> controller.listarProdutos();
                case "3" -> controller.buscarProdutoPorId();
                case "4" -> controller.atualizarProduto();
                case "5" -> controller.removerProduto();
                case "6" -> controller.buscarPorNome();
                case "7" -> controller.buscarPorCategoria();
                case "8" -> controller.adicionarEstoque();
                case "9" -> controller.removerEstoque();
                case "0" -> {
                    System.out.println("Voltando ao menu principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        controller.close();
        MenuPrincipal.exibir(usuarioLogado);
    }
}