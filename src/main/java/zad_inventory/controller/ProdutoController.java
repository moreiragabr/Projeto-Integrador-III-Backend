package zad_inventory.controller;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.ProdutoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.repository.ProdutoRepository;
import zad_inventory.repository.CategoriaRepository;
import zad_inventory.service.ProdutoService;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ProdutoController {
    private final ProdutoService service;
    private final Scanner scanner;
    private final UsuarioEntity usuarioLogado;

    public ProdutoController(UsuarioEntity usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        EntityManager em = DBConnection.getEntityManager();
        ProdutoRepository produtoRepo = new ProdutoRepository(em);
        CategoriaRepository categoriaRepo = new CategoriaRepository(em);
        this.service = new ProdutoService(produtoRepo, categoriaRepo);
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarProduto() {
        if (usuarioLogado == null || usuarioLogado.getId() == null) {
            System.out.println("\nErro: Nenhum usuário logado. Operação não permitida.");
            return;
        }

        System.out.println("\n--- CADASTRAR PRODUTO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("ID da Categoria: ");
        Long categoriaId = Long.parseLong(scanner.nextLine());

        ProdutoEntity novoProduto = new ProdutoEntity();
        novoProduto.setNomeProduto(nome);
        novoProduto.setCor(cor);
        novoProduto.setTamanho(tamanho);
        novoProduto.setQuantidade(quantidade);
        novoProduto.setCategoriaId(categoriaId);
        novoProduto.setUsuarioId(usuarioLogado.getId());

        try {
            ProdutoEntity produtoSalvo = service.salvarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso! ID: " + produtoSalvo.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        try {
            List<ProdutoEntity> produtos = service.buscarTodos();
            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
                return;
            }

            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s\n",
                    "ID", "Nome", "Qtd", "Categoria", "Cor", "Tamanho");
            produtos.forEach(p -> {
                System.out.printf("%-5d %-20s %-10d %-10d %-10s %-10s\n",
                        p.getId(), p.getNomeProduto(), p.getQuantidade(),
                        p.getCategoriaId(), p.getCor(), p.getTamanho());
            });
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    public void buscarProdutoPorId() {
        System.out.println("\n--- BUSCAR PRODUTO POR ID ---");
        System.out.print("Digite o ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            ProdutoEntity produto = service.buscarPorId(id);
            System.out.println("\nDados do Produto:");
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNomeProduto());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Categoria ID: " + produto.getCategoriaId());
            System.out.println("Cor: " + produto.getCor());
            System.out.println("Tamanho: " + produto.getTamanho());
        } catch (Exception e) {
            System.out.println("Erro na busca: " + e.getMessage());
        }
    }

    public void atualizarProduto() {
        System.out.println("\n--- ATUALIZAR PRODUTO ---");
        System.out.print("Digite o ID do produto: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            ProdutoEntity produto = service.buscarPorId(id);

            System.out.println("\nDeixe em branco para manter o valor atual");
            System.out.print("Novo nome [" + produto.getNomeProduto() + "]: ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) produto.setNomeProduto(nome);

            System.out.print("Nova quantidade [" + produto.getQuantidade() + "]: ");
            String qtdStr = scanner.nextLine();
            if (!qtdStr.isEmpty()) produto.setQuantidade(Integer.parseInt(qtdStr));

            System.out.print("Nova cor [" + produto.getCor() + "]: ");
            String cor = scanner.nextLine();
            if (!cor.isEmpty()) produto.setCor(cor);

            System.out.print("Novo tamanho [" + produto.getTamanho() + "]: ");
            String tamanho = scanner.nextLine();
            if (!tamanho.isEmpty()) produto.setTamanho(tamanho);

            System.out.print("Novo ID de Categoria [" + produto.getCategoriaId() + "]: ");
            String catStr = scanner.nextLine();
            if (!catStr.isEmpty()) produto.setCategoriaId(Long.parseLong(catStr));

            service.atualizar(produto);
            System.out.println("Produto atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void removerProduto() {
        System.out.println("\n--- REMOVER PRODUTO ---");
        System.out.print("Digite o ID do produto: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            service.remover(id);
            System.out.println("Produto removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    public void buscarPorNome() {
        System.out.println("\n--- BUSCAR POR NOME ---");
        System.out.print("Digite o nome ou parte: ");
        try {
            String nome = scanner.nextLine();
            List<ProdutoEntity> produtos = service.buscarPorNome(nome);

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto encontrado.");
                return;
            }

            System.out.println("\nResultados:");
            produtos.forEach(p -> {
                System.out.printf("ID: %d | Nome: %s | Qtd: %d | Categoria: %d\n",
                        p.getId(), p.getNomeProduto(), p.getQuantidade(), p.getCategoriaId());
            });
        } catch (Exception e) {
            System.out.println("Erro na busca: " + e.getMessage());
        }
    }

    public void buscarPorCategoria() {
        System.out.println("\n--- BUSCAR POR CATEGORIA ---");
        System.out.print("Digite o ID da categoria: ");
        try {
            Long categoriaId = Long.parseLong(scanner.nextLine());
            List<ProdutoEntity> produtos = service.buscarPorCategoria(categoriaId);

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto nesta categoria.");
                return;
            }

            System.out.println("\nProdutos na categoria:");
            produtos.forEach(p -> {
                System.out.printf("ID: %d | Nome: %s | Qtd: %d\n",
                        p.getId(), p.getNomeProduto(), p.getQuantidade());
            });
        } catch (Exception e) {
            System.out.println("Erro na busca: " + e.getMessage());
        }
    }

    public void adicionarEstoque() {
        System.out.println("\n--- ADICIONAR ESTOQUE ---");
        System.out.print("ID do produto: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Quantidade a adicionar: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        try {
            ProdutoEntity produto = service.adicionarEstoque(id, quantidade);
            System.out.printf("Estoque atualizado! Nova quantidade: %d\n", produto.getQuantidade());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void removerEstoque() {
        System.out.println("\n--- REMOVER ESTOQUE ---");
        System.out.print("ID do produto: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Quantidade a remover: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        try {
            ProdutoEntity produto = service.removerEstoque(id, quantidade);
            System.out.printf("Estoque atualizado! Nova quantidade: %d\n", produto.getQuantidade());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void close() {
        service.close();
    }
}