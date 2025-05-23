package zad_inventory.repository;

import zad_inventory.entity.ProdutoEntity;
import zad_inventory.entity.CategoriaEntity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProdutoRepository {
    private final EntityManager em;

    public ProdutoRepository(EntityManager em) {
        this.em = em;
    }

    public ProdutoEntity save(ProdutoEntity produto) {
        try {
            em.getTransaction().begin();
            if (produto.getId() == null) {
                em.persist(produto);
            } else {
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
            return produto;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    public ProdutoEntity findById(Long id) {
        try {
            return em.createQuery(
                            "SELECT p FROM ProdutoEntity p " +
                                    "LEFT JOIN FETCH p.categoria " +
                                    "WHERE p.id = :id", ProdutoEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Produto não encontrado com ID: " + id, e);
        }
    }

    public List<ProdutoEntity> findAll() {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria", ProdutoEntity.class)
                .getResultList();
    }

    public void delete(Long id) {
        try {
            em.getTransaction().begin();
            ProdutoEntity produto = findById(id);
            if (produto != null) {
                em.remove(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover produto", e);
        }
    }

    public List<ProdutoEntity> findByNomeContaining(String nome) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria " +
                                "WHERE p.nomeProduto LIKE :nome", ProdutoEntity.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public List<ProdutoEntity> findByCategoriaId(Long categoriaId) {
        return em.createQuery(
                        "SELECT p FROM ProdutoEntity p " +
                                "LEFT JOIN FETCH p.categoria " +
                                "WHERE p.categoria.id = :categoriaId", ProdutoEntity.class)
                .setParameter("categoriaId", categoriaId)
                .getResultList();
    }

    public int countByCategoriaId(Long categoriaId) {
        return em.createQuery(
                        "SELECT COUNT(p) FROM ProdutoEntity p " +
                                "WHERE p.categoria.id = :categoriaId", Long.class)
                .setParameter("categoriaId", categoriaId)
                .getSingleResult()
                .intValue();
    }

    public int countOperacoesVinculadas(Long produtoId) {
        return em.createQuery(
                        "SELECT COUNT(o) FROM OperacaoEntity o " +
                                "WHERE o.produto.id = :produtoId", Long.class)
                .setParameter("produtoId", produtoId)
                .getSingleResult()
                .intValue();
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}