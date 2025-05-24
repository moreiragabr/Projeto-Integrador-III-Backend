package zad_inventory.controller;

import zad_inventory.entity.ProdutoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.service.ProdutoService;
import zad_inventory.repository.ProdutoRepository;
import zad_inventory.repository.CategoriaRepository;
import zad_inventory.config.DBConnection;
import javax.persistence.EntityManager;
import java.util.List;


public class ProdutoController {
    private final ProdutoService produtoService;
    private final UsuarioEntity usuarioLogado;
    private final EntityManager em;

    public ProdutoController(UsuarioEntity usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.em = DBConnection.getEntityManager();


        ProdutoRepository produtoRepo = new ProdutoRepository(this.em);
        CategoriaRepository categoriaRepo = new CategoriaRepository(this.em);
        this.produtoService = new ProdutoService(produtoRepo, categoriaRepo);
    }

    public ProdutoEntity cadastrarProduto(String nome, String cor, String tamanho, int quantidade, Long categoriaId) {
        if (usuarioLogado == null || usuarioLogado.getId() == null) {
            throw new IllegalStateException("Nenhum usuário logado. Operação de cadastro de produto não permitida.");
        }

        ProdutoEntity novoProduto = new ProdutoEntity();
        novoProduto.setNomeProduto(nome);
        novoProduto.setCor(cor);
        novoProduto.setTamanho(tamanho);
        novoProduto.setQuantidade(quantidade);
        novoProduto.setCategoriaId(categoriaId);
        // ID atribuido pelo service
        return produtoService.salvarProduto(novoProduto, usuarioLogado.getId());
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoService.buscarTodos();
    }

    public ProdutoEntity buscarProdutoPorId(Long id) {

        return produtoService.buscarPorId(id);
    }

    public ProdutoEntity atualizarProduto(Long id, String nome, String cor, String tamanho, Integer quantidade, Long categoriaId) {
        ProdutoEntity produtoExistente = produtoService.buscarPorId(id);


        Long categoriaIdOriginal = produtoExistente.getCategoriaId();


        if (nome != null && !nome.trim().isEmpty()) {
            produtoExistente.setNomeProduto(nome);
        }
        if (cor != null && !cor.trim().isEmpty()) {
            produtoExistente.setCor(cor);
        }
        if (tamanho != null && !tamanho.trim().isEmpty()) {
            produtoExistente.setTamanho(tamanho);
        }
        if (quantidade != null) {
            produtoExistente.setQuantidade(quantidade);
        }
        if (categoriaId != null) {
            produtoExistente.setCategoriaId(categoriaId);
        }


        return produtoService.atualizarProduto(produtoExistente, categoriaIdOriginal);
    }

    public void removerProduto(Long id) {
        produtoService.removerProduto(id);
    }

    public List<ProdutoEntity> buscarProdutosPorNome(String nome) {
        return produtoService.buscarPorNome(nome);
    }

    public List<ProdutoEntity> buscarProdutosPorCategoria(Long categoriaId) {
        return produtoService.buscarPorCategoria(categoriaId);
    }

    public ProdutoEntity adicionarEstoqueProduto(Long id, int quantidadeParaAdicionar) {
        return produtoService.adicionarEstoque(id, quantidadeParaAdicionar);
    }

    public ProdutoEntity removerEstoqueProduto(Long id, int quantidadeParaRemover) {
        return produtoService.removerEstoque(id, quantidadeParaRemover);
    }


    public void close() {
        if (this.em != null && this.em.isOpen()) {
            this.em.close();
        }
    }
}