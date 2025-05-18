package zad_inventory.repository;

import zad_inventory.entity.OperacaoEntity;
import zad_inventory.enums.Situacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OperacaoRepository {

    private final EntityManager em;

    public OperacaoRepository(EntityManager em) {
        this.em = em;
    }

    public void save(OperacaoEntity operacao) {
        em.getTransaction().begin();
        em.persist(operacao);
        em.getTransaction().commit();
    }

    public List<OperacaoEntity> listAll() {
        return em.createQuery("SELECT o FROM OperacaoEntity o ORDER BY o.data DESC", OperacaoEntity.class)
                .getResultList();
    }


    public OperacaoEntity findById(Long id) {
        return em.find(OperacaoEntity.class, id);
    }

    public void update(OperacaoEntity operacao) {
        em.getTransaction().begin();
        em.merge(operacao);
        em.getTransaction().commit();
    }



    //CONSULTA POR SITUAÇÃO
    public List<OperacaoEntity> findBySituacao(Situacao situacao) {
        TypedQuery<OperacaoEntity> query = em.createQuery(
                "SELECT o FROM OperacaoEntity o WHERE o.situacao = :situacao", OperacaoEntity.class);
        query.setParameter("situacao", situacao);
        return query.getResultList();
    }

}
