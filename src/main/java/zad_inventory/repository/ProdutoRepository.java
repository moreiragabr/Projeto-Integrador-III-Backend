package zad_inventory.repository;

import zad_inventory.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {

    private final EntityManager entityManager;

    public ProdutoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Operações CRUD básicas

    public Produto save(Produto produto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (produto.getIdProduto() == null) {
                entityManager.persist(produto);
            } else {
                produto = entityManager.merge(produto);
            }
            transaction.commit();
            return produto;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Optional<Produto> findById(Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        return Optional.ofNullable(produto);
    }

    public List<Produto> findAll() {
        TypedQuery<Produto> query = entityManager.createQuery(
            "SELECT p FROM Produto p", Produto.class);
        return query.getResultList();
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Produto produto = entityManager.find(Produto.class, id);
            if (produto != null) {
                entityManager.remove(produto);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Consultas específicas

    public List<Produto> findByNome(String nome) {
        TypedQuery<Produto> query = entityManager.createQuery(
            "SELECT p FROM Produto p WHERE p.nomeProduto LIKE :nome", Produto.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    public List<Produto> findByCategoria(Long categoriaId) {
        TypedQuery<Produto> query = entityManager.createQuery(
            "SELECT p FROM Produto p WHERE p.categoria.id = :categoriaId", Produto.class);
        query.setParameter("categoriaId", categoriaId);
        return query.getResultList();
    }

    public List<Produto> findByPrecoBetween(BigDecimal min, BigDecimal max) {
        TypedQuery<Produto> query = entityManager.createQuery(
            "SELECT p FROM Produto p WHERE p.preco BETWEEN :min AND :max", Produto.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    public List<Produto> findComEstoqueBaixo(int nivel) {
        TypedQuery<Produto> query = entityManager.createQuery(
            "SELECT p FROM Produto p WHERE p.estoque <= :nivel", Produto.class);
        query.setParameter("nivel", nivel);
        return query.getResultList();
    }

    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(p) FROM Produto p", Long.class);
        return query.getSingleResult();
    }
}
