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

    public void registrarVenda(UsuarioEntity usuario, Long produtoId, int quantidade) {
        ProdutoEntity produto = produtoService.buscarPorId(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (produto.getQuantidade() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente. Atual: " + produto.getQuantidade());
        }

        // atualiza estoque
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoService.salvarProduto(produto);

        // cria e salva operação
        OperacaoEntity op = new OperacaoEntity();
        op.setProduto(produto);
        op.setUsuario(usuario);
        op.setQuantidade(quantidade);
        op.setSituacao(Situacao.REALIZADA);
        op.setData(LocalDateTime.now());
        repo.save(op);
    }


    public List<OperacaoEntity> buscarTodos() {
        return repo.listAll();
    }


    public OperacaoEntity buscarPorId(Long id) {
        return repo.findById(id);
    }


    public void atualizarSituacao(Long id, Situacao novaSituacao) {
        OperacaoEntity op = repo.findById(id);
        if (op == null) {
            throw new IllegalArgumentException("Operação não encontrada.");
        }
        op.setSituacao(novaSituacao);
        repo.update(op);
    }


    public List<OperacaoEntity> filtrarPorSituacao(Situacao situacao) {
        return repo.findBySituacao(situacao);
    }
}
