package zad_inventory.repository;

import zad_inventory.entity.CategoriaEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriaRepository {

    private final EntityManager em;

    public CategoriaRepository(EntityManager em) {
        this.em = em;
    }

    // Salvar categoria com tratamento transacional
    public CategoriaEntity salvar(CategoriaEntity categoria) {
        try {
            em.getTransaction().begin();
            if (categoria.getId() == null) {
                em.persist(categoria);
            } else {
                categoria = em.merge(categoria);
            }
            em.getTransaction().commit();
            return categoria;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar categoria: " + e.getMessage(), e);
        }
    }

    // Buscar todas as categorias
    public List<CategoriaEntity> buscarTodos() {
        return em.createQuery("SELECT c FROM CategoriaEntity c", CategoriaEntity.class)
                .getResultList();
    }

    // Buscar por ID com tratamento de exceção
    public CategoriaEntity buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return em.find(CategoriaEntity.class, id);
    }

    // Verificar existência por ID (NOVO MÉTODO)
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(c) FROM CategoriaEntity c WHERE c.id = :id",
                            Long.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return count != null && count > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    // Buscar por nome (NOVO MÉTODO ÚTIL)
    public CategoriaEntity buscarPorNome(String nome) {
        try {
            return em.createQuery(
                            "SELECT c FROM CategoriaEntity c WHERE c.nome = :nome",
                            CategoriaEntity.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Atualizar categoria com validação
    public CategoriaEntity atualizar(CategoriaEntity categoria) {
        if (categoria.getId() == null) {
            throw new IllegalArgumentException("Categoria deve ter ID para atualização");
        }
        if (!existsById(categoria.getId())) {
            throw new RuntimeException("Categoria não encontrada para atualização");
        }
        return salvar(categoria); // Reutiliza o método salvar
    }

    public void remover(CategoriaEntity categoria) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(categoria) ? categoria : em.merge(categoria));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover categoria", e);
        }
    }

    // Fechar EntityManager (SE NECESSÁRIO)
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}