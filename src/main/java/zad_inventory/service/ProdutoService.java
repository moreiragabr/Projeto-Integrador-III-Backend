package zad_inventory.service;

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

    public ProdutoEntity salvarProduto(ProdutoEntity produto) {
        validarProduto(produto);
        if (!categoriaRepository.existsById(produto.getCategoriaId())) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }
        return produtoRepository.save(produto);
    }

    public List<ProdutoEntity> buscarTodos() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity buscarPorId(Long id) {
        ProdutoEntity produto = produtoRepository.findById(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return produto;
    }

    public ProdutoEntity atualizar(ProdutoEntity produto) {
        validarProduto(produto);
        if (!categoriaRepository.existsById(produto.getCategoriaId())) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }
        return produtoRepository.save(produto);
    }

    public void remover(Long id) {
        if (produtoRepository.countOperacoesVinculadas(id) > 0) {
            throw new IllegalStateException("Produto possui operações vinculadas");
        }
        produtoRepository.delete(id);
    }

    public List<ProdutoEntity> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return produtoRepository.findByNomeContaining(nome);
    }

    public List<ProdutoEntity> buscarPorCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    public ProdutoEntity adicionarEstoque(Long produtoId, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        ProdutoEntity produto = buscarPorId(produtoId);
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return produtoRepository.save(produto);
    }

    public ProdutoEntity removerEstoque(Long produtoId, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        ProdutoEntity produto = buscarPorId(produtoId);
        if (produto.getQuantidade() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }

    private void validarProduto(ProdutoEntity produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (produto.getNomeProduto() == null || produto.getNomeProduto().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        if (produto.getCategoriaId() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }
    }

    public void close() {
        produtoRepository.close();
    }
}