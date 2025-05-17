package zad_inventory.menu;

import zad_inventory.entity.UsuarioEntity;
import zad_inventory.controller.UsuarioController;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.UsuarioService;
import zad_inventory.config.DBConnection;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class MenuUsuario {
    private static UsuarioController controller;
    private static Scanner scanner;
    private static UsuarioEntity usuarioLogado;

    // Método estático para compatibilidade com MenuAdmin
    public static void exibir(UsuarioEntity logado) {
        usuarioLogado = logado;
        scanner = new Scanner(System.in);
        EntityManager em = DBConnection.getEntityManager();
        controller = new UsuarioController(new UsuarioService(new UsuarioRepository(em)), scanner);

        new MenuUsuario().exibirInstancia();
    }

    // Método de instância
    public void exibirInstancia() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n========= MENU USUÁRIOS =========");
            System.out.println("1 - Listar Usuários");
            System.out.println("2 - Ranking de Usuários por Produtos Criados");
            System.out.println("3 - Cadastrar Novo Usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    controller.listarUsuarios();
                    break;
                case "2":
                    controller.exibirRanking();
                    break;
                case "3":
                    controller.cadastrarNovoUsuario(usuarioLogado);
                    break;
                case "0":
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}