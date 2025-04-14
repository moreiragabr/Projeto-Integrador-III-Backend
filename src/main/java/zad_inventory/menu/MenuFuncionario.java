package zad_inventory.menu;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.menu.MenuOperacao;
import zad_inventory.menu.MenuProduto;

import java.util.Scanner;

public class MenuFuncionario {

    private final UsuarioEntity usuarioLogado;
    private final Scanner scanner = new Scanner(System.in);

    public MenuFuncionario(UsuarioEntity usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
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
                case "1":
                    MenuProduto.exibir(usuarioLogado);
                    break;
                case "2":
                    MenuOperacao.Operacoes(usuarioLogado);
                    break;
                case "3":
                    MenuOperacao.listarTodas();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Saindo do menu funcionário...");
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
    }
}
