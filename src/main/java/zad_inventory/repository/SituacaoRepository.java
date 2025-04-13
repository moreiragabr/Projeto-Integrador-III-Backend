package zad_inventory.repository;

import zad_inventory.entity.SituacaoEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class SituacaoRepository {

    private EntityManager em;

    public SituacaoRepository(EntityManager em){this.em = em;}

    //CRUD

    public void salvar(SituacaoEntity situacao){
        em.getTransaction().begin();
        em.persist(situacao);
        em.getTransaction().commit();
    }

    public List<SituacaoEntity> buscarTodos(){
        return em.createQuery("SELECT s FROM SituacaoEntity s", SituacaoEntity.class).getResultList();
    }

    public SituacaoEntity buscarPorId(Long id){
        return em.find(SituacaoEntity.class, id);
    }

    public void atualizar(SituacaoEntity situacao){
        em.getTransaction().begin();
        em.merge(situacao);
        em.getTransaction().commit();
    }

    public void remover(SituacaoEntity situacao){
        em.getTransaction().begin();
        em.remove(em.contains(situacao) ? situacao : em.merge(situacao));
        em.getTransaction().commit();
    }
}
