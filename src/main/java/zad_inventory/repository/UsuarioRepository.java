package zad_inventory.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import zad_inventory.entity.Usuario;

import java.util.List;

public class UsuarioRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("usuarioPU");

    public void salvar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario buscarPorEmail(String email) {
        EntityManager em = emf.createEntityManager();
        List<Usuario> resultado = em
            .createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
            .setParameter("email", email)
            .getResultList();
        em.close();
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public Usuario buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }

    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        em.close();
        return usuarios;
    }

    public boolean removerPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
}
