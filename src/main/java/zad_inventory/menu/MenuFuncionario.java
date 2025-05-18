package zad_inventory.menu;

import zad_inventory.controller.OperacaoController;
import zad_inventory.entity.UsuarioEntity;
import java.util.Scanner;

public class MenuFuncionario {

    private final UsuarioEntity usuarioLogado;
    private final Scanner scanner = new Scanner(System.in);
    private final OperacaoController controller;

    public MenuFuncionario(UsuarioEntity usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        // Agora instanciamos o controller que cuida de registrar/listar operações
        this.controller = new OperacaoController(usuarioLogado);
    }

    public void exibir() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n========= MENU FUNCIONÁRIO =========");
            System.out.println("1 - Listar Produtos");
            System.out.println("2 - Realizar Venda");
            System.out.println("3 - Listar Minhas Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> MenuProduto.exibir(usuarioLogado);
                case "2" -> {
                    controller.registrarOperacao();
                }
                case "3" -> {
                    controller.listarOperacoes();
                }
                case "0" -> {
                    executando = false;
                    System.out.println("Saindo do menu funcionário...");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
