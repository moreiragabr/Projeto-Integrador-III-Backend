package zad_inventory.repository;

import zad_inventory.entity.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public UsuarioEntity buscarPorEmail(String email) {
        TypedQuery<UsuarioEntity> query = em.createQuery(
            "SELECT u FROM UsuarioEntity u WHERE u.email = :email", UsuarioEntity.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public List<UsuarioEntity> listarTodos() {
        return em.createQuery("SELECT u FROM UsuarioEntity u", UsuarioEntity.class).getResultList();
    }
}
