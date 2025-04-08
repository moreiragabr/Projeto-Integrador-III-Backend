package zad_inventory.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {

    private final EntityManagerFactory emf;
    
    public ProdutoRepository() {
        this.emf = Persistence.createEntityManagerFactory("produto-pu");
    }
    
    public void salvar(Produto produto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (produto.getId() == null) {
                em.persist(produto);
            } else {
                em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public Optional<Produto> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Produto produto = em.find(Produto.class, id);
            return Optional.ofNullable(produto);
        } finally {
            em.close();
        }
    }
    
    public List<Produto> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            if (produto != null) {
                em.remove(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public List<Produto> buscarPorCor(String cor) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                "SELECT p FROM Produto p WHERE p.cor = :cor", Produto.class);
            query.setParameter("cor", cor);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Produto> buscarPorTamanho(String tamanho) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                "SELECT p FROM Produto p WHERE p.tamanho = :tamanho", Produto.class);
            query.setParameter("tamanho", tamanho);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Produto> buscarPorFormato(String formato) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                "SELECT p FROM Produto p WHERE p.formato = :formato", Produto.class);
            query.setParameter("formato", formato);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Produto> buscarComEstoqueDisponivel() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                "SELECT p FROM Produto p WHERE p.estoque > 0", Produto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
