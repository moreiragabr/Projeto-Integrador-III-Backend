package zad_inventory.repository;

import zad_inventory.entity.CategoriaEntity;
import zad_inventory.entity.ProdutoEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProdutoRepository {

    private final EntityManager em;

    public ProdutoRepository(EntityManager em) {
        this.em = em;
    }

    // Salvar ou atualizar produto (com tratamento transacional melhorado)
    public ProdutoEntity save(ProdutoEntity produto) {
        try {
            em.getTransaction().begin();

            if (produto.getId() == null) {
                em.persist(produto);
            } else {
                produto = em.merge(produto);
            }

            // Força o carregamento da categoria imediatamente
            if (produto.getCategoriaId() != null) {
                produto.setCategoria(em.getReference(CategoriaEntity.class, produto.getCategoriaId()));
            }

            em.getTransaction().commit();
            return produto;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    // Buscar por ID (com JOIN FETCH para evitar LazyInitializationException)
    public ProdutoEntity findById(Long id) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria " +
                                "LEFT JOIN FETCH p.usuario " +
                                "WHERE p.id = :id", ProdutoEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // Listar todos (com JOIN FETCH para categoria e usuário)
    public List<ProdutoEntity> findAll() {
        List<ProdutoEntity> produtos = em.createQuery(
                        "SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.categoria",
                        ProdutoEntity.class)
                .getResultList();

        // Carrega as categorias para garantir
        produtos.forEach(p -> {
            if (p.getCategoria() == null && p.getCategoriaId() != null) {
                p.setCategoria(em.find(CategoriaEntity.class, p.getCategoriaId()));
            }
        });

        return produtos;
    }

    // Deletar produto (com tratamento transacional melhorado)
    public void delete(Long id) {
        try {
            em.getTransaction().begin();
            ProdutoEntity produto = findById(id); // Usa o método com JOIN FETCH
            if (produto != null) {
                em.remove(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    // Buscar por nome (contendo)
    public List<ProdutoEntity> findByNomeContaining(String nome) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria " +
                                "WHERE p.nomeProduto LIKE :nome",
                        ProdutoEntity.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    // Buscar por categoria (com JOIN FETCH)
    public List<ProdutoEntity> findByCategoriaId(Long categoriaId) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria " +
                                "WHERE p.categoria.id = :categoriaId",
                        ProdutoEntity.class)
                .setParameter("categoriaId", categoriaId)
                .getResultList();
    }

    // Buscar por nome da categoria (com JOIN FETCH)
    public List<ProdutoEntity> findByNomeCategoria(String nomeCategoria) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "JOIN FETCH p.categoria c " +
                                "WHERE c.nome = :nomeCategoria",
                        ProdutoEntity.class)
                .setParameter("nomeCategoria", nomeCategoria)
                .getResultList();
    }

    // Contar produtos por categoria
    public int countByCategoriaId(Long categoriaId) {
        Long count = em.createQuery(
                        "SELECT COUNT(p) FROM ProdutoEntity p " +
                                "WHERE p.categoria.id = :categoriaId",
                        Long.class)
                .setParameter("categoriaId", categoriaId)
                .getSingleResult();
        return count != null ? count.intValue() : 0;
    }

    // Buscar por cor (com JOIN FETCH)
    public List<ProdutoEntity> findByCor(String cor) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria " +
                                "WHERE p.cor = :cor",
                        ProdutoEntity.class)
                .setParameter("cor", cor)
                .getResultList();
    }

    // Buscar por tamanho (com JOIN FETCH)
    public List<ProdutoEntity> findByTamanho(String tamanho) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria " +
                                "WHERE p.tamanho = :tamanho",
                        ProdutoEntity.class)
                .setParameter("tamanho", tamanho)
                .getResultList();
    }

    // Contar operações vinculadas
    public int countOperacoesVinculadas(Long produtoId) {
        Long count = em.createQuery(
                        "SELECT COUNT(o) FROM OperacaoEntity o " +
                                "WHERE o.produto.id = :produtoId",
                        Long.class)
                .setParameter("produtoId", produtoId)
                .getSingleResult();
        return count != null ? count.intValue() : 0;
    }

    // Fechar EntityManager
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}