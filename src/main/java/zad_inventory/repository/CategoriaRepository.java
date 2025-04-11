package zad_inventory.repository;

import zad_inventory.entity.CategoriaEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriaRepository {

    private final EntityManager em;

    public CategoriaRepository(EntityManager em) {
        this.em = em;
    }

    public void salvar(CategoriaEntity categoria) {
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
    }

    public List<CategoriaEntity> listarTodas() {
        return em.createQuery("SELECT c FROM CategoriaEntity c", CategoriaEntity.class).getResultList();
    }

    public CategoriaEntity buscarPorId(Long id) {
        return em.find(CategoriaEntity.class, id);
    }

    public CategoriaEntity buscarPorNome(String nome) {
        TypedQuery<CategoriaEntity> query = em.createQuery(
                "SELECT c FROM CategoriaEntity c WHERE LOWER(c.nome) = LOWER(:nome)", CategoriaEntity.class);
        query.setParameter("nome", nome);
        return query.getResultStream().findFirst().orElse(null);
    }
}
