package zad_inventory;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.CategoriaEntity;
import zad_inventory.repository.CategoriaRepository;

import javax.persistence.EntityManager;
import java.util.Scanner;
import java.util.List;

public class MenuCategorias {
    public static void Categorias(String[] args) {
        EntityManager em = DBConnection.getEntityManager();
        CategoriaRepository repo = new CategoriaRepository(em);
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n--- MENU DE CATEGORIAS ---");
            System.out.println("1. Cadastrar nova categoria");
            System.out.println("2. Listar categorias");
            System.out.println("3. Atualizar categoria");
            System.out.println("4. Remover categoria");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome da categoria: ");
                    String nome = scanner.nextLine();

                    System.out.print("Descrição da categoria: ");
                    String descricao = scanner.nextLine();

                    CategoriaEntity novaCategoria = new CategoriaEntity(nome, descricao);
                    repo.salvar(novaCategoria);
                    System.out.println("Categoria cadastrada com sucesso!");
                    break;

                case 2:
                    List<CategoriaEntity> categorias = repo.buscarTodos();
                    if (categorias.isEmpty()) {
                        System.out.println("Nenhuma categoria cadastrada.");
                    } else {
                        System.out.println("Categorias cadastradas:");
                        categorias.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("ID da categoria para atualizar: ");
                    Long idAtualizar = scanner.nextLong();
                    scanner.nextLine();

                    CategoriaEntity existente = repo.buscarPorId(idAtualizar);
                    if (existente != null) {
                        System.out.print("Novo nome (atual: " + existente.getNome() + "): ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Nova descrição (atual: " + existente.getDescricao() + "): ");
                        String novaDescricao = scanner.nextLine();

                        existente.setNome(novoNome);
                        existente.setDescricao(novaDescricao);

                        repo.atualizar(existente);
                        System.out.println("Categoria atualizada com sucesso!");
                    } else {
                        System.out.println("Categoria não encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("ID da categoria para remover: ");
                    Long idRemover = scanner.nextLong();
                    scanner.nextLine();

                    CategoriaEntity categoriaRemover = repo.buscarPorId(idRemover);
                    if (categoriaRemover != null) {
                        repo.remover(categoriaRemover);
                        System.out.println("Categoria removida com sucesso!");
                    } else {
                        System.out.println("Categoria não encontrada.");
                    }
                    break;

                case 5:
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
        em.close();
        DBConnection.close();
    }

    public static void Categorias() {
    }
}
