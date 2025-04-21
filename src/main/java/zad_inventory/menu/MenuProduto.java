package zad_inventory.menu;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.ProdutoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.repository.ProdutoRepository;
import zad_inventory.service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class MenuProduto {

    public static void exibir(UsuarioEntity usuarioLogado) {
        ProdutoService produtoService = new ProdutoService(
                new ProdutoRepository(DBConnection.getEntityManager())
        );
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
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    ProdutoEntity novo = new ProdutoEntity();
                    System.out.print("Nome do produto: ");
                    novo.setNomeProduto(scanner.nextLine());
                    System.out.print("Cor: ");
                    novo.setCor(scanner.nextLine());
                    System.out.print("Tamanho: ");
                    novo.setTamanho(scanner.nextLine());
                    System.out.print("Quantidade: ");
                    novo.setQuantidade(scanner.nextInt());
                    System.out.print("ID da categoria: ");
                    novo.setCategoriaId(scanner.nextInt());
                    novo.setUsuarioId(usuarioLogado.getId());
                    ProdutoEntity salvo = produtoService.salvarProduto(novo);
                    System.out.println("✅ Produto salvo: " + salvo);
                }
                case 2 -> {
                    List<ProdutoEntity> produtos = produtoService.listarTodos();
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                    } else {
                        System.out.printf("\n%-4s %-20s %-10s %-10s %-20s %-20s %-10s %-10s\n",
                                "ID", "Nome", "Qtd", "Categoria", "Nome Categoria", "Descricao Categoria", "Cor", "Tamanho");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                        for (ProdutoEntity p : produtos) {
                            System.out.printf("%-4s %-20s %-10s %-10s %-20s %-20s %-10s %-10s\n",
                                    p.getId(),
                                    p.getNomeProduto(),
                                    p.getQuantidade(),
                                    p.getCategoriaId(),
                                    p.getNomeCategoria(),
                                    p.getDescricaoCategoria(),
                                    p.getCor(),
                                    p.getTamanho()
                            );
                        }
                    }
                }
                case 3 -> {
                    System.out.print("ID do produto a editar: ");
                    Long idEditar = scanner.nextLong();
                    scanner.nextLine();
                    ProdutoEntity produto = produtoService.buscarPorId(idEditar);
                    if (produto == null) {
                        System.out.println("❌ Produto não encontrado.");
                        break;
                    }
                    System.out.print("Novo nome (" + produto.getNomeProduto() + "): ");
                    produto.setNomeProduto(scanner.nextLine());
                    System.out.print("Nova cor (" + produto.getCor() + "): ");
                    produto.setCor(scanner.nextLine());
                    System.out.print("Novo tamanho (" + produto.getTamanho() + "): ");
                    produto.setTamanho(scanner.nextLine());
                    System.out.print("Nova quantidade (" + produto.getQuantidade() + "): ");
                    produto.setQuantidade(scanner.nextInt());
                    produtoService.salvarProduto(produto);
                    System.out.println("✅ Produto atualizado.");
                }
                case 4 -> {
                    System.out.print("ID do produto a excluir: ");
                    Long id = scanner.nextLong();
                    try {
                        produtoService.deletarProduto(id);
                        System.out.println("✅ Produto excluído.");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    List<ProdutoEntity> encontrados = produtoService.buscarPorNome(nome);
                    if (encontrados.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                    } else {
                        encontrados.forEach(System.out::println);
                    }
                }
                case 6 -> {
                    System.out.print("ID da categoria: ");
                    int categoriaId = scanner.nextInt();
                    List<ProdutoEntity> encontrados = produtoService.buscarPorCategoria(categoriaId);
                    if (encontrados.isEmpty()) {
                        System.out.println("Nenhum produto nesta categoria.");
                    } else {
                        encontrados.forEach(System.out::println);
                    }
                }
                case 7 -> {
                    System.out.print("ID do produto: ");
                    Long id = scanner.nextLong();
                    System.out.print("Quantidade a adicionar: ");
                    int qtd = scanner.nextInt();
                    ProdutoEntity atualizado = produtoService.adicionarEstoque(id, qtd);
                    System.out.println("✅ Estoque atualizado: " + atualizado.getQuantidade());
                }
                case 8 -> {
                    System.out.print("ID do produto: ");
                    Long id = scanner.nextLong();
                    System.out.print("Quantidade a remover: ");
                    int qtd = scanner.nextInt();
                    ProdutoEntity atualizado = produtoService.removerEstoque(id, qtd);
                    System.out.println("✅ Estoque atualizado: " + atualizado.getQuantidade());
                }
                case 0 -> {
                    executando = false;
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }

        produtoService.close();
        MenuPrincipal.exibir(usuarioLogado);
    }
}
