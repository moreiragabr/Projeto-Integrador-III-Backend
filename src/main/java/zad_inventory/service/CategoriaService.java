package zad_inventory.service;

import zad_inventory.config.DBConnection;
import zad_inventory.model.CategoriaEntity;
import zad_inventory.repository.CategoriaRepository;
import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaService {
    private final CategoriaRepository repo;

    public CategoriaService() {
        EntityManager em = DBConnection.getEntityManager();
        this.repo = new CategoriaRepository(em);
    }

    public CategoriaEntity salvar(CategoriaEntity categoria) {
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório!");
        }
        if (repo.buscarPorNome(categoria.getNome()) != null) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome!");
        }
        return repo.salvar(categoria);
    }

    public List<CategoriaEntity> buscarTodos() {
        return repo.buscarTodos();
    }

    public CategoriaEntity buscarPorId(Long id) {
        CategoriaEntity categoria = repo.buscarPorId(id);
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não encontrada com ID: " + id);
        }
        return categoria;
    }

    public void atualizar(Long id, String nome, String descricao) {
        CategoriaEntity categoria = buscarPorId(id);
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        repo.salvar(categoria);
    }

    public void remover(Long id) {
        CategoriaEntity categoria = buscarPorId(id);
        repo.remover(categoria);
    }
}