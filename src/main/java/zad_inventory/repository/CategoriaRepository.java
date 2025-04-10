package zad_inventory.repository;

import zad_inventory.entity.CategoriaEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaRepository {

    private EntityManager em;

    //CRUD

    public void salvar(CategoriaRepository categoria){
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
    }

    public List<CategoriaRepository> buscarTodos(){
        return em.createQuery("SELECT s FROM situacao s", CategoriaRepository.class).getResultList();
    }

    public CategoriaRepository buscarPorId(Long id){
        return em.find(CategoriaRepository.class, id);
    }

    public void atualizar(CategoriaRepository categoria){
        em.getTransaction().begin();
        em.merge(categoria);
        em.getTransaction().commit();
    }

    public void remover(CategoriaRepository categoria){
        em.getTransaction().begin();
        em.remove(em.contains(categoria) ? categoria : em.merge(categoria));
        em.getTransaction().commit();
    }
}