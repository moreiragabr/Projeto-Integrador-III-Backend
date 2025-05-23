package zad_inventory.menu;

import zad_inventory.controller.ProdutoController;
import zad_inventory.entity.ProdutoEntity;
import zad_inventory.entity.UsuarioEntity;

import java.util.List;
import java.util.Scanner;


public class MenuProduto {

    private final ProdutoController controller;
    private final Scanner scanner;


    public MenuProduto(UsuarioEntity usuarioLogado) {
        this.controller = new ProdutoController(usuarioLogado);
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n======= MENU DE PRODUTOS =======");
            System.out.println("1. Cadastrar Novo Produto");
            System.out.println("2. Listar Todos os Produtos");
            System.out.println("3. Buscar Produto por ID");
            System.out.println("4. Atualizar Produto Existente");
            System.out.println("5. Remover Produto");
            System.out.println("6. Buscar Produtos por Nome");
            System.out.println("7. Buscar Produtos por ID da Categoria");
            System.out.println("8. Adicionar Estoque a um Produto");
            System.out.println("9. Remover Estoque de um Produto");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1" -> viewCadastrarProduto();
                    case "2" -> viewListarTodosProdutos();
                    case "3" -> viewBuscarProdutoPorId();
                    case "4" -> viewAtualizarProduto();
                    case "5" -> viewRemoverProduto();
                    case "6" -> viewBuscarProdutosPorNome();
                    case "7" -> viewBuscarProdutosPorCategoria();
                    case "8" -> viewAdicionarEstoque();
                    case "9" -> viewRemoverEstoque();
                    case "0" -> {
                        System.out.println("Voltando ao menu anterior...");
                        executando = false;
                    }
                    default -> System.out.println("Opção inválida. Por favor, tente novamente.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.err.println("Erro de Validação/Operação: " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("Ocorreu um erro inesperado no sistema: " + e.getMessage());
            }
        }
        controller.close();
    }

    private void viewCadastrarProduto() {
        System.out.println("\n--- CADASTRAR NOVO PRODUTO ---");
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        System.out.print("Quantidade inicial: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        System.out.print("ID da Categoria: ");
        Long categoriaId = Long.parseLong(scanner.nextLine());

        ProdutoEntity produtoSalvo = controller.cadastrarProduto(nome, cor, tamanho, quantidade, categoriaId);
        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("Detalhes: " + formatarProdutoSimples(produtoSalvo));
    }

    private void viewListarTodosProdutos() {
        System.out.println("\n--- LISTA DE TODOS OS PRODUTOS ---");
        List<ProdutoEntity> produtos = controller.listarTodosProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado no sistema.");
        } else {
            System.out.printf("%-5s | %-25s | %-5s | %-15s | %-10s | %-10s | %-10s\n",
                    "ID", "Nome do Produto", "Qtd", "Categoria", "Cor", "Tamanho", "User ID");
            String separator = "-".repeat(5+3+25+3+5+3+15+3+10+3+10+3+10);
            System.out.println(separator);
            for (ProdutoEntity p : produtos) {
                String nomeCategoria = (p.getCategoria() != null && p.getCategoria().getNome() != null)
                        ? p.getCategoria().getNome() : "N/D (" + p.getCategoriaId() + ")";
                System.out.printf("%-5d | %-25.25s | %-5d | %-15.15s | %-10.10s | %-10.10s | %-10d\n",
                        p.getId(),
                        p.getNomeProduto(),
                        p.getQuantidade(),
                        nomeCategoria,
                        p.getCor() != null ? p.getCor() : "N/D",
                        p.getTamanho() != null ? p.getTamanho() : "N/D",
                        p.getUsuarioId() != null ? p.getUsuarioId() : -1L
                );
            }
            System.out.println(separator);
            System.out.println("Total de tipos de produto: " + produtos.size());
        }
    }

    private void viewBuscarProdutoPorId() {
        System.out.println("\n--- BUSCAR PRODUTO POR ID ---");
        System.out.print("Digite o ID do produto: ");
        Long id = Long.parseLong(scanner.nextLine());

        ProdutoEntity produto = controller.buscarProdutoPorId(id);

        System.out.println("Produto encontrado:");
        System.out.println(formatarProdutoDetalhado(produto));
    }

    private void viewAtualizarProduto() {
        System.out.println("\n--- ATUALIZAR PRODUTO ---");
        System.out.print("Digite o ID do produto a ser atualizado: ");
        Long id = Long.parseLong(scanner.nextLine());


        ProdutoEntity produtoAtual = controller.buscarProdutoPorId(id);
        System.out.println("Editando produto: " + formatarProdutoSimples(produtoAtual));
        System.out.println("(Deixe o campo em branco para manter o valor atual)");

        System.out.print("Novo Nome [" + produtoAtual.getNomeProduto() + "]: ");
        String nome = scanner.nextLine();
        System.out.print("Nova Cor [" + produtoAtual.getCor() + "]: ");
        String cor = scanner.nextLine();
        System.out.print("Novo Tamanho [" + produtoAtual.getTamanho() + "]: ");
        String tamanho = scanner.nextLine();

        Integer quantidade = null;
        System.out.print("Nova Quantidade [" + produtoAtual.getQuantidade() + "]: ");
        String qtdStr = scanner.nextLine();
        if (!qtdStr.trim().isEmpty()) {
            quantidade = Integer.parseInt(qtdStr);
        }

        Long categoriaId = null;
        String nomeCategoriaAtual = (produtoAtual.getCategoria() != null && produtoAtual.getCategoria().getNome() != null)
                ? produtoAtual.getCategoria().getNome()
                : "ID Categoria: " + produtoAtual.getCategoriaId();
        System.out.print("Novo ID da Categoria [" + nomeCategoriaAtual + "]: ");
        String catIdStr = scanner.nextLine();
        if (!catIdStr.trim().isEmpty()) {
            categoriaId = Long.parseLong(catIdStr);
        }

        ProdutoEntity produtoAtualizado = controller.atualizarProduto(id, nome, cor, tamanho, quantidade, categoriaId);
        System.out.println("Produto atualizado com sucesso!");
        System.out.println("Novos Detalhes: " + formatarProdutoDetalhado(produtoAtualizado));
    }

    private void viewRemoverProduto() {
        System.out.println("\n--- REMOVER PRODUTO ---");
        System.out.print("Digite o ID do produto a ser removido: ");
        Long id = Long.parseLong(scanner.nextLine());

        // Opcional: pedir confirmação
        System.out.print("Tem certeza que deseja remover o produto ID " + id + "? (S/N): ");
        String confirmacao = scanner.nextLine();
        if (confirmacao.equalsIgnoreCase("S")) {
            controller.removerProduto(id);
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void viewBuscarProdutosPorNome() {
        System.out.println("\n--- BUSCAR PRODUTOS POR NOME ---");
        System.out.print("Digite o nome ou parte do nome para buscar: ");
        String nome = scanner.nextLine();

        List<ProdutoEntity> produtos = controller.buscarProdutosPorNome(nome);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado com o nome: '" + nome + "'.");
        } else {
            System.out.println("Produtos encontrados com o nome '" + nome + "':");
            System.out.printf("%-5s | %-25s | %-5s | %-15s | %-10s | %-10s\n",
                    "ID", "Nome do Produto", "Qtd", "Categoria", "Cor", "Tamanho");
            String separator = "-".repeat(5+3+25+3+5+3+15+3+10+3+10);
            System.out.println(separator);
            for (ProdutoEntity p : produtos) {
                String nomeCategoria = (p.getCategoria() != null && p.getCategoria().getNome() != null)
                        ? p.getCategoria().getNome() : "N/D (" + p.getCategoriaId() + ")";
                System.out.printf("%-5d | %-25.25s | %-5d | %-15.15s | %-10.10s | %-10.10s\n",
                        p.getId(), p.getNomeProduto(),p.getQuantidade(), nomeCategoria,
                        p.getCor() != null ? p.getCor() : "N/D",
                        p.getTamanho() != null ? p.getTamanho() : "N/D");
            }
            System.out.println(separator);
            System.out.println("Total de produtos encontrados: " + produtos.size());
        }
    }

    private void viewBuscarProdutosPorCategoria() {
        System.out.println("\n--- BUSCAR PRODUTOS POR CATEGORIA ---");
        System.out.print("Digite o ID da Categoria: ");
        Long categoriaId = Long.parseLong(scanner.nextLine());

        List<ProdutoEntity> produtos = controller.buscarProdutosPorCategoria(categoriaId);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado para a categoria ID: " + categoriaId);
        } else {

            //CategoriaEntity categoria = categoriaController.buscarPorId(categoriaId);
            System.out.println("Produtos encontrados para a categoria ID " + categoriaId + ":");

            System.out.printf("%-5s | %-25s | %-5s | %-10s | %-10s\n",
                    "ID", "Nome do Produto", "Qtd", "Cor", "Tamanho");
            String separator = "-".repeat(5+3+25+3+5+3+10+3+10);
            System.out.println(separator);
            for (ProdutoEntity p : produtos) {
                System.out.printf("%-5d | %-25.25s | %-5d | %-10.10s | %-10.10s\n",
                        p.getId(), p.getNomeProduto(), p.getQuantidade(),
                        p.getCor() != null ? p.getCor() : "N/D",
                        p.getTamanho() != null ? p.getTamanho() : "N/D");
            }
            System.out.println(separator);
            System.out.println("Total de produtos encontrados: " + produtos.size());
        }
    }

    private void viewAdicionarEstoque() {
        System.out.println("\n--- ADICIONAR ESTOQUE ---");
        System.out.print("Digite o ID do produto: ");
        Long id = Long.parseLong(scanner.nextLine()); // Adicionar try-catch
        System.out.print("Quantidade a adicionar: ");
        int quantidade = Integer.parseInt(scanner.nextLine()); // Adicionar try-catch

        ProdutoEntity produtoAtualizado = controller.adicionarEstoqueProduto(id, quantidade);
        System.out.println("Estoque adicionado com sucesso!");
        System.out.println("Produto: " + produtoAtualizado.getNomeProduto() + " | Novo Estoque: " + produtoAtualizado.getQuantidade());
    }

    private void viewRemoverEstoque() {
        System.out.println("\n--- REMOVER ESTOQUE ---");
        System.out.print("Digite o ID do produto: ");
        Long id = Long.parseLong(scanner.nextLine()); // Adicionar try-catch
        System.out.print("Quantidade a remover: ");
        int quantidade = Integer.parseInt(scanner.nextLine()); // Adicionar try-catch

        ProdutoEntity produtoAtualizado = controller.removerEstoqueProduto(id, quantidade);
        System.out.println("Estoque removido com sucesso!");
        System.out.println("Produto: " + produtoAtualizado.getNomeProduto() + " | Novo Estoque: " + produtoAtualizado.getQuantidade());
    }

    private String formatarProdutoSimples(ProdutoEntity p) {
        if (p == null) return "Dados do produto indisponíveis.";
        String categoriaNome = (p.getCategoria() != null && p.getCategoria().getNome() != null)
                ? p.getCategoria().getNome() : "ID Cat:" + p.getCategoriaId();
        return String.format("ID: %d | Nome: %s | Qtd: %d | Cat: %s",
                p.getId(), p.getNomeProduto(), p.getQuantidade(), categoriaNome);
    }

    private String formatarProdutoDetalhado(ProdutoEntity p) {
        if (p == null) return "Dados do produto indisponíveis.";
        String categoriaInfo = "N/D";
        if (p.getCategoria() != null) {
            categoriaInfo = String.format("%s (ID: %d)", p.getCategoria().getNome(), p.getCategoria().getId());
        } else if (p.getCategoriaId() != null) {
            categoriaInfo = String.format("ID da Categoria: %d (Nome não carregado)", p.getCategoriaId());
        }

        return String.format(
                "  ID Produto: %d\n" +
                        "  Nome: %s\n" +
                        "  Quantidade em Estoque: %d\n" +
                        "  Cor: %s\n" +
                        "  Tamanho: %s\n" +
                        "  Categoria: %s\n" +
                        "  Cadastrado por Usuário ID: %d",
                p.getId(),
                p.getNomeProduto() != null ? p.getNomeProduto() : "N/D",
                p.getQuantidade(),
                p.getCor() != null ? p.getCor() : "N/D",
                p.getTamanho() != null ? p.getTamanho() : "N/D",
                categoriaInfo,
                p.getUsuarioId() != null ? p.getUsuarioId() : -1L
        );
    }
}