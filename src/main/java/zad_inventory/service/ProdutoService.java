package zad_inventory.service;

import zad_inventory.entity.Produto;
import zad_inventory.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Operações básicas de CRUD

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void removerProduto(Long id) {
        produtoRepository.delete(id);
    }

    // Operações de negócio específicas

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public List<Produto> buscarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoria(categoriaId);
    }

    public List<Produto> buscarPorFaixaDePreco(BigDecimal precoMin, BigDecimal precoMax) {
        return produtoRepository.findByPrecoBetween(precoMin, precoMax);
    }

    public List<Produto> listarProdutosComEstoqueBaixo(int nivelMinimo) {
        return produtoRepository.findComEstoqueBaixo(nivelMinimo);
    }

    public Produto atualizarEstoque(Long produtoId, int quantidade) {
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            produto.setEstoque(produto.getEstoque() + quantidade);
            return produtoRepository.save(produto);
        }
        throw new IllegalArgumentException("Produto não encontrado com ID: " + produtoId);
    }

    public long contarProdutos() {
        return produtoRepository.count();
    }

    // Validações e regras de negócio adicionais

    public void validarProduto(Produto produto) {
        if (produto.getNomeProduto() == null || produto.getNomeProduto().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        if (produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (produto.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }
    }
}
