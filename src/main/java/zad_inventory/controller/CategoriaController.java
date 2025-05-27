package zad_inventory.controller;

import zad_inventory.model.CategoriaEntity;
import zad_inventory.service.CategoriaService;
import java.util.List;

public class CategoriaController {
    private final CategoriaService service = new CategoriaService();

    public void cadastrarCategoria(String nome, String descricao) {
        service.salvar(new CategoriaEntity(nome, descricao));
    }

    public List<CategoriaEntity> listarCategorias() {
        return service.buscarTodos();
    }

    public CategoriaEntity buscarCategoriaPorId(Long id) {
        return service.buscarPorId(id);
    }

    public void atualizarCategoria(Long id, String nome, String descricao) {
        service.atualizar(id, nome, descricao);
    }

    public void removerCategoria(Long id) {
        service.remover(id);
    }
}