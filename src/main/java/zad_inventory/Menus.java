package zad_inventory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {

    //Menu inicial
    public static void menuInicial(boolean ehADM){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        if(ehADM){
            System.out.println("Perfil Administrativo\n");
        }else{
            System.out.printf("Usuário:  \n\n");//colocar usuario
        }
        System.out.println("==================================\n");
        System.out.println("1 - Venda e alteração de produtos\n");
        System.out.println("2 - Relatórios\n");
        if(ehADM){
            System.out.println("3 - Administrar usuários\n");
        }
        System.out.println("\n4 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: menuProdutos();
                case 2: menuRelatorios();
                case 3: if(ehADM){menuAdministrarUsuarios();};
                case 4: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    //Menu de produtos
    public static void menuProdutos(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Vendas\n");
        System.out.println("2 - Cadastro de produtos\n");
        System.out.println("3 - Consulta de produtos\n");
        System.out.println("4 - Remoção de produtos\n");
        System.out.println("5 - Alteração de produtos\n");
        System.out.println("\n7 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: menuVendas();
                case 2: menuCadastroProdutos();
                case 3: menuConsultaProdutos();
                case 4: menuRemocaoProdutos();
                case 5: menuAlteracaoProdutos();
                case 7: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    //Opções do menu de produtos

    public static void menuVendas(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja cadastrar uma nova venda?\n");
        System.out.println("\n1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: break;
                case 2: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    public static void menuCadastroProdutos(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja cadastrar um novo produto?\n");
        System.out.println("\n1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: break;
                case 2: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    public static void menuConsultaProdutos(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja consultar a lista de  produtos?\n");
        System.out.println("\n1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: break;
                case 2: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    public static void menuRemocaoProdutos(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja remover um produto?\n");
        System.out.println("\n1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: break;
                case 2: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    public static void menuAlteracaoProdutos(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("Deseja alterar um produto?\n");
        System.out.println("\n1 - Sim                  2 - Não\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: break;
                case 2: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }

    }

    //Menu de relatórios
    public static void menuRelatorios(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Gerar relatório individual\n");
        System.out.println("2 - Gerar relatório geral\n");
        System.out.println("\n4 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: menuRelatorioIndividual();
                case 2: menuRelatorioGeral();
                case 4: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    //Opções do menu de relatório

    public static void menuRelatorioIndividual(){

    }

    public static void menuRelatorioGeral(){

    }

    //Menu de administrar usuários
    public static void menuAdministrarUsuarios(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Editar usuários\n");
        System.out.println("2 - Cadastrar usuários\n");
        System.out.println("\n4 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: menuEditarUsuarios();
                case 2: menuCadastrarUsuarios();
                case 4: System.out.println("Encerrando sistema..."); return;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    //Opções do menu de administrar usuarios

    public static void menuEditarUsuarios(){

    }

    public static void menuCadastrarUsuarios(){

    }

}
