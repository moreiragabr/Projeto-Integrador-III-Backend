package zad_inventory.service;

import zad_inventory.entity.CategoriaEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class CategoriaService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(CategoriaEntity categoria) {
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

    @Transactional
    public void atualizar(CategoriaEntity categoria){
        em.getTransaction().begin();
        em.merge(categoria);
        em.getTransaction().commit();
    }

    @Transactional
    public void remover(CategoriaEntity categoria){
        em.getTransaction().begin();
        em.remove(em.contains(categoria) ? categoria : em.merge(categoria));
        em.getTransaction().commit();
    }
}