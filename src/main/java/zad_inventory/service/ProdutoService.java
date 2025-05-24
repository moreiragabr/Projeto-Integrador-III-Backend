package zad_inventory.service;

import zad_inventory.entity.CategoriaEntity;
import zad_inventory.entity.ProdutoEntity;
import zad_inventory.repository.ProdutoRepository;
import zad_inventory.config.DBConnection;
import javax.persistence.EntityManager;
import zad_inventory.repository.CategoriaRepository;
import java.util.List;

public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;


    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    public ProdutoService() {
        EntityManager em = DBConnection.getEntityManager();
        this.produtoRepository = new ProdutoRepository(em);
        this.categoriaRepository = new CategoriaRepository(em);
    }


    public ProdutoEntity salvarProduto(ProdutoEntity produto, Long usuarioIdLogado) {
        validarProduto(produto);
        if (produto.getCategoriaId() == null || !categoriaRepository.existsById(produto.getCategoriaId())) {
            throw new IllegalArgumentException("Categoria com ID " + produto.getCategoriaId() + " não encontrada ou ID da categoria nulo.");
        }

        CategoriaEntity categoriaAssociada = categoriaRepository.buscarPorId(produto.getCategoriaId());
        if (categoriaAssociada == null) {
            throw new IllegalArgumentException("Categoria com ID " + produto.getCategoriaId() + " não encontrada.");
        }
        produto.setCategoria(categoriaAssociada);
        produto.setUsuarioId(usuarioIdLogado);
        ProdutoEntity produtoSalvo = produtoRepository.save(produto);
        if (produtoSalvo == null) {
            throw new IllegalStateException("Erro ao salvar o produto.");
        }
        return produtoSalvo;
    }

    public List<ProdutoEntity> buscarTodos() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoEntity atualizarProduto(ProdutoEntity produto, Long categoriaIdOriginal) {
        validarProduto(produto);
        if (produto.getId() == null) {
            throw new IllegalArgumentException("Produto precisa de um ID para ser atualizado.");
        }
        if (produto.getCategoriaId() == null || !categoriaRepository.existsById(produto.getCategoriaId())) {
            if (produto.getCategoriaId() == null && categoriaIdOriginal != null && !categoriaRepository.existsById(categoriaIdOriginal)) {
                throw new IllegalArgumentException("Categoria original com ID " + categoriaIdOriginal + " não encontrada.");
            } else if (produto.getCategoriaId() != null) {
                throw new IllegalArgumentException("Nova categoria com ID " + produto.getCategoriaId() + " não encontrada.");
            }
        }
        return produtoRepository.save(produto);
    }

    public void removerProduto(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do produto para remoção não pode ser nulo.");
        }
        produtoRepository.findById(id); // Garante que o produto existe ou lança exceção

        if (produtoRepository.countOperacoesVinculadas(id) > 0) {
            throw new IllegalStateException("Não é possível remover o produto. Existem operações de venda vinculadas a ele.");
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
        if (categoriaId == null) {
            throw new IllegalArgumentException("ID da categoria para busca não pode ser nulo.");
        }
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new IllegalArgumentException("Categoria com ID " + categoriaId + " não encontrada para realizar a busca de produtos.");
        }
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    public ProdutoEntity adicionarEstoque(Long produtoId, int quantidade) {
        if (produtoId == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a ser adicionada deve ser maior que zero.");
        }

        ProdutoEntity produto = buscarPorId(produtoId);
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return produtoRepository.save(produto);
    }

    public ProdutoEntity removerEstoque(Long produtoId, int quantidade) {
        if (produtoId == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a ser removida deve ser maior que zero.");
        }

        ProdutoEntity produto = buscarPorId(produtoId);
        if (produto.getQuantidade() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente para remover a quantidade solicitada. Estoque atual: " + produto.getQuantidade());
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }

    private void validarProduto(ProdutoEntity produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Dados do produto não podem ser nulos.");
        }
        if (produto.getNomeProduto() == null || produto.getNomeProduto().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade do produto não pode ser negativa.");
        }
    }
}

