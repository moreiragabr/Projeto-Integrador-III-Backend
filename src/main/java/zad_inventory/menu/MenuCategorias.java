package zad_inventory.menu;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.CategoriaEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.repository.CategoriaRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class MenuCategorias {
    public static void Categorias(UsuarioEntity usuarioLogado) {
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
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome da categoria: ");
                    String nome = scanner.nextLine();
                    System.out.print("Descrição da categoria: ");
                    String descricao = scanner.nextLine();
                    CategoriaEntity nova = new CategoriaEntity(nome, descricao);
                    repo.salvar(nova);
                    System.out.println(" Categoria cadastrada com sucesso!");
                }
                case 2 -> {
                    List<CategoriaEntity> categorias = repo.buscarTodos();
                    if (categorias.isEmpty()) {
                        System.out.println("Nenhuma categoria cadastrada.");
                    } else {
                        categorias.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID da categoria para atualizar: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    CategoriaEntity existente = repo.buscarPorId(id);
                    if (existente != null) {
                        System.out.print("Novo nome (atual: " + existente.getNome() + "): ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Nova descrição (atual: " + existente.getDescricao() + "): ");
                        String novaDesc = scanner.nextLine();
                        existente.setNome(novoNome);
                        existente.setDescricao(novaDesc);
                        repo.atualizar(existente);
                        System.out.println(" Categoria atualizada!");
                    } else {
                        System.out.println(" Categoria não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da categoria para remover: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine(); // Limpar buffer

                    try {
                        CategoriaEntity categoria = repo.buscarPorId(id);
                        if (categoria != null) {
                            // Versão mais robusta da remoção
                            repo.remover(categoria);
                            System.out.println("✅ Categoria removida com sucesso!");
                        } else {
                            System.out.println("❌ Categoria não encontrada.");
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Erro ao remover: " + e.getMessage());
                    }
                }
                case 0 -> executando = false;
                default -> System.out.println(" Opção inválida.");
            }
        }

        em.close();
        MenuPrincipal.exibir(usuarioLogado);
    }
}
