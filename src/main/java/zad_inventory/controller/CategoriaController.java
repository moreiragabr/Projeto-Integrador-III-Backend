package zad_inventory.controller;

import zad_inventory.entity.CategoriaEntity;
import zad_inventory.service.CategoriaService;
import java.util.List;

public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController() {
        this.service = new CategoriaService();
    }

    public void salvar(CategoriaEntity categoria) {
        try {
            service.salvar(categoria);
            System.out.println("Categoria salva com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar categoria: " + e.getMessage());
        }
    }

    public List<CategoriaEntity> buscarTodos() {
        return service.buscarTodos();
    }

    public CategoriaEntity buscarPorId(Long id) {
        CategoriaEntity categoria = service.buscarPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
        }
        return categoria;
    }

    public void atualizar(CategoriaEntity categoria) {
        try {
            service.atualizar(categoria);
            System.out.println("Categoria atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void remover(CategoriaEntity categoria) {
        try {
            service.remover(categoria);
            System.out.println("Categoria removida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
        }
    }
}