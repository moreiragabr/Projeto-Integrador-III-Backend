package zad_inventory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {

    public static void menuInicial(boolean ehADM){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n========= Zad Inventory ==========\n");
        if(ehADM){
            System.out.println("Perfil Administrativo\n");
        }else{
            System.out.printf("Usuário:  \n\n");//colocar usuario
        }
        System.out.println("==================================\n");
        System.out.println("1 - Alteração e venda de produtos\n");
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
                case 1: menuAlteracaoProdutos();
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

    public static void menuAlteracaoProdutos(){
        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Vendas\n");
        System.out.println("2 - Cadastro de produtos\n");
        System.out.println("3 - Consulta de produtos\n");
        System.out.println("4 - Remoção de produtos\n");
        System.out.println("5 - Alteração de produtos\n");
        System.out.println("\n7 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");
    }

    public static void menuRelatorios(){
        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Gerar relatório individual\n");
        System.out.println("2 - Gerar relatório geral\n");
        System.out.println("\n4 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");
    }

    public static void menuAdministrarUsuarios(){
        System.out.println("\n\n========= Zad Inventory ==========\n");
        System.out.println("1 - Editar usuários\n");
        System.out.println("2 - Cadastrar usuários\n");
        System.out.println("\n4 - Sair\n");
        System.out.println("==================================\n");

        System.out.print("..::: Digite sua opção: ");
    }

}
