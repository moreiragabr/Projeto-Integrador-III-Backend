package zad_inventory.repository;

import zad_inventory.entity.ProdutoEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProdutoRepository {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ProdutoRepository() {
        this.emf = Persistence.createEntityManagerFactory("nome-da-sua-unidade-de-persistencia");
        this.em = emf.createEntityManager();
    }

    // Salvar ou atualizar produto
    public ProdutoEntity save(ProdutoEntity produto) {
        em.getTransaction().begin();
        if (produto.getId() == null) {
            em.persist(produto);
        } else {
            produto = em.merge(produto);
        }
        em.getTransaction().commit();
        return produto;
    }

    // Buscar por ID
    public ProdutoEntity findById(Long id) {
        return em.find(ProdutoEntity.class, id);
    }

    // Listar todos
    public List<ProdutoEntity> findAll() {
        TypedQuery<ProdutoEntity> query = em.createQuery("SELECT p FROM ProdutoEntity p", ProdutoEntity.class);
        return query.getResultList();
    }

    // Deletar produto
    public void delete(Long id) {
        em.getTransaction().begin();
        ProdutoEntity produto = em.find(ProdutoEntity.class, id);
        if (produto != null) {
            em.remove(produto);
        }
        em.getTransaction().commit();
    }

    // Buscar por nome (contendo)
    public List<ProdutoEntity> findByNomeContaining(String nome) {
        TypedQuery<ProdutoEntity> query = em.createQuery(
                "SELECT p FROM ProdutoEntity p WHERE p.nomeProduto LIKE :nome", ProdutoEntity.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    // Buscar por categoria
    public List<ProdutoEntity> findByCategoriaId(int categoriaId) {
        TypedQuery<ProdutoEntity> query = em.createQuery(
                "SELECT p FROM ProdutoEntity p WHERE p.categoriaId = :categoriaId", ProdutoEntity.class);
        query.setParameter("categoriaId", categoriaId);
        return query.getResultList();
    }

    // Buscar por cor
    public List<ProdutoEntity> findByCor(String cor) {
        TypedQuery<ProdutoEntity> query = em.createQuery(
                "SELECT p FROM ProdutoEntity p WHERE p.cor = :cor", ProdutoEntity.class);
        query.setParameter("cor", cor);
        return query.getResultList();
    }

    // Buscar por tamanho
    public List<ProdutoEntity> findByTamanho(String tamanho) {
        TypedQuery<ProdutoEntity> query = em.createQuery(
                "SELECT p FROM ProdutoEntity p WHERE p.tamanho = :tamanho", ProdutoEntity.class);
        query.setParameter("tamanho", tamanho);
        return query.getResultList();
    }

    // Fechar EntityManager
    public void close() {
        em.close();
        emf.close();
    }
}