package zad_inventory;

import zad_inventory.entity.ProdutoEntity;
import zad_inventory.service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class MenuProduto {
    public static void Produtos(String[] args) {
        ProdutoService produtoService = new ProdutoService();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== MENU DE PRODUTOS =====");
            System.out.println("1. Criar produto");
            System.out.println("2. Listar todos os produtos");
            System.out.println("3. Editar produto");
            System.out.println("4. Excluir produto");
            System.out.println("5. Buscar por nome");
            System.out.println("6. Buscar por categoria");
            System.out.println("7. Adicionar estoque");
            System.out.println("8. Remover estoque");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> {
                    ProdutoEntity novoProduto = new ProdutoEntity();
                    System.out.print("Nome do produto: ");
                    novoProduto.setNomeProduto(scanner.nextLine());
                    System.out.print("Cor: ");
                    novoProduto.setCor(scanner.nextLine());
                    System.out.print("Tamanho: ");
                    novoProduto.setTamanho(scanner.nextLine());
                    System.out.print("Quantidade: ");
                    novoProduto.setQuantidade(scanner.nextInt());
                    System.out.print("ID da categoria: ");
                    novoProduto.setCategoriaId(scanner.nextInt());
                    ProdutoEntity salvo = produtoService.salvarProduto(novoProduto);
                    System.out.println("Produto salvo: " + salvo);
                }
                case 2 -> {
                    List<ProdutoEntity> produtos = produtoService.listarTodos();
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                    } else {
                        produtos.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID do produto a editar: ");
                    Long idEditar = scanner.nextLong();
                    scanner.nextLine();
                    ProdutoEntity produtoEditar = produtoService.buscarPorId(idEditar);
                    if (produtoEditar == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }
                    System.out.print("Novo nome (atual: " + produtoEditar.getNomeProduto() + "): ");
                    produtoEditar.setNomeProduto(scanner.nextLine());
                    System.out.print("Nova cor (atual: " + produtoEditar.getCor() + "): ");
                    produtoEditar.setCor(scanner.nextLine());
                    System.out.print("Novo tamanho (atual: " + produtoEditar.getTamanho() + "): ");
                    produtoEditar.setTamanho(scanner.nextLine());
                    System.out.print("Nova quantidade (atual: " + produtoEditar.getQuantidade() + "): ");
                    produtoEditar.setQuantidade(scanner.nextInt());
                    scanner.nextLine();
                    produtoService.salvarProduto(produtoEditar);
                    System.out.println("Produto atualizado com sucesso.");
                }
                case 4 -> {
                    System.out.print("ID do produto a excluir: ");
                    Long idExcluir = scanner.nextLong();
                    scanner.nextLine();
                    produtoService.deletarProduto(idExcluir);
                    System.out.println("Produto excluído com sucesso.");
                }
                case 5 -> {
                    System.out.print("Digite o nome para buscar: ");
                    String nomeBusca = scanner.nextLine();
                    List<ProdutoEntity> resultados = produtoService.buscarPorNome(nomeBusca);
                    if (resultados.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                    } else {
                        resultados.forEach(System.out::println);
                    }
                }
                case 6 -> {
                    System.out.print("Digite o ID da categoria: ");
                    int categoriaId = scanner.nextInt();
                    List<ProdutoEntity> porCategoria = produtoService.buscarPorCategoria(categoriaId);
                    if (porCategoria.isEmpty()) {
                        System.out.println("Nenhum produto encontrado nesta categoria.");
                    } else {
                        porCategoria.forEach(System.out::println);
                    }
                }
                case 7 -> {
                    System.out.print("ID do produto: ");
                    Long idEstoque = scanner.nextLong();
                    System.out.print("Quantidade a adicionar: ");
                    int qtdAdd = scanner.nextInt();
                    ProdutoEntity atualizado = produtoService.adicionarEstoque(idEstoque, qtdAdd);
                    System.out.println("Estoque atualizado: " + atualizado.getQuantidade());
                }
                case 8 -> {
                    System.out.print("ID do produto: ");
                    Long idRemover = scanner.nextLong();
                    System.out.print("Quantidade a remover: ");
                    int qtdRem = scanner.nextInt();
                    ProdutoEntity atualizado = produtoService.removerEstoque(idRemover, qtdRem);
                    System.out.println("Estoque atualizado: " + atualizado.getQuantidade());
                }
                case 9 -> {
                    executando = false;
                    System.out.println("Encerrando...");
                }
                default -> System.out.println("Opção inválida.");
            }
        }

        produtoService.close();
        scanner.close();
    }
}