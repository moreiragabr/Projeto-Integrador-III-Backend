package zad_inventory.controller;

import zad_inventory.entity.CategoriaEntity;
import zad_inventory.service.CategoriaService;
import java.util.List;
import java.util.Scanner;

public class CategoriaController {
    private final CategoriaService service;
    private final Scanner scanner;

    public CategoriaController() {
        this.service = new CategoriaService();
        this.scanner = new Scanner(System.in);
    }

    // Métodos que lidam com interação do usuário
    public void cadastrarCategoria() {
        System.out.print("Digite o nome da categoria: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição da categoria: ");
        String descricao = scanner.nextLine();

        CategoriaEntity novaCategoria = new CategoriaEntity(nome, descricao);
        try {
            service.salvar(novaCategoria);
            System.out.println("Categoria cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
        }
    }

    public void listarCategorias() {
        List<CategoriaEntity> categorias = service.buscarTodos();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            System.out.println("\n=== LISTA DE CATEGORIAS ===");
            for (CategoriaEntity c : categorias) {
                System.out.printf("ID: %d | Nome: %s%n", c.getId(), c.getNome());
            }
        }
    }

    public void buscarCategoriaPorId() {
        System.out.print("Digite o ID da categoria: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            CategoriaEntity categoria = service.buscarPorId(id);
            if (categoria != null) {
                System.out.printf("Categoria encontrada: ID %d | Nome: %s%n",
                        categoria.getId(), categoria.getNome());
            } else {
                System.out.println("Categoria não encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite apenas números.");
        }
    }

    public void atualizarCategoria() {
        System.out.print("Digite o ID da categoria a ser atualizada: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            CategoriaEntity categoria = service.buscarPorId(id);
            if (categoria == null) {
                System.out.println("Categoria não encontrada.");
                return;
            }
            System.out.print("Digite o novo nome da categoria: ");
            String novoNome = scanner.nextLine();
            categoria.setNome(novoNome);
            service.atualizar(categoria);
            System.out.println("Categoria atualizada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite apenas números.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void removerCategoria() {
        System.out.print("Digite o ID da categoria a ser removida: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            service.remover(id);
            System.out.println("Categoria removida com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite apenas números.");
        } catch (Exception e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
        }
    }
}