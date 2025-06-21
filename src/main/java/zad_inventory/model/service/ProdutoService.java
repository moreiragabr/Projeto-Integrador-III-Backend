package zad_inventory.model.service;

import zad_inventory.model.config.DBConnection;
import zad_inventory.model.entity.ProdutoEntity;
import zad_inventory.model.entity.UsuarioEntity;
import zad_inventory.model.repository.CategoriaRepository;
import zad_inventory.model.repository.ProdutoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EntityManager entityManager;

    public ProdutoService() {
        this.entityManager = DBConnection.getEntityManager();
        this.produtoRepository = new ProdutoRepository(entityManager);
        this.categoriaRepository = new CategoriaRepository(entityManager);
    }

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.entityManager = produtoRepository.getEntityManager();
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    public void salvarProduto(ProdutoEntity produto, Long usuarioId) {
        salvarProduto(produto);
    }


    public void salvarProduto(ProdutoEntity produto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            produtoRepository.salvar(produto);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + e.getMessage());
        }
    }

    public List<ProdutoEntity> buscarProdutosPorCategoria(Long categoriaId) {
        return produtoRepository.buscarPorCategoria(categoriaId);
    }

    public ProdutoEntity buscarProdutoPorNome(String nomeProduto) {
        return produtoRepository.buscarPorNomeExato(nomeProduto);
    }



    public ProdutoEntity cadastrarProduto(UsuarioEntity usuario, String nomeProduto, String cor, String tamanho, int quantidade, Long categoriaId) {
        EntityTransaction transaction = entityManager.getTransaction();
        ProdutoEntity produto = new ProdutoEntity();
        try {
            transaction.begin();
            produto.setNomeProduto(nomeProduto);
            produto.setCor(cor);
            produto.setTamanho(tamanho);
            produto.setQuantidade(quantidade);
            produto.setCategoriaId(categoriaId);
            produto.setUsuario(usuario);
            produtoRepository.salvar(produto);
            transaction.commit();
            return produto;
        } catch (Exception e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
            return null;
        }
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoRepository.listarTodos();
    }

    public ProdutoEntity buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }

    public void atualizarProduto(UsuarioEntity usuario, Long id, String nome, String cor, String tamanho, int quantidade, Long categoriaId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ProdutoEntity produto = produtoRepository.buscarPorId(id);
            if (produto != null) {
                produto.setNomeProduto(nome);
                produto.setCor(cor);
                produto.setTamanho(tamanho);
                produto.setQuantidade(quantidade);
                produto.setCategoriaId(categoriaId);
                produto.setUsuario(usuario);
                produtoRepository.salvar(produto);
                transaction.commit();
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado para atualização!");
                transaction.rollback();
            }
        } catch (Exception e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage());
        }
    }
    

    public void removerProduto(UsuarioEntity usuario, Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ProdutoEntity produto = produtoRepository.buscarPorId(id);
            if (produto != null) {
                produtoRepository.excluir(produto);
                transaction.commit();
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                transaction.rollback();
            }
        } catch (Exception e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao remover produto: " + e.getMessage());
        }
    }
}
