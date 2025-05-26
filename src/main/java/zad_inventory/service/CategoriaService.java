package zad_inventory.service;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.CategoriaEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaService {

    // Operações básicas de CRUD (sem interação com usuário)
    public CategoriaEntity salvar(CategoriaEntity categoria) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
            return categoria;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar categoria", e);
        } finally {
            em.close();
        }
    }

    public List<CategoriaEntity> buscarTodos() {
        EntityManager em = DBConnection.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM CategoriaEntity c", CategoriaEntity.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public CategoriaEntity buscarPorId(Long id) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            return em.find(CategoriaEntity.class, id);
        } finally {
            em.close();
        }
    }

    public CategoriaEntity atualizar(CategoriaEntity categoria) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            CategoriaEntity categoriaAtualizada = em.merge(categoria);
            em.getTransaction().commit();
            return categoriaAtualizada;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar categoria", e);
        } finally {
            em.close();
        }
    }

    public void remover(Long id) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            CategoriaEntity categoria = em.find(CategoriaEntity.class, id);
            if (categoria != null) {
                em.remove(categoria);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover categoria", e);
        } finally {
            em.close();
        }
    }
}