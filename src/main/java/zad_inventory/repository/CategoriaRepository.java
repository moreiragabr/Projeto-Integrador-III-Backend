package zad_inventory.repository;

import zad_inventory.entity.CategoriaEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaRepository {

    private EntityManager em;

    public CategoriaRepository(EntityManager em) {
        this.em = em;
    }

    public void salvar(CategoriaEntity categoria){
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
    }

    public List<CategoriaEntity> buscarTodos(){
        return em.createQuery("SELECT c FROM CategoriaEntity c", CategoriaEntity.class).getResultList();
    }

    public CategoriaEntity buscarPorId(Long id){
        return em.find(CategoriaEntity.class, id);
    }

    public void atualizar(CategoriaEntity categoria){
        em.getTransaction().begin();
        em.merge(categoria);
        em.getTransaction().commit();
    }

    public void remover(CategoriaEntity categoria){
        em.getTransaction().begin();
        em.remove(em.contains(categoria) ? categoria : em.merge(categoria));
        em.getTransaction().commit();
    }
}