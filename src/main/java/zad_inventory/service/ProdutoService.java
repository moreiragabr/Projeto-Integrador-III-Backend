package zad_inventory.service;

import zad_inventory.entity.CategoriaEntity;
import zad_inventory.entity.ProdutoEntity;
import zad_inventory.repository.ProdutoRepository;
import zad_inventory.repository.CategoriaRepository;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Salvar ou atualizar produto (versão melhorada)
    public ProdutoEntity salvarProduto(ProdutoEntity produto) {
        validarProduto(produto);

        // Garante que a categoria existe
        if (!categoriaRepository.existsById(produto.getCategoriaId())) {
            throw new IllegalArgumentException("Categoria não encontrada com ID: " + produto.getCategoriaId());
        }

        return produtoRepository.save(produto);
    }

    public String verificarCategoriaNoBanco(Long categoriaId) {
        if (categoriaId == null) return "ID nulo";

        try {
            CategoriaEntity categoria = categoriaRepository.buscarPorId(categoriaId);
            return categoria != null ?
                    "Encontrada: " + categoria.getNome() :
                    "Não encontrada (ID: " + categoriaId + ")";
        } catch (Exception e) {
            return "Erro ao buscar: " + e.getMessage();
        }
    }

    // Buscar todos (com JOIN FETCH)
    public List<ProdutoEntity> listarTodos() {
        List<ProdutoEntity> produtos = produtoRepository.findAll();

        // Verificação opcional para debug
        produtos.forEach(p -> {
            if (p.getCategoria() == null) {
                System.out.println("[DEBUG] Produto ID " + p.getId() + " tem categoria nula");
            }
        });

        return produtos;
    }

    // Buscar por ID (com JOIN FETCH)
    public ProdutoEntity buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        ProdutoEntity produto = produtoRepository.findById(id);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        return produto;
    }

    // Deletar produto (versão melhorada)
    public void deletarProduto(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        ProdutoEntity produto = buscarPorId(id); // Usa o método que já faz JOIN FETCH

        int totalOperacoes = produtoRepository.countOperacoesVinculadas(id);
        if (totalOperacoes > 0) {
            throw new RuntimeException("Não é possível excluir - produto possui " + totalOperacoes + " operações vinculadas.");
        }

        produtoRepository.delete(id);
    }

    // Contar produtos por categoria
    public int contarProdutosPorCategoria(Long categoriaId) {
        if (categoriaId == null || categoriaId <= 0) {
            throw new IllegalArgumentException("ID de categoria inválido");
        }
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new IllegalArgumentException("Categoria não existe");
        }
        return produtoRepository.countByCategoriaId(categoriaId);
    }

    // Buscar por nome (com JOIN FETCH)
    public List<ProdutoEntity> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return produtoRepository.findByNomeContaining(nome);
    }

    // Buscar por categoria (com JOIN FETCH)
    public List<ProdutoEntity> buscarPorCategoria(Long categoriaId) {
        if (categoriaId == null || categoriaId <= 0) {
            throw new IllegalArgumentException("ID de categoria inválido");
        }
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new IllegalArgumentException("Categoria não existe");
        }
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    // Buscar por nome de categoria (com JOIN FETCH)
    public List<ProdutoEntity> buscarPorNomeCategoria(String nomeCategoria) {
        if (nomeCategoria == null || nomeCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
        }
        return produtoRepository.findByNomeCategoria(nomeCategoria);
    }

    // Controle de estoque (versão melhorada)
    public ProdutoEntity adicionarEstoque(Long produtoId, int quantidade) {
        if (produtoId == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        ProdutoEntity produto = buscarPorId(produtoId);
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return produtoRepository.save(produto);
    }

    public ProdutoEntity removerEstoque(Long produtoId, int quantidade) {
        if (produtoId == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        ProdutoEntity produto = buscarPorId(produtoId);
        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente. Disponível: " + produto.getQuantidade());
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }

    // Validações melhoradas
    private void validarProduto(ProdutoEntity produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (produto.getNomeProduto() == null || produto.getNomeProduto().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        if (produto.getCategoriaId() == null || produto.getCategoriaId() <= 0) {
            throw new IllegalArgumentException("ID da categoria inválido");
        }
    }

    // Fechar recursos
    public void close() {
        if (produtoRepository != null) {
            produtoRepository.close();
        }
    }
}