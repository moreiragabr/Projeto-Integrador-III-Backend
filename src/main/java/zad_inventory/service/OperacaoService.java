package zad_inventory.service;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.OperacaoEntity;
import zad_inventory.entity.ProdutoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.Situacao;
import zad_inventory.repository.CategoriaRepository;
import zad_inventory.repository.OperacaoRepository;
import zad_inventory.repository.ProdutoRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class OperacaoService {

    private final OperacaoRepository repo;
    private final ProdutoService produtoService;

    public OperacaoService() {
        EntityManager em = DBConnection.getEntityManager();
        this.repo = new OperacaoRepository(em);
        this.produtoService = new ProdutoService(
                new ProdutoRepository(em),
                new CategoriaRepository(em)
        );
    }

    public OperacaoService(OperacaoRepository operacaoRepository, ProdutoService produtoService) {
        this.repo = operacaoRepository;
        this.produtoService = produtoService;
    }

    public OperacaoEntity registrarVenda(UsuarioEntity usuario, Long produtoId, int quantidade) {
        ProdutoEntity produto = produtoService.buscarPorId(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + produtoId);
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (produto.getQuantidade() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente para o produto '" + produto.getNomeProduto() + "'. Atual: " + produto.getQuantidade());
        }

        // Atualiza estoque
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoService.salvarProduto(produto, usuario.getId()); // LINHA CORRIGIDA

        // Cria e salva operação
        OperacaoEntity op = new OperacaoEntity();
        op.setProduto(produto);
        op.setUsuario(usuario);
        op.setQuantidade(quantidade);
        op.setSituacao(Situacao.REALIZADA);
        op.setData(LocalDateTime.now());
        repo.save(op);
        return op; // Return da entidade criada
    }


    public List<OperacaoEntity> buscarTodos() {
        return repo.listAll();
    }


    public OperacaoEntity buscarPorId(Long id) {
        OperacaoEntity op = repo.findById(id);
        if (op == null) {
            throw new IllegalArgumentException("Operação não encontrada com ID: " + id);
        }
        return op;
    }


    public OperacaoEntity atualizarSituacao(Long id, Situacao novaSituacao) {
        OperacaoEntity op = repo.findById(id);
        if (op == null) {
            throw new IllegalArgumentException("Operação não encontrada para atualização com ID: " + id);
        }
        op.setSituacao(novaSituacao);
        repo.update(op);
        return op; // Return da entidade atualizada
    }


    public List<OperacaoEntity> filtrarPorSituacao(Situacao situacao) {
        if (situacao == null) {
            throw new IllegalArgumentException("Situação para filtro não pode ser nula.");
        }
        return repo.findBySituacao(situacao);
    }
}
