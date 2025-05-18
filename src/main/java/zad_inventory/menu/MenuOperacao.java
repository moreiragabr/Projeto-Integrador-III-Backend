package zad_inventory.menu;

import java.util.Scanner;
import zad_inventory.controller.OperacaoController;
import zad_inventory.entity.UsuarioEntity;

public class MenuOperacao {
    public static void Operacoes(UsuarioEntity usuarioLogado) {
        OperacaoController controller = new OperacaoController(usuarioLogado);
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU DE OPERAÇÕES ===");
            System.out.println("1. Registrar Operação");
            System.out.println("2. Listar Operações");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar Situação");
            System.out.println("5. Filtrar por Situação");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> {
                    try {
                        controller.registrarOperacao();
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case "2" -> {
                    try {
                        controller.listarOperacoes();
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case "3" -> {
                    try {
                        controller.buscarOperacaoPorId();
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case "4" -> {
                    try {
                        controller.atualizarSituacao();
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case "5" -> {
                    try {
                        controller.filtrarPorSituacao();
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case "0" -> {
                    System.out.println("Voltando ao menu principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
