package zad_inventory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import zad_inventory.entity.OperacaoEntity;
import zad_inventory.repository.OperacaoRepository;

import java.time.LocalDateTime;

public class RepositoryTest {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(OperacaoEntity.class); // Adicione todas as entidades aqui

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        OperacaoRepository repository = new OperacaoRepository(session.getEntityManagerFactory().createEntityManager());

        OperacaoEntity op = new OperacaoEntity();
//        op.setProdutoId(1);
//        op.setProdutoNome("Garrafa");
//        op.setUsuarioId(1);
//        op.setUsuarioNome("Lucas");
        op.setSituacao("Realizada");
        op.setQuantidade(2);
        op.setData(LocalDateTime.now());

        repository.save(op);
        System.out.println("Operação salva!");

        repository.listAll().forEach(System.out::println);

        session.close();
        sessionFactory.close();
    }
}
