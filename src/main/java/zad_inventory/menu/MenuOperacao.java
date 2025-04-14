package zad_inventory.menu;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.OperacaoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.enums.Situacao;
import zad_inventory.repository.OperacaoRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuOperacao {
    public static void Operacoes(UsuarioEntity usuarioLogado) {
        EntityManager em = DBConnection.getEntityManager();
        OperacaoRepository repo = new OperacaoRepository(em);
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n--- MENU DE OPERAÇÕES ---");
            System.out.println("1. Registrar nova venda");
            System.out.println("2. Listar todas as operações");
            System.out.println("3. Alterar situação de uma operação");
            System.out.println("4. Buscar por situação");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    OperacaoEntity nova = new OperacaoEntity();
                    System.out.print("ID do produto: ");
                    nova.setProdutoId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Nome do produto: ");
                    nova.setProdutoNome(scanner.nextLine());
                    System.out.print("ID do usuário: ");
                    nova.setUsuarioId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Nome do usuário: ");
                    nova.setUsuarioNome(scanner.nextLine());
                    System.out.print("Quantidade: ");
                    nova.setQuantidade(scanner.nextInt());
                    scanner.nextLine();
                    nova.setSituacao(Situacao.REALIZADA);
                    nova.setData(LocalDateTime.now());
                    repo.save(nova);
                    System.out.println("✅ Venda registrada!");
                }
                case 2 -> {
                    List<OperacaoEntity> operacoes = repo.listAll();
                    if (operacoes.isEmpty()) {
                        System.out.println("Nenhuma operação registrada.");
                    } else {
                        operacoes.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID da operação para alterar: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    OperacaoEntity op = repo.findById(id);
                    if (op != null) {
                        System.out.print("Nova situação (REALIZADA, CANCELADA, SEPARADA): ");
                        String novaSituacao = scanner.nextLine().toUpperCase();
                        try {
                            op.setSituacao(Situacao.valueOf(novaSituacao));
                            repo.update(op);
                            System.out.println("✅ Situação atualizada.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ Situação inválida.");
                        }
                    } else {
                        System.out.println("❌ Operação não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("Situação para filtrar (REALIZADA, CANCELADA, SEPARADA): ");
                    String filtro = scanner.nextLine().toUpperCase();
                    try {
                        Situacao situacao = Situacao.valueOf(filtro);
                        List<OperacaoEntity> resultado = repo.findBySituacao(situacao);
                        if (resultado.isEmpty()) {
                            System.out.println("Nenhuma operação com esta situação.");
                        } else {
                            resultado.forEach(System.out::println);
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Situação inválida.");
                    }
                }
                case 5 -> executando = false;
                default -> System.out.println("❌ Opção inválida.");
            }
        }

        em.close();
        MenuPrincipal.exibir(usuarioLogado);
    }

    public static void listarTodas() {
        EntityManager em = DBConnection.getEntityManager();
        OperacaoRepository repo = new OperacaoRepository(em);

        System.out.println("\n--- TODAS AS OPERAÇÕES REALIZADAS ---");
        List<OperacaoEntity> operacoes = repo.listAll();
        if (operacoes.isEmpty()) {
            System.out.println("Nenhuma operação registrada.");
        } else {
            operacoes.forEach(System.out::println);
        }

        em.close();
    }
}
