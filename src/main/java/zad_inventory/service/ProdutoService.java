package zad_inventory.service;

import zad_inventory.entity.ProdutoEntity;
import zad_inventory.repository.ProdutoRepository;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService() {
        this.produtoRepository = new ProdutoRepository();
    }

    // Salvar ou atualizar produto
    public ProdutoEntity salvarProduto(ProdutoEntity produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    // Buscar todos
    public List<ProdutoEntity> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar por ID
    public ProdutoEntity buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return produtoRepository.findById(id);
    }

    // Deletar produto
    public void deletarProduto(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        produtoRepository.delete(id);
    }

    // Métodos específicos de busca
    public List<ProdutoEntity> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return produtoRepository.findByNomeContaining(nome);
    }

    public List<ProdutoEntity> buscarPorCategoria(int categoriaId) {
        if (categoriaId <= 0) {
            throw new IllegalArgumentException("ID de categoria inválido");
        }
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    // Controle de estoque
    public ProdutoEntity adicionarEstoque(Long produtoId, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        ProdutoEntity produto = produtoRepository.findById(produtoId);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado");
        }

        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return produtoRepository.save(produto);
    }

    public ProdutoEntity removerEstoque(Long produtoId, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        ProdutoEntity produto = produtoRepository.findById(produtoId);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado");
        }

        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Quantidade insuficiente em estoque");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }

    // Validações
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
        if (produto.getCategoriaId() <= 0) {
            throw new IllegalArgumentException("Categoria inválida");
        }
    }

    // Fechar recursos
    public void close() {
        produtoRepository.close();
    }
}
