package zad_inventory.repository;

import zad_inventory.entity.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRepository {

    private final EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    // Salvar usuário
    public void salvar(UsuarioEntity usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    // Remover usuário
    public void remover(UsuarioEntity usuario) {
        em.getTransaction().begin();
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
        em.getTransaction().commit();
    }

    // Buscar por ID
    public UsuarioEntity buscarPorId(Long id) {
        return em.find(UsuarioEntity.class, id);
    }

    // Buscar por ID com produtos (carregamento eager)
    public UsuarioEntity buscarPorIdComProdutos(Long id) {
        TypedQuery<UsuarioEntity> query = em.createQuery(
                "SELECT u FROM UsuarioEntity u LEFT JOIN FETCH u.produtos WHERE u.id = :id",
                UsuarioEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    // Buscar por email
    public UsuarioEntity buscarPorEmail(String email) {
        TypedQuery<UsuarioEntity> query = em.createQuery(
                "SELECT u FROM UsuarioEntity u WHERE u.email = :email",
                UsuarioEntity.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    // Listar todos
    public List<UsuarioEntity> listarTodos() {
        return em.createQuery(
                        "SELECT u FROM UsuarioEntity u",
                        UsuarioEntity.class)
                .getResultList();
    }

    // Listar com contagem de produtos (substitui o @Formula)
    public List<UsuarioEntity> listarComTotalProdutos() {
        return em.createQuery(
                        "SELECT u, COUNT(p) as total " +
                                "FROM UsuarioEntity u LEFT JOIN u.produtos p " +
                                "GROUP BY u",
                        Object[].class)
                .getResultList()
                .stream()
                .map(result -> {
                    UsuarioEntity usuario = (UsuarioEntity) result[0];
                    usuario.setTotalProdutos((Long) result[1]); // ← Agora funciona!
                    return usuario;
                })
                .collect(Collectors.toList());
    }
}