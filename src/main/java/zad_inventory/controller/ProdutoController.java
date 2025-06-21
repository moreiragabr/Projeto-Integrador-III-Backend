package zad_inventory.controller;

import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.model.service.ProdutoService;

import java.util.List;

public class ProdutoController {

    private final ProdutoService produtoService;
    private final UsuarioEntity usuarioLogado;

    public ProdutoController(UsuarioEntity usuarioLogado) {
        this.produtoService = new ProdutoService();
        this.usuarioLogado = usuarioLogado;
    }

    public ProdutoEntity cadastrarProduto(String nomeProduto, String cor, String tamanho, int quantidade, Long categoriaId) {
        return produtoService.cadastrarProduto(usuarioLogado, nomeProduto, cor, tamanho, quantidade, categoriaId);
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoService.listarTodosProdutos();
    }

    public ProdutoEntity buscarProdutoPorId(Long id) {
        return produtoService.buscarPorId(id);
    }

    public void removerProduto(Long id) {
        produtoService.removerProduto(usuarioLogado, id);
    }

    public List<ProdutoEntity> buscarProdutosPorCategoria(Long categoriaId) {
        return produtoService.buscarProdutosPorCategoria(categoriaId);
    }

    public ProdutoEntity buscarProdutoPorNome(String nomeProduto) {
        return produtoService.buscarProdutoPorNome(nomeProduto);
    }

    public void atualizarProduto(Long id, String nome, String cor, String tamanho, int quantidade, Long categoriaId) {
        produtoService.atualizarProduto(usuarioLogado, id, nome, cor, tamanho, quantidade, categoriaId);
    }




}
