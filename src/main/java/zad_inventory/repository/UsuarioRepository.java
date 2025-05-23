package zad_inventory.repository;

import zad_inventory.entity.UsuarioEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRepository {

    private final EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    public void salvar(UsuarioEntity usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void remover(UsuarioEntity usuario) {
        em.getTransaction().begin();
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
        em.getTransaction().commit();
    }

    public UsuarioEntity buscarPorId(Long id) {
        return em.find(UsuarioEntity.class, id);
    }

    public UsuarioEntity buscarPorIdComProdutos(Long id) {
        TypedQuery<UsuarioEntity> query = em.createQuery(
                "SELECT u FROM UsuarioEntity u LEFT JOIN FETCH u.produtos WHERE u.id = :id",
                UsuarioEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public UsuarioEntity buscarPorEmail(String email) {
        try {
            TypedQuery<UsuarioEntity> query = em.createQuery(
                    "SELECT u FROM UsuarioEntity u WHERE u.email = :email",
                    UsuarioEntity.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<UsuarioEntity> listarTodos() {
        return em.createQuery(
                        "SELECT u FROM UsuarioEntity u",
                        UsuarioEntity.class)
                .getResultList();
    }

    public List<UsuarioEntity> listarComTotalProdutos() {
        List<Object[]> resultados = em.createQuery(
                        "SELECT u, COUNT(p) as total " +
                                "FROM UsuarioEntity u LEFT JOIN u.produtos p " +
                                "GROUP BY u", Object[].class)
                .getResultList();

        em.getTransaction().begin();

        List<UsuarioEntity> usuarios = resultados.stream().map(result -> {
            UsuarioEntity usuario = (UsuarioEntity) result[0];
            Long total = (Long) result[1];
            usuario.setTotalProdutos(total);
            return em.merge(usuario);
        }).collect(Collectors.toList());

        em.getTransaction().commit();

        return usuarios;
    }
}