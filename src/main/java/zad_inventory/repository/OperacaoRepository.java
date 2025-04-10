package zad_inventory.repository;

import zad_inventory.entity.OperacaoEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OperacaoRepository {

    private EntityManager em;

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

    public void delete(OperacaoEntity operacao) {
        em.getTransaction().begin();
        em.remove(em.contains(operacao) ? operacao : em.merge(operacao));
        em.getTransaction().commit();
    }

    //CONSULTA POR DATA EXATA
    //Utilizando TypedQuery para consultas personalizadas, visto que o EntityManager não serve
    //para buscas complexas como: por periodo, por parte da string e etc...

    public List<OperacaoEntity> findByDate(LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(LocalTime.MAX);

        TypedQuery<OperacaoEntity> query = em.createQuery(
                "SELECT o FROM OperacaoEntity o WHERE o.data BETWEEN :inicio AND :fim", OperacaoEntity.class);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        return query.getResultList();
    }

    //CONSULTA POR PERIODO
    public List<OperacaoEntity> findByPeriod(LocalDate inicio, LocalDate fim) {
        LocalDateTime dataInicio = inicio.atStartOfDay();
        LocalDateTime dataFim = fim.atTime(LocalTime.MAX);

        TypedQuery<OperacaoEntity> query = em.createQuery(
                "SELECT o FROM OperacaoEntity o WHERE o.data BETWEEN :inicio AND :fim", OperacaoEntity.class);
        query.setParameter("inicio", dataInicio);
        query.setParameter("fim", dataFim);
        return query.getResultList();
    }

    //CONSULTA POR USUARIO
    public List<OperacaoEntity> findByUsuarioNome(String nome) {
        TypedQuery<OperacaoEntity> query = em.createQuery(
                "SELECT o FROM OperacaoEntity o WHERE LOWER(o.usuarioNome) LIKE LOWER(:nome)", OperacaoEntity.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    //CONSULTA POR SITUAÇÃO
    public List<OperacaoEntity> findBySituacao(String situacao) {
        TypedQuery<OperacaoEntity> query = em.createQuery(
                "SELECT o FROM OperacaoEntity o WHERE LOWER(o.situacao) = LOWER(:sit)", OperacaoEntity.class);
        query.setParameter("sit", situacao);
        return query.getResultList();
    }

}
