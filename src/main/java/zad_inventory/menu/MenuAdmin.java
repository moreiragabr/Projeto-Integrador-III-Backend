package zad_inventory.menu;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.menu.MenuCategorias;
import zad_inventory.menu.MenuOperacao;

import java.util.Scanner;

public class MenuAdmin {

    private final UsuarioEntity usuarioLogado;
    private final Scanner scanner = new Scanner(System.in);

    public MenuAdmin(UsuarioEntity usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void exibir() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n========= MENU ADMINISTRADOR =========");
            System.out.println("1 - Gerenciar Produtos");
            System.out.println("2 - Gerenciar Categorias");
            System.out.println("3 - Gerenciar Operações (Vendas)");
            System.out.println("4 - Gerenciar Usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    MenuProduto.exibir(usuarioLogado);
                    break;
                case "2":
                    MenuCategorias.Categorias(usuarioLogado);
                    break;
                case "3":
                    MenuOperacao.Operacoes(usuarioLogado);
                    break;
                case "4":
                    MenuUsuario.exibir(usuarioLogado);
                    break;
                case "0":
                    executando = false;
                    System.out.println("Saindo do menu administrador...");
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
    }
}
