
package zad_inventory;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = DBConnection.getEntityManager();
            System.out.println("✅ Conexão com o banco de dados estabelecida com sucesso!");

            TypedQuery<UsuarioEntity> query = em.createQuery("SELECT u FROM UsuarioEntity u", UsuarioEntity.class);
            List<UsuarioEntity> usuarios = query.getResultList();

            // Exibe os resultados
            for (UsuarioEntity u : usuarios) {
                System.out.println("ID: " + u.getId());
                System.out.println("Nome: " + u.getNome());
                System.out.println("Email: " + u.getEmail());
                System.out.println("Tipo: " + u.getTipoUsuario());
                System.out.println("--------------------");
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

