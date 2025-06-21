package zad_inventory.model.repository;

import zad_inventory.model.entity.ProdutoEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ProdutoRepository {

    private final EntityManager entityManager;

    public ProdutoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void salvar(ProdutoEntity produto) {
        if (produto.getId() == null) {
            entityManager.persist(produto);
        } else {
            entityManager.merge(produto);
        }
    }

    public ProdutoEntity buscarPorId(Long id) {
        try {
            return entityManager.createQuery(
                            "SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.categoria WHERE p.id = :id",
                            ProdutoEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ProdutoEntity> listarTodos() {
        return entityManager.createQuery(
                        "SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.categoria",
                        ProdutoEntity.class)
                .getResultList();
    }

    public void excluir(ProdutoEntity produto) {
        entityManager.remove(produto);
    }


    public List<ProdutoEntity> buscarPorCategoria(Long categoriaId) {
        return entityManager.createQuery(
                        "SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.categoria " +
                                "WHERE p.categoria.id = :categoriaId",
                        ProdutoEntity.class)
                .setParameter("categoriaId", categoriaId)
                .getResultList();
    }

    public ProdutoEntity buscarPorNomeExato(String nomeProduto) {
        try {
            return entityManager.createQuery(
                            "SELECT p FROM ProdutoEntity p WHERE LOWER(p.nomeProduto) = LOWER(:nome)",
                            ProdutoEntity.class)
                    .setParameter("nome", nomeProduto)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
