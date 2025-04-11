package zad_inventory.service;

import zad_inventory.entity.CategoriaEntity;
import zad_inventory.repository.CategoriaRepository;

import java.util.List;

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void cadastrarCategoria(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio.");
        }
        CategoriaEntity existente = categoriaRepository.buscarPorNome(nome);
        if (existente != null) {
            throw new RuntimeException("Categoria já existe.");
        }
        categoriaRepository.salvar(new CategoriaEntity(nome));
    }

    public List<CategoriaEntity> listarCategorias() {
        return categoriaRepository.listarTodas();
    }

    public CategoriaEntity buscarPorNome(String nome) {
        return categoriaRepository.buscarPorNome(nome);
    }
}
